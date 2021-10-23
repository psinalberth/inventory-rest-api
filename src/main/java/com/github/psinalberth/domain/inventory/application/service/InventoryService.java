package com.github.psinalberth.domain.inventory.application.service;

import com.github.psinalberth.domain.company.application.domain.model.Company;
import com.github.psinalberth.domain.company.application.domain.model.Subsidiary;
import com.github.psinalberth.domain.inventory.adapters.mapper.InventoryMapper;
import com.github.psinalberth.domain.inventory.application.domain.dto.InventoryDto;
import com.github.psinalberth.domain.inventory.application.domain.exception.InventoryNotFoundException;
import com.github.psinalberth.domain.inventory.application.domain.model.Inventory;
import com.github.psinalberth.domain.inventory.application.port.incoming.CreateInventoryUseCase;
import com.github.psinalberth.domain.inventory.application.port.incoming.QueryInventoryUseCase;
import com.github.psinalberth.domain.inventory.application.port.outgoing.LoadInventoryPort;
import com.github.psinalberth.domain.inventory.application.port.outgoing.SaveInventoryPort;
import com.github.psinalberth.domain.shared.annotation.UseCase;
import com.github.psinalberth.domain.shared.application.port.RetrieveCompanyPort;
import com.github.psinalberth.domain.shared.application.port.RetrieveSubsidiaryPort;
import com.github.psinalberth.domain.shared.provider.StringProvider;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class InventoryService implements CreateInventoryUseCase, QueryInventoryUseCase {

    private final InventoryMapper inventoryMapper;
    private final RetrieveCompanyPort retrieveCompanyPort;
    private final RetrieveSubsidiaryPort retrieveSubsidiaryPort;
    private final LoadInventoryPort loadInventoryPort;
    private final SaveInventoryPort saveInventoryPort;
    private final StringProvider stringProvider;

    @Override
    public InventoryDto create(CreateInventoryCommand command) {

        Inventory inventory = inventoryMapper.toEntity(command);
        Company company = retrieveCompanyPort.retrieve(inventory.getCompany().getName());
        Subsidiary subsidiary = retrieveSubsidiaryPort.retrieve(inventory.getSubsidiary().getName());

        inventory.setCode(stringProvider.generateRandom(6).toUpperCase());
        inventory.setCompany(company);
        inventory.setSubsidiary(subsidiary);
        inventory.getBatchTypes()
                .forEach(batchType -> batchType.setInventory(inventory));

        return inventoryMapper.toOutputModel(saveInventoryPort.save(inventory));
    }

    @Override
    public InventoryDto query(QueryInventoryCommand command) {
        String identity = command.getCode().toUpperCase();
        return loadInventoryPort.findByCode(identity)
                .map(inventoryMapper::toOutputModel)
                .orElseThrow(() -> new InventoryNotFoundException(identity));
    }
}