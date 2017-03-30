package dpaulacontabilidade;

import DAO.ClienteDAO;
import Entidades.Clientes;
import java.io.IOException;
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
import javafx.scene.control.cell.PropertyValueFactory;

public class SelecionarClienteController implements Initializable {

    @FXML
    private TableView<Clientes> TabelaCliente;
    @FXML
    private TableColumn<Clientes, String> CCliente;
    @FXML
    private Button BCancela;
    @FXML
    private Button Bselecionar;

    private final ObservableList<Clientes> listaClientes = FXCollections.observableArrayList();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private String x = "";
    static SelecionarClienteController selecionarclientecontroller;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        selecionarclientecontroller=this;
        listaClientes.addAll(clienteDAO.pegartodosClientes());
        CCliente.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        TabelaCliente.setItems(listaClientes);
    }

    public void paremetroString(String nome) {
        this.x = nome;
    }

    public void selecionar() throws IOException {
        if ("empresa".equals(x)) {
            String nomeCliente = TabelaCliente.getSelectionModel().getSelectedItem().getNome();
            EmpresaController.cliente = TabelaCliente.getSelectionModel().getSelectedItem();
            EmpresaController.controle.setaALabel(nomeCliente);
            EmpresaController.palco.close();
        }
        if ("receita".equals(x)) {
            ReceitaController.receitacontroller.setaALabelCliente(TabelaCliente.getSelectionModel().getSelectedItem().getNome());
            ReceitaController.itensSeleciondos.setIDCliente(TabelaCliente.getSelectionModel().getSelectedItem());
            ReceitaController.receitacontroller.verificacaoDeCliente(1);
            ReceitaController.palco.close();
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        ReceitaController.palco.close();
        EmpresaController.palco.close();
    }
}
