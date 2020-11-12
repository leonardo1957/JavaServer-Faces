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
@Table(name = "aluno")
@PrimaryKeyJoinColumn (name = "idPessoaSenac")
public class Aluno extends PessoaSenac{
    
    private String matricula;

    public Aluno() {
    }

    public Aluno(String matricula, Long id, String nome, String email, String rg, String cpf) {
        super(id, nome, email, rg, cpf);
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
   
    
}
