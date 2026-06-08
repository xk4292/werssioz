package com.example.water_supply.controller.pipelinesection;

import javafx.beans.property.SimpleStringProperty;
import com.example.water_supply.model.PipelineSection;

public class PipelineSectionTableItem {
    private SimpleStringProperty sectionNumber;
    private SimpleStringProperty pipelineTypeName;
    private SimpleStringProperty status;
    private SimpleStringProperty installationDate;
    private SimpleStringProperty length;
    private PipelineSection pipelineSection;

    public PipelineSectionTableItem(PipelineSection section) {
        this.sectionNumber = new SimpleStringProperty(section.getSectionNumber());
        this.pipelineTypeName = new SimpleStringProperty(section.getPipelineType().getName());
        this.status = new SimpleStringProperty(section.getStatus());
        this.installationDate = new SimpleStringProperty(section.getInstallationDate().toString());
        this.length = new SimpleStringProperty(section.getLength().toString());
        this.pipelineSection = section;
    }


    public String getSectionNumber() { return sectionNumber.get(); }
    public SimpleStringProperty sectionNumberProperty() { return sectionNumber; }
    public String getPipelineTypeName() { return pipelineTypeName.get(); }
    public SimpleStringProperty pipelineTypeNameProperty() { return pipelineTypeName; }
    public String getStatus() { return status.get(); }
    public SimpleStringProperty statusProperty() { return status; }
    public String getInstallationDate() { return installationDate.get(); }
    public SimpleStringProperty installationDateProperty() { return installationDate; }
    public String getLength() { return length.get(); }
    public SimpleStringProperty lengthProperty() { return length; }
    public PipelineSection getPipelineSection() { return pipelineSection; }
}