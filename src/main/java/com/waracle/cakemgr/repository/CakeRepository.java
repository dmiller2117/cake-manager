package com.waracle.cakemgr.repository;

import com.waracle.cakemgr.entity.CakeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CakeRepository extends CrudRepository<CakeEntity, Long> {
  @Override
  List<CakeEntity> findAll();
}