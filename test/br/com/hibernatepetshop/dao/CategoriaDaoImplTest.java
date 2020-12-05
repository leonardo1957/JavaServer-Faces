/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernatepetshop.dao;

import br.com.hibernatepetshop.entidade.Categoria;
import br.com.hibernatepetshop.entidade.Fornecedor;
import br.com.hibernatepetshop.util.UtilTeste;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class CategoriaDaoImplTest {

    private Categoria categoria;
    private Session sessao;
    private CategoriaDao categoriaDao;

    public CategoriaDaoImplTest() {
        categoriaDao = new CategoriaDaoImpl();
    }

     // @Test
    public void testSalvar() {
        System.out.println("salvar");
        categoria = new Categoria (null,
                "Nome" + " " + UtilTeste.gerarCaracter(7)
        );

        sessao = HibernateUtil.abrirSessao();

        categoriaDao.salvarOuAlterar(categoria, sessao);
        sessao.close(); //fechar connection
        assertNotNull(categoria.getId());

    }

    // @Test
    public void testPesquisarPorID() {
        System.out.println("pesquisarPorID");
        Long id = null;
        Session sessao = null;
        CategoriaDaoImpl instance = new CategoriaDaoImpl();
        Categoria expResult = null;
        Categoria result = instance.pesquisarPorID(id, sessao);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    // @Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisarPorNome");
        String nome = "";
        Session session = null;
        CategoriaDaoImpl instance = new CategoriaDaoImpl();
        List<Categoria> expResult = null;
        List<Categoria> result = instance.pesquisarPorNome(nome, session);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

   //  @Test
    public void testPesquisarTodo() {
        System.out.println("pesquisarTodo");
        Session sessao = null;
        CategoriaDaoImpl instance = new CategoriaDaoImpl();
        List<Categoria> expResult = null;
        List<Categoria> result = instance.pesquisarTodo(sessao);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    // @Test
    public void testPesquisarPorNomeProduto() {
        System.out.println("pesquisarPorNomeProduto");
        String nomeProduto = "";
        Session sessao = null;
        CategoriaDaoImpl instance = new CategoriaDaoImpl();
        Categoria expResult = null;
        Categoria result = instance.pesquisarPorNomeProduto(nomeProduto, sessao);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    public Categoria buscarCategoriaBd() {
        Long id;
        sessao = HibernateUtil.abrirSessao();
        try {
            Query consulta = sessao.createQuery("SELECT max(id) from Categoria");
            id = (Long) consulta.uniqueResult(); //vai trazer unico valor pq é max id
            if (id == null) {
                sessao.close();
                testSalvar();
            } else {
                categoria = categoriaDao.pesquisarPorID(id, sessao);
                sessao.close();
            }
        } catch (Exception e) {
            System.err.println("deu ruim" + e.getMessage());
        }

        return categoria;
    }

}
