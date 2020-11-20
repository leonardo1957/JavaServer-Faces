/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernatepetshop.controle;

import br.com.hibernatepetshop.dao.FornecedorDao;
import br.com.hibernatepetshop.dao.FornecedorDaoImpl;
import br.com.hibernatepetshop.dao.HibernateUtil;
import br.com.hibernatepetshop.dao.ProdutoDao;
import br.com.hibernatepetshop.dao.ProdutoDaoImpl;
import br.com.hibernatepetshop.entidade.Fornecedor;
import br.com.hibernatepetshop.entidade.Produto;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import static org.primefaces.behavior.confirm.ConfirmBehavior.PropertyKeys.message;

/**
 *
 * @author User
 */
@ManagedBean(name = "produtoC")
@ViewScoped
public class ProdutoContole {
    

    private Session sessao;
    private int numeroAba =0;   
    private Produto produto;
    private ProdutoDao produtoDao;
    private DataModel<Produto>modelProdutos;

   // metodo de pesquisa p/ interface grafica
    public void pesquisarPorNome(){
        try {
            produtoDao = new ProdutoDaoImpl();
            sessao = HibernateUtil.abrirSessao();
            List<Produto> produtos = produtoDao.pesquisarPorNome(produto.getNome(), sessao);
            modelProdutos = new ListDataModel(produtos); //convesao do JSF de List, lista na interface.
        } catch (Exception e) {
            System.err.println("Erro ao pesquisar por Nome - controle" + e.getMessage());
        }finally{
            sessao.close();
        }
        
    
        
}
    
    
    public void excluir(){ 
        produto = modelProdutos.getRowData();
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            sessao = HibernateUtil.abrirSessao();
            produtoDao = new ProdutoDaoImpl();
            produtoDao.remover(produto, sessao);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Excluido com sucesso",  "Excluido com sucesso!") );
            produto.setNome(null);
            modelProdutos = null; //limpar a lista dps da exclusão
        } catch (Exception e) {
            System.out.println("deu ruim ao excluir" + e.getMessage());
        }finally{
            sessao.close();
        }
            
    }
    
    public void alterar(){
        numeroAba =1;
        produto = modelProdutos.getRowData();
    
    }
    
    public void salvar(){
        FacesContext context = FacesContext.getCurrentInstance();
        produtoDao = new ProdutoDaoImpl();
        sessao = HibernateUtil.abrirSessao();
        try {
            
            produtoDao.salvarOuAlterar(produto, sessao);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Salvo com sucesso",  "Salvo com sucesso!") );
            produto = new Produto();
            numeroAba =0;
            
        } catch (HibernateException e) {
            System.out.println("deu ruim ao excluir" + e.getMessage());;

        }finally{
            sessao.close();
        }
        
        
    }

      public Produto getProduto() {
        if(produto == null){
            produto = new Produto();
        }
            
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public DataModel getModelProdutos() {
        return modelProdutos;
    }

    public int getNumeroAba() {
        return numeroAba;
    }

    public void setNumeroAba(int numeroAba) {
        this.numeroAba = numeroAba;
    }
    
  
    
}
