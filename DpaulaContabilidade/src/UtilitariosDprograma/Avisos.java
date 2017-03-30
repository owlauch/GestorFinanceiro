package UtilitariosDprograma;

import javafx.scene.control.Alert;

public class Avisos{

    public void alerta() {
        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        dialogoInfo.setTitle("Diálogo de informação");
        dialogoInfo.setHeaderText("Esse é o cabeçalho...");
        dialogoInfo.setContentText("Informação importante!");
        dialogoInfo.showAndWait();
    }

    public void erro() {
        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
        dialogoErro.setTitle("Diálogo de Error");
        dialogoErro.setHeaderText("Esse é o cabeçalho...");
        dialogoErro.setContentText("UM ERROR!!! UM ERRO ACONTECEU!!! GEZUIS!");
        dialogoErro.showAndWait();
    }

    public void alerta2() {
        Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
        dialogoAviso.setTitle("OBJETO NULO");
        dialogoAviso.setHeaderText("O Item Não Foi Preenchido ou Selecionado");
        dialogoAviso.setContentText("Sem a Seleção a Alteração ou Exclusão Não Funionara");
        dialogoAviso.showAndWait();
    }
}
