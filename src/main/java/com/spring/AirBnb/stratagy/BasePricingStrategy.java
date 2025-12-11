package com.spring.AirBnb.stratagy;

import com.spring.AirBnb.entity.Inventory;

import java.math.BigDecimal;

public class BasePricingStrategy implements PricingStratagy{

    @Override
    public BigDecimal calculate(Inventory inventory) {
        return inventory.getRoom().getBasePrice();
    }
}
