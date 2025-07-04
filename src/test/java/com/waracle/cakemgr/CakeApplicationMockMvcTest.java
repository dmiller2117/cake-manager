package com.waracle.cakemgr;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.model.Cake;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = CakeApplication.class)
@AutoConfigureMockMvc
@WithMockUser
class CakeApplicationMockMvcTest {

  @Autowired private MockMvc mvc;
  private ObjectMapper objectMapper = new ObjectMapper();

  @Test
  @DisplayName("GET /cakes returns all cakes")
  public void getCakes_returnsCakes() throws Exception {
    mvc.perform(get("/cakes").accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$", hasSize(5)))
        .andExpect(jsonPath("$[0].title", equalTo("Lemon cheesecake")))
        .andExpect(jsonPath("$[1].title", equalTo("victoria sponge")))
        .andExpect(jsonPath("$[2].title", equalTo("Carrot cake")))
        .andExpect(jsonPath("$[3].title", equalTo("Banana cake")))
        .andExpect(jsonPath("$[4].title", equalTo("Birthday cake")))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @DisplayName("Add, Update and Delete a cake")
  public void addUpdateDeleteCakes_addsUpdatesAndDeletes() throws Exception {

    String json =
        objectMapper.writeValueAsString(
            Cake.builder()
                .title("caterpillar cake")
                .description("a cute caterpillar cake")
                .image("caterpillar-cake.jpg")
                .build());
    String contentAsString =
        mvc.perform(post("/cakes").contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("cakeId", equalTo(6)))
            .andExpect(jsonPath("description", equalTo("a cute caterpillar cake")))
            .andDo(MockMvcResultHandlers.print())
            .andReturn()
            .getResponse()
            .getContentAsString();

    Cake cakeToUpdate = objectMapper.readValue(contentAsString, Cake.class);
    cakeToUpdate.setDescription("an updated cute caterpillar cake");
    json = objectMapper.writeValueAsString(cakeToUpdate);
    mvc.perform(put("/cakes").contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("cakeId", equalTo(6)))
        .andExpect(jsonPath("description", equalTo("an updated cute caterpillar cake")))
        .andDo(MockMvcResultHandlers.print());

    mvc.perform(delete("/cakes/{cakeId}", "6").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }

  @ParameterizedTest
  @ValueSource(longs = {-3, 15, Integer.MAX_VALUE})
  @NullSource
  @DisplayName("PUT /cakes to update with invalid or null id returns not found")
  public void updateWithInvalidId_returnsNotFound(Long cakeId) throws Exception {
    String json =
        objectMapper.writeValueAsString(
            Cake.builder()
                .cakeId(cakeId)
                .title("cake with invalid ID")
                .description("should not update")
                .build());

    String errorMessage =
        mvc.perform(put("/cakes").contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(status().isNotFound())
            .andDo(MockMvcResultHandlers.print())
            .andReturn()
            .getResponse()
            .getErrorMessage();
    assertThat(errorMessage).isEqualTo("Cake Id not found");
  }

  @ParameterizedTest
  @ValueSource(strings = {"-1", "7", "1923"})
  @DisplayName("DELETE /cakes/{cakeId} to delete with invalid returns not found")
  public void deleteWithInvalidId_returnsNotFound(Long cakeId) throws Exception {
    String errorMessage =
        mvc.perform(delete("/cakes/{cakeId}", cakeId).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andReturn()
            .getResponse()
            .getErrorMessage();

    assertThat(errorMessage).isEqualTo("Cake Id not found");
  }
}