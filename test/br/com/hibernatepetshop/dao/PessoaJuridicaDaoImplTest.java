/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernatepetshop.dao;

import br.com.hibernatepetshop.entidade.Cartao;
import br.com.hibernatepetshop.entidade.PessoaFisica;
import br.com.hibernatepetshop.entidade.PessoaJuridica;
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
public class PessoaJuridicaDaoImplTest {
    private Session sesssao;
     private PessoaJuridica pessoaJuridica;
     private PessoaJuridicaDao pessoaJuridicaDao;
    
    
    public PessoaJuridicaDaoImplTest() {
         pessoaJuridicaDao = new PessoaJuridicaDaoImpl();
    }

    @Test
     public void TestSalvar() {
         System.out.println("Salvar");
          pessoaJuridica = new PessoaJuridica(null, UtilTeste.gerarCnpj(), UtilTeste.gerarNumero(5), UtilTeste.gerarNumero(5), UtilTeste.gerarEmail());
         List<Cartao> cartoes = new ArrayList<>();
        
        for (int i = 0; i < 2; i++){             
              cartoes.add(criarCartao());
          }
              
        pessoaJuridica.setCartoes(cartoes);
          for (Cartao cartao : cartoes) {
              cartao.setCliente(pessoaJuridica);
          }
        Session sesssao = HibernateUtil.abrirSessao();
          pessoaJuridicaDao.salvarOuAlterar(pessoaJuridica, sesssao);
          sesssao.close();
          
          
          assertNotNull(pessoaJuridica.getId());
          assertNotNull(pessoaJuridica.getCartoes().get(0).getId());
     }
     
       private Cartao criarCartao(){
          Cartao cartao = new Cartao(null, UtilTeste.gerarNumero(16), "MasterCard", "20/2027");
      
        return cartao;
       }
     
     @Test
     public void TestAlterar() {
         System.out.println("Alterar");
         buscarPessoaJuridicaBd();
        
        pessoaJuridica.setNome(UtilTeste.gerarCaracter(8));
        pessoaJuridica.getCartoes().get(0).setNumero("1234123412341234");
        
       sesssao = HibernateUtil.abrirSessao();
       pessoaJuridicaDao.salvarOuAlterar(pessoaJuridica, sesssao);
       
       PessoaJuridica pessoaJuridicaAlterado = pessoaJuridicaDao.pesquisarPorNome(pessoaJuridica.getNome(), sesssao).get(0);
            
      Cartao cartao = pessoaJuridicaAlterado.getCartoes().get(0);
       sesssao.close();
       
        assertEquals(pessoaJuridica.getNome(), pessoaJuridicaAlterado.getNome());
        assertEquals(pessoaJuridica.getCartoes().get(0).getNumero(), cartao.getNumero());
         
     }

     @Test
     public void TestExcluir() {
         System.out.println("Excluir");
         buscarPessoaJuridicaBd();
        sesssao = HibernateUtil.abrirSessao();
        pessoaJuridicaDao.remover(pessoaJuridica, sesssao);
        
        PessoaJuridica pessoaJuridicaExcluido = pessoaJuridicaDao.pesquisarPorNome(pessoaJuridica.getNome(), sesssao).get(0);
        sesssao.close();
        assertNull(pessoaJuridicaExcluido);
         
     }
     
     
     
    @Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisarPorNome");
        buscarPessoaJuridicaBd();
        sesssao = HibernateUtil.abrirSessao();
        List<PessoaJuridica> pessoasJuridicas = pessoaJuridicaDao.pesquisarPorNome(pessoaJuridica.getNome(), sesssao);
        assertTrue(pessoasJuridicas.size() > 0);
       
    }
    
    
    public PessoaJuridica buscarPessoaJuridicaBd() {
  
            sesssao = HibernateUtil.abrirSessao();
            Query consulta = sesssao.createQuery("SELECT distinct(p) from PessoaJuridica p join fetch p.cartoes");
            List<PessoaJuridica> pessoasJuridicas = consulta.list();
            if (pessoasJuridicas.isEmpty()) {
                sesssao.close();
                TestSalvar();
            }else{
              pessoaJuridica =  pessoasJuridicas.get(0);
                sesssao.close();
            }
                
        

        return pessoaJuridica;
    }
    
    
}
