package com.yildiz.serhat.sharingplatformservice.domain.model;

import com.yildiz.serhat.sharingplatformservice.domain.entity.Resource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ResourceResponseModel {
    private String author;
    private String title;
    private String date;
    private BigDecimal viewCount;
    private BigDecimal likeCount;
    private String url;

    public static ResourceResponseModel buildResourceModel(Resource resource) {
        return ResourceResponseModel.builder()
                .author(resource.getAuthor())
                .title(resource.getTitle())
                .date(resource.getDate())
                .viewCount(resource.getViewCount())
                .likeCount(resource.getLikeCount())
                .url(resource.getUrl())
                .build();
    }
}
