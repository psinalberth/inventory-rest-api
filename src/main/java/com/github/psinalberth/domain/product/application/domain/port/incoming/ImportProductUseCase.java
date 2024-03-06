package com.github.psinalberth.domain.product.application.domain.port.incoming;

import lombok.Value;

import java.io.InputStream;

public interface ImportProductUseCase {

    void doImport(ImportProductCommand command);

    @Value
    class ImportProductCommand {

        String code;

        InputStream productBase;
    }
}
