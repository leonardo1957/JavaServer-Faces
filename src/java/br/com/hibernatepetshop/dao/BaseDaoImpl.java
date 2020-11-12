/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernatepetshop.dao;

import java.io.Serializable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author User
 */
public abstract class BaseDaoImpl<T, ID> implements BaseDao<T, ID>, Serializable {

    private Transaction transacao; //modificação no banco de dados

    @Override
    public void salvarOuAlterar(T entidade, Session sessao) throws HibernateException {
        transacao = sessao.beginTransaction();

        sessao.saveOrUpdate(entidade);

        try {
            transacao.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getSimpleName()+ " " + entidade + " " + e.getMessage());
            transacao.rollback();
        }

    }

    @Override
    public void remover(T entidade, Session sessao) throws HibernateException {
         transacao = sessao.beginTransaction();

        sessao.delete(entidade);

        try {
            transacao.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getSimpleName()+ " " + entidade + " " + e.getMessage());
            transacao.rollback();
        }


    }

}
