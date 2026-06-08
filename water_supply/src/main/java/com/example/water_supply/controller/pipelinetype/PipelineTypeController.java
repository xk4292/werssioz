package com.example.water_supply.controller.pipelinetype;

import com.example.water_supply.WaterApp;
import com.example.water_supply.model.PipelineType;
import com.example.water_supply.service.PipelineTypeService;
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

public class PipelineTypeController {

    private List<PipelineType> pipelineTypes;
    private ObservableList<PipelineTypeTableItem> pipelineTypesObservable;

    @FXML
    private TableView<PipelineTypeTableItem> pipelineTypesTable;

    @FXML
    private TableColumn<PipelineType, String> nameColumn;

    @FXML
    private TableColumn<PipelineType, Integer> diameterColumn;

    @FXML
    private TableColumn<PipelineType, String> materialColumn;

    @FXML
    private TableColumn<PipelineType, String> pressureStandardColumn;

    @FXML
    private TableColumn<PipelineType, Integer> serviceLifeColumn;


    @FXML
    void switchToSections(ActionEvent event) {
        WaterApp.primaryStage.setScene(WaterApp.pipelineSectionsScene);
    }

    @FXML
    void switchToDistricts(ActionEvent event) {
        WaterApp.primaryStage.setScene(WaterApp.consumptionDistrictsScene);
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
    void addPipelineType(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(WaterApp.class.getResource("add-edit-pipeline-type-dialog.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(WaterApp.primaryStage);
            dialogStage.setMinWidth(400);
            dialogStage.setScene(new Scene(loader.load()));
            dialogStage.setTitle("Добавить тип трубопровода");

            AddEditPipelineTypeDialog controller = loader.getController();
            controller.setAddDialogStage(dialogStage);
            dialogStage.showAndWait();
            updateList();
        } catch (IOException e) {
            showAlert("Ошибка", "Ошибка открытия окна: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void editPipelineType(ActionEvent event) {
        PipelineTypeTableItem currentItem = pipelineTypesTable.getSelectionModel().getSelectedItem();

        if (currentItem != null) {
            try {
                FXMLLoader loader = new FXMLLoader(WaterApp.class.getResource("add-edit-pipeline-type-dialog.fxml"));
                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(WaterApp.primaryStage);
                dialogStage.setMinWidth(400);
                dialogStage.setScene(new Scene(loader.load()));
                dialogStage.setTitle("Редактировать тип трубопровода");

                AddEditPipelineTypeDialog controller = loader.getController();
                controller.setEditDialogStage(dialogStage, currentItem.getPipelineType());
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
    void deletePipelineType(ActionEvent event) {
        PipelineTypeTableItem currentItem = pipelineTypesTable.getSelectionModel().getSelectedItem();

        if (currentItem != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Подтверждение удаления");
            alert.setHeaderText("Удаление записи");
            alert.setContentText("Вы действительно хотите удалить \"" + currentItem.getName() + "\"?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                new PipelineTypeService().delete(currentItem.getPipelineType());
                updateList();
            }
        } else {
            showAlert("Предупреждение", "Выберите запись в таблице для удаления", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void updatePipelineTypes(ActionEvent event) {
        updateList();
        showAlert("Информация", "Список обновлен", Alert.AlertType.INFORMATION);
    }

    private void updateList() {
        pipelineTypes = new PipelineTypeService().findAll();
        pipelineTypesObservable = FXCollections.observableArrayList();
        for (PipelineType type : pipelineTypes) {
            pipelineTypesObservable.add(new PipelineTypeTableItem(type));
        }
        pipelineTypesTable.setItems(pipelineTypesObservable);
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        diameterColumn.setCellValueFactory(new PropertyValueFactory<>("diameter"));
        materialColumn.setCellValueFactory(new PropertyValueFactory<>("material"));
        pressureStandardColumn.setCellValueFactory(new PropertyValueFactory<>("pressureStandard"));
        serviceLifeColumn.setCellValueFactory(new PropertyValueFactory<>("serviceLife"));
        updateList();
    }
}