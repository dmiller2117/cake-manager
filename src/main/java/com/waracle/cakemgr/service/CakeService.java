package com.waracle.cakemgr.service;

import com.waracle.cakemgr.entity.CakeEntity;
import com.waracle.cakemgr.repository.CakeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CakeService {

  private final CakeRepository cakeRepository;

  @Autowired
  public CakeService(CakeRepository cakeRepository) {
    this.cakeRepository = cakeRepository;
  }

  public List<CakeEntity> getCakes() {
    return cakeRepository.findAll();
  }
}