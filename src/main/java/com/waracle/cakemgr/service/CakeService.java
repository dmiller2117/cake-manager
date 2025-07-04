package com.waracle.cakemgr.service;

import com.waracle.cakemgr.entity.CakeEntity;
import com.waracle.cakemgr.mapper.CakeMapper;
import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.repository.CakeRepository;
import java.util.List;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CakeService {

  private final CakeRepository cakeRepository;
  private final CakeMapper cakeMapper = Mappers.getMapper(CakeMapper.class);

  @Autowired
  public CakeService(CakeRepository cakeRepository) {
    this.cakeRepository = cakeRepository;
  }

  public List<Cake> getCakes() {
    return cakeMapper.map(cakeRepository.findAll());
  }

  public Cake addCake(Cake cake) {
    return cakeMapper.map(cakeRepository.save(cakeMapper.map(cake)));
  }

  public Cake updateCake(Cake cake) {
    Long cakeId = cake.getCakeId();
    if (cakeId == null || !cakeRepository.existsById(cakeId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cake Id not found");
    }
    CakeEntity entityToSave = cakeMapper.map(cake);
    CakeEntity savedEntity = cakeRepository.save(entityToSave);
    return cakeMapper.map(savedEntity);
  }

  public void deleteCake(Long cakeId) {
    if (!cakeRepository.existsById(cakeId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cake Id not found");
    }
    cakeRepository.deleteById(cakeId);
  }
}