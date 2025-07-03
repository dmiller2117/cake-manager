package com.waracle.cakemgr.controller;

import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.service.CakeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(value = "/cakes", produces = MediaType.APPLICATION_JSON_VALUE)
public class CakeController {

  private final CakeService cakeService;

  @Autowired
  public CakeController(CakeService cakeService) {
    this.cakeService = cakeService;
  }

  @GetMapping
  public ResponseEntity<List<Cake>> getCakes() {
    return ResponseEntity.ok(cakeService.getCakes());
  }

  @PostMapping
  public ResponseEntity<Cake> addCake(Cake cake) {
    return ResponseEntity.ok(cakeService.addCake(cake));
  }

  @PutMapping
  public ResponseEntity<Cake> updateCake(Cake cake) {
    return ResponseEntity.ok(cakeService.updateCake(cake));
  }

  @DeleteMapping
  public void deleteCake(Long id) {
    cakeService.deleteCake(id);
  }
}