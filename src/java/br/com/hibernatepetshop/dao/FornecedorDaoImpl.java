/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernatepetshop.dao;

import br.com.hibernatepetshop.entidade.Fornecedor;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class FornecedorDaoImpl extends BaseDaoImpl<Fornecedor,Long> implements FornecedorDao, Serializable{

    @Override
    public Fornecedor pesquisarPorID(Long id, Session sessao) throws HibernateException {      
       return(Fornecedor) sessao.get(Fornecedor.class, id);
    }

    @Override
    public List<Fornecedor> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("SELECT DISTINCT (f)FROM Fornecedor f LEFT JOIN FETCH f.enderecos e WHERE nome LIKE :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.list();
    }

    @Override
    public List<Fornecedor> pesquisarTodo(Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("FROM Fornecedor");
       
        return consulta.list(); 
    }

    @Override
    public Fornecedor pesquisarPorNomeProduto(String nomeProduto, Session sessao) throws HibernateException {
         Query consulta = sessao.createQuery("SELECT DISTINCT(f) FROM Fornecedor f join fetch  f.produtos p WHERE p.nome LIKE :nomeProduto"); //join fetch pq tem lazy na entidade Fornecedor
         consulta.setParameter("nomeProduto", "%" + nomeProduto + "%");
         return (Fornecedor) consulta.uniqueResult(); //casting
    }
   
    
}
