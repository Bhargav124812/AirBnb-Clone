package com.spring.AirBnb.service;

import com.spring.AirBnb.dto.ProfileUpdateRequestDto;
import com.spring.AirBnb.dto.UserDto;
import com.spring.AirBnb.entity.User;

public interface UserService {

    User getUserById(Long id);

    void updateTheUserRole(String role);

    void updateProfile(ProfileUpdateRequestDto profileUpdateRequestDto);

    UserDto getMyProfile();
}
