package com.spring.AirBnb.service;

import com.spring.AirBnb.entity.Room;

public interface InventoryService {

    void initializeRoomForAYear(Room room);

    void deleteFutureInventories(Room room);
}
