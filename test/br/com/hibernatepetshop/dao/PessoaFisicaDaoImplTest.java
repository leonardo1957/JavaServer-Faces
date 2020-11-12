/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernatepetshop.dao;

import br.com.hibernatepetshop.entidade.Cartao;
import br.com.hibernatepetshop.entidade.PessoaFisica;
import br.com.hibernatepetshop.util.UtilTeste;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class PessoaFisicaDaoImplTest {
     private Session sesssao;
     private PessoaFisica pessoaFisica;
     private PessoaFisicaDao pessoaFisicaDao;
    
    public PessoaFisicaDaoImplTest() {
        
        pessoaFisicaDao = new PessoaFisicaDaoImpl();
    }
    
    @Test
     public void TestSalvar() {
         System.out.println("Salvar");
         pessoaFisica = new PessoaFisica(null, UtilTeste.gerarCpf(), UtilTeste.gerarRg(), UtilTeste.gerarCaracter(5), UtilTeste.gerarEmail() );
         List<Cartao> cartoes = new ArrayList<>();
        
        for (int i = 0; i < 2; i++){             
              cartoes.add(criarCartao());
          }
              
        pessoaFisica.setCartoes(cartoes);
          for (Cartao cartao : cartoes) {
              cartao.setCliente(pessoaFisica);
          }
        Session sesssao = HibernateUtil.abrirSessao();
          pessoaFisicaDao.salvarOuAlterar(pessoaFisica, sesssao);
          sesssao.close();
          
          
          assertNotNull(pessoaFisica.getId());
          assertNotNull(pessoaFisica.getCartoes().get(0).getId());
     }
     
     
     
      private Cartao criarCartao(){
          Cartao cartao = new Cartao(null, UtilTeste.gerarNumero(16), "MasterCard", "20/2027");
      
        return cartao;
       
   
}
    
   //  @Test
     public void TestAlterar(){
         System.out.println("Alterar");
         buscarPessoaFisicaBd();
        
        pessoaFisica.setNome(UtilTeste.gerarCaracter(8));
        pessoaFisica.getCartoes().get(0).setNumero("1234123412341234");
        
       sesssao = HibernateUtil.abrirSessao();
       pessoaFisicaDao.salvarOuAlterar(pessoaFisica, sesssao);
       
       PessoaFisica pessoaFisicaAlterado = pessoaFisicaDao.pesquisarPorNome(pessoaFisica.getNome(), sesssao).get(0);
            
      Cartao cartao = pessoaFisicaAlterado.getCartoes().get(0);
       sesssao.close();
       
        assertEquals(pessoaFisica.getNome(), pessoaFisicaAlterado.getNome());
        assertEquals(pessoaFisica.getCartoes().get(0).getNumero(), cartao.getNumero());
     }
     
      
    // @Test
     public void TestExcluir(){
         System.out.println("Excluir");
         buscarPessoaFisicaBd();
        sesssao = HibernateUtil.abrirSessao();
        pessoaFisicaDao.remover(pessoaFisica, sesssao);
        
        PessoaFisica pessoaFisicaExcluido = pessoaFisicaDao.pesquisarPorNome(pessoaFisica.getNome(), sesssao).get(0);
        sesssao.close();
        assertNull(pessoaFisicaExcluido);
     }

  

   // @Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisarPorNome");
         buscarPessoaFisicaBd();
        sesssao = HibernateUtil.abrirSessao();
        List<PessoaFisica> pessoasFisicas = pessoaFisicaDao.pesquisarPorNome(pessoaFisica.getNome(), sesssao);
        assertTrue(pessoasFisicas.size() > 0);
       
    }
    
    
    public PessoaFisica buscarPessoaFisicaBd() {
  
            sesssao = HibernateUtil.abrirSessao();
            Query consulta = sesssao.createQuery("SELECT distinct(p) from PessoaFisica p join fetch p.cartoes");
            List<PessoaFisica> pessoasFisicas = consulta.list();
            if (pessoasFisicas.isEmpty()) {
                sesssao.close();
                TestSalvar();
            }else{
              pessoaFisica =  pessoasFisicas.get(0);
                sesssao.close();
            }
                
        

        return pessoaFisica;
    }
    
}
