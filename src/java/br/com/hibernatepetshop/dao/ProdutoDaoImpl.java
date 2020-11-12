/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernatepetshop.dao;

import br.com.hibernatepetshop.entidade.Produto;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class ProdutoDaoImpl extends BaseDaoImpl<Produto, Long> implements ProdutoDao{

    @Override
    public Produto pesquisarPorID(Long id, Session sessao) throws HibernateException {
        return (Produto) sessao.get(Produto.class, id);
    }

    @Override
    public List<Produto> pesquisarPorNome(String nome, Session session) throws HibernateException {
        Query consulta = session.createQuery("FROM Produto WHERE nome LIKE :nomeHQL");
        consulta.setParameter("nomeHQL", "%" + nome + "%");
        return consulta.list();
    }

    @Override
    public List<Produto> pesquisarPorFornecedor(String fornecedor, Session session) throws HibernateException {
        Query consulta = session.createQuery("FROM Produto WHERE fornecedor.nome LIKE :nomeFornecedor");
        consulta.setParameter("nomeFornecedor", "%" + fornecedor + "%");
        return consulta.list();
    }

 
    @Override
    public List<Produto> pesquisarPorProdutoEstoque(int qtd, String nomeProduto, Session session) throws HibernateException {
         Query consulta = session.createQuery("FROM Produto WHERE estoque <= :qtdEstoque AND nome LIKE :produto");
       consulta.setParameter("qtdEstoque", qtd);
        consulta.setParameter("produto", "%" + nomeProduto + "%");
        return consulta.list();
    }

    @Override
    public List<Produto> pesquisarPorPrecoMinimoMaximo(double minimo, double maximo, Session session) throws HibernateException {
         Query consulta = session.createQuery("FROM Produto WHERE preco BETWEEN :minimo AND :maximo");
       consulta.setParameter("minimo", minimo);
        consulta.setParameter("maximo",maximo);
        
        return consulta.list();
    }
    
}
