package com.yildiz.serhat.sharingplatformservice;

import com.yildiz.serhat.sharingplatformservice.domain.entity.Resource;
import com.yildiz.serhat.sharingplatformservice.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Set;

import static com.yildiz.serhat.sharingplatformservice.utils.CsvUtils.createResources;

@SpringBootApplication
@RequiredArgsConstructor
public class SharingPlatformServiceApplication {

    private final ResourceRepository resourceRepository;

    public static void main(String[] args) {
        SpringApplication.run(SharingPlatformServiceApplication.class, args);
    }

    @PostConstruct
    public void setup() {
        if (resourceRepository.findAll().size() > 0) {
            return;
        }
        Set<Resource> resources = createResources();
        resourceRepository.saveAll(resources);
    }
}

