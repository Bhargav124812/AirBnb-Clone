package com.spring.AirBnb.dto;

import com.spring.AirBnb.entity.Enums.Gender;
import lombok.Data;

import java.time.LocalDate;


@Data
public class ProfileUpdateRequestDto {

    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
}
