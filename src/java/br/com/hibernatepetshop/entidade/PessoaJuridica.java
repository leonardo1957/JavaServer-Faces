/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernatepetshop.entidade;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author User
 */

@Entity
@Table(name = "pessoaJuridica")
@PrimaryKeyJoinColumn(name = "idCliente")
public class PessoaJuridica extends Cliente{
    
    private String cnpj;
    private String inscricaoEstadual;

    public PessoaJuridica() {
    }

    public PessoaJuridica(Long id, String cnpj, String inscricaoEstadual, String nome, String email) {
        super(id, nome, email);
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }
    
    
    
}
