package com.example.water_supply.controller.pipelinesection;

import com.example.water_supply.WaterApp;
import com.example.water_supply.model.PipelineSection;
import com.example.water_supply.service.PipelineSectionService;
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

public class PipelineSectionController {

    private List<PipelineSection> sections;
    private ObservableList<PipelineSectionTableItem> sectionsObservable;

    @FXML private TableView<PipelineSectionTableItem> sectionsTable;
    @FXML private TableColumn<PipelineSection, String> sectionNumberColumn;
    @FXML private TableColumn<PipelineSection, String> pipelineTypeColumn;
    @FXML private TableColumn<PipelineSection, String> statusColumn;
    @FXML private TableColumn<PipelineSection, String> installationDateColumn;
    @FXML private TableColumn<PipelineSection, String> lengthColumn;


    @FXML
    void switchToTypes(ActionEvent event) {
        WaterApp.primaryStage.setScene(WaterApp.pipelineTypesScene);
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
    void addSection(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(WaterApp.class.getResource("add-edit-pipeline-section-dialog.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(WaterApp.primaryStage);
            dialogStage.setMinWidth(400);
            dialogStage.setScene(new Scene(loader.load()));
            dialogStage.setTitle("Добавить участок сети");

            AddEditPipelineSectionDialog controller = loader.getController();
            controller.setAddDialogStage(dialogStage);
            dialogStage.showAndWait();
            updateList();
        } catch (IOException e) {
            showAlert("Ошибка", "Ошибка открытия окна: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void editSection(ActionEvent event) {
        PipelineSectionTableItem currentItem = sectionsTable.getSelectionModel().getSelectedItem();
        if (currentItem != null) {
            try {
                FXMLLoader loader = new FXMLLoader(WaterApp.class.getResource("add-edit-pipeline-section-dialog.fxml"));
                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(WaterApp.primaryStage);
                dialogStage.setMinWidth(400);
                dialogStage.setScene(new Scene(loader.load()));
                dialogStage.setTitle("Редактировать участок сети");

                AddEditPipelineSectionDialog controller = loader.getController();
                controller.setEditDialogStage(dialogStage, currentItem.getPipelineSection());
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
    void deleteSection(ActionEvent event) {
        PipelineSectionTableItem currentItem = sectionsTable.getSelectionModel().getSelectedItem();
        if (currentItem != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Подтверждение удаления");
            alert.setHeaderText("Удаление записи");
            alert.setContentText("Вы действительно хотите удалить участок \"" + currentItem.getSectionNumber() + "\"?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                new PipelineSectionService().delete(currentItem.getPipelineSection());
                updateList();
            }
        } else {
            showAlert("Предупреждение", "Выберите запись в таблице для удаления", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void updateSections(ActionEvent event) {
        updateList();
    }

    private void updateList() {
        sections = new PipelineSectionService().findAll();
        sectionsObservable = FXCollections.observableArrayList();
        for (PipelineSection section : sections) {
            sectionsObservable.add(new PipelineSectionTableItem(section));
        }
        sectionsTable.setItems(sectionsObservable);
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void initialize() {
        sectionNumberColumn.setCellValueFactory(new PropertyValueFactory<>("sectionNumber"));
        pipelineTypeColumn.setCellValueFactory(new PropertyValueFactory<>("pipelineTypeName"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        installationDateColumn.setCellValueFactory(new PropertyValueFactory<>("installationDate"));
        lengthColumn.setCellValueFactory(new PropertyValueFactory<>("length"));
        updateList();
    }
}