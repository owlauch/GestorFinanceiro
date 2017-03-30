/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControleJPA;

import ControleJPA.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Empresas;
import Entidades.Receitamensal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author owlau
 */
public class ReceitamensalJpaController implements Serializable {

    public ReceitamensalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Receitamensal receitamensal) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresas IDEmpresa = receitamensal.getIDEmpresa();
            if (IDEmpresa != null) {
                IDEmpresa = em.getReference(IDEmpresa.getClass(), IDEmpresa.getIDEmpresa());
                receitamensal.setIDEmpresa(IDEmpresa);
            }
            em.persist(receitamensal);
            if (IDEmpresa != null) {
                IDEmpresa.getReceitamensalList().add(receitamensal);
                IDEmpresa = em.merge(IDEmpresa);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Receitamensal receitamensal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Receitamensal persistentReceitamensal = em.find(Receitamensal.class, receitamensal.getIdReceitaMensal());
            Empresas IDEmpresaOld = persistentReceitamensal.getIDEmpresa();
            Empresas IDEmpresaNew = receitamensal.getIDEmpresa();
            if (IDEmpresaNew != null) {
                IDEmpresaNew = em.getReference(IDEmpresaNew.getClass(), IDEmpresaNew.getIDEmpresa());
                receitamensal.setIDEmpresa(IDEmpresaNew);
            }
            receitamensal = em.merge(receitamensal);
            if (IDEmpresaOld != null && !IDEmpresaOld.equals(IDEmpresaNew)) {
                IDEmpresaOld.getReceitamensalList().remove(receitamensal);
                IDEmpresaOld = em.merge(IDEmpresaOld);
            }
            if (IDEmpresaNew != null && !IDEmpresaNew.equals(IDEmpresaOld)) {
                IDEmpresaNew.getReceitamensalList().add(receitamensal);
                IDEmpresaNew = em.merge(IDEmpresaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = receitamensal.getIdReceitaMensal();
                if (findReceitamensal(id) == null) {
                    throw new NonexistentEntityException("The receitamensal with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Receitamensal receitamensal;
            try {
                receitamensal = em.getReference(Receitamensal.class, id);
                receitamensal.getIdReceitaMensal();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The receitamensal with id " + id + " no longer exists.", enfe);
            }
            Empresas IDEmpresa = receitamensal.getIDEmpresa();
            if (IDEmpresa != null) {
                IDEmpresa.getReceitamensalList().remove(receitamensal);
                IDEmpresa = em.merge(IDEmpresa);
            }
            em.remove(receitamensal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Receitamensal> findReceitamensalEntities() {
        return findReceitamensalEntities(true, -1, -1);
    }

    public List<Receitamensal> findReceitamensalEntities(int maxResults, int firstResult) {
        return findReceitamensalEntities(false, maxResults, firstResult);
    }

    private List<Receitamensal> findReceitamensalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Receitamensal.class));
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

    public Receitamensal findReceitamensal(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Receitamensal.class, id);
        } finally {
            em.close();
        }
    }

    public int getReceitamensalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Receitamensal> rt = cq.from(Receitamensal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
