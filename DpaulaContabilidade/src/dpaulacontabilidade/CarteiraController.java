package dpaulacontabilidade;

import ControleJPA.exceptions.IllegalOrphanException;
import ControleJPA.exceptions.NonexistentEntityException;
import DAO.MetodoDePagamentoDAO;
import Entidades.Metodopagamento;
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

public class CarteiraController implements Initializable, ControlledScreen {

    @FXML
    private TableView<Metodopagamento> TabelaClientes;
    @FXML
    private TableColumn<Metodopagamento, String> TNome;
    @FXML
    private TextField ENome;
    @FXML
    private Button BNovo;
    @FXML
    private Button BDeletar;
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
    private Metodopagamento mtdoPgto = new Metodopagamento();
    private final MetodoDePagamentoDAO mtdopgtoDAO = new MetodoDePagamentoDAO();
    private final ObservableList<Metodopagamento> mtdoPgtoList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setarTabela();
    }

    public void setarTabela() {
        mtdoPgtoList.addAll(mtdopgtoDAO.pegartodosMetodopagamento());
        TNome.setCellValueFactory(new PropertyValueFactory<>("Descrição"));
        TabelaClientes.setItems(mtdoPgtoList);
    }

    @FXML
    public void novoMtdopgto() {
        Metodopagamento novomtdo = new Metodopagamento();
        novomtdo.setDescrição(ENome.getText().toUpperCase());
        mtdopgtoDAO.adicionarmtdoPgto(novomtdo);
        ENome.clear();
        mtdoPgtoList.add(novomtdo);
        TabelaClientes.refresh();
    }

    @FXML
    public void selecionar() {
        mtdoPgto.setIDMetodoPagamento(TabelaClientes.getSelectionModel().getSelectedItem().getIDMetodoPagamento());
        ENome.setText(TabelaClientes.getSelectionModel().getSelectedItem().getDescrição());
        BSalvar.setDisable(false);
        BNovo.setDisable(true);
    }

    @FXML
    public void alterarmtdopgto() throws Exception {
        mtdoPgto.setDescrição(ENome.getText());
        mtdopgtoDAO.alterarmtdoPgto(mtdoPgto);
        deletardaLista();
        mtdoPgtoList.add(mtdoPgto);
        TabelaClientes.refresh();
        BSalvar.setDisable(true);
        BNovo.setDisable(false);
    }

    @FXML
    public void excluirMtdoPgto() throws NonexistentEntityException, IllegalOrphanException {
        mtdopgtoDAO.removermtdoPgto(TabelaClientes.getSelectionModel().getSelectedItem().getIDMetodoPagamento());
        deletardaLista();
        BSalvar.setDisable(true);
        BNovo.setDisable(false);
    }

    public void deletardaLista() throws NonexistentEntityException, IllegalOrphanException {
        ObservableList<Metodopagamento> unidadeselecionada, todasunidades;
        todasunidades = TabelaClientes.getItems();
        unidadeselecionada = TabelaClientes.getSelectionModel().getSelectedItems();
        unidadeselecionada.forEach(todasunidades::remove);
        ENome.clear();
        TabelaClientes.refresh();
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
