package com.example.water_supply.controller.watermeasurement;

import com.example.water_supply.model.ConsumptionDistrict;
import com.example.water_supply.model.PipelineSection;
import com.example.water_supply.model.WaterMeasurement;
import com.example.water_supply.service.ConsumptionDistrictService;
import com.example.water_supply.service.PipelineSectionService;
import com.example.water_supply.service.WaterMeasurementService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.List;

public class AddEditWaterMeasurementDialog {
    @FXML private ComboBox<PipelineSection> pipelineSectionField;
    @FXML private ComboBox<ConsumptionDistrict> districtField;
    @FXML private TextField pressureField;
    @FXML private TextField flowVolumeField;
    @FXML private Button okButton;
    @FXML private Label errorLabel;

    private Stage dialogStage;
    private WaterMeasurement waterMeasurement;

    @FXML
    void initialize() {
        List<PipelineSection> sections = new PipelineSectionService().findAll();
        pipelineSectionField.getItems().addAll(FXCollections.observableList(sections));

        List<ConsumptionDistrict> districts = new ConsumptionDistrictService().findAll();
        districtField.getItems().addAll(FXCollections.observableList(districts));
    }

    void add() {
        try {
            WaterMeasurement measurement = new WaterMeasurement();
            measurement.setPipelineSection(pipelineSectionField.getValue());
            measurement.setConsumptionDistrict(districtField.getValue());
            measurement.setMeasurementTime(LocalDateTime.now());
            measurement.setPressure(pressureField.getText());
            measurement.setFlowVolume(flowVolumeField.getText());

            new WaterMeasurementService().save(measurement);
            dialogStage.close();
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    void edit() {
        try {
            waterMeasurement.setPipelineSection(pipelineSectionField.getValue());
            waterMeasurement.setConsumptionDistrict(districtField.getValue());
            waterMeasurement.setPressure(pressureField.getText());
            waterMeasurement.setFlowVolume(flowVolumeField.getText());

            new WaterMeasurementService().update(waterMeasurement);
            dialogStage.close();
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    public void setAddDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        okButton.setOnAction(e -> add());
    }

    public void setEditDialogStage(Stage dialogStage, WaterMeasurement measurement) {
        this.waterMeasurement = measurement;
        this.dialogStage = dialogStage;

        pipelineSectionField.setValue(measurement.getPipelineSection());
        districtField.setValue(measurement.getConsumptionDistrict());
        pressureField.setText(measurement.getPressure().toString());
        flowVolumeField.setText(measurement.getFlowVolume().toString());

        okButton.setOnAction(e -> edit());
    }
}