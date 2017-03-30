/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author owlau
 */
@Entity
@Table(name = "receitamensal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Receitamensal.findAll", query = "SELECT r FROM Receitamensal r")
    , @NamedQuery(name = "Receitamensal.findByIdReceitaMensal", query = "SELECT r FROM Receitamensal r WHERE r.idReceitaMensal = :idReceitaMensal")
    , @NamedQuery(name = "Receitamensal.findByReceitaBrutaMensal", query = "SELECT r FROM Receitamensal r WHERE r.receitaBrutaMensal = :receitaBrutaMensal")
    , @NamedQuery(name = "Receitamensal.findByData", query = "SELECT r FROM Receitamensal r WHERE r.data = :data")
    , @NamedQuery(name = "Receitamensal.findByCustosDespesas", query = "SELECT r FROM Receitamensal r WHERE r.custosDespesas = :custosDespesas")})
public class Receitamensal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idReceitaMensal")
    private Integer idReceitaMensal;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ReceitaBrutaMensal")
    private BigDecimal receitaBrutaMensal;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Column(name = "Custos&Despesas")
    private BigDecimal custosDespesas;
    @JoinColumn(name = "IDEmpresa", referencedColumnName = "IDEmpresa")
    @ManyToOne(optional = false)
    private Empresas iDEmpresa;

    public Receitamensal() {
    }

    public Receitamensal(Integer idReceitaMensal) {
        this.idReceitaMensal = idReceitaMensal;
    }

    public Integer getIdReceitaMensal() {
        return idReceitaMensal;
    }

    public void setIdReceitaMensal(Integer idReceitaMensal) {
        this.idReceitaMensal = idReceitaMensal;
    }

    public BigDecimal getReceitaBrutaMensal() {
        return receitaBrutaMensal;
    }

    public void setReceitaBrutaMensal(BigDecimal receitaBrutaMensal) {
        this.receitaBrutaMensal = receitaBrutaMensal;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getCustosDespesas() {
        return custosDespesas;
    }

    public void setCustosDespesas(BigDecimal custosDespesas) {
        this.custosDespesas = custosDespesas;
    }

    public Empresas getIDEmpresa() {
        return iDEmpresa;
    }

    public void setIDEmpresa(Empresas iDEmpresa) {
        this.iDEmpresa = iDEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReceitaMensal != null ? idReceitaMensal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Receitamensal)) {
            return false;
        }
        Receitamensal other = (Receitamensal) object;
        if ((this.idReceitaMensal == null && other.idReceitaMensal != null) || (this.idReceitaMensal != null && !this.idReceitaMensal.equals(other.idReceitaMensal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Receitamensal[ idReceitaMensal=" + idReceitaMensal + " ]";
    }
    
}
