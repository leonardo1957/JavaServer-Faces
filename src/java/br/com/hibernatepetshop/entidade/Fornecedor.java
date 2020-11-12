/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernatepetshop.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author User
 */
@Entity
@Table(name = "fornecedor")
@PrimaryKeyJoinColumn (name = "idPessoa")
public class Fornecedor extends Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
   

    @Lob
    private String descricao;

    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    
    
    @OneToMany (fetch = FetchType.LAZY, mappedBy = "fornecedor") //lazy pra nao demorar a consulta Lista de produtos por fonecedor
    private List<Produto> produtos;
    
     @OneToMany (mappedBy = "fornecedor", cascade = CascadeType.ALL)
      private List<Endereco> enderecos;

    public Fornecedor() {
    }

    public Fornecedor(Long id, String nome, String email, String telefone, String descricao, Date dataCadastro) {
        super(id, nome, email, telefone);
        this.descricao = descricao;
        this.dataCadastro = dataCadastro;
        
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    
  

}
