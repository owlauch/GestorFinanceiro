/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControleJPA;

import ControleJPA.exceptions.IllegalOrphanException;
import ControleJPA.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Receitas;
import java.util.ArrayList;
import java.util.List;
import Entidades.Despesas;
import Entidades.Metodopagamento;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author owlau
 */
public class MetodopagamentoJpaController implements Serializable {

    public MetodopagamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Metodopagamento metodopagamento) {
        if (metodopagamento.getReceitasList() == null) {
            metodopagamento.setReceitasList(new ArrayList<Receitas>());
        }
        if (metodopagamento.getDespesasList() == null) {
            metodopagamento.setDespesasList(new ArrayList<Despesas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Receitas> attachedReceitasList = new ArrayList<Receitas>();
            for (Receitas receitasListReceitasToAttach : metodopagamento.getReceitasList()) {
                receitasListReceitasToAttach = em.getReference(receitasListReceitasToAttach.getClass(), receitasListReceitasToAttach.getIDServicoPrestado());
                attachedReceitasList.add(receitasListReceitasToAttach);
            }
            metodopagamento.setReceitasList(attachedReceitasList);
            List<Despesas> attachedDespesasList = new ArrayList<Despesas>();
            for (Despesas despesasListDespesasToAttach : metodopagamento.getDespesasList()) {
                despesasListDespesasToAttach = em.getReference(despesasListDespesasToAttach.getClass(), despesasListDespesasToAttach.getIDDespesas());
                attachedDespesasList.add(despesasListDespesasToAttach);
            }
            metodopagamento.setDespesasList(attachedDespesasList);
            em.persist(metodopagamento);
            for (Receitas receitasListReceitas : metodopagamento.getReceitasList()) {
                Metodopagamento oldIDMetodoPagamentoOfReceitasListReceitas = receitasListReceitas.getIDMetodoPagamento();
                receitasListReceitas.setIDMetodoPagamento(metodopagamento);
                receitasListReceitas = em.merge(receitasListReceitas);
                if (oldIDMetodoPagamentoOfReceitasListReceitas != null) {
                    oldIDMetodoPagamentoOfReceitasListReceitas.getReceitasList().remove(receitasListReceitas);
                    oldIDMetodoPagamentoOfReceitasListReceitas = em.merge(oldIDMetodoPagamentoOfReceitasListReceitas);
                }
            }
            for (Despesas despesasListDespesas : metodopagamento.getDespesasList()) {
                Metodopagamento oldIDMetodoPagamentoOfDespesasListDespesas = despesasListDespesas.getIDMetodoPagamento();
                despesasListDespesas.setIDMetodoPagamento(metodopagamento);
                despesasListDespesas = em.merge(despesasListDespesas);
                if (oldIDMetodoPagamentoOfDespesasListDespesas != null) {
                    oldIDMetodoPagamentoOfDespesasListDespesas.getDespesasList().remove(despesasListDespesas);
                    oldIDMetodoPagamentoOfDespesasListDespesas = em.merge(oldIDMetodoPagamentoOfDespesasListDespesas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Metodopagamento metodopagamento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Metodopagamento persistentMetodopagamento = em.find(Metodopagamento.class, metodopagamento.getIDMetodoPagamento());
            List<Receitas> receitasListOld = persistentMetodopagamento.getReceitasList();
            List<Receitas> receitasListNew = metodopagamento.getReceitasList();
            List<Despesas> despesasListOld = persistentMetodopagamento.getDespesasList();
            List<Despesas> despesasListNew = metodopagamento.getDespesasList();
            List<String> illegalOrphanMessages = null;
            for (Receitas receitasListOldReceitas : receitasListOld) {
                if (!receitasListNew.contains(receitasListOldReceitas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Receitas " + receitasListOldReceitas + " since its IDMetodoPagamento field is not nullable.");
                }
            }
            for (Despesas despesasListOldDespesas : despesasListOld) {
                if (!despesasListNew.contains(despesasListOldDespesas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Despesas " + despesasListOldDespesas + " since its IDMetodoPagamento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Receitas> attachedReceitasListNew = new ArrayList<Receitas>();
            for (Receitas receitasListNewReceitasToAttach : receitasListNew) {
                receitasListNewReceitasToAttach = em.getReference(receitasListNewReceitasToAttach.getClass(), receitasListNewReceitasToAttach.getIDServicoPrestado());
                attachedReceitasListNew.add(receitasListNewReceitasToAttach);
            }
            receitasListNew = attachedReceitasListNew;
            metodopagamento.setReceitasList(receitasListNew);
            List<Despesas> attachedDespesasListNew = new ArrayList<Despesas>();
            for (Despesas despesasListNewDespesasToAttach : despesasListNew) {
                despesasListNewDespesasToAttach = em.getReference(despesasListNewDespesasToAttach.getClass(), despesasListNewDespesasToAttach.getIDDespesas());
                attachedDespesasListNew.add(despesasListNewDespesasToAttach);
            }
            despesasListNew = attachedDespesasListNew;
            metodopagamento.setDespesasList(despesasListNew);
            metodopagamento = em.merge(metodopagamento);
            for (Receitas receitasListNewReceitas : receitasListNew) {
                if (!receitasListOld.contains(receitasListNewReceitas)) {
                    Metodopagamento oldIDMetodoPagamentoOfReceitasListNewReceitas = receitasListNewReceitas.getIDMetodoPagamento();
                    receitasListNewReceitas.setIDMetodoPagamento(metodopagamento);
                    receitasListNewReceitas = em.merge(receitasListNewReceitas);
                    if (oldIDMetodoPagamentoOfReceitasListNewReceitas != null && !oldIDMetodoPagamentoOfReceitasListNewReceitas.equals(metodopagamento)) {
                        oldIDMetodoPagamentoOfReceitasListNewReceitas.getReceitasList().remove(receitasListNewReceitas);
                        oldIDMetodoPagamentoOfReceitasListNewReceitas = em.merge(oldIDMetodoPagamentoOfReceitasListNewReceitas);
                    }
                }
            }
            for (Despesas despesasListNewDespesas : despesasListNew) {
                if (!despesasListOld.contains(despesasListNewDespesas)) {
                    Metodopagamento oldIDMetodoPagamentoOfDespesasListNewDespesas = despesasListNewDespesas.getIDMetodoPagamento();
                    despesasListNewDespesas.setIDMetodoPagamento(metodopagamento);
                    despesasListNewDespesas = em.merge(despesasListNewDespesas);
                    if (oldIDMetodoPagamentoOfDespesasListNewDespesas != null && !oldIDMetodoPagamentoOfDespesasListNewDespesas.equals(metodopagamento)) {
                        oldIDMetodoPagamentoOfDespesasListNewDespesas.getDespesasList().remove(despesasListNewDespesas);
                        oldIDMetodoPagamentoOfDespesasListNewDespesas = em.merge(oldIDMetodoPagamentoOfDespesasListNewDespesas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = metodopagamento.getIDMetodoPagamento();
                if (findMetodopagamento(id) == null) {
                    throw new NonexistentEntityException("The metodopagamento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Metodopagamento metodopagamento;
            try {
                metodopagamento = em.getReference(Metodopagamento.class, id);
                metodopagamento.getIDMetodoPagamento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The metodopagamento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Receitas> receitasListOrphanCheck = metodopagamento.getReceitasList();
            for (Receitas receitasListOrphanCheckReceitas : receitasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Metodopagamento (" + metodopagamento + ") cannot be destroyed since the Receitas " + receitasListOrphanCheckReceitas + " in its receitasList field has a non-nullable IDMetodoPagamento field.");
            }
            List<Despesas> despesasListOrphanCheck = metodopagamento.getDespesasList();
            for (Despesas despesasListOrphanCheckDespesas : despesasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Metodopagamento (" + metodopagamento + ") cannot be destroyed since the Despesas " + despesasListOrphanCheckDespesas + " in its despesasList field has a non-nullable IDMetodoPagamento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(metodopagamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Metodopagamento> findMetodopagamentoEntities() {
        return findMetodopagamentoEntities(true, -1, -1);
    }

    public List<Metodopagamento> findMetodopagamentoEntities(int maxResults, int firstResult) {
        return findMetodopagamentoEntities(false, maxResults, firstResult);
    }

    private List<Metodopagamento> findMetodopagamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Metodopagamento.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Metodopagamento findMetodopagamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Metodopagamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getMetodopagamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Metodopagamento> rt = cq.from(Metodopagamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
