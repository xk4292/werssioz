package com.example.water_supply.controller.pipelinesection;

import com.example.water_supply.model.PipelineSection;
import com.example.water_supply.model.PipelineType;
import com.example.water_supply.service.PipelineSectionService;
import com.example.water_supply.service.PipelineTypeService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class AddEditPipelineSectionDialog {
    @FXML private ComboBox<PipelineType> pipelineTypeField;
    @FXML private TextField sectionNumberField;
    @FXML private ComboBox<String> statusField;
    @FXML private DatePicker installationDateField;
    @FXML private TextField lengthField;
    @FXML private Button okButton;
    @FXML private Label errorLabel;

    private Stage dialogStage;
    private PipelineSection pipelineSection;

    @FXML
    void initialize() {
        statusField.getItems().addAll("Активный", "На ремонте", "Заброшен", "Планируется");
        List<PipelineType> types = new PipelineTypeService().findAll();
        pipelineTypeField.getItems().addAll(FXCollections.observableList(types));
    }

    void add() {
        try {
            PipelineSection section = new PipelineSection();
            section.setPipelineType(pipelineTypeField.getValue());
            section.setSectionNumber(sectionNumberField.getText());
            section.setStatus(statusField.getValue());
            section.setInstallationDate(installationDateField.getValue());
            section.setLength(lengthField.getText());

            new PipelineSectionService().save(section);
            dialogStage.close();
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    void edit() {
        try {
            pipelineSection.setPipelineType(pipelineTypeField.getValue());
            pipelineSection.setSectionNumber(sectionNumberField.getText());
            pipelineSection.setStatus(statusField.getValue());
            pipelineSection.setInstallationDate(installationDateField.getValue());
            pipelineSection.setLength(lengthField.getText());

            new PipelineSectionService().update(pipelineSection);
            dialogStage.close();
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    public void setAddDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        okButton.setOnAction(e -> add());
    }

    public void setEditDialogStage(Stage dialogStage, PipelineSection section) {
        this.pipelineSection = section;
        this.dialogStage = dialogStage;

        pipelineTypeField.setValue(section.getPipelineType());
        sectionNumberField.setText(section.getSectionNumber());
        statusField.setValue(section.getStatus());
        installationDateField.setValue(section.getInstallationDate());
        lengthField.setText(section.getLength().toString());

        okButton.setOnAction(e -> edit());
    }
}