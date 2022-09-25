package com.yildiz.serhat.sharingplatformservice.repository;

import com.yildiz.serhat.sharingplatformservice.TestInitializer;
import com.yildiz.serhat.sharingplatformservice.domain.entity.Resource;
import com.yildiz.serhat.sharingplatformservice.domain.model.ResourceRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(initializers = TestInitializer.class)
class ResourceRepositoryTest {

    @Autowired
    private ResourceRepository resourceRepository;

    @BeforeEach
    void setup() {
        resourceRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        resourceRepository.deleteAll();
    }

    @Test
    void shouldFindByTitle() {
        ResourceRequest resourceRequest = new ResourceRequest("Climate action needs new frontline leadership",
                "Ozawa Bineshii Albert", "December 2021", valueOf(404000), valueOf(12000), "https://ted.com/talks/ozawa_bineshi_albert_climate_action_needs_new_frontline_leadership");

        Resource resource = Resource.buildResourceFromRequest(resourceRequest);

        resourceRepository.save(resource);

        Optional<Resource> climateChange = resourceRepository.findByTitle("Climate action needs new frontline leadership");

        Resource resource1 = climateChange.get();

        assertEquals(resourceRequest.author(), resource1.getAuthor());
        assertEquals(resourceRequest.title(), resource1.getTitle());
        assertEquals(resourceRequest.views(), resource1.getViewCount());
        assertEquals(resourceRequest.likes(), resource1.getLikeCount());
    }

}