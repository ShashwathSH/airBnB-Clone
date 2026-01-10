package com.shashwath.projects.airBnBApp.service;

import com.shashwath.projects.airBnBApp.dto.BookingDto;
import com.shashwath.projects.airBnBApp.dto.BookingRequest;
import com.shashwath.projects.airBnBApp.entity.*;
import com.shashwath.projects.airBnBApp.enums.BookingStatus;
import com.shashwath.projects.airBnBApp.expection.ResourceNotFoundException;
import com.shashwath.projects.airBnBApp.repository.BookingRepository;
import com.shashwath.projects.airBnBApp.repository.HotelRepository;
import com.shashwath.projects.airBnBApp.repository.InventoryRepository;
import com.shashwath.projects.airBnBApp.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private  final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public BookingDto initializeBooking(BookingRequest bookingRequest) {
        log.info("Initializing the booking for hotel : {}, room: {}, date {}-{}",bookingRequest.getHotelId(),
                bookingRequest.getRoomId(),bookingRequest.getCheckInDate(),bookingRequest.getCheckOutDate());
        Hotel hotel = hotelRepository.findById(bookingRequest.getHotelId()).orElseThrow(()->
                new ResourceNotFoundException("Hotel not found with id:"+bookingRequest.getHotelId()));

        Room room = roomRepository.findById(bookingRequest.getRoomId()).orElseThrow(()->
                new ResourceNotFoundException("Room not found with id:"+bookingRequest.getRoomId()));

        List<Inventory> inventroyList = inventoryRepository.findAndLockAvailableInventory(room.getId(),
                bookingRequest.getCheckInDate(),bookingRequest.getCheckOutDate(),bookingRequest.getRoomsCount());

        long daysCount = ChronoUnit.DAYS.between(bookingRequest.getCheckInDate(),bookingRequest.getCheckOutDate()) + 1;

        if(inventroyList.size() != daysCount){
            throw  new IllegalStateException("Room is not available anymore");
        }

        // Reserve the room / update the booked count of inventories
        for(Inventory inventory: inventroyList){
            inventory.setBookedCount(inventory.getBookedCount()+bookingRequest.getRoomsCount());
        }

        inventoryRepository.saveAll(inventroyList);

        // Create the booking
        User user = new User();
        user.setId(1L); // TODO: REMOVE DUMMY USER

        //TODO: Calculate dynamic price

        Booking booking = Booking.builder()
                .bookingStatus(BookingStatus.RESERVED)
                .hotel(hotel)
                .room(room)
                .checkInDate(bookingRequest.getCheckInDate())
                .checkOutDate(bookingRequest.getCheckOutDate())
                .user(user)
                .roomsCount(bookingRequest.getRoomsCount())
                .amount(BigDecimal.TEN)
                .build();

        booking = bookingRepository.save(booking);
        return modelMapper.map(booking, BookingDto.class);

    }


}
