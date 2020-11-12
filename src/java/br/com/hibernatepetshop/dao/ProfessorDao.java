/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernatepetshop.dao;

import br.com.hibernatepetshop.entidade.Professor;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public interface ProfessorDao extends BaseDao<Professor, Long>{
    
    List<Professor> pesquisarPorNome(String nome, Session sessao) throws HibernateException;
    
}
