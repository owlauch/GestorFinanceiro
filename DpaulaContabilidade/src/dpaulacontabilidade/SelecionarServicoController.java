/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpaulacontabilidade;

import DAO.ServicosDAO;
import Entidades.Servicos;
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

/**
 * FXML Controller class
 *
 * @author owlau
 */
public class SelecionarServicoController implements Initializable {

    @FXML
    private Button Bselecionar;
    @FXML
    private Button BCancela;
    @FXML
    private TableView<Servicos> TabelaCliente;
    @FXML
    private TableColumn<Servicos, String> CCliente;

    private final ServicosDAO servicosDAO = new ServicosDAO();
    private final ObservableList<Servicos> lstServicos = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setarTabela();
    }
    public void setarTabela(){
        lstServicos.addAll(servicosDAO.pegartodosServicos());
        CCliente.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        TabelaCliente.setItems(lstServicos);
    }

    @FXML
    private void selecionar(ActionEvent event) {
        ReceitaController.itensSeleciondos.setIDServico(TabelaCliente.getSelectionModel().getSelectedItem());
        ReceitaController.receitacontroller.setaALabelservico(TabelaCliente.getSelectionModel().getSelectedItem().getDescricao(),TabelaCliente.getSelectionModel().getSelectedItem().getValor());
        ReceitaController.palco.close();
    }

    @FXML
    private void cancelar(ActionEvent event) {
        ReceitaController.palco.close();
    }

}
