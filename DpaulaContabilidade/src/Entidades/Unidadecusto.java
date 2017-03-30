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
@Table(name = "unidadecusto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Unidadecusto.findAll", query = "SELECT u FROM Unidadecusto u")
    , @NamedQuery(name = "Unidadecusto.findByIDUnidadeCusto", query = "SELECT u FROM Unidadecusto u WHERE u.iDUnidadeCusto = :iDUnidadeCusto")
    , @NamedQuery(name = "Unidadecusto.findByDescricao", query = "SELECT u FROM Unidadecusto u WHERE u.descricao = :descricao")})
public class Unidadecusto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDUnidadeCusto")
    private Integer iDUnidadeCusto;
    @Column(name = "Descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDUnidadeCusto")
    private List<Despesas> despesasList;

    public Unidadecusto() {
    }

    public Unidadecusto(Integer iDUnidadeCusto) {
        this.iDUnidadeCusto = iDUnidadeCusto;
    }

    public Integer getIDUnidadeCusto() {
        return iDUnidadeCusto;
    }

    public void setIDUnidadeCusto(Integer iDUnidadeCusto) {
        this.iDUnidadeCusto = iDUnidadeCusto;
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
        hash += (iDUnidadeCusto != null ? iDUnidadeCusto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unidadecusto)) {
            return false;
        }
        Unidadecusto other = (Unidadecusto) object;
        if ((this.iDUnidadeCusto == null && other.iDUnidadeCusto != null) || (this.iDUnidadeCusto != null && !this.iDUnidadeCusto.equals(other.iDUnidadeCusto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Unidadecusto[ iDUnidadeCusto=" + iDUnidadeCusto + " ]";
    }
    
}
