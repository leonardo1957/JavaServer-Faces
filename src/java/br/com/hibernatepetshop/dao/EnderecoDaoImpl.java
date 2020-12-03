/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernatepetshop.dao;

import br.com.hibernatepetshop.entidade.Endereco;
import java.io.Serializable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author User
 */
public class EnderecoDaoImpl implements EnderecoDao, Serializable {

    @Override
    public void remover(Endereco entidade, Session sessao) throws HibernateException {
        Transaction transacao = sessao.beginTransaction();

        sessao.delete(entidade);

        try {
            transacao.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getSimpleName() + " " + entidade + " " + e.getMessage());
           
        }

    }

}
