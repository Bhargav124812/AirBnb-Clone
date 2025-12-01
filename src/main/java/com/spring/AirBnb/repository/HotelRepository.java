package com.spring.AirBnb.repository;

import com.spring.AirBnb.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
