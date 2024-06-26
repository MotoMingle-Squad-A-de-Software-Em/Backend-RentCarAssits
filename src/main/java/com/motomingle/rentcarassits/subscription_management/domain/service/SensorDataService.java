package com.motomingle.rentcarassits.subscription_management.domain.service;

import com.motomingle.rentcarassits.subscription_management.domain.model.entity.SensorData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SensorDataService {
    List<SensorData> readSensorData();
    Page<SensorData> readSensorData(Pageable pageable);
    SensorData create(SensorData sensorData);
    ResponseEntity<?> delete(Long sensorDataId);
}
