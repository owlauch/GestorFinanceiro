package dpaulacontabilidade;

import UtilitariosDprograma.Avisos;
import ControleJPA.exceptions.IllegalOrphanException;
import ControleJPA.exceptions.NonexistentEntityException;
import DAO.UnidadeDeCustoDAO;
import Entidades.Unidadecusto;
import java.net.URL;
import java.util.List;
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

public class UnidadeDeCustosController implements Initializable, ControlledScreen {

    @FXML
    private TableView<Unidadecusto> TabelaClientes;
    @FXML
    private TableColumn<Unidadecusto, String> TNome;
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
    private final UnidadeDeCustoDAO unidadeCustoDAO = new UnidadeDeCustoDAO();
    private final ObservableList listaUnidadeCusto = FXCollections.observableArrayList();
    private final Unidadecusto unidadeCusto = new Unidadecusto();
    private final Avisos avisos = new Avisos();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaUnidadeCusto.addAll(unidadeCustoDAO.pegartodosUnidadecusto());
        TNome.setCellValueFactory(new PropertyValueFactory<>("Descricao"));
        TabelaClientes.setItems(listaUnidadeCusto);
    }

    @FXML
    public void novo() throws Exception {
        Unidadecusto UC = new Unidadecusto();
        if (ENome.getText().trim().isEmpty()) {
            avisos.alerta2();
        } else {
            TabelaClientes.refresh();
            UC.setDescricao(ENome.getText().toUpperCase());
            unidadeCustoDAO.adicionarUnidadecusto(UC);
            UC.setIDUnidadeCusto(pegarid());
            listaUnidadeCusto.add(UC);
            TabelaClientes.refresh();
            ENome.clear();
        }

    }

    public int pegarid() {
        int i = 0;
        List<Unidadecusto> allUnidadecustos = unidadeCustoDAO.pegartodosUnidadecusto();
        if (!allUnidadecustos.isEmpty()) {
            for (Unidadecusto undCsto : allUnidadecustos) {
                if (undCsto.getIDUnidadeCusto() > i) {
                    i = undCsto.getIDUnidadeCusto();
                }
            }
        }
        return i;
    }

    public void deletardaLista() {
        ObservableList<Unidadecusto> unidadeselecionada, todasunidades;
        todasunidades = TabelaClientes.getItems();
        unidadeselecionada = TabelaClientes.getSelectionModel().getSelectedItems();
        unidadeselecionada.forEach(todasunidades::remove);
        ENome.clear();
        TabelaClientes.refresh();
    }

    @FXML
    public void deletar() throws IllegalOrphanException, NonexistentEntityException {
        if (!TabelaClientes.getSelectionModel().isEmpty()) {
            unidadeCustoDAO.excluirUnidadecusto(TabelaClientes.getSelectionModel().getSelectedItem().getIDUnidadeCusto());
            deletardaLista();
            BNovo.setDisable(false);
            BSalvar.setDisable(true);
        } else {
            avisos.alerta2();
        }
    }

    public void Selecionar() throws Exception {
        ENome.setText(TabelaClientes.getSelectionModel().getSelectedItem().getDescricao());
        BSalvar.setDisable(false);
        BNovo.setDisable(true);
        BDeletar.setDisable(false);
    }

    @FXML
    public void SalvarEdicao() throws Exception {
        Unidadecusto UC2 = new Unidadecusto();
        UC2 = TabelaClientes.getSelectionModel().getSelectedItem();
        UC2.setDescricao(ENome.getText());
        unidadeCustoDAO.alterarUnidadecusto(UC2);
        BNovo.setDisable(false);
        BSalvar.setDisable(true);
        listaUnidadeCusto.add(UC2);
        deletardaLista();
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
