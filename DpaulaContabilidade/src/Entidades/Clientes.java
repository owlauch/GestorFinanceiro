/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author owlau
 */
@Entity
@Table(name = "clientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clientes.findAll", query = "SELECT c FROM Clientes c")
    , @NamedQuery(name = "Clientes.findByIDCliente", query = "SELECT c FROM Clientes c WHERE c.iDCliente = :iDCliente")
    , @NamedQuery(name = "Clientes.findByNome", query = "SELECT c FROM Clientes c WHERE c.nome = :nome")
    , @NamedQuery(name = "Clientes.findByDataNascimento", query = "SELECT c FROM Clientes c WHERE c.dataNascimento = :dataNascimento")
    , @NamedQuery(name = "Clientes.findByCpf", query = "SELECT c FROM Clientes c WHERE c.cpf = :cpf")
    , @NamedQuery(name = "Clientes.findByRg", query = "SELECT c FROM Clientes c WHERE c.rg = :rg")
    , @NamedQuery(name = "Clientes.findByTitulo", query = "SELECT c FROM Clientes c WHERE c.titulo = :titulo")
    , @NamedQuery(name = "Clientes.findByEmail", query = "SELECT c FROM Clientes c WHERE c.email = :email")
    , @NamedQuery(name = "Clientes.findByTelefone", query = "SELECT c FROM Clientes c WHERE c.telefone = :telefone")
    , @NamedQuery(name = "Clientes.findByCelular", query = "SELECT c FROM Clientes c WHERE c.celular = :celular")})
public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCliente")
    private Integer iDCliente;
    @Column(name = "Nome")
    private String nome;
    @Column(name = "DataNascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Column(name = "CPF")
    private String cpf;
    @Column(name = "RG")
    private String rg;
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "Email")
    private String email;
    @Column(name = "Telefone")
    private String telefone;
    @Column(name = "Celular")
    private String celular;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDCliente")
    private List<Empresas> empresasList;
    @OneToMany(mappedBy = "iDCliente")
    private List<Receitas> receitasList;

    public Clientes() {
    }

    public Clientes(Integer iDCliente) {
        this.iDCliente = iDCliente;
    }

    public Integer getIDCliente() {
        return iDCliente;
    }

    public void setIDCliente(Integer iDCliente) {
        this.iDCliente = iDCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    @XmlTransient
    public List<Empresas> getEmpresasList() {
        return empresasList;
    }

    public void setEmpresasList(List<Empresas> empresasList) {
        this.empresasList = empresasList;
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
        hash += (iDCliente != null ? iDCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clientes)) {
            return false;
        }
        Clientes other = (Clientes) object;
        if ((this.iDCliente == null && other.iDCliente != null) || (this.iDCliente != null && !this.iDCliente.equals(other.iDCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Clientes[ iDCliente=" + iDCliente + " ]";
    }
    
}
