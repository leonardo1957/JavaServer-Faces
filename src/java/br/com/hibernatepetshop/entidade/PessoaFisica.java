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
@Table(name = "pessoaFisica")
@PrimaryKeyJoinColumn (name = "idCliente")
public class PessoaFisica extends Cliente{
    
    private String cpf;
    private String Rg;

    public PessoaFisica() {
    }

    public PessoaFisica(Long id, String cpf, String Rg, String nome, String email) {
        super(id, nome, email);
        this.cpf = cpf;
        this.Rg = Rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return Rg;
    }

    public void setRg(String Rg) {
        this.Rg = Rg;
    }

    
  
    
}
