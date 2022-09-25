package com.yildiz.serhat.sharingplatformservice.repository;

import com.yildiz.serhat.sharingplatformservice.domain.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    Optional<Resource> findByAuthor(String author);

    Optional<Resource> findByTitle(String title);

    Optional<Resource> findByViewCount(BigDecimal viewCount);

    Optional<Resource> findByLikeCount(BigDecimal likeCount);

    Optional<Resource> findByUrl(String url);

    Optional<Resource> findByDate(String date);
}
