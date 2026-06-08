package com.example.water_supply.controller.pipelinetype;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import com.example.water_supply.model.PipelineType;

public class PipelineTypeTableItem {
    private SimpleStringProperty name;
    private SimpleIntegerProperty diameter;
    private SimpleStringProperty material;
    private SimpleStringProperty pressureStandard;
    private SimpleIntegerProperty serviceLife;
    private PipelineType pipelineType;

    public PipelineTypeTableItem(PipelineType pipelineType) {
        this.name = new SimpleStringProperty(pipelineType.getName());
        this.diameter = new SimpleIntegerProperty(pipelineType.getDiameter());
        this.material = new SimpleStringProperty(pipelineType.getMaterial());
        this.pressureStandard = new SimpleStringProperty(pipelineType.getPressureStandard().toString());
        this.serviceLife = new SimpleIntegerProperty(pipelineType.getServiceLife());
        this.pipelineType = pipelineType;
    }

    public String getName() { return name.get(); }
    public SimpleStringProperty nameProperty() { return name; }
    public int getDiameter() { return diameter.get(); }
    public SimpleIntegerProperty diameterProperty() { return diameter; }
    public String getMaterial() { return material.get(); }
    public SimpleStringProperty materialProperty() { return material; }
    public String getPressureStandard() { return pressureStandard.get(); }
    public SimpleStringProperty pressureStandardProperty() { return pressureStandard; }
    public int getServiceLife() { return serviceLife.get(); }
    public SimpleIntegerProperty serviceLifeProperty() { return serviceLife; }
    public PipelineType getPipelineType() { return pipelineType; }
}