package DAO;

import ControleJPA.ClientesJpaController;
import ControleJPA.exceptions.IllegalOrphanException;
import ControleJPA.exceptions.NonexistentEntityException;
import Entidades.Clientes;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ClienteDAO {
    private final ClientesJpaController ControleClientes;
    private final EntityManagerFactory emf;
   public ClienteDAO(){
    emf=Persistence.createEntityManagerFactory("DpaulaContabilidadePU");
    ControleClientes=new ClientesJpaController(emf);
    }
   public void adicionarCliente (Clientes cliente){
       ControleClientes.create(cliente);       
   }
   public void alterarCliente (Clientes cliente) throws Exception{
       ControleClientes.edit(cliente);
   }
   public void removerCliente (int idCliente) throws NonexistentEntityException, IllegalOrphanException{
       ControleClientes.destroy(idCliente);
   }
   public List<Clientes> pegartodosClientes(){
     return ControleClientes.findClientesEntities();
   }
   public Clientes pegarClientesPorID(int IdCliente){
       return ControleClientes.findClientes(IdCliente);
   }
}
