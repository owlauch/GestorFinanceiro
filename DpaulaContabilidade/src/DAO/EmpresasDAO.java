package DAO;

import ControleJPA.EmpresasJpaController;
import ControleJPA.exceptions.IllegalOrphanException;
import ControleJPA.exceptions.NonexistentEntityException;
import Entidades.Empresas;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmpresasDAO {
    private final EmpresasJpaController ControleEmpresas;
    private final EntityManagerFactory emf;
   public EmpresasDAO(){
    emf=Persistence.createEntityManagerFactory("DpaulaContabilidadePU");
    ControleEmpresas=new EmpresasJpaController(emf);
    }
   public void adicionarEmpresa (Empresas cliente){
       ControleEmpresas.create(cliente);       
   }
   public void alterarEmpresa (Empresas cliente) throws Exception{
       ControleEmpresas.edit(cliente);
   }
   public void removerEmpresa (int idEmpresa) throws NonexistentEntityException, IllegalOrphanException{
       ControleEmpresas.destroy(idEmpresa);
   }
   public List<Empresas> pegartodosEmpresas(){
     return ControleEmpresas.findEmpresasEntities();
   }
   public Empresas pegarEmpresasPorID(int IdEmpresa){
       return ControleEmpresas.findEmpresas(IdEmpresa);
   }
}
