package com.waracle.cakemgr.mapper;

import com.waracle.cakemgr.entity.CakeEntity;
import com.waracle.cakemgr.model.Cake;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface CakeMapper {

  List<Cake> map(List<CakeEntity> cakeEntity);

  CakeEntity map(Cake cake);

  Cake map(CakeEntity cakeEntity);
}