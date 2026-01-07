package com.shashwath.projects.airBnBApp.service;

import com.shashwath.projects.airBnBApp.dto.HotelDto;
import com.shashwath.projects.airBnBApp.dto.HotelSearchRequest;
import com.shashwath.projects.airBnBApp.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {

    void initializeRoomForAYear(Room room);
    void deleteAllInventories(Room room);

    Page<HotelDto> searchHotels(HotelSearchRequest hotelSearchRequest);
}
