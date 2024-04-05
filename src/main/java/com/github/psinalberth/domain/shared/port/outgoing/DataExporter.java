package com.github.psinalberth.domain.shared.port.outgoing;

import java.io.InputStream;
import java.util.List;

public interface DataExporter<V, E> {

    InputStream doExport(E data);

    InputStream doExport(List<E> data);

    Class<V> getType();

    V mapFromBean(E input);

    String [] getHeaders();
}
