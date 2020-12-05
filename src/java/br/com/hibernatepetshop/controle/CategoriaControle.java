/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernatepetshop.controle;
import br.com.hibernatepetshop.dao.CategoriaDao;
import br.com.hibernatepetshop.dao.CategoriaDaoImpl;
import br.com.hibernatepetshop.dao.FornecedorDao;
import br.com.hibernatepetshop.dao.FornecedorDaoImpl;
import br.com.hibernatepetshop.dao.HibernateUtil;
import br.com.hibernatepetshop.dao.ProdutoDao;
import br.com.hibernatepetshop.dao.ProdutoDaoImpl;
import br.com.hibernatepetshop.entidade.Categoria;
import br.com.hibernatepetshop.entidade.Fornecedor;
import br.com.hibernatepetshop.entidade.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author User
 * 
 * fornecedor - produto = produto - categoria
 */


@ManagedBean(name = "categoriaC")
@ViewScoped
public final class CategoriaControle {
    private Categoria categoria;
    private Session sessao;
    private DataModel<Categoria> modelCategoria;
    private int numeroAba = 0;
    private CategoriaDao categoriaDao;
    
   
    
    public void pesquisarPorNome(){
        try {
            categoriaDao = new CategoriaDaoImpl();
            sessao = HibernateUtil.abrirSessao();
            List<Categoria> categorias = categoriaDao.pesquisarPorNome(categoria.getNome(), sessao);
            modelCategoria = new ListDataModel(categorias); //convesao do JSF de List, lista na interface.
        } catch (Exception e) {
            System.err.println("Erro ao pesquisar por Nome - controle" + e.getMessage());
        }finally{
            sessao.close();
        }
    }
    
        public void excluir(){ 
        categoria = modelCategoria.getRowData();
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            sessao = HibernateUtil.abrirSessao();
            categoriaDao = new CategoriaDaoImpl();
            categoriaDao.remover(categoria, sessao);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Excluido com sucesso",  "Excluido com sucesso!") );
            categoria.setNome(null);
            modelCategoria = null; //limpar a lista dps da exclusão
        } catch (Exception e) {
            System.out.println("deu ruim ao excluir" + e.getMessage());
        }finally{
            sessao.close();
        }
            
    }
         
        public void alterar(){
        numeroAba =1;
        categoria = modelCategoria.getRowData();
    
    }
         
        public void salvar(){
        FacesContext context = FacesContext.getCurrentInstance();
        categoriaDao = new CategoriaDaoImpl();
        sessao = HibernateUtil.abrirSessao();
        try {
            categoriaDao.salvarOuAlterar(categoria, sessao);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Salvo com sucesso",  "Salvo com sucesso!") );
            categoria = new Categoria();
            numeroAba =0;
            modelCategoria = null;
            
        } catch (HibernateException e) {
            System.out.println("deu ruim ao salvar" + e.getMessage());;

        }finally{
            sessao.close();
        }
        
        
    }
         public Categoria getCategoria() {
        if (categoria == null) {
            categoria = new Categoria();
        }
        return categoria;
    }

 

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

 

    public DataModel getModelCategoria() {
        return modelCategoria;
    }

 

    public int getNumeroAba() {
        return numeroAba;
    }

 

    public void setNumeroAba(int numeroAba) {
        this.numeroAba = numeroAba;
    }

    
}




