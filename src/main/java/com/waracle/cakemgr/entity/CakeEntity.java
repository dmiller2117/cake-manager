package com.waracle.cakemgr.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity(name = "cake")
public class CakeEntity implements Serializable {

  private static final long serialVersionUID = -1798070786993154676L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", unique = true, nullable = false)
  private Long cakeId;

  @Column(name = "title", unique = true, nullable = false, length = 100)
  private String title;

  @Column(name = "desc", nullable = false, length = 100)
  private String description;

  @Column(name = "image", nullable = false, length = 300)
  private String image;
}