package com.spring.AirBnb.service;

import com.spring.AirBnb.dto.HotelDto;
import com.spring.AirBnb.dto.HotelSearchDto;
import com.spring.AirBnb.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {

    void initializeRoomForAYear(Room room);

    void deleteFutureInventories(Room room);

    Page<HotelDto> searchHotel(HotelSearchDto hotelSearchDto);
}
