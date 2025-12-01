package com.spring.AirBnb.repository;

import com.spring.AirBnb.entity.Inventory;
import com.spring.AirBnb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    void deleteByDateAfterAndRoom(LocalDate date, Room room);
}
