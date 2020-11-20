/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernatepetshop.dao;

import br.com.hibernatepetshop.entidade.Categoria;
import br.com.hibernatepetshop.entidade.Fornecedor;
import br.com.hibernatepetshop.entidade.Produto;
import br.com.hibernatepetshop.util.UtilTeste;
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
public class ProdutoDaoImplTest {
    
    private Produto produto;
    private Fornecedor fornecedor;
    private ProdutoDao produtoDao;
    private Session sessao;
    private FornecedorDaoImplTest fornecedorDaoImplTest;
    private Categoria categoria;
    private CategoriaDaoImplTest categoriaDaoImplTest;
    
    public ProdutoDaoImplTest() {
        produtoDao = new ProdutoDaoImpl();
    }
    
   //  @Test
    public void testSalvar() {
        System.out.println("salvar");
        produto = new Produto(
                null,
                "Nome " + UtilTeste.gerarCaracter(7),
                UtilTeste.gerarNumero(5),
                "asdfasd, asdfasdf. basdla",
                Double.parseDouble(UtilTeste.gerarNumero(3)),
                Integer.parseInt(UtilTeste.gerarNumero(3))
        );
        
        fornecedorDaoImplTest = new FornecedorDaoImplTest();
        fornecedor = fornecedorDaoImplTest.buscarFornecedorBd();
        produto.setFornecedor(fornecedor);
        
        categoriaDaoImplTest = new CategoriaDaoImplTest();
        categoria = categoriaDaoImplTest.buscarCategoriaBd();
        produto.setCategoria(categoria);
        
        
        sessao = HibernateUtil.abrirSessao();
        produtoDao.salvarOuAlterar(produto, sessao);
        sessao.close();
        assertNotNull(produto.getId());
    }
     
   // @Test
     public void testAlterar() {
        System.out.println("alterar");
        buscarProdutoBd();
        produto.setNome("produto alterado");
          
        
       
        sessao = HibernateUtil.abrirSessao(); //metodo p/ abrir sessao do hibernate, classe dentro da DAO
        produtoDao.salvarOuAlterar(produto, sessao);
        Produto produtoAlt = produtoDao.pesquisarPorID(produto.getId(), sessao);
       
        sessao.close(); //fechar connection
        assertEquals(produto.getNome(), produtoAlt.getNome()); 
        
        
    }
     
    // @Test
     public void testExcluir() {
        System.out.println("Excluir");
        buscarProdutoBd();
        produto.setNome("produto deletado");
          
        
       
        sessao = HibernateUtil.abrirSessao(); //metodo p/ abrir sessao do hibernate, classe dentro da DAO
        produtoDao.remover(produto, sessao);
        Produto produtoExcluido = produtoDao.pesquisarPorID(produto.getId(), sessao);
       
        sessao.close(); //fechar connection
        assertNull(produtoExcluido); 
        
        
    }

   // @Test
    public void testPesquisarPorID() {
        System.out.println("pesquisarPorID");
        buscarProdutoBd();
        Long id = produto.getId();
        sessao = HibernateUtil.abrirSessao();
        Produto produtoId = produtoDao.pesquisarPorID(id, sessao);
        assertNotNull(produtoDao);
        
    }

    //@Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisarPorNome");
        buscarProdutoBd();
        sessao = HibernateUtil.abrirSessao();
        
        List produtos = produtoDao.pesquisarPorNome(produto.getNome(), sessao);
        sessao.close();
        assertTrue(produtos.size() > 0);
        
        
    }
    
    //@Test
    public void testPesquisarPorFornecedor() {
        System.out.println("pesquisarPorFornecedor");
  
        buscarProdutoBd();
        sessao = HibernateUtil.abrirSessao();
        List<Produto> produtos = produtoDao.pesquisarPorFornecedor(fornecedor.getNome(), sessao);
        sessao.close();
        assertTrue(produtos.size() > 0);
        
    
    }
    
    
    //@Test
    public void testPesquisarPorProdutoEstoque() {
        System.out.println("pesquisarPorProdutoEstoque");
        buscarProdutoBd();
        int qtd = produto.getEstoque();
        sessao = HibernateUtil.abrirSessao();
        List<Produto> produtos = produtoDao.pesquisarPorProdutoEstoque(qtd, produto.getNome(), sessao);
        System.out.println("Qtd" + produtos.size());
    }
    
    
     public Produto buscarProdutoBd(){
        Long id;
        sessao = HibernateUtil.abrirSessao();
        try {
            Query consulta = sessao.createQuery("SELECT max(id) from Produto");
            id = (Long) consulta.uniqueResult(); //vai trazer unico valor pq é max id
            if(id == null){
            sessao.close();
            testSalvar();
        }else{
                produto = produtoDao.pesquisarPorID(id, sessao);
                sessao.close();
            }
        } catch (Exception e) {
            System.err.println("deu ruim" + e.getMessage());
        }
        
        return produto;
    }

   // @Test
    public void testPesquisarPorPrecoMinimoMaximo() {
        System.out.println("pesquisarPorPrecoMinimoMaximo");
        buscarProdutoBd();
        
        sessao = HibernateUtil.abrirSessao();
        List<Produto> produtos = produtoDao.pesquisarPorPrecoMinimoMaximo(produto.getPreco() - 20, produto.getPreco() , sessao);
       
        //System.out.println("Tamanho: " + produtos.size());
        
        assertTrue(!produtos.isEmpty());
        
    } 



    
   
    
}
