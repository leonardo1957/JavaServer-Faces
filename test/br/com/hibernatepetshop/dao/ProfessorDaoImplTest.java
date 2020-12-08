/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hibernatepetshop.dao;


import br.com.hibernatepetshop.entidade.Endereco;
import static br.com.hibernatepetshop.entidade.Pessoa_.telefone;
import br.com.hibernatepetshop.entidade.Professor;
import br.com.hibernatepetshop.entidade.Telefone;
import br.com.hibernatepetshop.util.UtilTeste;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class ProfessorDaoImplTest {
    
    private Professor professor;
    private ProfessorDao professorDao;
    private Session sesssao;
    
    public ProfessorDaoImplTest() {
        professorDao = new ProfessorDaoImpl();
    }

    
   // @Test
    public void TestSalvar() {
        System.out.println("salvar");
        professor = new Professor(null, UtilTeste.gerarCaracter(5), UtilTeste.gerarCpf(), UtilTeste.gerarNumero(9), UtilTeste.gerarEmail(), UtilTeste.gerarNumero(9));
        Endereco endereco = new Endereco(null, UtilTeste.gerarCaracter(5), UtilTeste.gerarNumero(2), UtilTeste.gerarCaracter(4), UtilTeste.gerarCaracter(4), UtilTeste.gerarCaracter(4), UtilTeste.gerarNumero(9), UtilTeste.gerarCaracter(5)); 
        professor.setEndereco(endereco);
        endereco.setPessoaSenac(professor);
        List<Telefone> telefones = new ArrayList<>();
        
        for (int i = 0; i < 2; i++){             
              telefones.add(criarTelefone());
          }
              
        professor.setTelefones(telefones);
          for (Telefone telefone : telefones) {
              telefone.setPessoaSenac(professor);
          }
        Session sesssao = HibernateUtil.abrirSessao();
          professorDao.salvarOuAlterar(professor, sesssao);
          sesssao.close();
          
          assertNotNull(endereco.getId());
          assertNotNull(professor.getId());
          assertNotNull(professor.getTelefones().get(0).getId());
    }
    
    private Telefone criarTelefone(){
          Telefone telefone = new Telefone(null, UtilTeste.gerarTelefone(), "Fixo", "Claro");
      
   return telefone;
       
   
}
    
    //@Test
    public void TestAlterar(){
        System.out.println("Alterar");
        buscarProfessorBd();
        
        professor.setNome(UtilTeste.gerarCaracter(8));
        professor.getEndereco().setLogradouro("Av. Teste");
        professor.getTelefones().get(0).setNumero("(48)991673182");
        
       sesssao = HibernateUtil.abrirSessao();
       professorDao.salvarOuAlterar(professor, sesssao);
       
       Professor professorAlterado = professorDao.pesquisarPorNome(professor.getNome(), sesssao).get(0);
            
       Telefone telefone = professorAlterado.getTelefones().get(0);
       sesssao.close();
       
        assertEquals(professor.getNome(), professorAlterado.getNome());
        assertEquals(professor.getEndereco().getLogradouro(), professorAlterado.getEndereco().getLogradouro());
        assertEquals(professor.getTelefones().get(0).getNumero(), telefone.getNumero());
     
    }
    
    // @Test
    public void testExcluir(){
        System.out.println("Excluir");
        buscarProfessorBd();
        sesssao = HibernateUtil.abrirSessao();
        professorDao.remover(professor, sesssao);
        
        Professor professorExcluido = professorDao.pesquisarPorNome(professor.getNome(), sesssao).get(0);
        sesssao.close();
        assertNull(professorExcluido);
    }
    
    
   // @Test
    public void testPesquisarPorNome(){
        System.out.println("pesquisar por nome");
        buscarProfessorBd();
        sesssao = HibernateUtil.abrirSessao();
        List<Professor> professores = professorDao.pesquisarPorNome(professor.getNome(), sesssao);
        assertTrue(professores.size() > 0);
    }
    
     public Professor buscarProfessorBd() {
  
            sesssao = HibernateUtil.abrirSessao();
            Query consulta = sesssao.createQuery("SELECT distinct(p) from Professor p join fetch p.telefones");
            List<Professor> professores = consulta.list();
            if (professores.isEmpty()) {
                sesssao.close();
                TestSalvar();
            }else{
              professor =  professores.get(0);
                sesssao.close();
            }
                
        

        return professor;
    }

   // @Test
    public void testPesquisarPorID() {
        System.out.println("pesquisarPorID");
        Long id = null;
        Session sessao = null;
        ProfessorDaoImpl instance = new ProfessorDaoImpl();
        Professor expResult = null;
        Professor result = instance.pesquisarPorID(id, sessao);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

}

