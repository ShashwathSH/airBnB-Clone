package com.shashwath.projects.airBnBApp.strategy;

import com.shashwath.projects.airBnBApp.entity.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@RequiredArgsConstructor
public class SuregPricingStrategy implements PricingStrategy{

    private final PricingStrategy wrapped;

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        BigDecimal price =  wrapped.calculatePrice(inventory);
        return price.multiply(inventory.getSurgeFactor());
    }
}
