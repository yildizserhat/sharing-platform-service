package com.yildiz.serhat.sharingplatformservice.service;

import com.yildiz.serhat.sharingplatformservice.domain.model.ResourceRequest;
import com.yildiz.serhat.sharingplatformservice.domain.model.ResourceResponseModel;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Optional;

public interface ResourceService {

    void createResource(ResourceRequest resourceRequest);

    void convertAndCreateResources(MultipartFile file);

    void updateResource(Long id, ResourceRequest resourceRequest);

    void deleteAllResources();

    void deleteResourceById(Long id);

    Optional<ResourceResponseModel> findResourceByAuthor(String author);

    Optional<ResourceResponseModel> findResourceByTitle(String title);

    Optional<ResourceResponseModel> findResourceByViewCount(BigDecimal viewCount);

    Optional<ResourceResponseModel> findResourceByLikeCount(BigDecimal likeCount);

    Optional<ResourceResponseModel> findResourceByUrl(String url);

    Optional<ResourceResponseModel> findResourceByDate(String date);
}
