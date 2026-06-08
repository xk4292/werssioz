package com.example.water_supply.service;

import com.example.water_supply.model.WaterMeasurement;
import com.example.water_supply.repository.WaterMeasurementDao;
import java.util.List;

public class WaterMeasurementService {
    private WaterMeasurementDao waterMeasurementDao = new WaterMeasurementDao();

    public List<WaterMeasurement> findAll() { return waterMeasurementDao.findAll(); }
    public WaterMeasurement findOne(Long id) { return waterMeasurementDao.findOne(id); }
    public void save(WaterMeasurement entity) { if (entity != null) waterMeasurementDao.save(entity); }
    public void update(WaterMeasurement entity) { if (entity != null) waterMeasurementDao.update(entity); }
    public void delete(WaterMeasurement entity) { if (entity != null) waterMeasurementDao.delete(entity); }
    public void deleteById(Long id) { if (id != null) waterMeasurementDao.deleteById(id); }
}