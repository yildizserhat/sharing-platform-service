package com.yildiz.serhat.sharingplatformservice.service.impl;

import com.yildiz.serhat.sharingplatformservice.domain.entity.Resource;
import com.yildiz.serhat.sharingplatformservice.domain.model.ResourceRequest;
import com.yildiz.serhat.sharingplatformservice.repository.ResourceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.math.BigDecimal.valueOf;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ResourceServiceImplTest {

    @InjectMocks
    private ResourceServiceImpl resourceService;

    @Mock
    private ResourceRepository resourceRepository;

    @Test
    void shouldCreateResource() {
        ResourceRequest resourceRequest = new ResourceRequest("Climate action needs new frontline leadership",
                "Ozawa Bineshii Albert", "December 2021", valueOf(404000), valueOf(12000), "https://ted.com/talks/ozawa_bineshi_albert_climate_action_needs_new_frontline_leadership");

        Resource resource = Resource.buildResourceFromRequest(resourceRequest);

        resourceService.createResource(resourceRequest);
        verify(resourceRepository, atLeastOnce()).save(resource);
    }
}