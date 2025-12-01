package com.spring.AirBnb.service;

import com.spring.AirBnb.dto.RoomDto;

import java.util.List;

public interface RoomService {

    RoomDto createRoom(Long hotelId,RoomDto roomDto);

    List<RoomDto> getAllRoomsInHotel(Long hotelId);

    RoomDto getRoomById(Long id);

    void deleteRoomById(Long id);
}
