package com.spring.AirBnb.stratagy;

import com.spring.AirBnb.entity.Inventory;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class HolidayPricingStrategy implements PricingStratagy{
    private final PricingStratagy pricingStratagy;

    @Override
    public BigDecimal calculate(Inventory inventory) {
        BigDecimal price =pricingStratagy.calculate(inventory);
        boolean isHoliday= true;
        if(isHoliday){
            price = price.multiply(BigDecimal.valueOf(1.25));
        }
        return price;
    }
}
