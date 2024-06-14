package com.motomingle.rentcarassits.subscription_management.domain.persistence;

import com.motomingle.rentcarassits.subscription_management.domain.model.entity.Plans;
import com.motomingle.rentcarassits.subscription_management.domain.model.entity.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findByIdAndUserId(Long subscriptionId, Long userId);
    List<Subscription> findAllByUserId(Long userId);
}
