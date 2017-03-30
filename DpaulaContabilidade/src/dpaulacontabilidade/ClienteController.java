/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpaulacontabilidade;

import UtilitariosDprograma.Avisos;
import ControleJPA.exceptions.IllegalOrphanException;
import ControleJPA.exceptions.NonexistentEntityException;
import DAO.ClienteDAO;
import Entidades.Clientes;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClienteController implements Initializable, ControlledScreen {

    @FXML
    private TableView<Clientes> TabelaClientes;
    @FXML
    private TableColumn<Clientes, String> TNome;
    @FXML
    private TableColumn<Clientes, String> TCPF;
    @FXML
    private TableColumn<Clientes, String> TCelular;
    @FXML
    private TableColumn<Clientes, String> TTelefone;
    @FXML
    private TextField ENome;
    @FXML
    private TextField ECpf;
    @FXML
    private TextField ERg;
    @FXML
    private DatePicker Edata;
    @FXML
    private TextField ETitulo;
    @FXML
    private TextField ETelefone;
    @FXML
    private TextField ECelular;
    @FXML
    private Button BNovo;
    @FXML
    private Button BDeletar;
    @FXML
    private TextField EeMail;
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
    private final ObservableList<Clientes> listaClientes = FXCollections.observableArrayList();
    private final ClienteDAO clienteDao = new ClienteDAO();
    private final Clientes cliente = new Clientes();
    private final Avisos avisos = new Avisos();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaClientes.addAll(clienteDao.pegartodosClientes());
        TNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        TCPF.setCellValueFactory(new PropertyValueFactory<>("Cpf"));
        TCelular.setCellValueFactory(new PropertyValueFactory<>("Celular"));
        TTelefone.setCellValueFactory(new PropertyValueFactory<>("Telefone"));
        TabelaClientes.setItems(listaClientes);
    }

    public Clientes setarCliente() {
        Clientes nCliente = new Clientes();
        if (!ENome.getText().trim().isEmpty()) {
            nCliente.setNome(ENome.getText().toUpperCase());
        } else {
            ENome.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            avisos.alerta2();
        }
        if (Edata.getValue() != null) {
            nCliente.setDataNascimento(java.sql.Date.valueOf(Edata.getValue()));
        }
        nCliente.setCpf(ECpf.getText());
        nCliente.setEmail(EeMail.getText().toUpperCase());
        nCliente.setRg(ERg.getText());
        nCliente.setTitulo(ETitulo.getText());
        nCliente.setTelefone(ETelefone.getText());
        nCliente.setCelular(ECelular.getText());
        return nCliente;
    }

    @FXML
    public void novo(ActionEvent event) {
        Clientes x = new Clientes();
        x = setarCliente();
        if (!ENome.getText().trim().isEmpty()) {
            clienteDao.adicionarCliente(x);
            listaClientes.add(x);
            TabelaClientes.refresh();
        }
        Limpar();
        ENome.setStyle("-fx-border-color: null ; -fx-border-width: 2px ;");
    }

    @FXML
    public void SalvarEdicao() throws Exception {
        Clientes cliE = new Clientes();
        int idcliente;
        cliE = TabelaClientes.getSelectionModel().getSelectedItem();
        idcliente = cliE.getIDCliente();
        cliE = setarCliente();
        cliE.setIDCliente(idcliente);
        BNovo.setDisable(false);
        BSalvar.setDisable(true);
        try {
            clienteDao.alterarCliente(cliE);
        } catch (NullPointerException erro) {
        }
        deletardaLista();
        listaClientes.add(cliE);
        TabelaClientes.refresh();
    }

    @FXML
    public void Selecionar() {
        if (TabelaClientes.getSelectionModel().getSelectedItem() != null) {
            Limpar();
            Date data = TabelaClientes.getSelectionModel().getSelectedItem().getDataNascimento();
            ENome.setText(TabelaClientes.getSelectionModel().getSelectedItem().getNome());
            try {
                if (data != null) {
                    Edata.setValue(data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                }
            } catch (UnsupportedOperationException erro) {
                java.util.Date dat = TabelaClientes.getSelectionModel().getSelectedItem().getDataNascimento();
                LocalDate dat2 = new java.sql.Date(dat.getTime()).toLocalDate();
                Edata.setValue(dat2);
            }
            ECpf.setText(TabelaClientes.getSelectionModel().getSelectedItem().getCpf());
            EeMail.setText(TabelaClientes.getSelectionModel().getSelectedItem().getEmail());
            ERg.setText(TabelaClientes.getSelectionModel().getSelectedItem().getRg());
            ETitulo.setText(TabelaClientes.getSelectionModel().getSelectedItem().getTitulo());
            ETelefone.setText(TabelaClientes.getSelectionModel().getSelectedItem().getTelefone());
            ECelular.setText(TabelaClientes.getSelectionModel().getSelectedItem().getCelular());
            BSalvar.setDisable(false);
            BNovo.setDisable(true);
            BDeletar.setDisable(false);
        }
    }

    @FXML
    public void deletar() throws NonexistentEntityException, IllegalOrphanException {
        if (!TabelaClientes.getSelectionModel().isEmpty()) {
            clienteDao.removerCliente(TabelaClientes.getSelectionModel().getSelectedItem().getIDCliente());
            deletardaLista();
            BNovo.setDisable(false);
            BSalvar.setDisable(true);
        } else {
            avisos.alerta2();
        }
    }

    public void deletardaLista() {
        ObservableList<Clientes> unidadeselecionada, todasunidades;
        todasunidades = TabelaClientes.getItems();
        unidadeselecionada = TabelaClientes.getSelectionModel().getSelectedItems();
        unidadeselecionada.forEach(todasunidades::remove);
        Limpar();
        TabelaClientes.refresh();
    }

    public void Limpar() {
        ENome.clear();
        Edata.setValue(null);
        ECpf.clear();
        EeMail.clear();
        ERg.clear();
        ETitulo.clear();
        ETelefone.clear();
        ECelular.clear();
    }

    public int pegarid() {
        int i = 0;
        List<Clientes> allclientes = clienteDao.pegartodosClientes();
        if (!allclientes.isEmpty()) {
            for (Clientes Objeto : allclientes) {
                if (Objeto.getIDCliente() > i) {
                    i = Objeto.getIDCliente();
                }
            }
        }
        return i;
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
