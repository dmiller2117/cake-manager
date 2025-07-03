package com.waracle.cakemgr.model;

import lombok.Data;

@Data
public class Cake {

  private Long cakeId;
  private String title;
  private String description;
  private String image;
}