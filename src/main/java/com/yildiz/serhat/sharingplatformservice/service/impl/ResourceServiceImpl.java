package com.yildiz.serhat.sharingplatformservice.service.impl;

import com.yildiz.serhat.sharingplatformservice.domain.entity.Resource;
import com.yildiz.serhat.sharingplatformservice.domain.model.ResourceCsvModel;
import com.yildiz.serhat.sharingplatformservice.domain.model.ResourceRequest;
import com.yildiz.serhat.sharingplatformservice.domain.model.ResourceResponseModel;
import com.yildiz.serhat.sharingplatformservice.repository.ResourceRepository;
import com.yildiz.serhat.sharingplatformservice.service.ResourceService;
import com.yildiz.serhat.sharingplatformservice.utils.CsvUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;

    @Override
    public void createResource(ResourceRequest resourceRequest) {
        Resource resource = Resource.buildResourceFromRequest(resourceRequest);
        resourceRepository.save(resource);
    }

    @Override
    public void convertAndCreateResources(MultipartFile multipartFile) {
        validateFile(multipartFile);
        try {
            List<ResourceCsvModel> resourceCsvModels = CsvUtils.convertFromFileToResources(multipartFile);
            Set<Resource> resources = resourceCsvModels.stream()
                    .map(Resource::buildResourceFromCsvModel)
                    .collect(Collectors.toSet());
            resourceRepository.saveAll(resources);
            log.info("{} record(s) saved", resources.size());
        } catch (Exception ex) {
            log.error("Exception occurred: {}", ex.getMessage());
        }
    }

    private void validateFile(MultipartFile file) {
        CsvUtils.isCSVFormat(file.getContentType());
    }

    @Override
    public void updateResource(Long id, ResourceRequest resourceRequest) {
        Optional<Resource> resourceRepositoryById = resourceRepository.findById(id);
        resourceRepositoryById.ifPresent(resource -> createResource(resourceRequest));
        log.info("Resource is updated with id: {}", id);
    }

    @Override
    public Optional<ResourceResponseModel> findResourceByAuthor(String author) {
        Optional<Resource> resource = resourceRepository.findByAuthor(author);
        return resource.map(ResourceResponseModel::buildResourceModel);
    }

    @Override
    public Optional<ResourceResponseModel> findResourceByTitle(String title) {
        Optional<Resource> resource = resourceRepository.findByTitle(title);
        return resource.map(ResourceResponseModel::buildResourceModel);
    }

    @Override
    public Optional<ResourceResponseModel> findResourceByViewCount(BigDecimal viewCount) {
        Optional<Resource> resource = resourceRepository.findByViewCount(viewCount);
        return resource.map(ResourceResponseModel::buildResourceModel);
    }

    @Override
    public Optional<ResourceResponseModel> findResourceByLikeCount(BigDecimal likeCount) {
        Optional<Resource> resource = resourceRepository.findByLikeCount(likeCount);
        return resource.map(ResourceResponseModel::buildResourceModel);
    }

    @Override
    public Optional<ResourceResponseModel> findResourceByUrl(String url) {
        Optional<Resource> resource = resourceRepository.findByUrl(url);
        return resource.map(ResourceResponseModel::buildResourceModel);
    }

    @Override
    public Optional<ResourceResponseModel> findResourceByDate(String date) {
        Optional<Resource> resource = resourceRepository.findByDate(date);
        return resource.map(ResourceResponseModel::buildResourceModel);
    }

    @Override
    public void deleteAllResources() {
        resourceRepository.deleteAll();
    }

    @Override
    public void deleteResourceById(Long id) {
        resourceRepository.deleteById(id);
    }
}
