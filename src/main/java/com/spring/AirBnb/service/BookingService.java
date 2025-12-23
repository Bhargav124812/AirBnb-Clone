package com.spring.AirBnb.service;

import com.spring.AirBnb.dto.BookingDto;
import com.spring.AirBnb.dto.BookingRequestDto;
import com.spring.AirBnb.dto.GuestDto;
import com.stripe.model.Event;

import java.util.List;

public interface BookingService {
     BookingDto initializeBooking(BookingRequestDto bookingRequestDto);

     BookingDto addGuests(Long bookingId, List<GuestDto> guestList);

    String initiatePayments(Long bookingId);

    void capturePayment(Event event);

    void cancelBooking(Long bookingId);

}
