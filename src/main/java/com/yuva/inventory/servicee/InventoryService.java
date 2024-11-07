package com.yuva.inventory.servicee;

import com.yuva.inventory.model.Inventory;
import com.yuva.inventory.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public Integer addQuantity(String productId, Integer quantity) {
        Inventory inventory = Inventory.builder().id(null)
                .productId(productId).quantity(quantity).build();
        Inventory savedInventory = inventoryRepository.save(inventory);
        return savedInventory.getQuantity();
    }

    public Integer reduceQuantity(String productId, Integer quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId);
        inventory.setQuantity(inventory.getQuantity() - quantity);
        Inventory savedInventory = inventoryRepository.save(inventory);
        return savedInventory.getQuantity();
    }

    public Integer getQuantity(String productId) {
        Inventory inventory = inventoryRepository.findByProductId(productId);
        return inventory.getQuantity();
    }

    public Integer increaseQuantity(String productId, Integer quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId);
        inventory.setQuantity(inventory.getQuantity() + quantity);
        Inventory savedInventory = inventoryRepository.save(inventory);
        return savedInventory.getQuantity();
    }

    public void deleteByProductId(String productId) {
        inventoryRepository.deleteByProductId(productId);
    }
}
