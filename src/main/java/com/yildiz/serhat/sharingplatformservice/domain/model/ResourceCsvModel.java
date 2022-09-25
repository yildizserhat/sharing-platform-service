package com.yildiz.serhat.sharingplatformservice.domain.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ResourceCsvModel {
    @CsvBindByName
    private String title;

    @CsvBindByName
    private String author;

    @CsvBindByName
    private String date;

    @CsvBindByName
    private String views;

    @CsvBindByName
    private String likes;

    @CsvBindByName
    private String link;
}
