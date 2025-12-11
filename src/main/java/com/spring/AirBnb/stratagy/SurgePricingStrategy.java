package com.spring.AirBnb.stratagy;


import com.spring.AirBnb.entity.Inventory;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class SurgePricingStrategy implements PricingStratagy{

    private final PricingStratagy pricingStratagy;

    @Override
    public BigDecimal calculate(Inventory inventory) {
        BigDecimal price = pricingStratagy.calculate(inventory);
        return price.multiply(inventory.getSurgeFactor());
    }
}
