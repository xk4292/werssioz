package com.example.water_supply.controller.consumptiondistrict;

import com.example.water_supply.model.ConsumptionDistrict;
import com.example.water_supply.service.ConsumptionDistrictService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddEditConsumptionDistrictDialog {
    @FXML private TextField districtNameField;
    @FXML private TextField houseCountField;
    @FXML private TextField dispatchAddressField;
    @FXML private TextField phoneField;
    @FXML private Button okButton;
    @FXML private Label errorLabel;

    private Stage dialogStage;
    private ConsumptionDistrict consumptionDistrict;

    void add() {
        try {
            ConsumptionDistrict district = new ConsumptionDistrict();
            district.setDistrictName(districtNameField.getText());
            district.setHouseCount(Integer.parseInt(houseCountField.getText()));
            district.setDispatchAddress(dispatchAddressField.getText());
            district.setPhone(phoneField.getText());

            new ConsumptionDistrictService().save(district);
            dialogStage.close();
        } catch (NumberFormatException e) {
            errorLabel.setText("Количество домов должно быть числом!");
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    void edit() {
        try {
            consumptionDistrict.setDistrictName(districtNameField.getText());
            consumptionDistrict.setHouseCount(Integer.parseInt(houseCountField.getText()));
            consumptionDistrict.setDispatchAddress(dispatchAddressField.getText());
            consumptionDistrict.setPhone(phoneField.getText());

            new ConsumptionDistrictService().update(consumptionDistrict);
            dialogStage.close();
        } catch (NumberFormatException e) {
            errorLabel.setText("Количество домов должно быть числом!");
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    public void setAddDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        okButton.setOnAction(e -> add());
    }

    public void setEditDialogStage(Stage dialogStage, ConsumptionDistrict district) {
        this.consumptionDistrict = district;
        this.dialogStage = dialogStage;

        districtNameField.setText(district.getDistrictName());
        houseCountField.setText(String.valueOf(district.getHouseCount()));
        dispatchAddressField.setText(district.getDispatchAddress());
        phoneField.setText(district.getPhone());

        okButton.setOnAction(e -> edit());
    }
}