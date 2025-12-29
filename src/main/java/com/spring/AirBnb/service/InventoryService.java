package com.spring.AirBnb.service;

import com.spring.AirBnb.dto.*;
import com.spring.AirBnb.entity.Room;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InventoryService {

    void initializeRoomForAYear(Room room);

    void deleteFutureInventories(Room room);

    Page<HotelMinPriceDto> searchHotel(HotelSearchDto hotelSearchDto);

     List<InventoryDto> getAllInventoryByRoom(Long roomId);

    void updateInventory(Long roomId, UpdateInventoryRequestDto updateInventoryRequestDto);
}
