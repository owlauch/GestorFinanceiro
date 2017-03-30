/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControleJPA;

import ControleJPA.exceptions.IllegalOrphanException;
import ControleJPA.exceptions.NonexistentEntityException;
import Entidades.Centrodecusto;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Despesas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author owlau
 */
public class CentrodecustoJpaController implements Serializable {

    public CentrodecustoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Centrodecusto centrodecusto) {
        if (centrodecusto.getDespesasList() == null) {
            centrodecusto.setDespesasList(new ArrayList<Despesas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Despesas> attachedDespesasList = new ArrayList<Despesas>();
            for (Despesas despesasListDespesasToAttach : centrodecusto.getDespesasList()) {
                despesasListDespesasToAttach = em.getReference(despesasListDespesasToAttach.getClass(), despesasListDespesasToAttach.getIDDespesas());
                attachedDespesasList.add(despesasListDespesasToAttach);
            }
            centrodecusto.setDespesasList(attachedDespesasList);
            em.persist(centrodecusto);
            for (Despesas despesasListDespesas : centrodecusto.getDespesasList()) {
                Centrodecusto oldIDCentroDeCustoOfDespesasListDespesas = despesasListDespesas.getIDCentroDeCusto();
                despesasListDespesas.setIDCentroDeCusto(centrodecusto);
                despesasListDespesas = em.merge(despesasListDespesas);
                if (oldIDCentroDeCustoOfDespesasListDespesas != null) {
                    oldIDCentroDeCustoOfDespesasListDespesas.getDespesasList().remove(despesasListDespesas);
                    oldIDCentroDeCustoOfDespesasListDespesas = em.merge(oldIDCentroDeCustoOfDespesasListDespesas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Centrodecusto centrodecusto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Centrodecusto persistentCentrodecusto = em.find(Centrodecusto.class, centrodecusto.getIDCentroDeCusto());
            List<Despesas> despesasListOld = persistentCentrodecusto.getDespesasList();
            List<Despesas> despesasListNew = centrodecusto.getDespesasList();
            List<String> illegalOrphanMessages = null;
            for (Despesas despesasListOldDespesas : despesasListOld) {
                if (!despesasListNew.contains(despesasListOldDespesas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Despesas " + despesasListOldDespesas + " since its IDCentroDeCusto field is not nullable.");
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
            centrodecusto.setDespesasList(despesasListNew);
            centrodecusto = em.merge(centrodecusto);
            for (Despesas despesasListNewDespesas : despesasListNew) {
                if (!despesasListOld.contains(despesasListNewDespesas)) {
                    Centrodecusto oldIDCentroDeCustoOfDespesasListNewDespesas = despesasListNewDespesas.getIDCentroDeCusto();
                    despesasListNewDespesas.setIDCentroDeCusto(centrodecusto);
                    despesasListNewDespesas = em.merge(despesasListNewDespesas);
                    if (oldIDCentroDeCustoOfDespesasListNewDespesas != null && !oldIDCentroDeCustoOfDespesasListNewDespesas.equals(centrodecusto)) {
                        oldIDCentroDeCustoOfDespesasListNewDespesas.getDespesasList().remove(despesasListNewDespesas);
                        oldIDCentroDeCustoOfDespesasListNewDespesas = em.merge(oldIDCentroDeCustoOfDespesasListNewDespesas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = centrodecusto.getIDCentroDeCusto();
                if (findCentrodecusto(id) == null) {
                    throw new NonexistentEntityException("The centrodecusto with id " + id + " no longer exists.");
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
            Centrodecusto centrodecusto;
            try {
                centrodecusto = em.getReference(Centrodecusto.class, id);
                centrodecusto.getIDCentroDeCusto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The centrodecusto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Despesas> despesasListOrphanCheck = centrodecusto.getDespesasList();
            for (Despesas despesasListOrphanCheckDespesas : despesasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Centrodecusto (" + centrodecusto + ") cannot be destroyed since the Despesas " + despesasListOrphanCheckDespesas + " in its despesasList field has a non-nullable IDCentroDeCusto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(centrodecusto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Centrodecusto> findCentrodecustoEntities() {
        return findCentrodecustoEntities(true, -1, -1);
    }

    public List<Centrodecusto> findCentrodecustoEntities(int maxResults, int firstResult) {
        return findCentrodecustoEntities(false, maxResults, firstResult);
    }

    private List<Centrodecusto> findCentrodecustoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Centrodecusto.class));
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

    public Centrodecusto findCentrodecusto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Centrodecusto.class, id);
        } finally {
            em.close();
        }
    }

    public int getCentrodecustoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Centrodecusto> rt = cq.from(Centrodecusto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
