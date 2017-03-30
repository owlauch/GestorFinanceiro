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
@Table(name = "receitas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Receitas.findAll", query = "SELECT r FROM Receitas r")
    , @NamedQuery(name = "Receitas.findByIDServicoPrestado", query = "SELECT r FROM Receitas r WHERE r.iDServicoPrestado = :iDServicoPrestado")
    , @NamedQuery(name = "Receitas.findByValor", query = "SELECT r FROM Receitas r WHERE r.valor = :valor")
    , @NamedQuery(name = "Receitas.findByDataPrestacaoServico", query = "SELECT r FROM Receitas r WHERE r.dataPrestacaoServico = :dataPrestacaoServico")})
public class Receitas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDServicoPrestado")
    private Integer iDServicoPrestado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "Valor")
    private BigDecimal valor;
    @Basic(optional = false)
    @Column(name = "DataPrestacaoServico")
    @Temporal(TemporalType.DATE)
    private Date dataPrestacaoServico;
    @JoinColumn(name = "IDMetodoPagamento", referencedColumnName = "IDMetodoPagamento")
    @ManyToOne(optional = false)
    private Metodopagamento iDMetodoPagamento;
    @JoinColumn(name = "IDCliente", referencedColumnName = "IDCliente")
    @ManyToOne
    private Clientes iDCliente;
    @JoinColumn(name = "IDEmpresa", referencedColumnName = "IDEmpresa")
    @ManyToOne
    private Empresas iDEmpresa;
    @JoinColumn(name = "IDServico", referencedColumnName = "IDServico")
    @ManyToOne(optional = false)
    private Servicos iDServico;

    public Receitas() {
    }

    public Receitas(Integer iDServicoPrestado) {
        this.iDServicoPrestado = iDServicoPrestado;
    }

    public Receitas(Integer iDServicoPrestado, BigDecimal valor, Date dataPrestacaoServico) {
        this.iDServicoPrestado = iDServicoPrestado;
        this.valor = valor;
        this.dataPrestacaoServico = dataPrestacaoServico;
    }

    public Integer getIDServicoPrestado() {
        return iDServicoPrestado;
    }

    public void setIDServicoPrestado(Integer iDServicoPrestado) {
        this.iDServicoPrestado = iDServicoPrestado;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getDataPrestacaoServico() {
        return dataPrestacaoServico;
    }

    public void setDataPrestacaoServico(Date dataPrestacaoServico) {
        this.dataPrestacaoServico = dataPrestacaoServico;
    }

    public Metodopagamento getIDMetodoPagamento() {
        return iDMetodoPagamento;
    }

    public void setIDMetodoPagamento(Metodopagamento iDMetodoPagamento) {
        this.iDMetodoPagamento = iDMetodoPagamento;
    }

    public Clientes getIDCliente() {
        return iDCliente;
    }

    public void setIDCliente(Clientes iDCliente) {
        this.iDCliente = iDCliente;
    }

    public Empresas getIDEmpresa() {
        return iDEmpresa;
    }

    public void setIDEmpresa(Empresas iDEmpresa) {
        this.iDEmpresa = iDEmpresa;
    }

    public Servicos getIDServico() {
        return iDServico;
    }

    public void setIDServico(Servicos iDServico) {
        this.iDServico = iDServico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDServicoPrestado != null ? iDServicoPrestado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Receitas)) {
            return false;
        }
        Receitas other = (Receitas) object;
        if ((this.iDServicoPrestado == null && other.iDServicoPrestado != null) || (this.iDServicoPrestado != null && !this.iDServicoPrestado.equals(other.iDServicoPrestado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Receitas[ iDServicoPrestado=" + iDServicoPrestado + " ]";
    }
    
}
