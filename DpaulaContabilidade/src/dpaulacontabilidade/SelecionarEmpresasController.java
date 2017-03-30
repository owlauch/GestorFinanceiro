/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpaulacontabilidade;

import DAO.EmpresasDAO;
import Entidades.Empresas;
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

public class SelecionarEmpresasController implements Initializable {

    @FXML
    private Button Bselecionar;
    @FXML
    private Button BCancela;
    @FXML
    private TableView<Empresas> TabelaCliente;
    @FXML
    private TableColumn<Empresas, String> CCliente;

    private final EmpresasDAO empresasDAO = new EmpresasDAO();
    private final ObservableList<Empresas> lstEmpresas = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setarTabela();

    }

    @FXML
    public void setarTabela() {
        lstEmpresas.addAll(empresasDAO.pegartodosEmpresas());
        CCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TabelaCliente.setItems(lstEmpresas);
    }

    @FXML
    private void selecionar(ActionEvent event) {
        ReceitaController.itensSeleciondos.setIDEmpresa(TabelaCliente.getSelectionModel().getSelectedItem());
        ReceitaController.receitacontroller.setaALabelEmpresa(TabelaCliente.getSelectionModel().getSelectedItem().getNome());
        ReceitaController.receitacontroller.verificacaoDeCliente(2);
        ReceitaController.palco.close();
    }

    @FXML
    private void cancelar(ActionEvent event) {
        ReceitaController.palco.close();
    }

}
