/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernatepetshop.dao;

import br.com.hibernatepetshop.entidade.Endereco;
import br.com.hibernatepetshop.entidade.Funcionario;
import br.com.hibernatepetshop.util.UtilTeste;
import java.util.List;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class FuncionarioDaoImplTest {
    
    private Funcionario funcionario;
    private FuncionarioDao funcionarioDao;
    private Session sessao;
    
    public FuncionarioDaoImplTest() {
        funcionarioDao = new FuncionarioDaoImpl();
    }
    
    @Test
    public void testSalvar(){
        System.out.println("salvar");
        funcionario = new Funcionario(UtilTeste.gerarNumero(5), null, "Func " + UtilTeste.gerarCaracter(5), UtilTeste.gerarEmail(), UtilTeste.gerarTelefone());
        
        
        Endereco endereco = new Endereco(null, UtilTeste.gerarCaracter(5), UtilTeste.gerarNumero(2), UtilTeste.gerarCaracter(4), UtilTeste.gerarCaracter(4), UtilTeste.gerarCaracter(4), UtilTeste.gerarNumero(9), UtilTeste.gerarCaracter(5));
        funcionario.setEndereco(endereco);
        endereco.setFuncionario(funcionario);
        sessao = HibernateUtil.abrirSessao();
        funcionarioDao.salvarOuAlterar(funcionario, sessao);
        sessao.close();
        assertNotNull(funcionario.getId());
        assertNotNull(endereco.getId());
        
        
    }
    
    

  //  @Test
    public void testPesquisarPorID() {
        System.out.println("pesquisarPorID");
       
    }

 //   @Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisarPorNome");
        
    }
    
}
