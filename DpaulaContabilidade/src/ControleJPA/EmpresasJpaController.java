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
import Entidades.Clientes;
import Entidades.Empresas;
import Entidades.Receitas;
import java.util.ArrayList;
import java.util.List;
import Entidades.Receitamensal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author owlau
 */
public class EmpresasJpaController implements Serializable {

    public EmpresasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empresas empresas) {
        if (empresas.getReceitasList() == null) {
            empresas.setReceitasList(new ArrayList<Receitas>());
        }
        if (empresas.getReceitamensalList() == null) {
            empresas.setReceitamensalList(new ArrayList<Receitamensal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes IDCliente = empresas.getIDCliente();
            if (IDCliente != null) {
                IDCliente = em.getReference(IDCliente.getClass(), IDCliente.getIDCliente());
                empresas.setIDCliente(IDCliente);
            }
            List<Receitas> attachedReceitasList = new ArrayList<Receitas>();
            for (Receitas receitasListReceitasToAttach : empresas.getReceitasList()) {
                receitasListReceitasToAttach = em.getReference(receitasListReceitasToAttach.getClass(), receitasListReceitasToAttach.getIDServicoPrestado());
                attachedReceitasList.add(receitasListReceitasToAttach);
            }
            empresas.setReceitasList(attachedReceitasList);
            List<Receitamensal> attachedReceitamensalList = new ArrayList<Receitamensal>();
            for (Receitamensal receitamensalListReceitamensalToAttach : empresas.getReceitamensalList()) {
                receitamensalListReceitamensalToAttach = em.getReference(receitamensalListReceitamensalToAttach.getClass(), receitamensalListReceitamensalToAttach.getIdReceitaMensal());
                attachedReceitamensalList.add(receitamensalListReceitamensalToAttach);
            }
            empresas.setReceitamensalList(attachedReceitamensalList);
            em.persist(empresas);
            if (IDCliente != null) {
                IDCliente.getEmpresasList().add(empresas);
                IDCliente = em.merge(IDCliente);
            }
            for (Receitas receitasListReceitas : empresas.getReceitasList()) {
                Empresas oldIDEmpresaOfReceitasListReceitas = receitasListReceitas.getIDEmpresa();
                receitasListReceitas.setIDEmpresa(empresas);
                receitasListReceitas = em.merge(receitasListReceitas);
                if (oldIDEmpresaOfReceitasListReceitas != null) {
                    oldIDEmpresaOfReceitasListReceitas.getReceitasList().remove(receitasListReceitas);
                    oldIDEmpresaOfReceitasListReceitas = em.merge(oldIDEmpresaOfReceitasListReceitas);
                }
            }
            for (Receitamensal receitamensalListReceitamensal : empresas.getReceitamensalList()) {
                Empresas oldIDEmpresaOfReceitamensalListReceitamensal = receitamensalListReceitamensal.getIDEmpresa();
                receitamensalListReceitamensal.setIDEmpresa(empresas);
                receitamensalListReceitamensal = em.merge(receitamensalListReceitamensal);
                if (oldIDEmpresaOfReceitamensalListReceitamensal != null) {
                    oldIDEmpresaOfReceitamensalListReceitamensal.getReceitamensalList().remove(receitamensalListReceitamensal);
                    oldIDEmpresaOfReceitamensalListReceitamensal = em.merge(oldIDEmpresaOfReceitamensalListReceitamensal);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empresas empresas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresas persistentEmpresas = em.find(Empresas.class, empresas.getIDEmpresa());
            Clientes IDClienteOld = persistentEmpresas.getIDCliente();
            Clientes IDClienteNew = empresas.getIDCliente();
            List<Receitas> receitasListOld = persistentEmpresas.getReceitasList();
            List<Receitas> receitasListNew = empresas.getReceitasList();
            List<Receitamensal> receitamensalListOld = persistentEmpresas.getReceitamensalList();
            List<Receitamensal> receitamensalListNew = empresas.getReceitamensalList();
            List<String> illegalOrphanMessages = null;
            for (Receitamensal receitamensalListOldReceitamensal : receitamensalListOld) {
                if (!receitamensalListNew.contains(receitamensalListOldReceitamensal)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Receitamensal " + receitamensalListOldReceitamensal + " since its IDEmpresa field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (IDClienteNew != null) {
                IDClienteNew = em.getReference(IDClienteNew.getClass(), IDClienteNew.getIDCliente());
                empresas.setIDCliente(IDClienteNew);
            }
            List<Receitas> attachedReceitasListNew = new ArrayList<Receitas>();
            for (Receitas receitasListNewReceitasToAttach : receitasListNew) {
                receitasListNewReceitasToAttach = em.getReference(receitasListNewReceitasToAttach.getClass(), receitasListNewReceitasToAttach.getIDServicoPrestado());
                attachedReceitasListNew.add(receitasListNewReceitasToAttach);
            }
            receitasListNew = attachedReceitasListNew;
            empresas.setReceitasList(receitasListNew);
            List<Receitamensal> attachedReceitamensalListNew = new ArrayList<Receitamensal>();
            for (Receitamensal receitamensalListNewReceitamensalToAttach : receitamensalListNew) {
                receitamensalListNewReceitamensalToAttach = em.getReference(receitamensalListNewReceitamensalToAttach.getClass(), receitamensalListNewReceitamensalToAttach.getIdReceitaMensal());
                attachedReceitamensalListNew.add(receitamensalListNewReceitamensalToAttach);
            }
            receitamensalListNew = attachedReceitamensalListNew;
            empresas.setReceitamensalList(receitamensalListNew);
            empresas = em.merge(empresas);
            if (IDClienteOld != null && !IDClienteOld.equals(IDClienteNew)) {
                IDClienteOld.getEmpresasList().remove(empresas);
                IDClienteOld = em.merge(IDClienteOld);
            }
            if (IDClienteNew != null && !IDClienteNew.equals(IDClienteOld)) {
                IDClienteNew.getEmpresasList().add(empresas);
                IDClienteNew = em.merge(IDClienteNew);
            }
            for (Receitas receitasListOldReceitas : receitasListOld) {
                if (!receitasListNew.contains(receitasListOldReceitas)) {
                    receitasListOldReceitas.setIDEmpresa(null);
                    receitasListOldReceitas = em.merge(receitasListOldReceitas);
                }
            }
            for (Receitas receitasListNewReceitas : receitasListNew) {
                if (!receitasListOld.contains(receitasListNewReceitas)) {
                    Empresas oldIDEmpresaOfReceitasListNewReceitas = receitasListNewReceitas.getIDEmpresa();
                    receitasListNewReceitas.setIDEmpresa(empresas);
                    receitasListNewReceitas = em.merge(receitasListNewReceitas);
                    if (oldIDEmpresaOfReceitasListNewReceitas != null && !oldIDEmpresaOfReceitasListNewReceitas.equals(empresas)) {
                        oldIDEmpresaOfReceitasListNewReceitas.getReceitasList().remove(receitasListNewReceitas);
                        oldIDEmpresaOfReceitasListNewReceitas = em.merge(oldIDEmpresaOfReceitasListNewReceitas);
                    }
                }
            }
            for (Receitamensal receitamensalListNewReceitamensal : receitamensalListNew) {
                if (!receitamensalListOld.contains(receitamensalListNewReceitamensal)) {
                    Empresas oldIDEmpresaOfReceitamensalListNewReceitamensal = receitamensalListNewReceitamensal.getIDEmpresa();
                    receitamensalListNewReceitamensal.setIDEmpresa(empresas);
                    receitamensalListNewReceitamensal = em.merge(receitamensalListNewReceitamensal);
                    if (oldIDEmpresaOfReceitamensalListNewReceitamensal != null && !oldIDEmpresaOfReceitamensalListNewReceitamensal.equals(empresas)) {
                        oldIDEmpresaOfReceitamensalListNewReceitamensal.getReceitamensalList().remove(receitamensalListNewReceitamensal);
                        oldIDEmpresaOfReceitamensalListNewReceitamensal = em.merge(oldIDEmpresaOfReceitamensalListNewReceitamensal);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empresas.getIDEmpresa();
                if (findEmpresas(id) == null) {
                    throw new NonexistentEntityException("The empresas with id " + id + " no longer exists.");
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
            Empresas empresas;
            try {
                empresas = em.getReference(Empresas.class, id);
                empresas.getIDEmpresa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empresas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Receitamensal> receitamensalListOrphanCheck = empresas.getReceitamensalList();
            for (Receitamensal receitamensalListOrphanCheckReceitamensal : receitamensalListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empresas (" + empresas + ") cannot be destroyed since the Receitamensal " + receitamensalListOrphanCheckReceitamensal + " in its receitamensalList field has a non-nullable IDEmpresa field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Clientes IDCliente = empresas.getIDCliente();
            if (IDCliente != null) {
                IDCliente.getEmpresasList().remove(empresas);
                IDCliente = em.merge(IDCliente);
            }
            List<Receitas> receitasList = empresas.getReceitasList();
            for (Receitas receitasListReceitas : receitasList) {
                receitasListReceitas.setIDEmpresa(null);
                receitasListReceitas = em.merge(receitasListReceitas);
            }
            em.remove(empresas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empresas> findEmpresasEntities() {
        return findEmpresasEntities(true, -1, -1);
    }

    public List<Empresas> findEmpresasEntities(int maxResults, int firstResult) {
        return findEmpresasEntities(false, maxResults, firstResult);
    }

    private List<Empresas> findEmpresasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empresas.class));
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

    public Empresas findEmpresas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empresas.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpresasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empresas> rt = cq.from(Empresas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
