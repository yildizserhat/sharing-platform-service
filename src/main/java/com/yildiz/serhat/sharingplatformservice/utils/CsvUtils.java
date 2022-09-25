package com.yildiz.serhat.sharingplatformservice.utils;

import com.opencsv.bean.CsvToBeanBuilder;
import com.yildiz.serhat.sharingplatformservice.domain.entity.Resource;
import com.yildiz.serhat.sharingplatformservice.domain.model.ResourceCsvModel;
import com.yildiz.serhat.sharingplatformservice.exception.FileNotValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class CsvUtils {
    private static final char CSV_COLUMN_SEPARATOR = ',';
    public static String CSV_TYPE = "text/csv";

    public static void isCSVFormat(String contentType) {
        if (!CSV_TYPE.equals(contentType)) {
            throw new FileNotValidException("File is not in CSV format", HttpStatus.BAD_REQUEST);
        }
    }

    public static Set<Resource> createResources() {
        File csvFile = new File("src/main/resources/data.csv");
        List<ResourceCsvModel> csvModels = convertFromFileToResources(csvFile);

        return csvModels.stream()
                .map(Resource::buildResourceFromCsvModel)
                .collect(Collectors.toSet());
    }

    public static List<ResourceCsvModel> convertFromFileToResources(File file) {
        try {
            return new CsvToBeanBuilder<ResourceCsvModel>(new FileReader(file))
                    .withSeparator(CSV_COLUMN_SEPARATOR)
                    .withIgnoreQuotations(false)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withType(ResourceCsvModel.class)
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            log.error("Exception occurred while uploading: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static List<ResourceCsvModel> convertFromFileToResources(MultipartFile file) {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            return new CsvToBeanBuilder<ResourceCsvModel>(reader)
                    .withSeparator(CSV_COLUMN_SEPARATOR)
                    .withIgnoreQuotations(false)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withType(ResourceCsvModel.class)
                    .build()
                    .parse();
        } catch (Exception e) {
            log.error("Exception occurred while uploading: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}


