package com.spring.AirBnb.service;

import com.spring.AirBnb.dto.BookingDto;
import com.spring.AirBnb.dto.HotelDto;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface HotelService {

  HotelDto createNewHotel(HotelDto hotelDto);

  HotelDto getHotelById(Long id);

  HotelDto updateHotelById(Long id,HotelDto hotelDto);

  void deleteHotelById(Long id);

  void activateHotel(Long id);

    List<HotelDto> getAllHotels();

    List<BookingDto> getAllBookingByHotelId(Long hotelId);
}

