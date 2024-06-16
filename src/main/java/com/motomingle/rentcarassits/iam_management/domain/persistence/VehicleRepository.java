package com.motomingle.rentcarassits.iam_management.domain.persistence;

import com.motomingle.rentcarassits.iam_management.domain.model.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Vehicle findByModel(String model);
    Vehicle findByBrand(String brand);
    Vehicle findBySeats(int seats);
    Vehicle findById(long id);
    Vehicle findByPrice(double price);
}
