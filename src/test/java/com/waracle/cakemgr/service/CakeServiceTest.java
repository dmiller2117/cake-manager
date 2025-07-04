package com.waracle.cakemgr.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.waracle.cakemgr.entity.CakeEntity;
import com.waracle.cakemgr.mapper.CakeMapper;
import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.repository.CakeRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
class CakeServiceTest {
  @Mock private CakeRepository cakeRepository;

  private final CakeMapper cakeMapper = Mappers.getMapper(CakeMapper.class);

  @InjectMocks private CakeService cakeService;

  @Test
  @DisplayName("getCakes returns mapped list of cakes")
  void getCakes_returnsMappedCakes() {
    CakeEntity entity = new CakeEntity(1L, "Cake", "Nice", "url");
    when(cakeRepository.findAll()).thenReturn(List.of(entity));

    List<Cake> cakes = cakeService.getCakes();

    assertEquals(1, cakes.size());
    assertEquals("Cake", cakes.get(0).getTitle());
    verify(cakeRepository).findAll();
  }

  @Test
  @DisplayName("addCake saves and returns mapped cake")
  void addCake_returnsSavedCake() {
    Cake cake = new Cake(null, "New Cake", "Yum", "image.jpg");
    CakeEntity entity = cakeMapper.map(cake);
    entity.setCakeId(5L);

    when(cakeRepository.save(any(CakeEntity.class))).thenReturn(entity);

    Cake result = cakeService.addCake(cake);

    assertNotNull(result.getCakeId());
    assertEquals("New Cake", result.getTitle());
    verify(cakeRepository).save(any(CakeEntity.class));
  }

  @Test
  @DisplayName("updateCake throws if ID is null or not found")
  void updateCake_throwsIfIdInvalid() {
    Cake cakeWithoutId = new Cake(null, "Cake", "desc", "img");

    assertThrows(ResponseStatusException.class, () -> cakeService.updateCake(cakeWithoutId));

    when(cakeRepository.existsById(99L)).thenReturn(false);
    Cake cakeWithInvalidId = new Cake(99L, "Cake", "desc", "img");

    assertThrows(ResponseStatusException.class, () -> cakeService.updateCake(cakeWithInvalidId));
  }

  @Test
  @DisplayName("updateCake saves and returns updated cake")
  void updateCake_returnsUpdatedCake() {
    Cake cake = new Cake(1L, "Updated Cake", "desc", "img");
    when(cakeRepository.existsById(1L)).thenReturn(true);
    when(cakeRepository.save(any(CakeEntity.class)))
        .thenAnswer(
            invocation -> {
              CakeEntity e = invocation.getArgument(0);
              e.setCakeId(1L);
              return e;
            });

    Cake result = cakeService.updateCake(cake);

    assertEquals("Updated Cake", result.getTitle());
    verify(cakeRepository).save(any(CakeEntity.class));
  }

  @Test
  @DisplayName("deleteCake throws if not found")
  void deleteCake_throwsIfNotFound() {
    when(cakeRepository.existsById(1L)).thenReturn(false);

    assertThrows(ResponseStatusException.class, () -> cakeService.deleteCake(1L));
  }

  @Test
  @DisplayName("deleteCake deletes if exists")
  void deleteCake_deletesSuccessfully() {
    when(cakeRepository.existsById(1L)).thenReturn(true);

    cakeService.deleteCake(1L);

    verify(cakeRepository).deleteById(1L);
  }
}