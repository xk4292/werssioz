package com.example.water_supply.repository;

import com.example.water_supply.model.WaterMeasurement;

public class WaterMeasurementDao extends BaseDao<WaterMeasurement> {
    public WaterMeasurementDao() {
        super(WaterMeasurement.class);
    }
}