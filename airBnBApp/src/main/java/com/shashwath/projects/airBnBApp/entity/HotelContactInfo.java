package com.shashwath.projects.airBnBApp.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import tools.jackson.databind.node.StringNode;

@Getter
@Setter
@Embeddable
public class HotelContactInfo {

    private String address;
    private String phoneNumber;
    private String email;
    private String location;
}
