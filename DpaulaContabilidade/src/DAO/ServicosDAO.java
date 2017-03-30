package DAO;

import ControleJPA.ServicosJpaController;
import ControleJPA.exceptions.IllegalOrphanException;
import ControleJPA.exceptions.NonexistentEntityException;
import Entidades.Servicos;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ServicosDAO {
    private final ServicosJpaController ControleServicos;
    private final EntityManagerFactory emf;
   public ServicosDAO(){
    emf=Persistence.createEntityManagerFactory("DpaulaContabilidadePU");
    ControleServicos=new ServicosJpaController(emf);
    }
   public void adicionarServico (Servicos Value){
       ControleServicos.create(Value);       
   }
   public void alterarServico (Servicos Value) throws Exception{
       ControleServicos.edit(Value);
   }
   public void removerServico (int idServico) throws NonexistentEntityException, IllegalOrphanException{
       ControleServicos.destroy(idServico);
   }
   public List<Servicos> pegartodosServicos(){
     return ControleServicos.findServicosEntities();
   }
   public Servicos pegarServicosPorID(int IdServico){
       return ControleServicos.findServicos(IdServico);
   }
}
