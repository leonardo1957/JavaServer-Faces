/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernatepetshop.dao;


import br.com.hibernatepetshop.entidade.PessoaJuridica;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public interface PessoaJuridicaDao extends BaseDao<PessoaJuridica, Long>{
    
       List<PessoaJuridica> pesquisarPorNome (String nome, Session sessao) throws HibernateException;
    
}
