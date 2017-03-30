package DAO;

import ControleJPA.MetodopagamentoJpaController;
import ControleJPA.exceptions.IllegalOrphanException;
import ControleJPA.exceptions.NonexistentEntityException;
import Entidades.Metodopagamento;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MetodoDePagamentoDAO {
    private final MetodopagamentoJpaController ControleMetodopagamento;
    private final EntityManagerFactory emf;
   public MetodoDePagamentoDAO(){
    emf=Persistence.createEntityManagerFactory("DpaulaContabilidadePU");
    ControleMetodopagamento=new MetodopagamentoJpaController(emf);
    }
   public void adicionarmtdoPgto (Metodopagamento value){
       ControleMetodopagamento.create(value);       
   }
   public void alterarmtdoPgto (Metodopagamento value) throws Exception{
       ControleMetodopagamento.edit(value);
   }
   public void removermtdoPgto (int idValue) throws NonexistentEntityException, IllegalOrphanException{
       ControleMetodopagamento.destroy(idValue);
   }
   public List<Metodopagamento> pegartodosMetodopagamento(){
     return ControleMetodopagamento.findMetodopagamentoEntities();
   }
   public Metodopagamento pegarMetodopagamentoPorID(int IdmtdoPgto){
       return ControleMetodopagamento.findMetodopagamento(IdmtdoPgto);
   }
}
