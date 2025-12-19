package com.spring.AirBnb.service;

import com.spring.AirBnb.entity.User;

public interface UserService {

    User getUserById(Long id);

    void updateTheUserRole(String role);
}
