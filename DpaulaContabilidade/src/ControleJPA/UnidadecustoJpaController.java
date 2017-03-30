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
import Entidades.Despesas;
import Entidades.Unidadecusto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author owlau
 */
public class UnidadecustoJpaController implements Serializable {

    public UnidadecustoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Unidadecusto unidadecusto) {
        if (unidadecusto.getDespesasList() == null) {
            unidadecusto.setDespesasList(new ArrayList<Despesas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Despesas> attachedDespesasList = new ArrayList<Despesas>();
            for (Despesas despesasListDespesasToAttach : unidadecusto.getDespesasList()) {
                despesasListDespesasToAttach = em.getReference(despesasListDespesasToAttach.getClass(), despesasListDespesasToAttach.getIDDespesas());
                attachedDespesasList.add(despesasListDespesasToAttach);
            }
            unidadecusto.setDespesasList(attachedDespesasList);
            em.persist(unidadecusto);
            for (Despesas despesasListDespesas : unidadecusto.getDespesasList()) {
                Unidadecusto oldIDUnidadeCustoOfDespesasListDespesas = despesasListDespesas.getIDUnidadeCusto();
                despesasListDespesas.setIDUnidadeCusto(unidadecusto);
                despesasListDespesas = em.merge(despesasListDespesas);
                if (oldIDUnidadeCustoOfDespesasListDespesas != null) {
                    oldIDUnidadeCustoOfDespesasListDespesas.getDespesasList().remove(despesasListDespesas);
                    oldIDUnidadeCustoOfDespesasListDespesas = em.merge(oldIDUnidadeCustoOfDespesasListDespesas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Unidadecusto unidadecusto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Unidadecusto persistentUnidadecusto = em.find(Unidadecusto.class, unidadecusto.getIDUnidadeCusto());
            List<Despesas> despesasListOld = persistentUnidadecusto.getDespesasList();
            List<Despesas> despesasListNew = unidadecusto.getDespesasList();
            List<String> illegalOrphanMessages = null;
            for (Despesas despesasListOldDespesas : despesasListOld) {
                if (!despesasListNew.contains(despesasListOldDespesas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Despesas " + despesasListOldDespesas + " since its IDUnidadeCusto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Despesas> attachedDespesasListNew = new ArrayList<Despesas>();
            for (Despesas despesasListNewDespesasToAttach : despesasListNew) {
                despesasListNewDespesasToAttach = em.getReference(despesasListNewDespesasToAttach.getClass(), despesasListNewDespesasToAttach.getIDDespesas());
                attachedDespesasListNew.add(despesasListNewDespesasToAttach);
            }
            despesasListNew = attachedDespesasListNew;
            unidadecusto.setDespesasList(despesasListNew);
            unidadecusto = em.merge(unidadecusto);
            for (Despesas despesasListNewDespesas : despesasListNew) {
                if (!despesasListOld.contains(despesasListNewDespesas)) {
                    Unidadecusto oldIDUnidadeCustoOfDespesasListNewDespesas = despesasListNewDespesas.getIDUnidadeCusto();
                    despesasListNewDespesas.setIDUnidadeCusto(unidadecusto);
                    despesasListNewDespesas = em.merge(despesasListNewDespesas);
                    if (oldIDUnidadeCustoOfDespesasListNewDespesas != null && !oldIDUnidadeCustoOfDespesasListNewDespesas.equals(unidadecusto)) {
                        oldIDUnidadeCustoOfDespesasListNewDespesas.getDespesasList().remove(despesasListNewDespesas);
                        oldIDUnidadeCustoOfDespesasListNewDespesas = em.merge(oldIDUnidadeCustoOfDespesasListNewDespesas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = unidadecusto.getIDUnidadeCusto();
                if (findUnidadecusto(id) == null) {
                    throw new NonexistentEntityException("The unidadecusto with id " + id + " no longer exists.");
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
            Unidadecusto unidadecusto;
            try {
                unidadecusto = em.getReference(Unidadecusto.class, id);
                unidadecusto.getIDUnidadeCusto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The unidadecusto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Despesas> despesasListOrphanCheck = unidadecusto.getDespesasList();
            for (Despesas despesasListOrphanCheckDespesas : despesasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Unidadecusto (" + unidadecusto + ") cannot be destroyed since the Despesas " + despesasListOrphanCheckDespesas + " in its despesasList field has a non-nullable IDUnidadeCusto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(unidadecusto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Unidadecusto> findUnidadecustoEntities() {
        return findUnidadecustoEntities(true, -1, -1);
    }

    public List<Unidadecusto> findUnidadecustoEntities(int maxResults, int firstResult) {
        return findUnidadecustoEntities(false, maxResults, firstResult);
    }

    private List<Unidadecusto> findUnidadecustoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Unidadecusto.class));
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

    public Unidadecusto findUnidadecusto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Unidadecusto.class, id);
        } finally {
            em.close();
        }
    }

    public int getUnidadecustoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Unidadecusto> rt = cq.from(Unidadecusto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
