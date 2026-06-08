package com.example.water_supply.controller.watermeasurement;

import com.example.water_supply.WaterApp;
import com.example.water_supply.model.WaterMeasurement;
import com.example.water_supply.service.WaterMeasurementService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class WaterMeasurementController {

    private List<WaterMeasurement> measurements;
    private ObservableList<WaterMeasurementTableItem> measurementsObservable;

    @FXML private TableView<WaterMeasurementTableItem> measurementsTable;
    @FXML private TableColumn<WaterMeasurement, String> measurementTimeColumn;
    @FXML private TableColumn<WaterMeasurement, String> pipelineSectionColumn;
    @FXML private TableColumn<WaterMeasurement, String> districtNameColumn;
    @FXML private TableColumn<WaterMeasurement, String> pressureColumn;
    @FXML private TableColumn<WaterMeasurement, String> flowVolumeColumn;


    @FXML
    void switchToTypes(ActionEvent event) {
        WaterApp.primaryStage.setScene(WaterApp.pipelineTypesScene);
    }

    @FXML
    void switchToSections(ActionEvent event) {
        WaterApp.primaryStage.setScene(WaterApp.pipelineSectionsScene);
    }

    @FXML
    void switchToDistricts(ActionEvent event) {
        WaterApp.primaryStage.setScene(WaterApp.consumptionDistrictsScene);
    }

    @FXML
    void powerOff(ActionEvent event) {
        WaterApp.primaryStage.close();
    }


    @FXML
    void addMeasurement(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(WaterApp.class.getResource("add-edit-water-measurement-dialog.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(WaterApp.primaryStage);
            dialogStage.setMinWidth(400);
            dialogStage.setScene(new Scene(loader.load()));
            dialogStage.setTitle("Добавить замер показателей");

            AddEditWaterMeasurementDialog controller = loader.getController();
            controller.setAddDialogStage(dialogStage);
            dialogStage.showAndWait();
            updateList();
        } catch (IOException e) {
            showAlert("Ошибка", "Ошибка открытия окна: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void editMeasurement(ActionEvent event) {
        WaterMeasurementTableItem currentItem = measurementsTable.getSelectionModel().getSelectedItem();
        if (currentItem != null) {
            try {
                FXMLLoader loader = new FXMLLoader(WaterApp.class.getResource("add-edit-water-measurement-dialog.fxml"));
                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(WaterApp.primaryStage);
                dialogStage.setMinWidth(400);
                dialogStage.setScene(new Scene(loader.load()));
                dialogStage.setTitle("Редактировать замер показателей");

                AddEditWaterMeasurementDialog controller = loader.getController();
                controller.setEditDialogStage(dialogStage, currentItem.getWaterMeasurement());
                dialogStage.showAndWait();
                updateList();
            } catch (IOException e) {
                showAlert("Ошибка", "Ошибка открытия окна: " + e.getMessage(), Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Предупреждение", "Выберите запись в таблице для редактирования", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void deleteMeasurement(ActionEvent event) {
        WaterMeasurementTableItem currentItem = measurementsTable.getSelectionModel().getSelectedItem();
        if (currentItem != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Подтверждение удаления");
            alert.setHeaderText("Удаление записи");
            alert.setContentText("Вы действительно хотите удалить замер от " + currentItem.getMeasurementTime() + "?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                new WaterMeasurementService().delete(currentItem.getWaterMeasurement());
                updateList();
            }
        } else {
            showAlert("Предупреждение", "Выберите запись в таблице для удаления", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void updateMeasurements(ActionEvent event) {
        updateList();
    }

    private void updateList() {
        measurements = new WaterMeasurementService().findAll();
        measurementsObservable = FXCollections.observableArrayList();
        for (WaterMeasurement measurement : measurements) {
            measurementsObservable.add(new WaterMeasurementTableItem(measurement));
        }
        measurementsTable.setItems(measurementsObservable);
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void initialize() {
        measurementTimeColumn.setCellValueFactory(new PropertyValueFactory<>("measurementTime"));
        pipelineSectionColumn.setCellValueFactory(new PropertyValueFactory<>("pipelineSectionNumber"));
        districtNameColumn.setCellValueFactory(new PropertyValueFactory<>("districtName"));
        pressureColumn.setCellValueFactory(new PropertyValueFactory<>("pressure"));
        flowVolumeColumn.setCellValueFactory(new PropertyValueFactory<>("flowVolume"));
        updateList();
    }
}