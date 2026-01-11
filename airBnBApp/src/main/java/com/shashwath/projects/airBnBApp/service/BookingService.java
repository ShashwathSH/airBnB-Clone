package com.shashwath.projects.airBnBApp.service;

import com.shashwath.projects.airBnBApp.dto.BookingDto;
import com.shashwath.projects.airBnBApp.dto.BookingRequest;
import com.shashwath.projects.airBnBApp.dto.GuestDto;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface BookingService {
    BookingDto initializeBooking(BookingRequest bookingRequest);

    BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList);
}
