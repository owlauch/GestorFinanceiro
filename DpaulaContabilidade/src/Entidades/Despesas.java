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
@Table(name = "despesas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Despesas.findAll", query = "SELECT d FROM Despesas d")
    , @NamedQuery(name = "Despesas.findByIDDespesas", query = "SELECT d FROM Despesas d WHERE d.iDDespesas = :iDDespesas")
    , @NamedQuery(name = "Despesas.findByDescricao", query = "SELECT d FROM Despesas d WHERE d.descricao = :descricao")
    , @NamedQuery(name = "Despesas.findByData", query = "SELECT d FROM Despesas d WHERE d.data = :data")
    , @NamedQuery(name = "Despesas.findByValor", query = "SELECT d FROM Despesas d WHERE d.valor = :valor")})
public class Despesas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDDespesas")
    private Integer iDDespesas;
    @Column(name = "Descricao")
    private String descricao;
    @Column(name = "Data")
    @Temporal(TemporalType.DATE)
    private Date data;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "Valor")
    private BigDecimal valor;
    @JoinColumn(name = "IDCentroDeCusto", referencedColumnName = "IDCentroDeCusto")
    @ManyToOne(optional = false)
    private Centrodecusto iDCentroDeCusto;
    @JoinColumn(name = "IDMetodoPagamento", referencedColumnName = "IDMetodoPagamento")
    @ManyToOne(optional = false)
    private Metodopagamento iDMetodoPagamento;
    @JoinColumn(name = "IDUnidadeCusto", referencedColumnName = "IDUnidadeCusto")
    @ManyToOne(optional = false)
    private Unidadecusto iDUnidadeCusto;

    public Despesas() {
    }

    public Despesas(Integer iDDespesas) {
        this.iDDespesas = iDDespesas;
    }

    public Despesas(Integer iDDespesas, BigDecimal valor) {
        this.iDDespesas = iDDespesas;
        this.valor = valor;
    }

    public Integer getIDDespesas() {
        return iDDespesas;
    }

    public void setIDDespesas(Integer iDDespesas) {
        this.iDDespesas = iDDespesas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Centrodecusto getIDCentroDeCusto() {
        return iDCentroDeCusto;
    }

    public void setIDCentroDeCusto(Centrodecusto iDCentroDeCusto) {
        this.iDCentroDeCusto = iDCentroDeCusto;
    }

    public Metodopagamento getIDMetodoPagamento() {
        return iDMetodoPagamento;
    }

    public void setIDMetodoPagamento(Metodopagamento iDMetodoPagamento) {
        this.iDMetodoPagamento = iDMetodoPagamento;
    }

    public Unidadecusto getIDUnidadeCusto() {
        return iDUnidadeCusto;
    }

    public void setIDUnidadeCusto(Unidadecusto iDUnidadeCusto) {
        this.iDUnidadeCusto = iDUnidadeCusto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDDespesas != null ? iDDespesas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Despesas)) {
            return false;
        }
        Despesas other = (Despesas) object;
        if ((this.iDDespesas == null && other.iDDespesas != null) || (this.iDDespesas != null && !this.iDDespesas.equals(other.iDDespesas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Despesas[ iDDespesas=" + iDDespesas + " ]";
    }
    
}
