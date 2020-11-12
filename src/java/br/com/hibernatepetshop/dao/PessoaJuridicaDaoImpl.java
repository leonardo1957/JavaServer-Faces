/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernatepetshop.dao;

import br.com.hibernatepetshop.entidade.PessoaFisica;
import br.com.hibernatepetshop.entidade.PessoaJuridica;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class PessoaJuridicaDaoImpl extends BaseDaoImpl<PessoaJuridica, Long> implements PessoaJuridicaDao, Serializable{
    
     @Override
    public PessoaJuridica pesquisarPorID(Long id, Session sessao) throws HibernateException {
        return (PessoaJuridica) sessao.get(PessoaJuridica.class, id);
    }

    @Override
    public List<PessoaJuridica> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("Select distinct (p) from PessoaJuridica p join fetch p.cartoes where p.nome like :nomePessoaJuridica"); // "cartoes" list  da classe Cliente
        consulta.setParameter("nomePessoaJuridica", "%" + nome + "%");       
        return consulta.list();
    }
    
}
