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
@Table(name = "metodopagamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Metodopagamento.findAll", query = "SELECT m FROM Metodopagamento m")
    , @NamedQuery(name = "Metodopagamento.findByIDMetodoPagamento", query = "SELECT m FROM Metodopagamento m WHERE m.iDMetodoPagamento = :iDMetodoPagamento")
    , @NamedQuery(name = "Metodopagamento.findByDescri\u00e7\u00e3o", query = "SELECT m FROM Metodopagamento m WHERE m.descri\u00e7\u00e3o = :descri\u00e7\u00e3o")})
public class Metodopagamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDMetodoPagamento")
    private Integer iDMetodoPagamento;
    @Column(name = "Descri\u00e7\u00e3o")
    private String descrição;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDMetodoPagamento")
    private List<Receitas> receitasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDMetodoPagamento")
    private List<Despesas> despesasList;

    public Metodopagamento() {
    }

    public Metodopagamento(Integer iDMetodoPagamento) {
        this.iDMetodoPagamento = iDMetodoPagamento;
    }

    public Integer getIDMetodoPagamento() {
        return iDMetodoPagamento;
    }

    public void setIDMetodoPagamento(Integer iDMetodoPagamento) {
        this.iDMetodoPagamento = iDMetodoPagamento;
    }

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    @XmlTransient
    public List<Receitas> getReceitasList() {
        return receitasList;
    }

    public void setReceitasList(List<Receitas> receitasList) {
        this.receitasList = receitasList;
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
        hash += (iDMetodoPagamento != null ? iDMetodoPagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Metodopagamento)) {
            return false;
        }
        Metodopagamento other = (Metodopagamento) object;
        if ((this.iDMetodoPagamento == null && other.iDMetodoPagamento != null) || (this.iDMetodoPagamento != null && !this.iDMetodoPagamento.equals(other.iDMetodoPagamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Metodopagamento[ iDMetodoPagamento=" + iDMetodoPagamento + " ]";
    }
    
}
