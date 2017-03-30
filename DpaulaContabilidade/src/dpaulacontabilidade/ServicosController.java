package dpaulacontabilidade;

import UtilitariosDprograma.Avisos;
import ControleJPA.exceptions.IllegalOrphanException;
import ControleJPA.exceptions.NonexistentEntityException;
import DAO.ServicosDAO;
import Entidades.Servicos;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ServicosController implements Initializable, ControlledScreen {

    @FXML
    private TableView<Servicos> TabelaClientes;
    @FXML
    private Button BNovo;
    @FXML
    private Button BDeletar;
    @FXML
    private TableColumn<Servicos, String> TDescricao;
    @FXML
    private TableColumn<Servicos, String> TValor;
    @FXML
    private TextField EDescricao;
    @FXML
    private TextField EValor;
    @FXML
    private Button BSalvar;
    @FXML
    private Button BCliente;
    @FXML
    private Button BEmpresa;
    @FXML
    private Button BServico;
    @FXML
    private Button BCarteira;
    @FXML
    private Button BUC;
    @FXML
    private Button BDestino;
    @FXML
    private Button BRReceitas;
    @FXML
    private Button BRDespesas;
    @FXML
    private Button BRTransferencias;
    @FXML
    private Button BREmprestimos;
    @FXML
    private Button BRCustosPessoais;
    @FXML
    private Button BRHonorarios;
    @FXML
    private Button BRSimplesNacional;
    @FXML
    private Button BLReceitas;
    @FXML
    private Button BLDespesas;
    @FXML
    private Button BLEmprestimos;
    @FXML
    private Button BLTransferencias;
    @FXML
    private Button BLCSimples;

    ScreensController myController;
    private final Servicos servicos = new Servicos();
    private final ObservableList<Servicos> listaServicos = FXCollections.observableArrayList();
    private final ServicosDAO servicosDAO = new ServicosDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setaTabela();
    }

    public Servicos setarServico(Servicos servico) {
        if (EDescricao.getText().trim().isEmpty()) {
            Avisos avisos = new Avisos();
            avisos.alerta2();
        } else {
            servico.setDescricao(EDescricao.getText().toUpperCase());
        }
        if (!EValor.getText().trim().isEmpty()) {
            servico.setValor(BigDecimal.valueOf((Float.valueOf(EValor.getText()))));
        } else {
            Avisos avisos = new Avisos();
            avisos.alerta2();
        }
        return servico;
    }

    @FXML
    public void selecionar() {
        try {
            EDescricao.setText(TabelaClientes.getSelectionModel().getSelectedItem().getDescricao());
            EValor.setText(TabelaClientes.getSelectionModel().getSelectedItem().getValor().toString());
            BSalvar.setDisable(false);
            BNovo.setDisable(true);
        } catch (NullPointerException erro) {

        }
    }

    @FXML
    public void editarServico() throws Exception {
        Servicos edServico = new Servicos();
        edServico.setIDServico(TabelaClientes.getSelectionModel().getSelectedItem().getIDServico());
        edServico=setarServico(edServico);
        servicosDAO.alterarServico(edServico);
        deletardaLista();
        Limpar();
        BNovo.setDisable(false);
        BSalvar.setDisable(true);
        listaServicos.add(edServico);
        TabelaClientes.refresh();
    }

    @FXML
    public void novoServico() {
        Servicos nServico = new Servicos();
        nServico=setarServico(nServico);
        servicosDAO.adicionarServico(nServico);
        listaServicos.add(nServico);
        TabelaClientes.refresh();
        Limpar();
    }

    @FXML
    public void excluirServico() throws NonexistentEntityException, IllegalOrphanException {
        servicosDAO.removerServico(TabelaClientes.getSelectionModel().getSelectedItem().getIDServico());
        deletardaLista();
        BNovo.setDisable(false);
        BSalvar.setDisable(true);
    }

    public void Limpar() {
        EDescricao.clear();
        EValor.clear();
    }

    public void deletardaLista() {
        ObservableList<Servicos> unidadeselecionada, todasunidades;
        todasunidades = TabelaClientes.getItems();
        unidadeselecionada = TabelaClientes.getSelectionModel().getSelectedItems();
        unidadeselecionada.forEach(todasunidades::remove);
        Limpar();
        TabelaClientes.refresh();
    }

    public void setaTabela() {
        listaServicos.addAll(servicosDAO.pegartodosServicos());
        TDescricao.setCellValueFactory(new PropertyValueFactory<>("Descricao"));
        TValor.setCellValueFactory(new PropertyValueFactory<>("Valor"));
        TabelaClientes.setItems(listaServicos);
    }

      @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void goToCliente(ActionEvent event) {
        myController.setScreen(DpaulaContabilidade.screen2ID);
    }

    @FXML
    private void goToEmpresa(ActionEvent event) {
        myController.setScreen(DpaulaContabilidade.screen3ID);
    }

    @FXML
    private void gotoServicos(ActionEvent event) {
        myController.setScreen(DpaulaContabilidade.screen4ID);
    }

    @FXML
    private void goToCarteira(ActionEvent event) {
        myController.setScreen(DpaulaContabilidade.screen5ID);
    }

    @FXML
    private void goToUnideDeCustos(ActionEvent event) {
        myController.setScreen(DpaulaContabilidade.screen6ID);
    }

    @FXML
    private void goToReceita(ActionEvent event) {
        myController.setScreen(DpaulaContabilidade.screen7ID);
    }

    @FXML
    private void goToDespesas(ActionEvent event) {
        myController.setScreen(DpaulaContabilidade.screen8ID);
    }

    @FXML
    private void goToDestino(ActionEvent event) {
        myController.setScreen(DpaulaContabilidade.screen9ID);
    }

    @FXML
    private void goToAPagar(ActionEvent event) {
        myController.setScreen(DpaulaContabilidade.screen10ID);
    }

    @FXML
    private void goToAReceber(ActionEvent event) {
        myController.setScreen(DpaulaContabilidade.screen11ID);
    }

    @FXML
    private void goToEmprestimos(ActionEvent event) {
        myController.setScreen(DpaulaContabilidade.screen12ID);
    }

    @FXML
    private void goToReceitaSimples(ActionEvent event) {
        myController.setScreen(DpaulaContabilidade.screen13ID);
    }

    @FXML
    private void goToTransferencias(ActionEvent event) {
        myController.setScreen(DpaulaContabilidade.screen14ID);
    }
}
