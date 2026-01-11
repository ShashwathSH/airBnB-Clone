package com.shashwath.projects.airBnBApp.repository;

import com.shashwath.projects.airBnBApp.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}