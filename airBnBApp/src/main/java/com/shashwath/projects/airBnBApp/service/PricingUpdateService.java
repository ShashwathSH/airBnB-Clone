package com.shashwath.projects.airBnBApp.service;

import com.shashwath.projects.airBnBApp.entity.Hotel;
import com.shashwath.projects.airBnBApp.entity.Inventory;
import com.shashwath.projects.airBnBApp.repository.HotelMinPriceRepository;
import com.shashwath.projects.airBnBApp.repository.HotelRepository;
import com.shashwath.projects.airBnBApp.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PricingUpdateService {

    // scheduler to update the inventory and HotelMinPrice table every hour

    private final HotelRepository hotelRepository;
    private final InventoryRepository inventoryRepository;
    private final HotelMinPriceRepository hotelMinPriceRepository;

    public void updatePrice(){
        int page =0;
        int batchSize = 100;

        while (true){
            Page<Hotel> hotelPage = hotelRepository.findAll(PageRequest.of(page,batchSize));
            if(hotelPage.isEmpty()){
                break;
            }
            hotelPage.getContent().forEach(this::updateHotelPrices);
            page++;
        }
    }

    private void updateHotelPrices(Hotel hotel){
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(1);

        List<Inventory> inventoryList = inventoryRepository.findByHotelAndDateBetween(hotel,startDate,endDate);

    }
}
