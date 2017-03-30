/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControleJPA;

import ControleJPA.exceptions.IllegalOrphanException;
import ControleJPA.exceptions.NonexistentEntityException;
import Entidades.Clientes;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Empresas;
import java.util.ArrayList;
import java.util.List;
import Entidades.Receitas;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author owlau
 */
public class ClientesJpaController implements Serializable {

    public ClientesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Clientes clientes) {
        if (clientes.getEmpresasList() == null) {
            clientes.setEmpresasList(new ArrayList<Empresas>());
        }
        if (clientes.getReceitasList() == null) {
            clientes.setReceitasList(new ArrayList<Receitas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Empresas> attachedEmpresasList = new ArrayList<Empresas>();
            for (Empresas empresasListEmpresasToAttach : clientes.getEmpresasList()) {
                empresasListEmpresasToAttach = em.getReference(empresasListEmpresasToAttach.getClass(), empresasListEmpresasToAttach.getIDEmpresa());
                attachedEmpresasList.add(empresasListEmpresasToAttach);
            }
            clientes.setEmpresasList(attachedEmpresasList);
            List<Receitas> attachedReceitasList = new ArrayList<Receitas>();
            for (Receitas receitasListReceitasToAttach : clientes.getReceitasList()) {
                receitasListReceitasToAttach = em.getReference(receitasListReceitasToAttach.getClass(), receitasListReceitasToAttach.getIDServicoPrestado());
                attachedReceitasList.add(receitasListReceitasToAttach);
            }
            clientes.setReceitasList(attachedReceitasList);
            em.persist(clientes);
            for (Empresas empresasListEmpresas : clientes.getEmpresasList()) {
                Clientes oldIDClienteOfEmpresasListEmpresas = empresasListEmpresas.getIDCliente();
                empresasListEmpresas.setIDCliente(clientes);
                empresasListEmpresas = em.merge(empresasListEmpresas);
                if (oldIDClienteOfEmpresasListEmpresas != null) {
                    oldIDClienteOfEmpresasListEmpresas.getEmpresasList().remove(empresasListEmpresas);
                    oldIDClienteOfEmpresasListEmpresas = em.merge(oldIDClienteOfEmpresasListEmpresas);
                }
            }
            for (Receitas receitasListReceitas : clientes.getReceitasList()) {
                Clientes oldIDClienteOfReceitasListReceitas = receitasListReceitas.getIDCliente();
                receitasListReceitas.setIDCliente(clientes);
                receitasListReceitas = em.merge(receitasListReceitas);
                if (oldIDClienteOfReceitasListReceitas != null) {
                    oldIDClienteOfReceitasListReceitas.getReceitasList().remove(receitasListReceitas);
                    oldIDClienteOfReceitasListReceitas = em.merge(oldIDClienteOfReceitasListReceitas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Clientes clientes) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes persistentClientes = em.find(Clientes.class, clientes.getIDCliente());
            List<Empresas> empresasListOld = persistentClientes.getEmpresasList();
            List<Empresas> empresasListNew = clientes.getEmpresasList();
            List<Receitas> receitasListOld = persistentClientes.getReceitasList();
            List<Receitas> receitasListNew = clientes.getReceitasList();
            List<String> illegalOrphanMessages = null;
            for (Empresas empresasListOldEmpresas : empresasListOld) {
                if (!empresasListNew.contains(empresasListOldEmpresas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Empresas " + empresasListOldEmpresas + " since its IDCliente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Empresas> attachedEmpresasListNew = new ArrayList<Empresas>();
            for (Empresas empresasListNewEmpresasToAttach : empresasListNew) {
                empresasListNewEmpresasToAttach = em.getReference(empresasListNewEmpresasToAttach.getClass(), empresasListNewEmpresasToAttach.getIDEmpresa());
                attachedEmpresasListNew.add(empresasListNewEmpresasToAttach);
            }
            empresasListNew = attachedEmpresasListNew;
            clientes.setEmpresasList(empresasListNew);
            List<Receitas> attachedReceitasListNew = new ArrayList<Receitas>();
            for (Receitas receitasListNewReceitasToAttach : receitasListNew) {
                receitasListNewReceitasToAttach = em.getReference(receitasListNewReceitasToAttach.getClass(), receitasListNewReceitasToAttach.getIDServicoPrestado());
                attachedReceitasListNew.add(receitasListNewReceitasToAttach);
            }
            receitasListNew = attachedReceitasListNew;
            clientes.setReceitasList(receitasListNew);
            clientes = em.merge(clientes);
            for (Empresas empresasListNewEmpresas : empresasListNew) {
                if (!empresasListOld.contains(empresasListNewEmpresas)) {
                    Clientes oldIDClienteOfEmpresasListNewEmpresas = empresasListNewEmpresas.getIDCliente();
                    empresasListNewEmpresas.setIDCliente(clientes);
                    empresasListNewEmpresas = em.merge(empresasListNewEmpresas);
                    if (oldIDClienteOfEmpresasListNewEmpresas != null && !oldIDClienteOfEmpresasListNewEmpresas.equals(clientes)) {
                        oldIDClienteOfEmpresasListNewEmpresas.getEmpresasList().remove(empresasListNewEmpresas);
                        oldIDClienteOfEmpresasListNewEmpresas = em.merge(oldIDClienteOfEmpresasListNewEmpresas);
                    }
                }
            }
            for (Receitas receitasListOldReceitas : receitasListOld) {
                if (!receitasListNew.contains(receitasListOldReceitas)) {
                    receitasListOldReceitas.setIDCliente(null);
                    receitasListOldReceitas = em.merge(receitasListOldReceitas);
                }
            }
            for (Receitas receitasListNewReceitas : receitasListNew) {
                if (!receitasListOld.contains(receitasListNewReceitas)) {
                    Clientes oldIDClienteOfReceitasListNewReceitas = receitasListNewReceitas.getIDCliente();
                    receitasListNewReceitas.setIDCliente(clientes);
                    receitasListNewReceitas = em.merge(receitasListNewReceitas);
                    if (oldIDClienteOfReceitasListNewReceitas != null && !oldIDClienteOfReceitasListNewReceitas.equals(clientes)) {
                        oldIDClienteOfReceitasListNewReceitas.getReceitasList().remove(receitasListNewReceitas);
                        oldIDClienteOfReceitasListNewReceitas = em.merge(oldIDClienteOfReceitasListNewReceitas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = clientes.getIDCliente();
                if (findClientes(id) == null) {
                    throw new NonexistentEntityException("The clientes with id " + id + " no longer exists.");
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
            Clientes clientes;
            try {
                clientes = em.getReference(Clientes.class, id);
                clientes.getIDCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clientes with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Empresas> empresasListOrphanCheck = clientes.getEmpresasList();
            for (Empresas empresasListOrphanCheckEmpresas : empresasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Clientes (" + clientes + ") cannot be destroyed since the Empresas " + empresasListOrphanCheckEmpresas + " in its empresasList field has a non-nullable IDCliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Receitas> receitasList = clientes.getReceitasList();
            for (Receitas receitasListReceitas : receitasList) {
                receitasListReceitas.setIDCliente(null);
                receitasListReceitas = em.merge(receitasListReceitas);
            }
            em.remove(clientes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Clientes> findClientesEntities() {
        return findClientesEntities(true, -1, -1);
    }

    public List<Clientes> findClientesEntities(int maxResults, int firstResult) {
        return findClientesEntities(false, maxResults, firstResult);
    }

    private List<Clientes> findClientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Clientes.class));
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

    public Clientes findClientes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Clientes.class, id);
        } finally {
            em.close();
        }
    }

    public int getClientesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Clientes> rt = cq.from(Clientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
