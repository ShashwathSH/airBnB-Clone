package com.shashwath.projects.airBnBApp.repository;

import com.shashwath.projects.airBnBApp.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
}
