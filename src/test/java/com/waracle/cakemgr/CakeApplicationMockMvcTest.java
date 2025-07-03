package com.waracle.cakemgr;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CakeApplication.class)
@AutoConfigureMockMvc
class CakeApplicationMockMvcTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testController() throws Exception {
        mvc.perform(get("/cakes")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].title", equalTo("Lemon cheesecake")))
                .andExpect(jsonPath("$[1].title", equalTo("victoria sponge")))
                .andExpect(jsonPath("$[2].title", equalTo("Carrot cake")))
                .andExpect(jsonPath("$[3].title", equalTo("Banana cake")))
                .andExpect(jsonPath("$[4].title", equalTo("Birthday cake")));
    }
}