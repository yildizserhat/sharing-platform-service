package com.yildiz.serhat.sharingplatformservice.domain.model;

import lombok.NonNull;

import java.math.BigDecimal;

public record ResourceRequest(
        @NonNull String title,
        @NonNull String author,
        @NonNull String date,
        @NonNull BigDecimal views,
        @NonNull BigDecimal likes,
        @NonNull String url
) {
}
