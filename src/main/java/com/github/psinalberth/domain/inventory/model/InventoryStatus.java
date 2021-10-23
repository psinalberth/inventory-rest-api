package com.github.psinalberth.domain.inventory.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum InventoryStatus {

    CREATED(1, "Created"),
    IN_PROGRESS(2, "In Progress"),
    FINISHED(3, "Finished"),
    CANCELLED(4, "Cancelled"),
    REOPENED(5, "Reopened");

    private final int value;
    private final String name;
}
