package com.motomingle.rentcarassits.subscription_management.service;

import com.motomingle.rentcarassits.shared.exception.ResourceNotFoundException;
import com.motomingle.rentcarassits.shared.exception.ResourceValidationException;
import com.motomingle.rentcarassits.subscription_management.domain.model.entity.SensorData;
import com.motomingle.rentcarassits.subscription_management.domain.persistence.SensorDataRepository;
import com.motomingle.rentcarassits.subscription_management.domain.service.SensorDataService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SensorDataServiceIn implements SensorDataService {
    private static final String ENTITY = "Sensor Data";

    private final SensorDataRepository sensorDataRepository;
    private final Validator validator;

    public SensorDataServiceIn(SensorDataRepository sensorDataRepository, Validator validator) {
        this.sensorDataRepository = sensorDataRepository;
        this.validator = validator;
    }

    @Override
    public List<SensorData> readSensorData() {
        return sensorDataRepository.findAll();
    }

    @Override
    public Page<SensorData> readSensorData(Pageable pageable) {
        return sensorDataRepository.findAll(pageable);
    }

    @Override
    public SensorData create(SensorData sensorData) {
        Set<ConstraintViolation<SensorData>> violations = validator.validate(sensorData);
        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        return sensorDataRepository.save(sensorData);
    }

    @Override
    public ResponseEntity<?> delete(Long sensorDataId) {
        return sensorDataRepository.findById(sensorDataId).map(
                sensorData -> {
                    sensorDataRepository.delete(sensorData);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, sensorDataId));
    }
}
