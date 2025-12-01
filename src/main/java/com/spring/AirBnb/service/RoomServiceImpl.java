package com.spring.AirBnb.service;

import com.spring.AirBnb.dto.RoomDto;
import com.spring.AirBnb.entity.Hotel;
import com.spring.AirBnb.entity.Room;
import com.spring.AirBnb.exception.ResourceNotFoundException;
import com.spring.AirBnb.repository.HotelRepository;
import com.spring.AirBnb.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;

    private final ModelMapper modelMapper;

    private final HotelRepository hotelRepository;

    private final InventoryService inventoryService;


    @Override
    public RoomDto createRoom(Long hotelId, RoomDto roomDto) {
        log.info("Creating the room with Hotel id {}",hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: "+hotelId));
        Room room = modelMapper.map(roomDto,Room.class);
        room.setHotel(hotel);
        room=roomRepository.save(room);

        if (hotel.getActive()) {
            inventoryService.initializeRoomForAYear(room);
        }
        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {
        log.info("Getting all rooms in hotel with ID: {}", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: "+hotelId));

        return hotel.getRooms()
                .stream()
                .map((element) -> modelMapper.map(element, RoomDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomById(Long id) {
        log.info("Getting the room with ID: {}", id);
        Room room = roomRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: "+id));
        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    @Transactional
    public void deleteRoomById(Long id) {
        log.info("Deleting the room with ID: {}", id);
        Room room = roomRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: "+id));
        inventoryService.deleteFutureInventories(room);
        roomRepository.deleteById(id);
    }
}
