package com.spring.AirBnb.stratagy;

import com.spring.AirBnb.entity.Inventory;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
public class UrgencyPricingStrategy implements PricingStratagy{

    private final PricingStratagy pricingStratagy;

    @Override
    public BigDecimal calculate(Inventory inventory) {
        BigDecimal price = pricingStratagy.calculate(inventory);
        LocalDate today = LocalDate.now();
        if(!inventory.getDate().isBefore(today) && inventory.getDate().isBefore(today.plusDays(7))) {
            price = price.multiply(BigDecimal.valueOf(1.15));
        }
        return price;
    }
}
