/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernatepetshop.dao;

import br.com.hibernatepetshop.entidade.Funcionario;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public interface FuncionarioDao extends BaseDao<Funcionario, Long> {
    
    List<Funcionario> pesquisarPorNome(String nome, Session session) throws HibernateException;    
}
