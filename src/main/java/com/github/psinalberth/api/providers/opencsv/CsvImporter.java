package com.github.psinalberth.api.providers.opencsv;

import com.github.psinalberth.domain.shared.port.outgoing.DataImporter;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public abstract class CsvImporter<V, E> implements DataImporter<V, E> {

    public Stream<V> doImport(final InputStream is) {

        final var parser = new CSVParserBuilder()
                .withIgnoreLeadingWhiteSpace(true)
                .withSeparator('|')
                .withQuoteChar('\"')
                .build();

        final var reader = new CSVReaderBuilder(new InputStreamReader(is))
                .withCSVParser(parser)
                .build();

        try {

            var strategy = new HeaderColumnNameMappingStrategy<E>();
            strategy.setType(getTarget());

            return new CsvToBeanBuilder<E>(reader)
                    .withMappingStrategy(strategy)
                    .withType(getTarget())
                    .build()
                    .stream()
                    .map(this::mapToBean);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
