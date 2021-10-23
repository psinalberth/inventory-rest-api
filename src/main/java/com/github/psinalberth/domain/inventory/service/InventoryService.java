package com.github.psinalberth.domain.inventory.service;

import com.github.psinalberth.domain.company.model.Company;
import com.github.psinalberth.domain.company.model.Subsidiary;
import com.github.psinalberth.domain.company.repository.CompanyRepository;
import com.github.psinalberth.domain.company.repository.SubsidiaryRepository;
import com.github.psinalberth.domain.inventory.exception.InventoryNotFoundException;
import com.github.psinalberth.domain.inventory.mapper.InventoryMapper;
import com.github.psinalberth.domain.inventory.model.Inventory;
import com.github.psinalberth.domain.inventory.dto.output.InventoryDto;
import com.github.psinalberth.domain.inventory.repository.InventoryRepository;
import com.github.psinalberth.domain.inventory.usecase.CreateInventoryUseCase;
import com.github.psinalberth.domain.inventory.usecase.QueryInventoryUseCase;
import com.github.psinalberth.domain.shared.annotation.UseCase;
import com.github.psinalberth.domain.shared.provider.StringProvider;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class InventoryService implements CreateInventoryUseCase, QueryInventoryUseCase {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;
    private final CompanyRepository companyRepository;
    private final SubsidiaryRepository subsidiaryRepository;
    private final StringProvider stringProvider;

    @Override
    public InventoryDto create(CreateInventoryCommand command) {

        Inventory inventory = inventoryMapper.toEntity(command);
        Company company = companyRepository.findByName(inventory.getCompany().getName())
                .orElseGet(() -> companyRepository.save(inventory.getCompany()));
        Subsidiary subsidiary = subsidiaryRepository.findByName(inventory.getSubsidiary().getName())
                .orElseGet(() -> subsidiaryRepository.save(inventory.getSubsidiary()));

        inventory.setCode(stringProvider.generateRandom(6).toUpperCase());
        inventory.setCompany(company);
        inventory.setSubsidiary(subsidiary);
        inventory.getBatchTypes()
                .forEach(batchType -> batchType.setInventory(inventory));

        return inventoryMapper.toOutputModel(inventoryRepository.save(inventory));
    }

    @Override
    public InventoryDto query(QueryInventoryCommand command) {
        String identity = command.getCode().toUpperCase();
        return inventoryRepository.findByCode(identity)
                .map(inventoryMapper::toOutputModel)
                .orElseThrow(() -> new InventoryNotFoundException(identity));
    }
}