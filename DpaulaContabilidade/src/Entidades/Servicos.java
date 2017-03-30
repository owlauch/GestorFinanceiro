/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "servicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servicos.findAll", query = "SELECT s FROM Servicos s")
    , @NamedQuery(name = "Servicos.findByIDServico", query = "SELECT s FROM Servicos s WHERE s.iDServico = :iDServico")
    , @NamedQuery(name = "Servicos.findByDescricao", query = "SELECT s FROM Servicos s WHERE s.descricao = :descricao")
    , @NamedQuery(name = "Servicos.findByValor", query = "SELECT s FROM Servicos s WHERE s.valor = :valor")})
public class Servicos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDServico")
    private Integer iDServico;
    @Basic(optional = false)
    @Column(name = "Descricao")
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Valor")
    private BigDecimal valor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDServico")
    private List<Receitas> receitasList;

    public Servicos() {
    }

    public Servicos(Integer iDServico) {
        this.iDServico = iDServico;
    }

    public Servicos(Integer iDServico, String descricao) {
        this.iDServico = iDServico;
        this.descricao = descricao;
    }

    public Integer getIDServico() {
        return iDServico;
    }

    public void setIDServico(Integer iDServico) {
        this.iDServico = iDServico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @XmlTransient
    public List<Receitas> getReceitasList() {
        return receitasList;
    }

    public void setReceitasList(List<Receitas> receitasList) {
        this.receitasList = receitasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDServico != null ? iDServico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicos)) {
            return false;
        }
        Servicos other = (Servicos) object;
        if ((this.iDServico == null && other.iDServico != null) || (this.iDServico != null && !this.iDServico.equals(other.iDServico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Servicos[ iDServico=" + iDServico + " ]";
    }
    
}
