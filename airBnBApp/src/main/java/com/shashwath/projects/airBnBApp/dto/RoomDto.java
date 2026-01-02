package com.shashwath.projects.airBnBApp.dto;

import lombok.Data;


import java.math.BigDecimal;

//Room Data Transfer Object
@Data
public class RoomDto {

    private Long id;

    private String type;

    private BigDecimal basePrice;

    private String[] photos;

    private String[] amenities;

    private Integer totalCount;

    private Integer capacity;

}
