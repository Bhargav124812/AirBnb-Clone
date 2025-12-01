package com.spring.AirBnb.dto;

import com.spring.AirBnb.entity.HotelContactInfo;
import lombok.Data;

@Data
public class HotelDto {
    private Long id;
    private String name;
    private String city;
    private HotelContactInfo hotelContactInfo;
    private String[] photos;
    private String[] amenities;
    private Boolean active;
}
