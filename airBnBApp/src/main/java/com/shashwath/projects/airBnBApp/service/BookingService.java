package com.shashwath.projects.airBnBApp.service;

import com.shashwath.projects.airBnBApp.dto.BookingDto;
import com.shashwath.projects.airBnBApp.dto.BookingRequest;
import org.jspecify.annotations.Nullable;

public interface BookingService {
    BookingDto initializeBooking(BookingRequest bookingRequest);
}
