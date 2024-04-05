package com.github.psinalberth.domain.shared.port.outgoing;

import java.io.InputStream;
import java.util.stream.Stream;

public interface DataImporter<V, E> {

    Stream<V> doImport(InputStream inputStream);

    V mapToBean(E input);

    Class<E> getTarget();
}
