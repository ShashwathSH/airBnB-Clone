package com.shashwath.projects.airBnBApp.dto;

import com.shashwath.projects.airBnBApp.entity.User;
import com.shashwath.projects.airBnBApp.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class GuestDto {

    private Long id;

    private User user;

    private String name;

    private Gender gender;

    private Integer age;
}
