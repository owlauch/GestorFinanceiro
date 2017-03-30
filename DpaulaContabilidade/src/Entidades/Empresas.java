/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author owlau
 */
@Entity
@Table(name = "empresas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresas.findAll", query = "SELECT e FROM Empresas e")
    , @NamedQuery(name = "Empresas.findByIDEmpresa", query = "SELECT e FROM Empresas e WHERE e.iDEmpresa = :iDEmpresa")
    , @NamedQuery(name = "Empresas.findByNome", query = "SELECT e FROM Empresas e WHERE e.nome = :nome")
    , @NamedQuery(name = "Empresas.findByDataAbertura", query = "SELECT e FROM Empresas e WHERE e.dataAbertura = :dataAbertura")
    , @NamedQuery(name = "Empresas.findByCnpj", query = "SELECT e FROM Empresas e WHERE e.cnpj = :cnpj")
    , @NamedQuery(name = "Empresas.findByEmail", query = "SELECT e FROM Empresas e WHERE e.email = :email")
    , @NamedQuery(name = "Empresas.findByTelefone", query = "SELECT e FROM Empresas e WHERE e.telefone = :telefone")
    , @NamedQuery(name = "Empresas.findByCelular", query = "SELECT e FROM Empresas e WHERE e.celular = :celular")
    , @NamedQuery(name = "Empresas.findBySenhaSimplesNacional", query = "SELECT e FROM Empresas e WHERE e.senhaSimplesNacional = :senhaSimplesNacional")
    , @NamedQuery(name = "Empresas.findBySenhaCertificado", query = "SELECT e FROM Empresas e WHERE e.senhaCertificado = :senhaCertificado")
    , @NamedQuery(name = "Empresas.findBySenhaNfe", query = "SELECT e FROM Empresas e WHERE e.senhaNfe = :senhaNfe")
    , @NamedQuery(name = "Empresas.findByValorHonorario", query = "SELECT e FROM Empresas e WHERE e.valorHonorario = :valorHonorario")})
public class Empresas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDEmpresa")
    private Integer iDEmpresa;
    @Column(name = "Nome")
    private String nome;
    @Column(name = "DataAbertura")
    @Temporal(TemporalType.DATE)
    private Date dataAbertura;
    @Column(name = "CNPJ")
    private String cnpj;
    @Column(name = "Email")
    private String email;
    @Column(name = "Telefone")
    private String telefone;
    @Column(name = "Celular")
    private String celular;
    @Column(name = "SenhaSimplesNacional")
    private String senhaSimplesNacional;
    @Column(name = "SenhaCertificado")
    private String senhaCertificado;
    @Column(name = "SenhaNfe")
    private String senhaNfe;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ValorHonorario")
    private BigDecimal valorHonorario;
    @JoinColumn(name = "IDCliente", referencedColumnName = "IDCliente")
    @ManyToOne(optional = false)
    private Clientes iDCliente;
    @OneToMany(mappedBy = "iDEmpresa")
    private List<Receitas> receitasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDEmpresa")
    private List<Receitamensal> receitamensalList;

    public Empresas() {
    }

    public Empresas(Integer iDEmpresa) {
        this.iDEmpresa = iDEmpresa;
    }

    public Integer getIDEmpresa() {
        return iDEmpresa;
    }

    public void setIDEmpresa(Integer iDEmpresa) {
        this.iDEmpresa = iDEmpresa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getSenhaSimplesNacional() {
        return senhaSimplesNacional;
    }

    public void setSenhaSimplesNacional(String senhaSimplesNacional) {
        this.senhaSimplesNacional = senhaSimplesNacional;
    }

    public String getSenhaCertificado() {
        return senhaCertificado;
    }

    public void setSenhaCertificado(String senhaCertificado) {
        this.senhaCertificado = senhaCertificado;
    }

    public String getSenhaNfe() {
        return senhaNfe;
    }

    public void setSenhaNfe(String senhaNfe) {
        this.senhaNfe = senhaNfe;
    }

    public BigDecimal getValorHonorario() {
        return valorHonorario;
    }

    public void setValorHonorario(BigDecimal valorHonorario) {
        this.valorHonorario = valorHonorario;
    }

    public Clientes getIDCliente() {
        return iDCliente;
    }

    public void setIDCliente(Clientes iDCliente) {
        this.iDCliente = iDCliente;
    }

    @XmlTransient
    public List<Receitas> getReceitasList() {
        return receitasList;
    }

    public void setReceitasList(List<Receitas> receitasList) {
        this.receitasList = receitasList;
    }

    @XmlTransient
    public List<Receitamensal> getReceitamensalList() {
        return receitamensalList;
    }

    public void setReceitamensalList(List<Receitamensal> receitamensalList) {
        this.receitamensalList = receitamensalList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDEmpresa != null ? iDEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresas)) {
            return false;
        }
        Empresas other = (Empresas) object;
        if ((this.iDEmpresa == null && other.iDEmpresa != null) || (this.iDEmpresa != null && !this.iDEmpresa.equals(other.iDEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Empresas[ iDEmpresa=" + iDEmpresa + " ]";
    }
    
}
