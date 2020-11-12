/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernatepetshop.dao;

import br.com.hibernatepetshop.entidade.Produto;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public interface ProdutoDao extends BaseDao<Produto, Long>{ 
    
     List<Produto> pesquisarPorNome(String nome, Session session) throws HibernateException;    
   
    // List<Produto> pesquisarTodo(Session sessao) throws HibernateException;    
     
     List<Produto> pesquisarPorFornecedor(String fornecedor, Session session) throws HibernateException;  
    
     List<Produto> pesquisarPorProdutoEstoque(int qtd, String nomeProduto, Session session) throws HibernateException;  
     
     List<Produto> pesquisarPorPrecoMinimoMaximo(double minimo, double maximo, Session session) throws HibernateException;  
    
}
