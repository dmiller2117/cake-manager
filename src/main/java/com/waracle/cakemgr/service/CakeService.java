package com.waracle.cakemgr.service;

import com.waracle.cakemgr.mapper.CakeMapper;
import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.repository.CakeRepository;
import java.util.List;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CakeService {

  private final CakeRepository cakeRepository;
  private CakeMapper cakeMapper = Mappers.getMapper(CakeMapper.class);

  @Autowired
  public CakeService(CakeRepository cakeRepository) {
    this.cakeRepository = cakeRepository;
  }

  public List<Cake> getCakes() {
    return cakeMapper.map(cakeRepository.findAll());
  }

  public Cake addCake(Cake cake) {
    return updateCake(cake);
  }

  public Cake updateCake(Cake cake) {
    return cakeMapper.map(cakeRepository.save(cakeMapper.map(cake)));
  }

  public void deleteCake(Long id) {
    cakeRepository.deleteById(id);
  }
}