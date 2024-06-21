package com.motomingle.rentcarassits.iam_management.domain.persistence;


import com.motomingle.rentcarassits.iam_management.domain.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId);
    List<Booking> findByVehicleId(Long vehicleId);
}