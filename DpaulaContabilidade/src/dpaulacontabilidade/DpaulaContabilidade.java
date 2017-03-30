package dpaulacontabilidade;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DpaulaContabilidade extends Application {

    public static String screen1ID = "main";
    public static String screen1File = "MenuPrincipal.fxml";
    public static String screen2ID = "Cliente";
    public static String screen2File = "Cliente.fxml";
    public static String screen3ID = "Empresa";
    public static String screen3File = "Empresa.fxml";
    public static String screen4ID = "Serviços";
    public static String screen4File = "Serviços.fxml";
    public static String screen5ID = "Carteira";
    public static String screen5File = "Carteira.fxml";
    public static String screen6ID = "UnidadeDeCustos";
    public static String screen6File = "UnidadeDeCustos.fxml";
    public static String screen7ID = "Receitas";
    public static String screen7File = "Receita.fxml";
    public static String screen8ID = "Despesas";
    public static String screen8File = "Despesas.fxml";
    public static String screen9ID = "Destinos";
    public static String screen9File = "Destinos.fxml";
    public static String screen10ID = "APagar";
    public static String screen10File = "APagar.fxml";
    public static String screen11ID = "AReceber";
    public static String screen11File = "AReceber.fxml";
    public static String screen12ID = "Emprestimos";
    public static String screen12File = "Emprestimos.fxml";
    public static String screen13ID = "ReceitaSimples";
    public static String screen13File = "ReceitaSimples.fxml";
    public static String screen14ID = "Transferencias";
    public static String screen14File = "Transferencias.fxml";

    @Override
    public void start(Stage primaryStage) {

        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(DpaulaContabilidade.screen1ID, DpaulaContabilidade.screen1File);
        mainContainer.loadScreen(DpaulaContabilidade.screen2ID, DpaulaContabilidade.screen2File);
        mainContainer.loadScreen(DpaulaContabilidade.screen3ID, DpaulaContabilidade.screen3File);
        mainContainer.loadScreen(DpaulaContabilidade.screen4ID, DpaulaContabilidade.screen4File);
        mainContainer.loadScreen(DpaulaContabilidade.screen5ID, DpaulaContabilidade.screen5File);
        mainContainer.loadScreen(DpaulaContabilidade.screen6ID, DpaulaContabilidade.screen6File);
        mainContainer.loadScreen(DpaulaContabilidade.screen7ID, DpaulaContabilidade.screen7File);
        mainContainer.loadScreen(DpaulaContabilidade.screen8ID, DpaulaContabilidade.screen8File);
        mainContainer.loadScreen(DpaulaContabilidade.screen9ID, DpaulaContabilidade.screen9File);
        mainContainer.loadScreen(DpaulaContabilidade.screen10ID, DpaulaContabilidade.screen10File);
        mainContainer.loadScreen(DpaulaContabilidade.screen11ID, DpaulaContabilidade.screen11File);
        mainContainer.loadScreen(DpaulaContabilidade.screen12ID, DpaulaContabilidade.screen12File);
        mainContainer.loadScreen(DpaulaContabilidade.screen13ID, DpaulaContabilidade.screen13File);
        mainContainer.loadScreen(DpaulaContabilidade.screen14ID, DpaulaContabilidade.screen14File);
        mainContainer.setScreen(DpaulaContabilidade.screen1ID);
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMaxHeight(640);
        primaryStage.setMaxWidth(1015);
        primaryStage.setMinHeight(640);
        primaryStage.setMinWidth(1015);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
