package DAO;

import ControleJPA.ReceitasJpaController;
import ControleJPA.exceptions.IllegalOrphanException;
import ControleJPA.exceptions.NonexistentEntityException;
import Entidades.Receitas;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ReceitasDAO {
    private final ReceitasJpaController ControleReceitas;
    private final EntityManagerFactory emf;
   public ReceitasDAO(){
    emf=Persistence.createEntityManagerFactory("DpaulaContabilidadePU");
    ControleReceitas=new ReceitasJpaController(emf);
    }
   public void adicionarReceita (Receitas value){
       ControleReceitas.create(value);       
   }
   public void alterarReceita (Receitas value) throws Exception{
       ControleReceitas.edit(value);
   }
   public void removerReceita (int idReceita) throws NonexistentEntityException, IllegalOrphanException{
       ControleReceitas.destroy(idReceita);
   }
   public List<Receitas> pegartodosReceitas(){
     return ControleReceitas.findReceitasEntities();
   }
   public Receitas pegarReceitasPorID(int IdReceita){
       return ControleReceitas.findReceitas(IdReceita);
   }
}
