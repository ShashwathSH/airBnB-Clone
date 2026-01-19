package com.shashwath.projects.airBnBApp.strategy;

import com.shashwath.projects.airBnBApp.entity.Inventory;


import java.math.BigDecimal;


public class BasePricingStrategy implements PricingStrategy{
    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        return inventory.getRoom().getBasePrice();
    }
}
