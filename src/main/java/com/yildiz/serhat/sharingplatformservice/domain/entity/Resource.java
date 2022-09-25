package com.yildiz.serhat.sharingplatformservice.domain.entity;

import com.yildiz.serhat.sharingplatformservice.domain.model.ResourceCsvModel;
import com.yildiz.serhat.sharingplatformservice.domain.model.ResourceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "resource", indexes = {@Index(name = "idx_author_title_url", unique = true, columnList = "title,author,url")})
public class Resource extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "date")
    private String date;

    @Column(name = "view_count", precision = 30)
    private BigDecimal viewCount;

    @Column(name = "like_count", precision = 30)
    private BigDecimal likeCount;

    @Column(name = "url")
    private String url;

    public static Resource buildResourceFromCsvModel(ResourceCsvModel resourceCsvModel) {
        return Resource.builder()
                .title(resourceCsvModel.getTitle())
                .author(resourceCsvModel.getAuthor())
                .date(resourceCsvModel.getDate())
                .likeCount(new BigDecimal(resourceCsvModel.getLikes()))
                .viewCount(new BigDecimal(resourceCsvModel.getViews()))
                .url(resourceCsvModel.getLink())
                .build();
    }

    public static Resource buildResourceFromRequest(ResourceRequest resourceRequest) {
        return Resource.builder()
                .author(resourceRequest.author())
                .likeCount(resourceRequest.likes())
                .viewCount(resourceRequest.views())
                .date(resourceRequest.date())
                .title(resourceRequest.title())
                .url(resourceRequest.url())
                .build();
    }
}
