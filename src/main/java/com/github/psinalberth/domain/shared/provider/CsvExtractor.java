package com.github.psinalberth.domain.shared.provider;

import java.io.InputStream;
import java.util.List;

public interface CsvExtractor<E> {

    List<E> extract(Class<E> clazz, InputStream is);
}
