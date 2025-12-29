package com.spring.AirBnb.service;

import com.spring.AirBnb.dto.BookingDto;
import com.spring.AirBnb.dto.HotelDto;
import com.spring.AirBnb.entity.Booking;
import com.spring.AirBnb.entity.Hotel;
import com.spring.AirBnb.entity.Room;
import com.spring.AirBnb.entity.User;
import com.spring.AirBnb.exception.ResourceNotFoundException;
import com.spring.AirBnb.repository.BookingRepository;
import com.spring.AirBnb.repository.HotelRepository;
import com.spring.AirBnb.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.spring.AirBnb.util.AppUtils.getCurrentUser;


@Service
@RequiredArgsConstructor
@Slf4j
public class HotelServiceImple implements HotelService{

    private final HotelRepository hotelRepository;

    private final ModelMapper modelMapper;

    private final InventoryService inventoryService;

    private final RoomRepository roomRepository;

    private final BookingRepository bookingRepository;

    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        log.info("Creating a new hotel with name: {}", hotelDto.getName());
        Hotel hotel = modelMapper.map(hotelDto,Hotel.class);
        hotel.setActive(false);
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        hotel.setOwner(user);
        hotel=hotelRepository.save(hotel);
        log.info("Created a new hotel with ID: {}", hotelDto.getId());
        return modelMapper.map(hotel,HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long id) {
        log.info("Getting the hotel with ID: {}", id);
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel not found with ID: "+id));
        return modelMapper.map(hotel,HotelDto.class);
    }

    @Override
    public HotelDto updateHotelById(Long id, HotelDto hotelDto) {
        log.info("Updating the hotel with ID: {}", id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: "+id));
        modelMapper.map(hotelDto, hotel);
        hotel.setId(id);
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    @Transactional
    public void deleteHotelById(Long id) {
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: "+id));

        for(Room room: hotel.getRooms()) {
            inventoryService.deleteFutureInventories(room);
            roomRepository.deleteById(room.getId());
        }
        hotelRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void activateHotel(Long id) {
        log.info("Activating the hotel with ID: {}", id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: "+id));

        hotel.setActive(true);

        for(Room room: hotel.getRooms()) {
            inventoryService.initializeRoomForAYear(room);
        }
    }

    @Override
    public List<HotelDto> getAllHotels() {
        log.info("Getting All Hotels OF the USer");
        User user= getCurrentUser();
        List<Hotel>hotels=hotelRepository.findByOwner(user);
        return hotels.stream().map((element) -> modelMapper.map(element,HotelDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingDto> getAllBookingByHotelId(Long hotelId) {
        log.info("Getting the Booking For The ID {}",hotelId);
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel not " +
                "found with ID: "+hotelId));
        User user = getCurrentUser();

        log.info("Getting all booking for the hotel with ID: {}", hotelId);

        if(!user.equals(hotel.getOwner())) throw new AccessDeniedException("You are not the owner of hotel with id: "+hotelId);

        List<Booking> bookings = bookingRepository.findByHotel(hotel);

        return bookings.stream()
                .map((element) -> modelMapper.map(element, BookingDto.class))
                .collect(Collectors.toList());

    }
}
