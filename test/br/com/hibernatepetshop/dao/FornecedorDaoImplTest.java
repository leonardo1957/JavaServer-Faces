/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernatepetshop.dao;

import br.com.hibernatepetshop.entidade.Endereco;
import br.com.hibernatepetshop.entidade.Fornecedor;
import br.com.hibernatepetshop.entidade.Produto;
import br.com.hibernatepetshop.util.UtilTeste;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class FornecedorDaoImplTest {
   
    private Fornecedor fornecedor;
    private FornecedorDao fornecedorDao;
    private Session sessao;
    
    public FornecedorDaoImplTest() {
        fornecedorDao = new FornecedorDaoImpl();
    }
   
    
    @Test
    public void testSalvar() {
        System.out.println("salvar");
        List<Endereco> enderecos = new ArrayList<>();
        fornecedor = new Fornecedor(null ,
                "Nome" + " " + UtilTeste.gerarCaracter(5),
                UtilTeste.gerarEmail(),
                UtilTeste.gerarTelefone(),
                "teste, teste, teste", 
                new Date()
        );
        
        
         for (int i = 0; i < 2; i++) {
             enderecos.add(criarEndereco());
             
         }
         fornecedor.setEnderecos(enderecos);
         for (Endereco endereco : enderecos) {
             endereco.setFornecedor(fornecedor);
         }
         
         sessao = HibernateUtil.abrirSessao(); //metodo p/ abrir sessao do hibernate, classe dentro da DAO        
         fornecedorDao.salvarOuAlterar(fornecedor, sessao);
         sessao.close(); //fechar connection
         assertNotNull(fornecedor.getId()); 
         for (Endereco endereco : enderecos) {
             assertNotNull(endereco.getId());
             
         }
    }
    
    private Endereco criarEndereco(){
          Endereco endereco = new Endereco(null, UtilTeste.gerarCaracter(5), UtilTeste.gerarNumero(2), UtilTeste.gerarCaracter(4), UtilTeste.gerarCaracter(4), UtilTeste.gerarCaracter(4), UtilTeste.gerarNumero(9), UtilTeste.gerarCaracter(5));
        return endereco;
    }
    
    //    @Test
    public void testAlterar() {
        System.out.println("alterar");
        buscarFornecedorBd();
        fornecedor.setNome("nome alterado");
        fornecedor.setEmail("email alterado");    
        
       
        sessao = HibernateUtil.abrirSessao(); //metodo p/ abrir sessao do hibernate, classe dentro da DAO
        fornecedorDao.salvarOuAlterar(fornecedor, sessao);
        Fornecedor fornecedorAlt = fornecedorDao.pesquisarPorID(fornecedor.getId(), sessao);
       
        sessao.close(); //fechar connection
        assertEquals(fornecedor.getNome(), fornecedorAlt.getNome()); 
        assertEquals(fornecedor.getEmail(), fornecedorAlt.getEmail()); 
        
    } //    @Test
    
     // @Test
    public void testExcluir() {
        System.out.println("Excluir");
        buscarFornecedorBd();
        
        sessao = HibernateUtil.abrirSessao();
        fornecedorDao.remover(fornecedor, sessao);
        
        Fornecedor fornecedorExcluido = fornecedorDao.pesquisarPorID(fornecedor.getId(), sessao);
        sessao.close();
        assertNull(fornecedorExcluido);
        
    }
    
   //  @Test
    public void testPesquisarPorID() {
        System.out.println("pesquisarPorID");
        buscarFornecedorBd();
        Long id = fornecedor.getId();
        sessao = HibernateUtil.abrirSessao();
        Fornecedor fornecedorId = fornecedorDao.pesquisarPorID(id, sessao);
        assertNotNull(fornecedorId);
        
    }
    
   

   @Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisarPorNome");
        buscarFornecedorBd();
        
        sessao = HibernateUtil.abrirSessao();
       List <Fornecedor> fornecedores = fornecedorDao.pesquisarPorNome(fornecedor.getNome(), sessao);
        sessao.close();
        assertTrue(fornecedores.size() > 0);
        assertTrue(fornecedores.get(0).getEnderecos().size() > 0);
    }

  //@Test
    public void testPesquisarTodo() {
        System.out.println("pesquisarTodo");
        buscarFornecedorBd();
        
        sessao = HibernateUtil.abrirSessao();
       List <Fornecedor> fornecedores = fornecedorDao.pesquisarTodo(sessao);
        sessao.close();
        
    }
    
    
  //  @Test
    public void testPesquisarPorNomeProduto() {
        System.out.println("pesquisarPorNomeProduto");
        sessao = HibernateUtil.abrirSessao();
      fornecedor = fornecedorDao.pesquisarPorNomeProduto("escada", sessao);
      sessao.close();
        System.out.println("Fornecedor" + fornecedor.getNome());
        for (Produto prod : fornecedor.getProdutos()){
            System.out.println("Produto" + prod.getNome());
            System.out.println("Produto" + prod.getEstoque());
            System.out.println("");
        }
       
    }
    
    public Fornecedor buscarFornecedorBd(){
        Long id;
        sessao = HibernateUtil.abrirSessao();
        try {
            Query consulta = sessao.createQuery("SELECT max(id) from Fornecedor");
            id = (Long) consulta.uniqueResult(); //vai trazer unico valor pq é max id
            if(id == null){
            sessao.close();
            testSalvar();
        }else{
                fornecedor = fornecedorDao.pesquisarPorID(id, sessao);
                sessao.close();
            }
        } catch (Exception e) {
            System.err.println("deu ruim" + e.getMessage());
        }
        
        return fornecedor;
    }

    
}
