package dpaulacontabilidade;

import UtilitariosDprograma.Avisos;
import ControleJPA.exceptions.IllegalOrphanException;
import ControleJPA.exceptions.NonexistentEntityException;
import DAO.ClienteDAO;
import DAO.EmpresasDAO;
import Entidades.Clientes;
import Entidades.Empresas;
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

public class EmpresaController implements Initializable, ControlledScreen {

    ScreensController myController;
    @FXML
    private TableView<Empresas> TabelaClientes;
    @FXML
    private TableColumn<Empresas, String> TNome;
    @FXML
    private TableColumn<Empresas, String> TCPF;
    @FXML
    private TableColumn<Empresas, String> TCelular;
    @FXML
    private TextField ENome;
    @FXML
    private Button BNovo;
    @FXML
    private Button BDeletar;
    @FXML
    private DatePicker Edata;
    @FXML
    private TextField ETelefone;
    @FXML
    private TextField ECelular;
    @FXML
    private Button Bselecionar;
    @FXML
    private Button BSalvar;
    @FXML
    private Label Lcliente;
    @FXML
    private TextField ECNPJ;
    @FXML
    private TextField EEmail;
    @FXML
    private TextField ESenhaSimples;
    @FXML
    private TextField ESenhaCertificado;
    @FXML
    private TextField ESenhaNFE;
    @FXML
    private TextField EValorHonorario;
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

