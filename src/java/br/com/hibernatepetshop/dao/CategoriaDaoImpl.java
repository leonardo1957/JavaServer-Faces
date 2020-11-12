/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernatepetshop.dao;

import br.com.hibernatepetshop.entidade.Categoria;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class CategoriaDaoImpl extends BaseDaoImpl<Categoria,Long> implements CategoriaDao, Serializable{

    @Override
    public Categoria pesquisarPorID(Long id, Session sessao) throws HibernateException {
        return (Categoria) sessao.get(Categoria.class, id);
    }

    @Override
    public List<Categoria> pesquisarPorNome(String nome, Session session) throws HibernateException {
       Query consulta = session.createQuery("FROM Categoria WHERE nome LIKE :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.list();
    }

    @Override
    public List<Categoria> pesquisarTodo(Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("FROM Categoria");
       
        return consulta.list(); 
    }

    @Override
    public Categoria pesquisarPorNomeProduto(String nomeProduto, Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("SELECT DISTINCT(c) FROM Categoria c join fetch  c.produtos p WHERE p.nome LIKE :nomeProduto"); 
         consulta.setParameter("nomeProduto", "%" + nomeProduto + "%");
         return (Categoria) consulta.uniqueResult(); //casting
    }
    
}
