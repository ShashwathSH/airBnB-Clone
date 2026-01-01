package com.shashwath.projects.airBnBApp.service;

import com.shashwath.projects.airBnBApp.dto.RoomDto;
import com.shashwath.projects.airBnBApp.entity.Hotel;
import com.shashwath.projects.airBnBApp.entity.Room;
import com.shashwath.projects.airBnBApp.expection.ResourceNotFoundException;
import com.shashwath.projects.airBnBApp.repository.HotelRepository;
import com.shashwath.projects.airBnBApp.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    @Override
    public RoomDto createNewRoom(Long hotelId,RoomDto roomDto) {
        log.info("Creating a new Room in hotel with id: {}", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: "+hotelId));
        Room room = modelMapper.map(roomDto,Room.class);
        room.setHotel(hotel);
        room = roomRepository.save(room);

        //TODO: create inventory as soon as room is created and if hotel is active
        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {
        log.info("Getting all Rooms in hotel with id: {}", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: "+hotelId));
        return hotel.getRooms()
                .stream()
                .map((element) -> modelMapper.map(element, RoomDto.class))
                .collect(Collectors.toList() );
    }

    @Override
    public RoomDto getRoomById(Long roomId) {
        log.info("Getting the Room with id: {}", roomId);
        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: "+roomId));
        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public void deleteRoomById(Long roomId) {
        log.info("Deleting the Room with id: {}", roomId);
        boolean exists = roomRepository.existsById(roomId);
        if(!exists) {
            throw new ResourceNotFoundException("Room not found with id: "+roomId);
        }
        roomRepository.deleteById(roomId);
        }
        //TODO: Delete all future inventory for this room
    }

