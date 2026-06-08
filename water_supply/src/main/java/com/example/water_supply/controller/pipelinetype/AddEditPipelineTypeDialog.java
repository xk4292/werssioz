package com.example.water_supply.controller.pipelinetype;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.example.water_supply.model.PipelineType;
import com.example.water_supply.service.PipelineTypeService;

public class AddEditPipelineTypeDialog {
    @FXML private TextField nameField;
    @FXML private TextField diameterField;
    @FXML private TextField materialField;
    @FXML private TextField pressureStandardField;
    @FXML private TextField serviceLifeField;
    @FXML private Button okButton;
    @FXML private Label errorLabel;
    private Stage dialogStage;
    private PipelineType pipelineType;

    void add() {
        try {
            PipelineType type = new PipelineType();
            type.setName(nameField.getText());
            type.setDiameter(Integer.parseInt(diameterField.getText()));
            type.setMaterial(materialField.getText());
            type.setPressureStandard(pressureStandardField.getText());
            type.setServiceLife(Integer.parseInt(serviceLifeField.getText()));
            new PipelineTypeService().save(type);
            dialogStage.close();
        } catch (NumberFormatException e) {
            errorLabel.setText("Диаметр и срок службы должны быть числами!");
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    void edit() {
        try {
            pipelineType.setName(nameField.getText());
            pipelineType.setDiameter(Integer.parseInt(diameterField.getText()));
            pipelineType.setMaterial(materialField.getText());
            pipelineType.setPressureStandard(pressureStandardField.getText());
            pipelineType.setServiceLife(Integer.parseInt(serviceLifeField.getText()));
            new PipelineTypeService().update(pipelineType);
            dialogStage.close();
        } catch (NumberFormatException e) {
            errorLabel.setText("Диаметр и срок службы должны быть числами!");
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    public void setAddDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        okButton.setOnAction(e -> add());
    }

    public void setEditDialogStage(Stage dialogStage, PipelineType pipelineType) {
        this.pipelineType = pipelineType;
        this.dialogStage = dialogStage;
        nameField.setText(pipelineType.getName());
        diameterField.setText(String.valueOf(pipelineType.getDiameter()));
        materialField.setText(pipelineType.getMaterial());
        pressureStandardField.setText(pipelineType.getPressureStandard().toString());
        serviceLifeField.setText(String.valueOf(pipelineType.getServiceLife()));
        okButton.setOnAction(e -> edit());
    }
}