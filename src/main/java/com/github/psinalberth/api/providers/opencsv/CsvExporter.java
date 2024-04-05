package com.github.psinalberth.api.providers.opencsv;

import com.github.psinalberth.domain.shared.port.outgoing.DataExporter;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.MappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public abstract class CsvExporter<V, E> implements DataExporter<V, E> {

    public InputStream doExport(final E data) {
        var outputStream = new ByteArrayOutputStream();
        try (var writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
            makeCsvBean(writer).write(this.mapFromBean(data));
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException | IOException e) {
            return null;
        }

        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    @Override
    public InputStream doExport(final List<E> data) {
        var parsedData = data.stream().map(this::mapFromBean).toList();
        var outputStream = new ByteArrayOutputStream();
        try (var writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
            makeCsvBean(writer).write(parsedData);
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException | IOException e) {
            return null;
        }

        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    private StatefulBeanToCsv<V> makeCsvBean(OutputStreamWriter writer) {

        var strategy = defineMappingStrategy();
        strategy.setType(getType());

        return new StatefulBeanToCsvBuilder<V>(writer)
                .withOrderedResults(true)
                .withMappingStrategy(strategy)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .build();
    }

    private MappingStrategy<V> defineMappingStrategy() {
        return new ColumnPositionMappingStrategy<>() {
            @Override
            public String[] generateHeader(V bean) throws CsvRequiredFieldEmptyException {
                super.generateHeader(bean);
                return getHeaders();
            }
        };
    }
}
