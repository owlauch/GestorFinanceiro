package dpaulacontabilidade;

import ControleJPA.exceptions.IllegalOrphanException;
import ControleJPA.exceptions.NonexistentEntityException;
import DAO.CentroDeCustosDAO;
import Entidades.Centrodecusto;
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
import javafx.scene.input.MouseEvent;

public class DestinosController implements Initializable, ControlledScreen {

    @FXML
    private TableView<Centrodecusto> TabelaClientes;
    @FXML
    private TableColumn<Centrodecusto, String> TNome;
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
    @FXML
    private Button BLAReceber;
    @FXML
    private Button BLAPagar;

    ScreensController myController;
    private final CentroDeCustosDAO centrodecustosDAO = new CentroDeCustosDAO();
    private final Centrodecusto centrodecustos = new Centrodecusto();
    private final ObservableList<Centrodecusto> listaCentrodecustos = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setartabela();
    }

    public void setartabela() {
        listaCentrodecustos.addAll(centrodecustosDAO.pegartodosCentrodecusto());
        TNome.setCellValueFactory(new PropertyValueFactory<>("Descricao"));
        TabelaClientes.setItems(listaCentrodecustos);

    }

    public void novoCentrodecustos() throws Exception {
        Centrodecusto novoCentrodecustos = new Centrodecusto();
        if (ENome != null) {
            novoCentrodecustos.setDescricao(ENome.getText());
            novoCentrodecustos.setIDCentroDeCusto(pegarid() + 1);
            listaCentrodecustos.add(novoCentrodecustos);
            TabelaClientes.refresh();
            ENome.clear();
            centrodecustosDAO.adicionarCentrodecusto(novoCentrodecustos);
        }
    }

    public int pegarid() {
        int i = 0;
        List<Centrodecusto> alltipos = centrodecustosDAO.pegartodosCentrodecusto();
        if (!alltipos.isEmpty()) {
            for (Centrodecusto tp : alltipos) {
                if (tp.getIDCentroDeCusto() > i) {
                    i = tp.getIDCentroDeCusto();
                }
            }
        }
        return i;
    }

    public void selecionarCentrodecustos() {
        BSalvar.setDisable(false);
        BNovo.setDisable(true);
        ENome.setText(TabelaClientes.getSelectionModel().getSelectedItem().getDescricao());
    }

    public void alterarCentrodecustos() throws Exception {
        Centrodecusto alterarCentrodecustos = new Centrodecusto();
        alterarCentrodecustos = TabelaClientes.getSelectionModel().getSelectedItem();
        alterarCentrodecustos.setDescricao(ENome.getText());
        centrodecustosDAO.alterarCentrodecusto(alterarCentrodecustos);
        deletardaLista();
        listaCentrodecustos.add(alterarCentrodecustos);
        TabelaClientes.refresh();
        BSalvar.setDisable(true);
        BNovo.setDisable(false);
    }

    public void excluirCentrodecustos() throws NonexistentEntityException, IllegalOrphanException {
        centrodecustosDAO.excluirCentrodecusto(TabelaClientes.getSelectionModel().getSelectedItem().getIDCentroDeCusto());
        deletardaLista();
        BSalvar.setDisable(true);
        BNovo.setDisable(false);

    }

    public void deletardaLista() {
        ObservableList<Centrodecusto> unidadeselecionada, todasunidades;
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

    @FXML
    private void selecionarTipo(MouseEvent event) {
    }

    @FXML
    private void novoTipo(ActionEvent event) {
    }

    @FXML
    private void excluirTipo(ActionEvent event) {
    }

    @FXML
    private void alterarTipo(MouseEvent event) {
    }
}
