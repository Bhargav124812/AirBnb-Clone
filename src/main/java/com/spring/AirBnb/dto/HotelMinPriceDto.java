package com.spring.AirBnb.dto;


import com.spring.AirBnb.entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelMinPriceDto {
    private Hotel hotel;
    private Double price;
}
