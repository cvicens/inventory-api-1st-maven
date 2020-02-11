package com.redhat.cloudnative.inventory.data;

import com.redhat.cloudnative.inventory.model.InventoryItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface InventoryRepository extends JpaRepository<InventoryItem, Integer> {
    public InventoryItem findByItemId(String itemId);
}