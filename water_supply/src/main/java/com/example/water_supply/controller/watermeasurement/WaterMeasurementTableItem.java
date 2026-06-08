package com.example.water_supply.controller.watermeasurement;

import javafx.beans.property.SimpleStringProperty;
import com.example.water_supply.model.WaterMeasurement;

public class WaterMeasurementTableItem {
    private SimpleStringProperty measurementTime;
    private SimpleStringProperty pressure;
    private SimpleStringProperty flowVolume;
    private SimpleStringProperty pipelineSectionNumber;
    private SimpleStringProperty districtName;
    private WaterMeasurement waterMeasurement;

    public WaterMeasurementTableItem(WaterMeasurement measurement) {
        this.measurementTime = new SimpleStringProperty(
                measurement.getMeasurementTime() != null ? measurement.getMeasurementTime().toString() : ""
        );
        this.pressure = new SimpleStringProperty(
                measurement.getPressure() != null ? measurement.getPressure().toString() : ""
        );
        this.flowVolume = new SimpleStringProperty(
                measurement.getFlowVolume() != null ? measurement.getFlowVolume().toString() : ""
        );
        this.pipelineSectionNumber = new SimpleStringProperty(
                measurement.getPipelineSection() != null ? measurement.getPipelineSection().getSectionNumber() : ""
        );
        this.districtName = new SimpleStringProperty(
                measurement.getConsumptionDistrict() != null ? measurement.getConsumptionDistrict().getDistrictName() : ""
        );
        this.waterMeasurement = measurement;
    }

    public String getMeasurementTime() { return measurementTime.get(); }
    public SimpleStringProperty measurementTimeProperty() { return measurementTime; }

    public String getPressure() { return pressure.get(); }
    public SimpleStringProperty pressureProperty() { return pressure; }

    public String getFlowVolume() { return flowVolume.get(); }
    public SimpleStringProperty flowVolumeProperty() { return flowVolume; }

    public String getPipelineSectionNumber() { return pipelineSectionNumber.get(); }
    public SimpleStringProperty pipelineSectionNumberProperty() { return pipelineSectionNumber; }

    public String getDistrictName() { return districtName.get(); }
    public SimpleStringProperty districtNameProperty() { return districtName; }

    public WaterMeasurement getWaterMeasurement() { return waterMeasurement; }
}