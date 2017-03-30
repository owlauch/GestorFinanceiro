package UtilitariosDprograma;

import Entidades.Clientes;
import Entidades.Empresas;
import Entidades.Metodopagamento;
import Entidades.Servicos;
import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReceitaConvertida {

    private Integer iDServicoPrestado;
    private BigDecimal valor;
    private Date dataPrestacaoServico;
    private Metodopagamento iDMetodoPagamento;
    private Clientes iDCliente;
    private Empresas IDEmpresa;
    private Servicos iDServico;
    private String nome, mtdopgto, servico, datasimples;
    private Date dts;
    Format formatter = new SimpleDateFormat("dd/MM/yyyy");

    public Empresas getIDEmpresa() {
        return IDEmpresa;
    }

    public void setIDEmpresa(Empresas IDEmpresa) {
        this.IDEmpresa = IDEmpresa;
        if(this.nome==""){
        this.nome=IDEmpresa.getNome();
        }
    }

    public String getDatasimples() {
        return datasimples;
    }

    public void setDatasimples(String datasimples) {
        this.datasimples = datasimples;
    }

    public Date getData() {
        return dts;
    }

    public void setData(Date data) {
        setDatasimples(formatter.format(data));
        this.dts = data;
    }

    public Integer getiDServicoPrestado() {
        return iDServicoPrestado;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Date getDataPrestacaoServico() {
        return dataPrestacaoServico;
    }

    public Metodopagamento getiDMetodoPagamento() {
        return iDMetodoPagamento;
    }

    public Clientes getiDCliente() {
        return iDCliente;
    }

    public Servicos getiDServico() {
        return iDServico;
    }

    public String getNome() {
        return nome;
    }

    public String getMtdopgto() {
        return mtdopgto;
    }

    public String getServico() {
        return servico;
    }

    public void setiDServicoPrestado(Integer iDServicoPrestado) {
        this.iDServicoPrestado = iDServicoPrestado;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setDataPrestacaoServico(Date dataPrestacaoServico) {
        this.dataPrestacaoServico = dataPrestacaoServico;
    }

    public void setiDMetodoPagamento(Metodopagamento iDMetodoPagamento) {
        this.iDMetodoPagamento = iDMetodoPagamento;
        this.mtdopgto = iDMetodoPagamento.getDescrição();
    }

    public void setiDCliente(Clientes iDCliente) {
        this.iDCliente = iDCliente;
        if(iDCliente.getNome()!=null){
        this.nome = iDCliente.getNome();
        }
    }

    public void setiDServico(Servicos iDServico) {
        this.iDServico = iDServico;
        this.servico = iDServico.getDescricao();
    }

}
