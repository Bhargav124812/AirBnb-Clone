package com.spring.AirBnb.stratagy;

import com.spring.AirBnb.entity.Inventory;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class OccupancyPricingStrategy implements PricingStratagy{
    private  final  PricingStratagy pricingStratagy;
    @Override
    public BigDecimal calculate(Inventory inventory) {
        BigDecimal price = pricingStratagy.calculate(inventory);
        double occupancyRate = (double) inventory.getBookedCount() / inventory.getTotalCount();
        if(occupancyRate>0.8){
            price = price.multiply(BigDecimal.valueOf(1.2));
        }
        return price;
    }
}
