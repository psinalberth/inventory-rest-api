package com.github.psinalberth.infrastructure.shared;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class OpenCsvExtractor {

    public <E> List<E> extract(Class<E> clazz, InputStream is) {
        final var reader = new CSVReader(new InputStreamReader(is));

        return new CsvToBeanBuilder<E>(reader)
                .withIgnoreLeadingWhiteSpace(true)
                .withQuoteChar('\'')
                .withType(clazz)
                .withSeparator(',')
                .build()
                .parse();
    }
}
