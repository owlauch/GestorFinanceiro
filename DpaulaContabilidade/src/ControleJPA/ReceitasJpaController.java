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
import Entidades.Metodopagamento;
import Entidades.Clientes;
import Entidades.Empresas;
import Entidades.Receitas;
import Entidades.Servicos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author owlau
 */
public class ReceitasJpaController implements Serializable {

    public ReceitasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Receitas receitas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Metodopagamento IDMetodoPagamento = receitas.getIDMetodoPagamento();
            if (IDMetodoPagamento != null) {
                IDMetodoPagamento = em.getReference(IDMetodoPagamento.getClass(), IDMetodoPagamento.getIDMetodoPagamento());
                receitas.setIDMetodoPagamento(IDMetodoPagamento);
            }
            Clientes IDCliente = receitas.getIDCliente();
            if (IDCliente != null) {
                IDCliente = em.getReference(IDCliente.getClass(), IDCliente.getIDCliente());
                receitas.setIDCliente(IDCliente);
            }
            Empresas IDEmpresa = receitas.getIDEmpresa();
            if (IDEmpresa != null) {
                IDEmpresa = em.getReference(IDEmpresa.getClass(), IDEmpresa.getIDEmpresa());
                receitas.setIDEmpresa(IDEmpresa);
            }
            Servicos IDServico = receitas.getIDServico();
            if (IDServico != null) {
                IDServico = em.getReference(IDServico.getClass(), IDServico.getIDServico());
                receitas.setIDServico(IDServico);
            }
            em.persist(receitas);
            if (IDMetodoPagamento != null) {
                IDMetodoPagamento.getReceitasList().add(receitas);
                IDMetodoPagamento = em.merge(IDMetodoPagamento);
            }
            if (IDCliente != null) {
                IDCliente.getReceitasList().add(receitas);
                IDCliente = em.merge(IDCliente);
            }
            if (IDEmpresa != null) {
                IDEmpresa.getReceitasList().add(receitas);
                IDEmpresa = em.merge(IDEmpresa);
            }
            if (IDServico != null) {
                IDServico.getReceitasList().add(receitas);
                IDServico = em.merge(IDServico);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Receitas receitas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Receitas persistentReceitas = em.find(Receitas.class, receitas.getIDServicoPrestado());
            Metodopagamento IDMetodoPagamentoOld = persistentReceitas.getIDMetodoPagamento();
            Metodopagamento IDMetodoPagamentoNew = receitas.getIDMetodoPagamento();
            Clientes IDClienteOld = persistentReceitas.getIDCliente();
            Clientes IDClienteNew = receitas.getIDCliente();
            Empresas IDEmpresaOld = persistentReceitas.getIDEmpresa();
            Empresas IDEmpresaNew = receitas.getIDEmpresa();
            Servicos IDServicoOld = persistentReceitas.getIDServico();
            Servicos IDServicoNew = receitas.getIDServico();
            if (IDMetodoPagamentoNew != null) {
                IDMetodoPagamentoNew = em.getReference(IDMetodoPagamentoNew.getClass(), IDMetodoPagamentoNew.getIDMetodoPagamento());
                receitas.setIDMetodoPagamento(IDMetodoPagamentoNew);
            }
            if (IDClienteNew != null) {
                IDClienteNew = em.getReference(IDClienteNew.getClass(), IDClienteNew.getIDCliente());
                receitas.setIDCliente(IDClienteNew);
            }
            if (IDEmpresaNew != null) {
                IDEmpresaNew = em.getReference(IDEmpresaNew.getClass(), IDEmpresaNew.getIDEmpresa());
                receitas.setIDEmpresa(IDEmpresaNew);
            }
            if (IDServicoNew != null) {
                IDServicoNew = em.getReference(IDServicoNew.getClass(), IDServicoNew.getIDServico());
                receitas.setIDServico(IDServicoNew);
            }
            receitas = em.merge(receitas);
            if (IDMetodoPagamentoOld != null && !IDMetodoPagamentoOld.equals(IDMetodoPagamentoNew)) {
                IDMetodoPagamentoOld.getReceitasList().remove(receitas);
                IDMetodoPagamentoOld = em.merge(IDMetodoPagamentoOld);
            }
            if (IDMetodoPagamentoNew != null && !IDMetodoPagamentoNew.equals(IDMetodoPagamentoOld)) {
                IDMetodoPagamentoNew.getReceitasList().add(receitas);
                IDMetodoPagamentoNew = em.merge(IDMetodoPagamentoNew);
            }
            if (IDClienteOld != null && !IDClienteOld.equals(IDClienteNew)) {
                IDClienteOld.getReceitasList().remove(receitas);
                IDClienteOld = em.merge(IDClienteOld);
            }
            if (IDClienteNew != null && !IDClienteNew.equals(IDClienteOld)) {
                IDClienteNew.getReceitasList().add(receitas);
                IDClienteNew = em.merge(IDClienteNew);
            }
            if (IDEmpresaOld != null && !IDEmpresaOld.equals(IDEmpresaNew)) {
                IDEmpresaOld.getReceitasList().remove(receitas);
                IDEmpresaOld = em.merge(IDEmpresaOld);
            }
            if (IDEmpresaNew != null && !IDEmpresaNew.equals(IDEmpresaOld)) {
                IDEmpresaNew.getReceitasList().add(receitas);
                IDEmpresaNew = em.merge(IDEmpresaNew);
            }
            if (IDServicoOld != null && !IDServicoOld.equals(IDServicoNew)) {
                IDServicoOld.getReceitasList().remove(receitas);
                IDServicoOld = em.merge(IDServicoOld);
            }
            if (IDServicoNew != null && !IDServicoNew.equals(IDServicoOld)) {
                IDServicoNew.getReceitasList().add(receitas);
                IDServicoNew = em.merge(IDServicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = receitas.getIDServicoPrestado();
                if (findReceitas(id) == null) {
                    throw new NonexistentEntityException("The receitas with id " + id + " no longer exists.");
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
            Receitas receitas;
            try {
                receitas = em.getReference(Receitas.class, id);
                receitas.getIDServicoPrestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The receitas with id " + id + " no longer exists.", enfe);
            }
            Metodopagamento IDMetodoPagamento = receitas.getIDMetodoPagamento();
            if (IDMetodoPagamento != null) {
                IDMetodoPagamento.getReceitasList().remove(receitas);
                IDMetodoPagamento = em.merge(IDMetodoPagamento);
            }
            Clientes IDCliente = receitas.getIDCliente();
            if (IDCliente != null) {
                IDCliente.getReceitasList().remove(receitas);
                IDCliente = em.merge(IDCliente);
            }
            Empresas IDEmpresa = receitas.getIDEmpresa();
            if (IDEmpresa != null) {
                IDEmpresa.getReceitasList().remove(receitas);
                IDEmpresa = em.merge(IDEmpresa);
            }
            Servicos IDServico = receitas.getIDServico();
            if (IDServico != null) {
                IDServico.getReceitasList().remove(receitas);
                IDServico = em.merge(IDServico);
            }
            em.remove(receitas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Receitas> findReceitasEntities() {
        return findReceitasEntities(true, -1, -1);
    }

    public List<Receitas> findReceitasEntities(int maxResults, int firstResult) {
        return findReceitasEntities(false, maxResults, firstResult);
    }

    private List<Receitas> findReceitasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Receitas.class));
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

    public Receitas findReceitas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Receitas.class, id);
        } finally {
            em.close();
        }
    }

    public int getReceitasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Receitas> rt = cq.from(Receitas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
