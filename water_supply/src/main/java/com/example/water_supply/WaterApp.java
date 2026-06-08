package com.example.water_supply;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class WaterApp extends Application {

    public static Stage primaryStage;
    public static Scene pipelineTypesScene;
    public static Scene pipelineSectionsScene;
    public static Scene consumptionDistrictsScene;
    public static Scene waterMeasurementsScene;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;

        pipelineTypesScene = createScene("pipeline-type-view.fxml");
        pipelineSectionsScene = createScene("pipeline-section-view.fxml");
        consumptionDistrictsScene = createScene("consumption-district-view.fxml");
        waterMeasurementsScene = createScene("water-measurement-view.fxml");

        primaryStage.setMinWidth(1200);
        primaryStage.setMinHeight(675);
        primaryStage.setTitle("Система водоснабжения");

        primaryStage.setScene(pipelineTypesScene);
        primaryStage.show();
        pipelineSectionsScene.getStylesheets().add("base-styles.css");
        pipelineTypesScene.getStylesheets().add("base-styles.css");
        consumptionDistrictsScene.getStylesheets().add("base-styles.css");
        waterMeasurementsScene.getStylesheets().add("base-styles.css");
    }

    private Scene createScene(String name) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WaterApp.class.getResource(name));
        return new Scene(fxmlLoader.load());
    }

    public static void main(String[] args) {
        launch();
    }
}