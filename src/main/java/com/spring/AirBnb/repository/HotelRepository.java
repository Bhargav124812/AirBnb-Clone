package com.spring.AirBnb.repository;

import com.spring.AirBnb.entity.Hotel;
import com.spring.AirBnb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByOwner(User user);
}
