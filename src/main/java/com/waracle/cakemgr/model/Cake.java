package com.waracle.cakemgr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cake {
  private Long cakeId;
  private String title;
  private String description;
  private String image;
}