package DAO;


import ControleJPA.UnidadecustoJpaController;
import ControleJPA.exceptions.IllegalOrphanException;
import ControleJPA.exceptions.NonexistentEntityException;
import Entidades.Unidadecusto;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UnidadeDeCustoDAO {
    private final UnidadecustoJpaController ControleUnidadeCusto;
    private final EntityManagerFactory emf;
   public UnidadeDeCustoDAO(){
    emf=Persistence.createEntityManagerFactory("DpaulaContabilidadePU");
    ControleUnidadeCusto=new UnidadecustoJpaController(emf);
    } 
   public void adicionarUnidadecusto(Unidadecusto Unidadecusto) throws Exception{
       ControleUnidadeCusto.create(Unidadecusto);
   }
   public void alterarUnidadecusto(Unidadecusto Unidadecusto) throws NonexistentEntityException, Exception{
       ControleUnidadeCusto.edit(Unidadecusto);
   }
   public void excluirUnidadecusto(int idUnidadecusto) throws IllegalOrphanException, NonexistentEntityException{
       ControleUnidadeCusto.destroy(idUnidadecusto);
   }
      public List<Unidadecusto> pegartodosUnidadecusto(){
     return ControleUnidadeCusto.findUnidadecustoEntities();
   }
   public Unidadecusto pegarUnidadecustoPorID(int idUnidadecusto){
       return ControleUnidadeCusto.findUnidadecusto(idUnidadecusto);
   }
}
