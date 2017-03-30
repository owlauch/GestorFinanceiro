package dpaulacontabilidade;

import ControleJPA.exceptions.IllegalOrphanException;
import ControleJPA.exceptions.NonexistentEntityException;
import DAO.ReceitasDAO;
import Entidades.Receitas;
import UtilitariosDprograma.ConversorReceita;
import UtilitariosDprograma.ReceitaConvertida;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ReceitaController implements Initializable, ControlledScreen {

    @FXML
    private TableView<ReceitaConvertida> TabelaReceitas;
    @FXML
    private TableColumn<ReceitaConvertida, String> TCMTDOpgto;
    @FXML
    private TableColumn<ReceitaConvertida, String> TCdataPGTO;
    @FXML
    private TableColumn<ReceitaConvertida, String> TCCliente;
    @FXML
    private TableColumn<ReceitaConvertida, String> TCServiços;
    @FXML
    private TableColumn<ReceitaConvertida, String> TCValorPG;
    @FXML
    private Button BSelectMetodo;
    @FXML
    private Button BSelectServico;
    @FXML
    private Button BSelectCliente;
    @FXML
    private Button BNovo;
    @FXML
    private Button BSalvar;
    @FXML
    private Button BDeletar;
    @FXML
    private Button BSelectEmpresa;
    @FXML
    private DatePicker DPicker;
    @FXML
    private Label TLEmpresa;
    @FXML
    private Label TlMetodoPagamento;
    @FXML
    private Label TLServicoPrestado;
    @FXML
    private Label TLCliente;
    @FXML
    private TextField TFValorPgto;
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
    private final ReceitaConvertida receitas = new ReceitaConvertida();
    private ObservableList<ReceitaConvertida> lstclientes = FXCollections.observableArrayList();
    private final ConversorReceita conversordereceita = new ConversorReceita();
    public static ReceitaController receitacontroller;
    public final ReceitasDAO receitasDAO = new ReceitasDAO();
    static Receitas itensSeleciondos = new Receitas();
    static Stage palco = new Stage();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        receitacontroller = this;
        setarTabela();
    }

    public void verificacaoDeCliente(int x) {
        if (x == 1) {
            BSelectEmpresa.setDisable(true);
            BSelectCliente.setDisable(false);
        } else if (x == 2) {
            BSelectCliente.setDisable(true);
            BSelectEmpresa.setDisable(false);
        }
    }

    public void setaALabelMtdopgto(String t) {
        TlMetodoPagamento.setText(t);
        TlMetodoPagamento.setTextFill(Color.BLACK);
        TlMetodoPagamento.setFont(Font.font("System", FontWeight.BOLD, 12));
        TlMetodoPagamento.setOpacity(1);
    }

    public void excluirReceita() throws NonexistentEntityException, IllegalOrphanException {
        receitasDAO.removerReceita(TabelaReceitas.getSelectionModel().getSelectedItem().getiDServicoPrestado());
        deletardaLista();
    }

    public void setaALabelservico(String t, BigDecimal x) {
        TLServicoPrestado.setText(t);
        TLServicoPrestado.setTextFill(Color.BLACK);
        TLServicoPrestado.setFont(Font.font("System", FontWeight.BOLD, 12));
        TLServicoPrestado.setOpacity(1);
        TFValorPgto.setText(x.toString());
    }

    public void setaALabelEmpresa(String t) {
        TLEmpresa.setText(t);
        TLEmpresa.setTextFill(Color.BLACK);
        TLEmpresa.setFont(Font.font("System", FontWeight.BOLD, 12));
        TLEmpresa.setOpacity(1);
    }

    public void setaALabelCliente(String t) {
        TLCliente.setText(t);
        TLCliente.setTextFill(Color.BLACK);
        TLCliente.setFont(Font.font("System", FontWeight.BOLD, 12));
        TLCliente.setOpacity(1);
    }

    public void setarTabela() {
        lstclientes = conversordereceita.converter();
        TCCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TCMTDOpgto.setCellValueFactory(new PropertyValueFactory<>("mtdopgto"));
        TCServiços.setCellValueFactory(new PropertyValueFactory<>("servico"));
        TCValorPG.setCellValueFactory(new PropertyValueFactory<>("Valor"));
        TCdataPGTO.setCellValueFactory(new PropertyValueFactory<>("datasimples"));
        TabelaReceitas.setItems(lstclientes);
        LocalDate data = LocalDate.now();
        DPicker.setValue(data);
    }

    @FXML
    public void janelaCliente() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("SelecionarCliente.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        palco.setMaxHeight(400);
        palco.setMaxWidth(200);
        palco.setMinWidth(200);
        palco.setMinHeight(400);
        palco.setScene(scene);
        SelecionarClienteController.selecionarclientecontroller.paremetroString("receita");
        palco.show();
    }

    @FXML
    public void janelamtdo() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("SelecionarMtdo.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        palco.setMaxHeight(400);
        palco.setMaxWidth(200);
        palco.setMinWidth(200);
        palco.setMinHeight(400);
        palco.setScene(scene);
        palco.show();
    }

    @FXML
    public void janelaServico() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("SelecionarServico.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        palco.setMaxHeight(400);
        palco.setMaxWidth(200);
        palco.setMinWidth(200);
        palco.setMinHeight(400);
        palco.setScene(scene);
        palco.show();
    }

    @FXML
    public void janelaEmpresas() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("SelecionarEmpresas.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        palco.setMaxHeight(400);
        palco.setMaxWidth(200);
        palco.setMinWidth(200);
        palco.setMinHeight(400);
        palco.setScene(scene);
        palco.show();
    }

    @FXML
    public void novaReceita() {
        Receitas novaReceita = new Receitas();
        ReceitasDAO receiDao = new ReceitasDAO();
        novaReceita.setDataPrestacaoServico(java.sql.Date.valueOf(DPicker.getValue()));
        novaReceita.setIDCliente(itensSeleciondos.getIDCliente());
        novaReceita.setIDEmpresa(itensSeleciondos.getIDEmpresa());
        novaReceita.setIDMetodoPagamento(itensSeleciondos.getIDMetodoPagamento());
        novaReceita.setIDServico(itensSeleciondos.getIDServico());
        novaReceita.setValor(BigDecimal.valueOf(Double.valueOf((TFValorPgto.getText()))));
        receiDao.adicionarReceita(novaReceita);
        lstclientes.add(conversordereceita.converterEntidade(novaReceita));
        TabelaReceitas.refresh();
        zerartabela();
    }

    public void zerartabela() {
        TLCliente.setText("SELECIONE ---->");
        TLCliente.setTextFill(Color.BLACK);
        TLCliente.setFont(Font.font("System", FontWeight.NORMAL, 12));
        TLCliente.setOpacity(0.4);
        TLEmpresa.setText("SELECIONE ---->");
        TLEmpresa.setTextFill(Color.BLACK);
        TLEmpresa.setFont(Font.font("System", FontWeight.NORMAL, 12));
        TLEmpresa.setOpacity(0.4);
        TLServicoPrestado.setText("SELECIONE ---->");
        TLServicoPrestado.setTextFill(Color.BLACK);
        TLServicoPrestado.setFont(Font.font("System", FontWeight.NORMAL, 12));
        TLServicoPrestado.setOpacity(0.4);
        TlMetodoPagamento.setText("SELECIONE ---->");
        TlMetodoPagamento.setTextFill(Color.BLACK);
        TlMetodoPagamento.setFont(Font.font("System", FontWeight.NORMAL, 12));
        TlMetodoPagamento.setOpacity(0.4);
        BSelectCliente.setDisable(false);
        BSelectEmpresa.setDisable(false);
        TFValorPgto.clear();

    }

    public void deletardaLista() throws NonexistentEntityException, IllegalOrphanException {
        ObservableList<ReceitaConvertida> unidadeselecionada, todasunidades;
        todasunidades = TabelaReceitas.getItems();
        unidadeselecionada = TabelaReceitas.getSelectionModel().getSelectedItems();
        unidadeselecionada.forEach(todasunidades::remove);
        TabelaReceitas.refresh();
    }

    public void selecionarReceita() {
        TabelaReceitas.refresh();
        if (TabelaReceitas.getSelectionModel().getSelectedItem().getiDCliente() != null) {
            TLCliente.setText(TabelaReceitas.getSelectionModel().getSelectedItem().getiDCliente().getNome());
            BSelectEmpresa.setDisable(true);
        } else {
            TLEmpresa.setText(TabelaReceitas.getSelectionModel().getSelectedItem().getIDEmpresa().getNome());
            BSelectEmpresa.setDisable(true);
        }
        TLServicoPrestado.setText(TabelaReceitas.getSelectionModel().getSelectedItem().getiDServico().getDescricao());
        TlMetodoPagamento.setText(TabelaReceitas.getSelectionModel().getSelectedItem().getiDMetodoPagamento().getDescrição());
        TFValorPgto.setText(TabelaReceitas.getSelectionModel().getSelectedItem().getValor().toString());
        java.util.Date data = TabelaReceitas.getSelectionModel().getSelectedItem().getData();
        LocalDate dat2 = new java.sql.Date(data.getTime()).toLocalDate();
        DPicker.setValue(dat2);
        BSalvar.setDisable(false);
        BNovo.setDisable(true);
        BSelectCliente.setDisable(true);
        BSelectEmpresa.setDisable(true);
        BSelectMetodo.setDisable(true);
        BSelectServico.setDisable(true);
    }

    public void editarReceita() throws Exception {
        Receitas novaReceita = new Receitas();
        ReceitasDAO receiDao = new ReceitasDAO();
        novaReceita.setDataPrestacaoServico(java.sql.Date.valueOf(DPicker.getValue()));
        novaReceita.setIDCliente(TabelaReceitas.getSelectionModel().getSelectedItem().getiDCliente());
        novaReceita.setIDEmpresa(TabelaReceitas.getSelectionModel().getSelectedItem().getIDEmpresa());
        novaReceita.setIDMetodoPagamento(TabelaReceitas.getSelectionModel().getSelectedItem().getiDMetodoPagamento());
        novaReceita.setIDServico(TabelaReceitas.getSelectionModel().getSelectedItem().getiDServico());
        novaReceita.setValor(BigDecimal.valueOf(Double.valueOf((TFValorPgto.getText()))));
        novaReceita.setIDServicoPrestado(TabelaReceitas.getSelectionModel().getSelectedItem().getiDServicoPrestado());
        receiDao.alterarReceita(novaReceita);
        deletardaLista();
        lstclientes.add(conversordereceita.converterEntidade(novaReceita));
        TabelaReceitas.refresh();
        zerartabela();
        BNovo.setDisable(false);
        BSalvar.setDisable(true);
        BSelectCliente.setDisable(false);
        BSelectEmpresa.setDisable(false);
        BSelectMetodo.setDisable(false);
        BSelectServico.setDisable(false);

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
