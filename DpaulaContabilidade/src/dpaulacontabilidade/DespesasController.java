package dpaulacontabilidade;

import DAO.DespesasDAO;
import Entidades.Despesas;
import Entidades.Unidadecusto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DespesasController implements Initializable, ControlledScreen {

    @FXML
    private TableView<Despesas> TabelaReceitas;
    @FXML
    private TableColumn<Despesas, String> TCMTDOpgto;
    @FXML
    private TableColumn<Despesas, String> TCdataPGTO;
    @FXML
    private TableColumn<Despesas, String> TCDestino;
    @FXML
    private TableColumn<Despesas, String> TCItem;
    @FXML
    private TableColumn<Despesas, String> TCUC;
    @FXML
    private TableColumn<Despesas, String> TCValor;
    @FXML
    private Button BSelectMetodo;
    @FXML
    private Button BSelectCliente;
    @FXML
    private Label TlMetodoPagamento;
    @FXML
    private Label TLCliente;
    @FXML
    private Button BSelectCliente1;
    @FXML
    private Label TLCliente1;
    @FXML
    private Button BNovo;
    @FXML
    private Button BSalvar;
    @FXML
    private Button BDeletar;
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
    private final Despesas despesas = new Despesas();
    private final DespesasDAO despesasDAO = new DespesasDAO();
    private final ObservableList<Despesas> listaDespesas = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setartabela();
    }

    public void setartabela() {
        listaDespesas.addAll(despesasDAO.pegartodosDespesa());
        TCItem.setCellValueFactory(new PropertyValueFactory<>("item"));
        TCDestino.setCellValueFactory(new PropertyValueFactory<>("Destino"));
        TCMTDOpgto.setCellValueFactory(new PropertyValueFactory<>("Metodo"));
        TCUC.setCellValueFactory(new PropertyValueFactory<>("UnidadedeCusto"));
        TCdataPGTO.setCellValueFactory(new PropertyValueFactory<>("data"));
        TabelaReceitas.setItems(listaDespesas);

    }

    @FXML
    private void salvarEmpresa(ActionEvent event) {
    }

    @FXML
    private void deletar(ActionEvent event) {
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
