package com.motomingle.rentcarassits.subscription_management.domain.persistence;

import com.motomingle.rentcarassits.subscription_management.domain.model.entity.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
}
