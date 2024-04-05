package com.github.psinalberth.api.providers.opencsv;

import com.github.psinalberth.domain.shared.port.outgoing.DataImporter;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public abstract class CsvImporter<V, E> implements DataImporter<V, E> {

    public Stream<V> doImport(final InputStream is) {
        final var reader = new CSVReader(new InputStreamReader(is));

        return new CsvToBeanBuilder<E>(reader)
                .withIgnoreLeadingWhiteSpace(true)
                .withQuoteChar('\'')
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withType(getTarget())
                .build()
                .stream()
                .map(this::mapToBean);
    }
}
