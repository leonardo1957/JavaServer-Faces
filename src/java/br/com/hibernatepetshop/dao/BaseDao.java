/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernatepetshop.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public interface BaseDao<T, ID>{
    
    void salvarOuAlterar(T entidade, Session sessao) throws HibernateException;
   
    void remover (T entidade, Session sessao) throws HibernateException;
    
    T pesquisarPorID(Long id, Session sessao) throws HibernateException;
    
}
