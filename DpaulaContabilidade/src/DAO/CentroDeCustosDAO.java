/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ControleJPA.CentrodecustoJpaController;
import ControleJPA.exceptions.IllegalOrphanException;
import ControleJPA.exceptions.NonexistentEntityException;
import Entidades.Centrodecusto;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author owlau
 */
public class CentroDeCustosDAO {

    private final CentrodecustoJpaController ControleCentroCustos;
    private final EntityManagerFactory emf;

    public CentroDeCustosDAO() {
        emf = Persistence.createEntityManagerFactory("DpaulaContabilidadePU");
        ControleCentroCustos = new CentrodecustoJpaController(emf);
    }

    public void adicionarCentrodecusto(Centrodecusto Centrodecusto) throws Exception {
        ControleCentroCustos.create(Centrodecusto);
    }

    public void alterarCentrodecusto(Centrodecusto Centrodecusto) throws NonexistentEntityException, Exception {
        ControleCentroCustos.edit(Centrodecusto);
    }

    public void excluirCentrodecusto(int idCentrodecusto) throws IllegalOrphanException, NonexistentEntityException {
        ControleCentroCustos.destroy(idCentrodecusto);
    }

    public List<Centrodecusto> pegartodosCentrodecusto() {
        return ControleCentroCustos.findCentrodecustoEntities();
    }

    public Centrodecusto pegarCentrodecustoPorID(int idCentrodecusto) {
        return ControleCentroCustos.findCentrodecusto(idCentrodecusto);
    }
}
