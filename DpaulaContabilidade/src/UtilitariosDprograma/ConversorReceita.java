package UtilitariosDprograma;

import DAO.ReceitasDAO;
import Entidades.Receitas;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ConversorReceita {

    private final ObservableList<Receitas> receitas = FXCollections.observableArrayList();
    private final ObservableList<ReceitaConvertida> receitasconver = FXCollections.observableArrayList();
    private final ReceitasDAO receitasdao = new ReceitasDAO();
    private final int tamanho = receitasdao.pegartodosReceitas().size();

    public ObservableList<ReceitaConvertida> converter() {
        int i = 0, yui;
        Date dta;
        receitas.addAll(receitasdao.pegartodosReceitas());
        yui = pegarid();

        for (i = 0; i < receitas.size(); i++) {
            ReceitaConvertida rconvertida = new ReceitaConvertida();
            rconvertida.setDataPrestacaoServico(receitas.get(i).getDataPrestacaoServico());
            rconvertida.setData(receitas.get(i).getDataPrestacaoServico());
            rconvertida.setValor(receitas.get(i).getValor());
            if (receitas.get(i).getIDCliente() != null) {
                rconvertida.setiDCliente(receitas.get(i).getIDCliente());
            }
            rconvertida.setiDMetodoPagamento(receitas.get(i).getIDMetodoPagamento());
            rconvertida.setiDServico(receitas.get(i).getIDServico());
            if (receitas.get(i).getIDEmpresa() != null) {
                rconvertida.setIDEmpresa(receitas.get(i).getIDEmpresa());
            }
            rconvertida.setiDServicoPrestado(receitas.get(i).getIDServicoPrestado());
            receitasconver.add(rconvertida);
        }
        return receitasconver;
    }

    public int pegarid() {
        int i = 0;
        List<Receitas> allclientes = receitasdao.pegartodosReceitas();
        if (!allclientes.isEmpty()) {
            for (Receitas Objeto : allclientes) {
                if (Objeto.getIDServicoPrestado() > i) {
                    i = Objeto.getIDServicoPrestado();
                }
            }
        }
        return i;
    }

    public ReceitaConvertida converterEntidade(Receitas rctas) {
        ReceitaConvertida recConvertida = new ReceitaConvertida();
        recConvertida.setDataPrestacaoServico(rctas.getDataPrestacaoServico());
        recConvertida.setData(rctas.getDataPrestacaoServico());
        recConvertida.setIDEmpresa(rctas.getIDEmpresa());
        recConvertida.setiDCliente(rctas.getIDCliente());
        recConvertida.setiDMetodoPagamento(rctas.getIDMetodoPagamento());
        recConvertida.setiDServico(rctas.getIDServico());
        recConvertida.setValor(rctas.getValor());
        recConvertida.setiDServicoPrestado(rctas.getIDServicoPrestado());
        return recConvertida;
    }
}
