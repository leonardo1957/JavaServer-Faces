/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernatepetshop.controle;

import br.com.hibernatepetshop.dao.EnderecoDao;
import br.com.hibernatepetshop.dao.EnderecoDaoImpl;
import br.com.hibernatepetshop.dao.HibernateUtil;
import br.com.hibernatepetshop.dao.ProfessorDao;
import br.com.hibernatepetshop.dao.ProfessorDaoImpl;
import br.com.hibernatepetshop.dao.TelefoneDao;
import br.com.hibernatepetshop.dao.TelefoneDaoImpl;
import br.com.hibernatepetshop.entidade.Endereco;
import br.com.hibernatepetshop.entidade.Professor;
import br.com.hibernatepetshop.entidade.Telefone;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author User
 */
@ManagedBean(name = "professorC")
@ViewScoped
public class ProfessorControle {
  
   
    private Session sessao;
    private DataModel <Professor>modelProfessores;
    private int numeroAba =0;
    private List<Telefone> telefones;
    private Telefone telefone;   
    private Professor professor;
    private ProfessorDao professorDao;
    private List<Endereco> enderecos;
    private Endereco endereco;
    

   // metodo de pesquisa p/ interface grafica
    public void pesquisarPorNome(){
        try {
            professorDao = new ProfessorDaoImpl();
            sessao = HibernateUtil.abrirSessao();
            List<Professor> professores = professorDao.pesquisarPorNome(professor.getNome(), sessao);
            modelProfessores = new ListDataModel(professores); //convesao do JSF de List, lista na interface.
        } catch (Exception e) {
            System.err.println("Erro ao pesquisar por Nome - controle" + e.getMessage());
        }finally{
            sessao.close();
        }
        
    
        
}
    
    
    public void excluir(){ 
        professor = modelProfessores.getRowData();
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            sessao = HibernateUtil.abrirSessao();
            professorDao = new ProfessorDaoImpl();
            professorDao.remover(professor, sessao);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Excluido com sucesso",  "Excluido com sucesso!") );
            professor.setNome(null);
            modelProfessores = null; //limpar a lista dps da exclusão
        } catch (Exception e) {
            System.out.println("deu ruim ao excluir" + e.getMessage());
        }finally{
            sessao.close();
        }
            
    }
    
    public void excluirEndereco(Endereco endereco){
          enderecos.remove(endereco);
        if (endereco.getId() != null){
           {
            FacesContext context = FacesContext.getCurrentInstance();
            EnderecoDao enderecoDao = new EnderecoDaoImpl();
            sessao = HibernateUtil.abrirSessao();
            try {
                enderecoDao.remover(endereco, sessao);
                 context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Excluido com sucesso",  "Excluido com sucesso!") );
                 numeroAba =0;
            } catch (HibernateException e) {
                System.out.println("Erro ao excluir endereco" + e.getMessage());
            }finally{
                sessao.close();
            }
        }
    }
    }
    
    
    public void excluirTelefones(Telefone telefone){
          telefones.remove(telefone);
        if (telefone.getId() != null){
           {
            FacesContext context = FacesContext.getCurrentInstance();
            TelefoneDao telefoneDao = new TelefoneDaoImpl();
            sessao = HibernateUtil.abrirSessao();
            try {
                telefoneDao.remover(telefone, sessao);
                 context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Excluido com sucesso",  "Excluido com sucesso!") );
                 numeroAba =0;
            } catch (HibernateException e) {
                System.out.println("Erro ao excluir endereco" + e.getMessage());
            }finally{
                sessao.close();
            }
        }
    }
    }
    
    public void alterar(){
        numeroAba =1;
        professor = modelProfessores.getRowData();
        telefones = professor.getTelefones();
        endereco = professor.getEndereco();
    
    }
    
    public void carregarTelefone(Telefone telefone){
        this.telefone = telefone;
        
   }
    
    public void carregarEndereco(Endereco endereco){
        this.endereco = endereco;
        
   }
    
    public void onRowCancel(RowEditEvent<Telefone> event){
        System.out.println("Cancelando alterar endecero");
    }
    
     public void onRowEdit(RowEditEvent<Telefone> event){
        event.getObject().getId();
    }
    
    
    public void salvar(){
        FacesContext context = FacesContext.getCurrentInstance();
        professorDao = new ProfessorDaoImpl();
        sessao = HibernateUtil.abrirSessao();
        try {            
            professorDao.salvarOuAlterar(professor, sessao);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Salvo com sucesso",  "Salvo com sucesso!") );
            professor = new Professor();
            numeroAba =0;
            modelProfessores = null;
            
        } catch (HibernateException e) {
            System.out.println("deu ruim ao excluir" + e.getMessage());;

        }finally{
            sessao.close();
        }
        
        
    }
    
    
    public void novoTelefone(){
        telefone = new Telefone();
    }
    
    
    public void salvarTelefone(){
        if (telefones == null) {
            telefones = new ArrayList<Telefone>();
            professor.setTelefones(telefones);
        }
        if (telefone.getId() == null) {
             telefones.add(telefone);
        }
        
        professor.setTelefones(telefones);
    }
    
    
    
     public void novoEndereco(){
        endereco = new Endereco();
    }
    
    
    public void salvarEndereco(){
        if (enderecos == null) {
            enderecos = new ArrayList<Endereco>();
            professor.setEndereco(endereco);
        }
        if (endereco.getId() == null) {
             enderecos.add(endereco);
        }
        
        professor.setEndereco(endereco);
    }
    
    
    public Professor getProfessor() {
        if(professor == null){
            professor = new Professor();
        }
            
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public DataModel getModelProfessores() {
        return modelProfessores;
    }

    public int getNumeroAba() {
        return numeroAba;
    }

    public void setNumeroAba(int numeroAba) {
        this.numeroAba = numeroAba;
    }



    public Telefone getTelefone() {
        if (telefone ==null) {
            telefone = new Telefone();
        }
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }
    
       public List<Telefone> getTelefones() {
        return telefones;
    }
       
       
       public Endereco getEndereco() {
        if (endereco ==null) {
            endereco = new Endereco();
        }
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
       public List<Endereco> getEnderecos() {
        return enderecos;
    }
  
    
}
