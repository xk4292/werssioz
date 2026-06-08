package com.example.water_supply.service;

import com.example.water_supply.model.PipelineType;
import com.example.water_supply.repository.PipelineTypeDao;
import java.util.List;

public class PipelineTypeService {
    private PipelineTypeDao pipelineTypeDao = new PipelineTypeDao();

    public List<PipelineType> findAll() { return pipelineTypeDao.findAll(); }
    public PipelineType findOne(Long id) { return pipelineTypeDao.findOne(id); }
    public void save(PipelineType entity) { if (entity != null) pipelineTypeDao.save(entity); }
    public void update(PipelineType entity) { if (entity != null) pipelineTypeDao.update(entity); }
    public void delete(PipelineType entity) { if (entity != null) pipelineTypeDao.delete(entity); }
    public void deleteById(Long id) { if (id != null) pipelineTypeDao.deleteById(id); }
}