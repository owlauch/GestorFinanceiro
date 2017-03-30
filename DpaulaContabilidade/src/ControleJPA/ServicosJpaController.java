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
import Entidades.Servicos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author owlau
 */
public class ServicosJpaController implements Serializable {

    public ServicosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicos servicos) {
        if (servicos.getReceitasList() == null) {
            servicos.setReceitasList(new ArrayList<Receitas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Receitas> attachedReceitasList = new ArrayList<Receitas>();
            for (Receitas receitasListReceitasToAttach : servicos.getReceitasList()) {
                receitasListReceitasToAttach = em.getReference(receitasListReceitasToAttach.getClass(), receitasListReceitasToAttach.getIDServicoPrestado());
                attachedReceitasList.add(receitasListReceitasToAttach);
            }
            servicos.setReceitasList(attachedReceitasList);
            em.persist(servicos);
            for (Receitas receitasListReceitas : servicos.getReceitasList()) {
                Servicos oldIDServicoOfReceitasListReceitas = receitasListReceitas.getIDServico();
                receitasListReceitas.setIDServico(servicos);
                receitasListReceitas = em.merge(receitasListReceitas);
                if (oldIDServicoOfReceitasListReceitas != null) {
                    oldIDServicoOfReceitasListReceitas.getReceitasList().remove(receitasListReceitas);
                    oldIDServicoOfReceitasListReceitas = em.merge(oldIDServicoOfReceitasListReceitas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Servicos servicos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicos persistentServicos = em.find(Servicos.class, servicos.getIDServico());
            List<Receitas> receitasListOld = persistentServicos.getReceitasList();
            List<Receitas> receitasListNew = servicos.getReceitasList();
            List<String> illegalOrphanMessages = null;
            for (Receitas receitasListOldReceitas : receitasListOld) {
                if (!receitasListNew.contains(receitasListOldReceitas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Receitas " + receitasListOldReceitas + " since its IDServico field is not nullable.");
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
            servicos.setReceitasList(receitasListNew);
            servicos = em.merge(servicos);
            for (Receitas receitasListNewReceitas : receitasListNew) {
                if (!receitasListOld.contains(receitasListNewReceitas)) {
                    Servicos oldIDServicoOfReceitasListNewReceitas = receitasListNewReceitas.getIDServico();
                    receitasListNewReceitas.setIDServico(servicos);
                    receitasListNewReceitas = em.merge(receitasListNewReceitas);
                    if (oldIDServicoOfReceitasListNewReceitas != null && !oldIDServicoOfReceitasListNewReceitas.equals(servicos)) {
                        oldIDServicoOfReceitasListNewReceitas.getReceitasList().remove(receitasListNewReceitas);
                        oldIDServicoOfReceitasListNewReceitas = em.merge(oldIDServicoOfReceitasListNewReceitas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = servicos.getIDServico();
                if (findServicos(id) == null) {
                    throw new NonexistentEntityException("The servicos with id " + id + " no longer exists.");
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
            Servicos servicos;
            try {
                servicos = em.getReference(Servicos.class, id);
                servicos.getIDServico();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Receitas> receitasListOrphanCheck = servicos.getReceitasList();
            for (Receitas receitasListOrphanCheckReceitas : receitasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Servicos (" + servicos + ") cannot be destroyed since the Receitas " + receitasListOrphanCheckReceitas + " in its receitasList field has a non-nullable IDServico field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(servicos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Servicos> findServicosEntities() {
        return findServicosEntities(true, -1, -1);
    }

    public List<Servicos> findServicosEntities(int maxResults, int firstResult) {
        return findServicosEntities(false, maxResults, firstResult);
    }

    private List<Servicos> findServicosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Servicos.class));
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

    public Servicos findServicos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servicos.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Servicos> rt = cq.from(Servicos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
