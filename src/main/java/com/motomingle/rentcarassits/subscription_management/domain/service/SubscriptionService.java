package com.motomingle.rentcarassits.subscription_management.domain.service;

import com.motomingle.rentcarassits.subscription_management.domain.model.entity.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SubscriptionService {
    List<Subscription> getAll();
    Page<Subscription> getAll(Pageable pageable);
    Subscription create(Long userId, Subscription subscription);
    Subscription update(Long userId, Long subscriptionId, Subscription subscription);
    ResponseEntity<?> delete(Long userId, Long subscriptionId);
    List<Subscription> getAllByUserId(Long userId);
}
