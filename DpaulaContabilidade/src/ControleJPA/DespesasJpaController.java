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
import Entidades.Centrodecusto;
import Entidades.Despesas;
import Entidades.Metodopagamento;
import Entidades.Unidadecusto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author owlau
 */
public class DespesasJpaController implements Serializable {

    public DespesasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Despesas despesas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Centrodecusto IDCentroDeCusto = despesas.getIDCentroDeCusto();
            if (IDCentroDeCusto != null) {
                IDCentroDeCusto = em.getReference(IDCentroDeCusto.getClass(), IDCentroDeCusto.getIDCentroDeCusto());
                despesas.setIDCentroDeCusto(IDCentroDeCusto);
            }
            Metodopagamento IDMetodoPagamento = despesas.getIDMetodoPagamento();
            if (IDMetodoPagamento != null) {
                IDMetodoPagamento = em.getReference(IDMetodoPagamento.getClass(), IDMetodoPagamento.getIDMetodoPagamento());
                despesas.setIDMetodoPagamento(IDMetodoPagamento);
            }
            Unidadecusto IDUnidadeCusto = despesas.getIDUnidadeCusto();
            if (IDUnidadeCusto != null) {
                IDUnidadeCusto = em.getReference(IDUnidadeCusto.getClass(), IDUnidadeCusto.getIDUnidadeCusto());
                despesas.setIDUnidadeCusto(IDUnidadeCusto);
            }
            em.persist(despesas);
            if (IDCentroDeCusto != null) {
                IDCentroDeCusto.getDespesasList().add(despesas);
                IDCentroDeCusto = em.merge(IDCentroDeCusto);
            }
            if (IDMetodoPagamento != null) {
                IDMetodoPagamento.getDespesasList().add(despesas);
                IDMetodoPagamento = em.merge(IDMetodoPagamento);
            }
            if (IDUnidadeCusto != null) {
                IDUnidadeCusto.getDespesasList().add(despesas);
                IDUnidadeCusto = em.merge(IDUnidadeCusto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Despesas despesas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Despesas persistentDespesas = em.find(Despesas.class, despesas.getIDDespesas());
            Centrodecusto IDCentroDeCustoOld = persistentDespesas.getIDCentroDeCusto();
            Centrodecusto IDCentroDeCustoNew = despesas.getIDCentroDeCusto();
            Metodopagamento IDMetodoPagamentoOld = persistentDespesas.getIDMetodoPagamento();
            Metodopagamento IDMetodoPagamentoNew = despesas.getIDMetodoPagamento();
            Unidadecusto IDUnidadeCustoOld = persistentDespesas.getIDUnidadeCusto();
            Unidadecusto IDUnidadeCustoNew = despesas.getIDUnidadeCusto();
            if (IDCentroDeCustoNew != null) {
                IDCentroDeCustoNew = em.getReference(IDCentroDeCustoNew.getClass(), IDCentroDeCustoNew.getIDCentroDeCusto());
                despesas.setIDCentroDeCusto(IDCentroDeCustoNew);
            }
            if (IDMetodoPagamentoNew != null) {
                IDMetodoPagamentoNew = em.getReference(IDMetodoPagamentoNew.getClass(), IDMetodoPagamentoNew.getIDMetodoPagamento());
                despesas.setIDMetodoPagamento(IDMetodoPagamentoNew);
            }
            if (IDUnidadeCustoNew != null) {
                IDUnidadeCustoNew = em.getReference(IDUnidadeCustoNew.getClass(), IDUnidadeCustoNew.getIDUnidadeCusto());
                despesas.setIDUnidadeCusto(IDUnidadeCustoNew);
            }
            despesas = em.merge(despesas);
            if (IDCentroDeCustoOld != null && !IDCentroDeCustoOld.equals(IDCentroDeCustoNew)) {
                IDCentroDeCustoOld.getDespesasList().remove(despesas);
                IDCentroDeCustoOld = em.merge(IDCentroDeCustoOld);
            }
            if (IDCentroDeCustoNew != null && !IDCentroDeCustoNew.equals(IDCentroDeCustoOld)) {
                IDCentroDeCustoNew.getDespesasList().add(despesas);
                IDCentroDeCustoNew = em.merge(IDCentroDeCustoNew);
            }
            if (IDMetodoPagamentoOld != null && !IDMetodoPagamentoOld.equals(IDMetodoPagamentoNew)) {
                IDMetodoPagamentoOld.getDespesasList().remove(despesas);
                IDMetodoPagamentoOld = em.merge(IDMetodoPagamentoOld);
            }
            if (IDMetodoPagamentoNew != null && !IDMetodoPagamentoNew.equals(IDMetodoPagamentoOld)) {
                IDMetodoPagamentoNew.getDespesasList().add(despesas);
                IDMetodoPagamentoNew = em.merge(IDMetodoPagamentoNew);
            }
            if (IDUnidadeCustoOld != null && !IDUnidadeCustoOld.equals(IDUnidadeCustoNew)) {
                IDUnidadeCustoOld.getDespesasList().remove(despesas);
                IDUnidadeCustoOld = em.merge(IDUnidadeCustoOld);
            }
            if (IDUnidadeCustoNew != null && !IDUnidadeCustoNew.equals(IDUnidadeCustoOld)) {
                IDUnidadeCustoNew.getDespesasList().add(despesas);
                IDUnidadeCustoNew = em.merge(IDUnidadeCustoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = despesas.getIDDespesas();
                if (findDespesas(id) == null) {
                    throw new NonexistentEntityException("The despesas with id " + id + " no longer exists.");
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
            Despesas despesas;
            try {
                despesas = em.getReference(Despesas.class, id);
                despesas.getIDDespesas();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The despesas with id " + id + " no longer exists.", enfe);
            }
            Centrodecusto IDCentroDeCusto = despesas.getIDCentroDeCusto();
            if (IDCentroDeCusto != null) {
                IDCentroDeCusto.getDespesasList().remove(despesas);
                IDCentroDeCusto = em.merge(IDCentroDeCusto);
            }
            Metodopagamento IDMetodoPagamento = despesas.getIDMetodoPagamento();
            if (IDMetodoPagamento != null) {
                IDMetodoPagamento.getDespesasList().remove(despesas);
                IDMetodoPagamento = em.merge(IDMetodoPagamento);
            }
            Unidadecusto IDUnidadeCusto = despesas.getIDUnidadeCusto();
            if (IDUnidadeCusto != null) {
                IDUnidadeCusto.getDespesasList().remove(despesas);
                IDUnidadeCusto = em.merge(IDUnidadeCusto);
            }
            em.remove(despesas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Despesas> findDespesasEntities() {
        return findDespesasEntities(true, -1, -1);
    }

    public List<Despesas> findDespesasEntities(int maxResults, int firstResult) {
        return findDespesasEntities(false, maxResults, firstResult);
    }

    private List<Despesas> findDespesasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Despesas.class));
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

    public Despesas findDespesas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Despesas.class, id);
        } finally {
            em.close();
        }
    }

    public int getDespesasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Despesas> rt = cq.from(Despesas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
