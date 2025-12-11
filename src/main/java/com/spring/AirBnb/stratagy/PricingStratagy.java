package com.spring.AirBnb.stratagy;

import com.spring.AirBnb.entity.Inventory;

import java.math.BigDecimal;

public interface PricingStratagy {

    BigDecimal calculate(Inventory inventory);
}
