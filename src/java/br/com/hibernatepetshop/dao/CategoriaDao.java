/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernatepetshop.dao;

import br.com.hibernatepetshop.entidade.Categoria;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public interface CategoriaDao extends BaseDao<Categoria, Long>{
    
    List<Categoria> pesquisarPorNome(String nome, Session session) throws HibernateException;    
   
    List<Categoria> pesquisarTodo(Session sessao) throws HibernateException;
    
     Categoria pesquisarPorNomeProduto(String nomeProduto, Session sessao)throws HibernateException;
    
}
