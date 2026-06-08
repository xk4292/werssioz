package com.example.water_supply.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pipeline_sections")
public class PipelineSection {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pipeline_type_id")
    private PipelineType pipelineType;

    @Column(name = "section_number", nullable = false, unique = true)
    private String sectionNumber;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "installation_date", nullable = false)
    private LocalDate installationDate;

    @Column(name = "length", nullable = false)
    private BigDecimal length;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public PipelineType getPipelineType() { return pipelineType; }
    public void setPipelineType(PipelineType pipelineType) {
        if (pipelineType != null) {
            this.pipelineType = pipelineType;
        } else {
            throw new IllegalArgumentException("Тип трубопровода не может быть пустым!");
        }
    }

    public String getSectionNumber() { return sectionNumber; }
    public void setSectionNumber(String sectionNumber) {
        if (sectionNumber != null && !sectionNumber.isEmpty()) {
            this.sectionNumber = sectionNumber;
        } else {
            throw new IllegalArgumentException("Номер участка не может быть пустым!");
        }
    }

    public String getStatus() { return status; }
    public void setStatus(String status) {
        if (status != null && !status.isEmpty()) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Статус не может быть пустым!");
        }
    }

    public LocalDate getInstallationDate() { return installationDate; }
    public void setInstallationDate(LocalDate installationDate) {
        if (installationDate != null) {
            this.installationDate = installationDate;
        } else {
            throw new IllegalArgumentException("Дата укладки не может быть пустой!");
        }
    }

    public BigDecimal getLength() { return length; }
    public void setLength(String lengthText) {
        if (lengthText != null && !lengthText.isEmpty()) {
            try {
                BigDecimal length = new BigDecimal(lengthText);
                if (length.compareTo(BigDecimal.ZERO) > 0) {
                    this.length = length;
                } else {
                    throw new IllegalArgumentException("Длина должна быть больше 0!");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Длина должна быть числом!");
            }
        } else {
            throw new IllegalArgumentException("Длина не может быть пустой!");
        }
    }
    public void setLength(BigDecimal length) { this.length = length; }

    @Override
    public String toString() {
        return sectionNumber + " (" + (pipelineType != null ? pipelineType.getName() : null) + ")";
    }
}