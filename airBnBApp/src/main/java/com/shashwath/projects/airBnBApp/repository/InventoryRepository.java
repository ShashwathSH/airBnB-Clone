package com.shashwath.projects.airBnBApp.repository;

import com.shashwath.projects.airBnBApp.entity.Hotel;
import com.shashwath.projects.airBnBApp.entity.Inventory;
import com.shashwath.projects.airBnBApp.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {

    @Transactional
    @Modifying
    @Query("delete from Inventory i where i.room = ?1")
    void deleteByRoom(Room room);


    @Query("""
            SELECT DISTINCT i.hotel
            FROM Inventory i
            WHERE i.city = :city
                AND i.date BETWEEN :startDate AND :endDate
                AND i.closed = false
                AND (i.totalCount - i.bookedCount >= :roomsCount)
            GROUP BY i.hotel,i.room
            HAVING COUNT(i.date) = :dateCount
            """)
    Page<Hotel> findHotelsWithAvailableInventory(
            @Param("city") String city,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("roomsCount") Integer roomsCount,
            @Param("dateCount") Long dateCount,
            Pageable pageable
            );


}
