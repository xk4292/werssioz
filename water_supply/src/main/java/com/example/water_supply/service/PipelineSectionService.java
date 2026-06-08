package com.example.water_supply.service;

import com.example.water_supply.model.PipelineSection;
import com.example.water_supply.repository.PipelineSectionDao;
import java.util.List;

public class PipelineSectionService {
    private PipelineSectionDao pipelineSectionDao = new PipelineSectionDao();

    public List<PipelineSection> findAll() { return pipelineSectionDao.findAll(); }
    public PipelineSection findOne(Long id) { return pipelineSectionDao.findOne(id); }
    public void save(PipelineSection entity) { if (entity != null) pipelineSectionDao.save(entity); }
    public void update(PipelineSection entity) { if (entity != null) pipelineSectionDao.update(entity); }
    public void delete(PipelineSection entity) { if (entity != null) pipelineSectionDao.delete(entity); }
    public void deleteById(Long id) { if (id != null) pipelineSectionDao.deleteById(id); }
}