package com.motomingle.rentcarassits.iam_management.domain.service;

import com.motomingle.rentcarassits.iam_management.domain.model.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


import java.util.List;

public interface VehicleService {
    List<Vehicle> getAllVehicles();
    Page<Vehicle> getAllVehicles(Pageable pageable);
    Vehicle createVehicle(Vehicle vehicle);
    Vehicle updateVehicle(Long vehicleId, Vehicle request);
    ResponseEntity<?> deleteVehicle(Long Id);
    Vehicle getVehicleById(Long id);
}
