/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author owlau
 */
@Entity
@Table(name = "centrodecusto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Centrodecusto.findAll", query = "SELECT c FROM Centrodecusto c")
    , @NamedQuery(name = "Centrodecusto.findByIDCentroDeCusto", query = "SELECT c FROM Centrodecusto c WHERE c.iDCentroDeCusto = :iDCentroDeCusto")
    , @NamedQuery(name = "Centrodecusto.findByDescricao", query = "SELECT c FROM Centrodecusto c WHERE c.descricao = :descricao")})
public class Centrodecusto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCentroDeCusto")
    private Integer iDCentroDeCusto;
    @Column(name = "Descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDCentroDeCusto")
    private List<Despesas> despesasList;

    public Centrodecusto() {
    }

    public Centrodecusto(Integer iDCentroDeCusto) {
        this.iDCentroDeCusto = iDCentroDeCusto;
    }

    public Integer getIDCentroDeCusto() {
        return iDCentroDeCusto;
    }

    public void setIDCentroDeCusto(Integer iDCentroDeCusto) {
        this.iDCentroDeCusto = iDCentroDeCusto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<Despesas> getDespesasList() {
        return despesasList;
    }

    public void setDespesasList(List<Despesas> despesasList) {
        this.despesasList = despesasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDCentroDeCusto != null ? iDCentroDeCusto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Centrodecusto)) {
            return false;
        }
        Centrodecusto other = (Centrodecusto) object;
        if ((this.iDCentroDeCusto == null && other.iDCentroDeCusto != null) || (this.iDCentroDeCusto != null && !this.iDCentroDeCusto.equals(other.iDCentroDeCusto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Centrodecusto[ iDCentroDeCusto=" + iDCentroDeCusto + " ]";
    }
    
}
