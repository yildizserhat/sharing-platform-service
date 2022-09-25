package com.yildiz.serhat.sharingplatformservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yildiz.serhat.sharingplatformservice.TestInitializer;
import com.yildiz.serhat.sharingplatformservice.domain.entity.Resource;
import com.yildiz.serhat.sharingplatformservice.domain.model.ResourceRequest;
import com.yildiz.serhat.sharingplatformservice.domain.model.UserResponseModel;
import com.yildiz.serhat.sharingplatformservice.repository.ResourceRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(initializers = TestInitializer.class)
@SpringBootTest
@AutoConfigureMockMvc
class ResourceControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private UserController userController;

    @AfterEach
    void tearDown() {
        resourceRepository.deleteAll();
    }

    @BeforeEach
    void setup() {
        resourceRepository.deleteAll();
    }

    @Test
    @SneakyThrows
    void shouldCreateResource() {
        UserResponseModel register = userController.login("yildiz_serhat@hotmail.com");

        ResourceRequest resourceRequest = new ResourceRequest("Climate action needs new frontline leadership",
                "Serhat Yildiz", "December 2021", valueOf(404000), valueOf(12000), "https://tedd.com/talks/ozawa_bineshi_albert_climate_action_needs_new_frontline_leadership");
        String url = "/v1/resources";

        mvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resourceRequest))
                        .header("Authorization", "Bearer " + register.getToken()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.meta").exists())
                .andExpect(jsonPath("$.meta.return-code").value("0"))
                .andExpect(jsonPath("$.meta.return-message").value("success"));

        List<Resource> all = resourceRepository.findAll();
        Resource resource = all.get(0);


        assertEquals(valueOf(404000), resource.getViewCount());
        assertEquals("Serhat Yildiz", resource.getAuthor());
        assertEquals("Climate action needs new frontline leadership", resource.getTitle());
        assertEquals("December 2021", resource.getDate());
    }
}