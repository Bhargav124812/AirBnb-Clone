package com.spring.AirBnb.controller;


import com.spring.AirBnb.dto.HotelDto;
import com.spring.AirBnb.dto.HotelSearchDto;
import com.spring.AirBnb.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelSearchController {

    private final InventoryService inventoryService;

    @GetMapping("/search")
    public ResponseEntity<Page<HotelDto>> searchHotels(@RequestBody HotelSearchDto hotelSearchDto) {

        Page<HotelDto> page = inventoryService.searchHotel(hotelSearchDto);
        return ResponseEntity.ok(page);
    }
}
