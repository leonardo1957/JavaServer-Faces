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
@Table(name = "professor")
@PrimaryKeyJoinColumn (name = "idPessoaSenac")
public class Professor extends PessoaSenac{
    
    
    
    private String cracha;

    public Professor() {
    }

    public Professor(Long id, String nome, String cpf, String rg, String email, String cracha) {
        super(id, nome, cpf, rg, email);
        this.cracha = cracha;
    }  

    public String getCracha() {
        return cracha;
    }

    public void setCracha(String cracha) {
        this.cracha = cracha;
    }

   
    
    
}
