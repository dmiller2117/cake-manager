package com.waracle.cakemgr.controller;

import com.waracle.cakemgr.entity.CakeEntity;
import com.waracle.cakemgr.service.CakeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CakeController {

  private final CakeService cakeService;

  @Autowired
  public CakeController(CakeService cakeService) {
    this.cakeService = cakeService;
  }

  @GetMapping(value = "/cakes", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<CakeEntity>> getCakes() {
    return ResponseEntity.ok(cakeService.getCakes());
  }
}