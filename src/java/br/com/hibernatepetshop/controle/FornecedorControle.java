/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernatepetshop.controle;

import br.com.hibernatepetshop.dao.FornecedorDao;
import br.com.hibernatepetshop.dao.FornecedorDaoImpl;
import br.com.hibernatepetshop.dao.HibernateUtil;
import br.com.hibernatepetshop.entidade.Fornecedor;
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
@ManagedBean(name = "fornecedorC")
@ViewScoped
public class FornecedorControle {
    
    private Fornecedor fornecedor;
    private FornecedorDao fornecedorDao;
    private Session sessao;
    private DataModel <Fornecedor>modelFornecedores;
    private int numeroAba =0;

   // metodo de pesquisa p/ interface grafica
    public void pesquisarPorNome(){
        try {
            fornecedorDao = new FornecedorDaoImpl();
            sessao = HibernateUtil.abrirSessao();
            List<Fornecedor> fornecedores = fornecedorDao.pesquisarPorNome(fornecedor.getNome(), sessao);
            modelFornecedores = new ListDataModel(fornecedores); //convesao do JSF de List, lista na interface.
        } catch (Exception e) {
            System.err.println("Erro ao pesquisar por Nome - controle" + e.getMessage());
        }finally{
            sessao.close();
        }
        
    
        
}
    
    
    public void excluir(){ 
        fornecedor = modelFornecedores.getRowData();
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            sessao = HibernateUtil.abrirSessao();
            fornecedorDao = new FornecedorDaoImpl();
            fornecedorDao.remover(fornecedor, sessao);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Excluido com sucesso",  "Excluido com sucesso!") );
            fornecedor.setNome(null);
            modelFornecedores = null; //limpar a lista dps da exclusão
        } catch (Exception e) {
            System.out.println("deu ruim ao excluir" + e.getMessage());
        }finally{
            sessao.close();
        }
            
    }
    
    public void alterar(){
        numeroAba =1;
        fornecedor = modelFornecedores.getRowData();
    
    }
    
    public void salvar(){
        FacesContext context = FacesContext.getCurrentInstance();
        fornecedorDao = new FornecedorDaoImpl();
        sessao = HibernateUtil.abrirSessao();
        try {
            fornecedor.setDataCadastro(new Date());
            fornecedorDao.salvarOuAlterar(fornecedor, sessao);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Salvo com sucesso",  "Salvo com sucesso!") );
            fornecedor = new Fornecedor();
            numeroAba =0;
            
        } catch (HibernateException e) {
            System.out.println("deu ruim ao excluir" + e.getMessage());;

        }finally{
            sessao.close();
        }
        
        
    }
    
    public Fornecedor getFornecedor() {
        if(fornecedor == null){
            fornecedor = new Fornecedor();
        }
            
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public DataModel getModelFornecedores() {
        return modelFornecedores;
    }

    public int getNumeroAba() {
        return numeroAba;
    }

    public void setNumeroAba(int numeroAba) {
        this.numeroAba = numeroAba;
    }
    
  
    
}
