package com.shashwath.projects.airBnBApp.service;

import com.shashwath.projects.airBnBApp.entity.Room;

public interface InventoryService {

    void initializeRoomForAYear(Room room);
    void deleteFutureInventories(Room room);
}
