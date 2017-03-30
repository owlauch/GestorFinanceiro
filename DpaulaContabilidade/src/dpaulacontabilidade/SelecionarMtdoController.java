
package dpaulacontabilidade;

import DAO.MetodoDePagamentoDAO;
import Entidades.Metodopagamento;
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


public class SelecionarMtdoController implements Initializable {

    @FXML
    private Button Bselecionar;
    @FXML
    private Button BCancela;
    @FXML
    private TableView<Metodopagamento> TabelaCliente;
    @FXML
    private TableColumn<Metodopagamento, String> CCliente;
    
    private final MetodoDePagamentoDAO mtdoDAO = new MetodoDePagamentoDAO();
    private final ObservableList<Metodopagamento>listademtdo=FXCollections.observableArrayList();
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setarTabela();
    }
    @FXML
    public void setarTabela(){
        listademtdo.addAll(mtdoDAO.pegartodosMetodopagamento());
        CCliente.setCellValueFactory(new PropertyValueFactory<>("descrição"));
        TabelaCliente.setItems(listademtdo);
    }

    @FXML
    private void selecionar(ActionEvent event)throws IOException {
        ReceitaController.itensSeleciondos.setIDMetodoPagamento(TabelaCliente.getSelectionModel().getSelectedItem());
        String value=TabelaCliente.getSelectionModel().getSelectedItem().getDescrição();
        System.out.println(value);
        ReceitaController.receitacontroller.setaALabelMtdopgto(value);
        ReceitaController.palco.close();
    }
    

    @FXML
    private void cancelar(ActionEvent event) {
        ReceitaController.palco.close();
    }
    
}
