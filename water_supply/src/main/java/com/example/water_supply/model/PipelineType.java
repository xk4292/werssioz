package com.example.water_supply.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "pipeline_types")
public class PipelineType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "diameter", nullable = false)
    private Integer diameter;

    @Column(name = "material", nullable = false)
    private String material;

    @Column(name = "pressure_standard", nullable = false)
    private BigDecimal pressureStandard;

    @Column(name = "service_life", nullable = false)
    private Integer serviceLife;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Наименование типа трубопровода не может быть пустым!");
        }
    }

    public Integer getDiameter() { return diameter; }
    public void setDiameter(Integer diameter) {
        if (diameter != null && diameter > 0) {
            this.diameter = diameter;
        } else {
            throw new IllegalArgumentException("Диаметр должен быть больше 0!");
        }
    }

    public String getMaterial() { return material; }
    public void setMaterial(String material) {
        if (material != null && !material.isEmpty()) {
            this.material = material;
        } else {
            throw new IllegalArgumentException("Материал не может быть пустым!");
        }
    }

    public BigDecimal getPressureStandard() { return pressureStandard; }
    public void setPressureStandard(String pressureStandardText) {
        if (pressureStandardText != null && !pressureStandardText.isEmpty()) {
            try {
                BigDecimal pressure = new BigDecimal(pressureStandardText);
                if (pressure.compareTo(BigDecimal.ZERO) > 0) {
                    this.pressureStandard = pressure;
                } else {
                    throw new IllegalArgumentException("Норматив давления должен быть больше 0!");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Норматив давления должен быть числом!");
            }
        } else {
            throw new IllegalArgumentException("Норматив давления не может быть пустым!");
        }
    }
    public void setPressureStandard(BigDecimal pressureStandard) { this.pressureStandard = pressureStandard; }

    public Integer getServiceLife() { return serviceLife; }
    public void setServiceLife(Integer serviceLife) {
        if (serviceLife != null && serviceLife > 0) {
            this.serviceLife = serviceLife;
        } else {
            throw new IllegalArgumentException("Срок службы должен быть больше 0!");
        }
    }

    @Override
    public String toString() {
        return name + " (" + diameter + " мм, " + material + ")";
    }
}