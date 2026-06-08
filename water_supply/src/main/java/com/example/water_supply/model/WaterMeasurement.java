package com.example.water_supply.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "water_measurements")
public class WaterMeasurement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pipeline_section_id")
    private PipelineSection pipelineSection;

    @ManyToOne
    @JoinColumn(name = "consumption_district_id")
    private ConsumptionDistrict consumptionDistrict;

    @Column(name = "measurement_time", nullable = false)
    private LocalDateTime measurementTime;

    @Column(name = "pressure", nullable = false)
    private BigDecimal pressure;

    @Column(name = "flow_volume", nullable = false)
    private BigDecimal flowVolume;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public PipelineSection getPipelineSection() { return pipelineSection; }
    public void setPipelineSection(PipelineSection pipelineSection) {
        if (pipelineSection != null) {
            this.pipelineSection = pipelineSection;
        } else {
            throw new IllegalArgumentException("Участок сети не может быть пустым!");
        }
    }

    public ConsumptionDistrict getConsumptionDistrict() { return consumptionDistrict; }
    public void setConsumptionDistrict(ConsumptionDistrict consumptionDistrict) {
        if (consumptionDistrict != null) {
            this.consumptionDistrict = consumptionDistrict;
        } else {
            throw new IllegalArgumentException("Район потребления не может быть пустым!");
        }
    }

    public LocalDateTime getMeasurementTime() { return measurementTime; }
    public void setMeasurementTime(LocalDateTime measurementTime) {
        if (measurementTime != null) {
            this.measurementTime = measurementTime;
        } else {
            throw new IllegalArgumentException("Время замера не может быть пустым!");
        }
    }

    public BigDecimal getPressure() { return pressure; }
    public void setPressure(String pressureText) {
        if (pressureText != null && !pressureText.isEmpty()) {
            try {
                BigDecimal pressure = new BigDecimal(pressureText);
                if (pressure.compareTo(BigDecimal.ZERO) >= 0) {
                    this.pressure = pressure;
                } else {
                    throw new IllegalArgumentException("Давление не может быть отрицательным!");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Давление должно быть числом!");
            }
        } else {
            throw new IllegalArgumentException("Давление не может быть пустым!");
        }
    }
    public void setPressure(BigDecimal pressure) { this.pressure = pressure; }

    public BigDecimal getFlowVolume() { return flowVolume; }
    public void setFlowVolume(String flowVolumeText) {
        if (flowVolumeText != null && !flowVolumeText.isEmpty()) {
            try {
                BigDecimal flowVolume = new BigDecimal(flowVolumeText);
                if (flowVolume.compareTo(BigDecimal.ZERO) >= 0) {
                    this.flowVolume = flowVolume;
                } else {
                    throw new IllegalArgumentException("Объем расхода не может быть отрицательным!");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Объем расхода должен быть числом!");
            }
        } else {
            throw new IllegalArgumentException("Объем расхода не может быть пустым!");
        }
    }
    public void setFlowVolume(BigDecimal flowVolume) { this.flowVolume = flowVolume; }

    @Override
    public String toString() {
        return "WaterMeasurement{" +
                "id=" + id +
                ", measurementTime=" + measurementTime +
                ", pressure=" + pressure +
                ", flowVolume=" + flowVolume +
                '}';
    }
}