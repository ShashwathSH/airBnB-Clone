package com.shashwath.projects.airBnBApp.service;


import com.shashwath.projects.airBnBApp.dto.RoomDto;

import java.util.List;

public interface RoomService {
    RoomDto createNewRoom(Long hotelId,RoomDto roomDto);
    List<RoomDto> getAllRoomsInHotel(Long hotelId);
    RoomDto getRoomById(Long roomId);

    void deleteRoomById(Long roomId);
}
