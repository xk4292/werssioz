package com.example.water_supply.service;

import com.example.water_supply.model.ConsumptionDistrict;
import com.example.water_supply.repository.ConsumptionDistrictDao;
import java.util.List;

public class ConsumptionDistrictService {
    private ConsumptionDistrictDao consumptionDistrictDao = new ConsumptionDistrictDao();

    public List<ConsumptionDistrict> findAll() { return consumptionDistrictDao.findAll(); }
    public ConsumptionDistrict findOne(Long id) { return consumptionDistrictDao.findOne(id); }
    public void save(ConsumptionDistrict entity) { if (entity != null) consumptionDistrictDao.save(entity); }
    public void update(ConsumptionDistrict entity) { if (entity != null) consumptionDistrictDao.update(entity); }
    public void delete(ConsumptionDistrict entity) { if (entity != null) consumptionDistrictDao.delete(entity); }
    public void deleteById(Long id) { if (id != null) consumptionDistrictDao.deleteById(id); }
}