    private final EmpresasDAO empresasDAO = new EmpresasDAO();
    private final Empresas empresa = new Empresas();
    static Clientes cliente = new Clientes();
    private final ObservableList<Empresas> listaEmpresas = FXCollections.observableArrayList();
    static Stage palco = new Stage();
    public static EmpresaController controle;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controle = this;
        setarTabela();
    }

    public void setarTabela() {
        listaEmpresas.addAll(empresasDAO.pegartodosEmpresas());
        TNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        TCPF.setCellValueFactory(new PropertyValueFactory<>("Cnpj"));
        TCelular.setCellValueFactory(new PropertyValueFactory<>("Celular"));
        TabelaClientes.setItems(listaEmpresas);
    }

    @FXML
    public void janela() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("SelecionarCliente.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        palco.setMaxHeight(400);
        palco.setMaxWidth(200);
        palco.setMinWidth(200);
        palco.setMinHeight(400);
        palco.setScene(scene);
        SelecionarClienteController.selecionarclientecontroller.paremetroString("empresa");
        palco.show();
    }

    public void setaALabel(String t) {
        Lcliente.setText(t);
        Lcliente.setTextFill(Color.BLACK);
        Lcliente.setFont(Font.font("System", FontWeight.BOLD, 12));
        Lcliente.setOpacity(1);
    }

    public Empresas setarEmpresa() {
        Empresas setarEmpresa = new Empresas();
        try {
            setarEmpresa.setIDCliente(cliente);
        } catch (NullPointerException erro) {
        }
        if (ENome.getText().trim().isEmpty()) {
            Avisos avisos = new Avisos();
            ENome.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            avisos.alerta2();
        } else {
            ENome.setStyle("-fx-border-color: null ; ");
            setarEmpresa.setNome(ENome.getText().toUpperCase());
        }
        if (setarEmpresa.getIDCliente() == null) {
            Lcliente.setTextFill(Color.RED);
            Lcliente.setOpacity(1);
            Avisos avisos = new Avisos();
            avisos.alerta2();
        }
        setarEmpresa.setCnpj(ECNPJ.getText());
        if (Edata.getValue() == null) {
            Avisos avisos = new Avisos();
            Edata.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            avisos.alerta2();
        } else {
            Edata.setStyle("-fx-border-color: null;");
            setarEmpresa.setDataAbertura(java.sql.Date.valueOf(Edata.getValue()));
        }
        try {
            if (EValorHonorario.getText().trim().isEmpty()) {
                EValorHonorario.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                Avisos avisos = new Avisos();
                avisos.alerta2();
            } else {
                EValorHonorario.setStyle("-fx-border-color: null;");
                setarEmpresa.setValorHonorario(BigDecimal.valueOf(Float.valueOf(EValorHonorario.getText())));
            }
        } catch (NullPointerException erro) {
            EValorHonorario.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            Avisos avisos = new Avisos();
            avisos.alerta2();
        }
        setarEmpresa.setEmail(EEmail.getText().toUpperCase());
        setarEmpresa.setTelefone(ETelefone.getText());
        setarEmpresa.setCelular(ECelular.getText());
        setarEmpresa.setSenhaSimplesNacional(ESenhaSimples.getText());
        setarEmpresa.setSenhaCertificado(ESenhaCertificado.getText());
        setarEmpresa.setSenhaNfe(ESenhaNFE.getText());
        setarEmpresa.setIDCliente(cliente);
        return setarEmpresa;
    }

    @FXML
    public void salvarEmpresa() {
        int flag = 0;
        Empresas salvarEmpresas = new Empresas();
        salvarEmpresas = setarEmpresa();
        if (salvarEmpresas.getNome() != null) {
            flag++;
        }
        if (salvarEmpresas.getDataAbertura() != null) {
            flag++;
        }
        if (salvarEmpresas.getIDCliente() != null) {
            flag++;
        }
        if (salvarEmpresas.getValorHonorario() != null) {
            flag++;
        }
        if (flag == 4) {
            empresasDAO.adicionarEmpresa(salvarEmpresas);
            listaEmpresas.add(salvarEmpresas);
            TabelaClientes.refresh();
            limpar();
        }
    }

    public void limpar() {
        ENome.clear();
        ECNPJ.clear();
        Edata.setValue(null);
        EValorHonorario.clear();
        Lcliente.setText("Selecione ->");
        Lcliente.setTextFill(Color.BLACK);
        Lcliente.setFont(Font.font("System", FontWeight.NORMAL, 12));
        Lcliente.setOpacity(0.4);
        EEmail.clear();
        ETelefone.clear();
        ECelular.clear();
        ESenhaSimples.clear();
        ESenhaCertificado.clear();
        ESenhaNFE.clear();
    }

    @FXML
    public void SelecionarEmpresa() {
        Clientes clienteSelecionado = new Clientes();
        ClienteDAO clienteDAO = new ClienteDAO();
        ENome.setText(TabelaClientes.getSelectionModel().getSelectedItem().getNome());
        ECNPJ.setText(TabelaClientes.getSelectionModel().getSelectedItem().getCnpj());
        if (TabelaClientes.getSelectionModel().getSelectedItem().getDataAbertura() != null) {
            java.util.Date dat = TabelaClientes.getSelectionModel().getSelectedItem().getDataAbertura();
            LocalDate dat2 = new java.sql.Date(dat.getTime()).toLocalDate();
            Edata.setValue(dat2);
        } else {
            Edata.setValue(null);
        }
        try {
            EValorHonorario.setText(TabelaClientes.getSelectionModel().getSelectedItem().getValorHonorario().toString());
        } catch (NullPointerException erro) {
            EValorHonorario.setText(null);
        }
        clienteSelecionado = TabelaClientes.getSelectionModel().getSelectedItem().getIDCliente();
        Lcliente.setText(clienteSelecionado.getNome());
        empresa.setIDCliente(clienteSelecionado);
        EEmail.setText(TabelaClientes.getSelectionModel().getSelectedItem().getEmail());
        ETelefone.setText(TabelaClientes.getSelectionModel().getSelectedItem().getTelefone());
        ECelular.setText(TabelaClientes.getSelectionModel().getSelectedItem().getCelular());
        ESenhaSimples.setText(TabelaClientes.getSelectionModel().getSelectedItem().getSenhaSimplesNacional());
        ESenhaCertificado.setText(TabelaClientes.getSelectionModel().getSelectedItem().getSenhaCertificado());
        ESenhaNFE.setText(TabelaClientes.getSelectionModel().getSelectedItem().getSenhaNfe());
        BNovo.setDisable(true);
        BSalvar.setDisable(false);
    }

    @FXML
    public void editarEmpresa() throws Exception {
        int flag = 0;
        Empresas editarEmpresas = new Empresas();
        editarEmpresas = setarEmpresa();
        editarEmpresas.setIDEmpresa(TabelaClientes.getSelectionModel().getSelectedItem().getIDEmpresa());
        try {
            if (editarEmpresas.getIDCliente().getIDCliente() != null) {
                BNovo.setDisable(false);
                BSalvar.setDisable(true);
            }
        } catch (NullPointerException erro) {
            editarEmpresas.setIDCliente(cliente);
        }
        if (editarEmpresas.getIDEmpresa() != null) {
            flag++;
        }
        if (editarEmpresas.getNome() != null) {
            flag++;
        }
        if (editarEmpresas.getDataAbertura() != null) {
            flag++;
        }
        if (editarEmpresas.getIDCliente() != null) {
            flag++;
        }
        if (editarEmpresas.getValorHonorario() != null) {
            flag++;
        }
        if (flag == 5) {
            empresasDAO.alterarEmpresa(editarEmpresas);
            deletardaLista();
            listaEmpresas.add(editarEmpresas);
            TabelaClientes.refresh();
        }
        cliente = null;
    }

    public void deletardaLista() throws NonexistentEntityException, IllegalOrphanException {
        ObservableList<Empresas> unidadeselecionada, todasunidades;
        todasunidades = TabelaClientes.getItems();
        unidadeselecionada = TabelaClientes.getSelectionModel().getSelectedItems();
        unidadeselecionada.forEach(todasunidades::remove);
        limpar();
        TabelaClientes.refresh();
    }

    @FXML
    public void deletar() throws NonexistentEntityException, IllegalOrphanException {
        Empresas deletarEmpresas = new Empresas();
        deletarEmpresas = TabelaClientes.getSelectionModel().getSelectedItem();
        if (!TabelaClientes.getSelectionModel().isEmpty()) {
            empresasDAO.removerEmpresa(TabelaClientes.getSelectionModel().getSelectedItem().getIDEmpresa());
            deletardaLista();
            BNovo.setDisable(false);
            BSalvar.setDisable(true);
        } else {
            Avisos avisos = new Avisos();
            avisos.alerta2();
        }
        cliente = null;
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
