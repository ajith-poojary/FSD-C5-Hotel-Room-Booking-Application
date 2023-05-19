package com.upgrad.booking.repositories;

import com.upgrad.booking.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingServiceRepository extends JpaRepository<Booking,Integer> {

}
