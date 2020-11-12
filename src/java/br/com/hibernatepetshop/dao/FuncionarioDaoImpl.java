/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernatepetshop.dao;

import br.com.hibernatepetshop.entidade.Funcionario;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class FuncionarioDaoImpl extends BaseDaoImpl<Funcionario, Long>  implements FuncionarioDao{

    @Override
    public Funcionario pesquisarPorID(Long id, Session sessao) throws HibernateException {
        return (Funcionario) sessao.get(Funcionario.class, id);
    }

    @Override
    public List<Funcionario> pesquisarPorNome(String nome, Session session) throws HibernateException {
        Query consulta = session.createQuery("FROM Funcionario WHERE nome LIKE :nomeHQL");
        consulta.setParameter("nomeHQL", "%" + nome + "%");
        return consulta.list();
        
    }
    
    
}
