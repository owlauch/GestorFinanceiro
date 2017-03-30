package DAO;

import ControleJPA.DespesasJpaController;
import ControleJPA.exceptions.IllegalOrphanException;
import ControleJPA.exceptions.NonexistentEntityException;
import Entidades.Despesas;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DespesasDAO {

    private final DespesasJpaController ControleUnidadeCusto;
    private final EntityManagerFactory emf;

    public DespesasDAO() {
        emf = Persistence.createEntityManagerFactory("DpaulaContabilidadePU");
        ControleUnidadeCusto = new DespesasJpaController(emf);
    }

    public void adicionarDespesa(Despesas despesa) throws Exception {
        ControleUnidadeCusto.create(despesa);
    }

    public void alterarDespesa(Despesas despesa) throws NonexistentEntityException, Exception {
        ControleUnidadeCusto.edit(despesa);
    }

    public void excluirDespesa(int idDespesa) throws IllegalOrphanException, NonexistentEntityException {
        ControleUnidadeCusto.destroy(idDespesa);
    }

    public List<Despesas> pegartodosDespesa() {
        return ControleUnidadeCusto.findDespesasEntities();
    }

    public Despesas pegarDespesaPorID(int idDespesa) {
        return ControleUnidadeCusto.findDespesas(idDespesa);
    }
}
