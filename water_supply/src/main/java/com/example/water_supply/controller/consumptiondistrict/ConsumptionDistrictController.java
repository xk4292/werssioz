package com.example.water_supply.controller.consumptiondistrict;

import com.example.water_supply.WaterApp;
import com.example.water_supply.model.ConsumptionDistrict;
import com.example.water_supply.service.ConsumptionDistrictService;
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

public class ConsumptionDistrictController {

    private List<ConsumptionDistrict> districts;
    private ObservableList<ConsumptionDistrictTableItem> districtsObservable;

    @FXML private TableView<ConsumptionDistrictTableItem> districtsTable;
    @FXML private TableColumn<ConsumptionDistrict, String> districtNameColumn;
    @FXML private TableColumn<ConsumptionDistrict, Integer> houseCountColumn;
    @FXML private TableColumn<ConsumptionDistrict, String> dispatchAddressColumn;
    @FXML private TableColumn<ConsumptionDistrict, String> phoneColumn;


    @FXML
    void switchToTypes(ActionEvent event) {
        WaterApp.primaryStage.setScene(WaterApp.pipelineTypesScene);
    }

    @FXML
    void switchToSections(ActionEvent event) {
        WaterApp.primaryStage.setScene(WaterApp.pipelineSectionsScene);
    }

    @FXML
    void switchToMeasurements(ActionEvent event) {
        WaterApp.primaryStage.setScene(WaterApp.waterMeasurementsScene);
    }

    @FXML
    void powerOff(ActionEvent event) {
        WaterApp.primaryStage.close();
    }


    @FXML
    void addDistrict(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(WaterApp.class.getResource("add-edit-consumption-district-dialog.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(WaterApp.primaryStage);
            dialogStage.setMinWidth(400);
            dialogStage.setScene(new Scene(loader.load()));
            dialogStage.setTitle("Добавить район потребления");

            AddEditConsumptionDistrictDialog controller = loader.getController();
            controller.setAddDialogStage(dialogStage);
            dialogStage.showAndWait();
            updateList();
        } catch (IOException e) {
            showAlert("Ошибка", "Ошибка открытия окна: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void editDistrict(ActionEvent event) {
        ConsumptionDistrictTableItem currentItem = districtsTable.getSelectionModel().getSelectedItem();
        if (currentItem != null) {
            try {
                FXMLLoader loader = new FXMLLoader(WaterApp.class.getResource("add-edit-consumption-district-dialog.fxml"));
                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(WaterApp.primaryStage);
                dialogStage.setMinWidth(400);
                dialogStage.setScene(new Scene(loader.load()));
                dialogStage.setTitle("Редактировать район потребления");

                AddEditConsumptionDistrictDialog controller = loader.getController();
                controller.setEditDialogStage(dialogStage, currentItem.getConsumptionDistrict());
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
    void deleteDistrict(ActionEvent event) {
        ConsumptionDistrictTableItem currentItem = districtsTable.getSelectionModel().getSelectedItem();
        if (currentItem != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Подтверждение удаления");
            alert.setHeaderText("Удаление записи");
            alert.setContentText("Вы действительно хотите удалить \"" + currentItem.getDistrictName() + "\"?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                new ConsumptionDistrictService().delete(currentItem.getConsumptionDistrict());
                updateList();
            }
        } else {
            showAlert("Предупреждение", "Выберите запись в таблице для удаления", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void updateDistricts(ActionEvent event) {
        updateList();
    }

    private void updateList() {
        districts = new ConsumptionDistrictService().findAll();
        districtsObservable = FXCollections.observableArrayList();
        for (ConsumptionDistrict district : districts) {
            districtsObservable.add(new ConsumptionDistrictTableItem(district));
        }
        districtsTable.setItems(districtsObservable);
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void initialize() {
        districtNameColumn.setCellValueFactory(new PropertyValueFactory<>("districtName"));
        houseCountColumn.setCellValueFactory(new PropertyValueFactory<>("houseCount"));
        dispatchAddressColumn.setCellValueFactory(new PropertyValueFactory<>("dispatchAddress"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        updateList();
    }
}