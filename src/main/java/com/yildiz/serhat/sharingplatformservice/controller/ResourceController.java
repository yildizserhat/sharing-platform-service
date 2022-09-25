package com.yildiz.serhat.sharingplatformservice.controller;

import com.yildiz.serhat.sharingplatformservice.domain.model.ApiResponse;
import com.yildiz.serhat.sharingplatformservice.domain.model.ResourceRequest;
import com.yildiz.serhat.sharingplatformservice.domain.model.ResourceResponseModel;
import com.yildiz.serhat.sharingplatformservice.service.ResourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/v1/resources")
@RequiredArgsConstructor
@Tag(name = "resource", description = "Endpoints about resources")
public class ResourceController {

    private final ResourceService resourceService;

    @PostMapping
    @Operation(summary = "Create TedTalks")
    public ApiResponse<Void> createResource(@Valid @RequestBody ResourceRequest resourceRequest) {
        resourceService.createResource(resourceRequest);
        return ApiResponse.<Void>builder()
                .build();
    }

    @PostMapping("/upload")
    @Operation(summary = "Upload CSV File")
    public ApiResponse<Void> uploadResource(@RequestParam("file") MultipartFile file) {
        resourceService.convertAndCreateResources(file);
        return ApiResponse.<Void>builder()
                .build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update TedTalks")
    public ApiResponse<Void> updateResource(@PathVariable("id") Long id, @Valid @RequestBody ResourceRequest resourceRequest) {
        resourceService.updateResource(id, resourceRequest);
        return ApiResponse.<Void>builder().build();
    }

    @DeleteMapping
    @Operation(summary = "Delete All TedTalks")
    public ApiResponse<Void> deleteAllResources() {
        resourceService.deleteAllResources();
        return ApiResponse.<Void>builder().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete All TedTalks")
    public ApiResponse<Void> deleteResourceById(@PathVariable("id") Long id) {
        resourceService.deleteResourceById(id);
        return ApiResponse.<Void>builder().build();
    }

    @GetMapping("/author/{author}")
    @Operation(summary = "Get Resource By Author")
    public ApiResponse<ResourceResponseModel> findResourceByAuthor(@PathVariable("author") String author) {
        Optional<ResourceResponseModel> resource = resourceService.findResourceByAuthor(author);
        return ApiResponse.<ResourceResponseModel>builder()
                .operationResultData(resource.get())
                .build();
    }

    @GetMapping("/title/{title}")
    @Operation(summary = "Get Resource By title")
    public ApiResponse<ResourceResponseModel> findResourceByTitle(@PathVariable("title") String title) {
        Optional<ResourceResponseModel> resource = resourceService.findResourceByTitle(title);
        return ApiResponse.<ResourceResponseModel>builder()
                .operationResultData(resource.get())
                .build();
    }

    @GetMapping("/view/{view}")
    @Operation(summary = "Get Resource By view count")
    public ApiResponse<ResourceResponseModel> findResourceByViewCount(@PathVariable("view") BigDecimal view) {
        Optional<ResourceResponseModel> resource = resourceService.findResourceByViewCount(view);
        return ApiResponse.<ResourceResponseModel>builder()
                .operationResultData(resource.get())
                .build();
    }

    @GetMapping("/like/{like}")
    @Operation(summary = "Get Resource By like count")
    public ApiResponse<ResourceResponseModel> findResourceByLikeCount(@PathVariable("like") BigDecimal like) {
        Optional<ResourceResponseModel> resource = resourceService.findResourceByLikeCount(like);
        return ApiResponse.<ResourceResponseModel>builder()
                .operationResultData(resource.get())
                .build();
    }

    @GetMapping("/url")
    @Operation(summary = "Get Resource By link")
    public ApiResponse<ResourceResponseModel> findResourceByUrl(@RequestParam("url") String url) {
        Optional<ResourceResponseModel> resource = resourceService.findResourceByUrl(url);
        return ApiResponse.<ResourceResponseModel>builder()
                .operationResultData(resource.get())
                .build();
    }

    @PostMapping("/date/{date}")
    @Operation(summary = "Get Resource By link")
    public ApiResponse<ResourceResponseModel> findResourceByDate(@PathVariable("date") String date) {
        Optional<ResourceResponseModel> resource = resourceService.findResourceByDate(date);
        return ApiResponse.<ResourceResponseModel>builder()
                .operationResultData(resource.get())
                .build();
    }
}
