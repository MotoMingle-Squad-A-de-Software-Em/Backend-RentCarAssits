package com.motomingle.rentcarassits.iam_management.service;

import com.motomingle.rentcarassits.iam_management.domain.model.entity.Vehicle;
import com.motomingle.rentcarassits.iam_management.domain.persistence.VehicleRepository;
import com.motomingle.rentcarassits.iam_management.domain.service.VehicleService;
import com.motomingle.rentcarassits.shared.exception.ResourceNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static org.hibernate.usertype.DynamicParameterizedType.ENTITY;

@Service
public class VehicleServiceIn implements VehicleService {
    private static final String ENTITY_NAME = "vehicle";
    private final VehicleRepository vehicleRepository;
    private final Validator validator;

    public VehicleServiceIn(VehicleRepository vehicleRepository, Validator validator) {
        this.vehicleRepository = vehicleRepository;
        this.validator = validator;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Page<Vehicle> getAllVehicles(Pageable pageable) {
        return vehicleRepository.findAll(pageable);
    }

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        Set<ConstraintViolation<Vehicle>> violations = validator.validate(vehicle);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(ENTITY, violations);
        }

        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle updateVehicle(Long vehicleId, Vehicle request) {
        Set<ConstraintViolation<Vehicle>> violations = validator.validate(request);
        if(!violations.isEmpty()) {
            throw new ConstraintViolationException(ENTITY, violations);
        }

        return vehicleRepository.findById(vehicleId).map(vehicle ->
                vehicleRepository.save(vehicle.withModel(request.getModel())
                        .withBrand(request.getBrand())
                        .withAddress(request.getAddress())
                        .withSeats(request.getSeats())
                        .withStars(request.getStars())
                        .withFeatures(request.getFeatures())
                        .withPrice(request.getPrice())
                        .withVehiclePicture(request.getVehiclePicture())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, vehicleId));
    }

    @Override
    public ResponseEntity<?> deleteVehicle(Long Id) {
        return vehicleRepository.findById(Id).map(
                vehicle -> {
                    vehicleRepository.delete(vehicle);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException(ENTITY_NAME, Id));
    }

    @Override
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(ENTITY, id));
    }
}
