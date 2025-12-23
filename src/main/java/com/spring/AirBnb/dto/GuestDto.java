package com.spring.AirBnb.dto;

import com.spring.AirBnb.entity.Enums.Gender;
import com.spring.AirBnb.entity.User;
import lombok.Data;


@Data
public class GuestDto {
    private Long id;
    private User user;
    private String name;
    private Gender gender;
    private Integer age;
}
