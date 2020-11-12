/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernatepetshop.dao;

import br.com.hibernatepetshop.entidade.PessoaFisica;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public interface PessoaFisicaDao extends BaseDao<PessoaFisica, Long> {
    
    
    List<PessoaFisica> pesquisarPorNome (String nome, Session sessao) throws HibernateException;
    
}
