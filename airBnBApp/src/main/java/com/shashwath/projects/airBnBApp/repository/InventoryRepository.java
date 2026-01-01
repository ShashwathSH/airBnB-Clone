package com.shashwath.projects.airBnBApp.repository;

import com.shashwath.projects.airBnBApp.entity.Inventory;
import com.shashwath.projects.airBnBApp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {


    @Transactional
    @Modifying
    @Query("delete from Inventory i where i.date > ?1 and i.room = ?2")
    void deleteByDateAfterAndRoom(LocalDate date, Room room);


}
