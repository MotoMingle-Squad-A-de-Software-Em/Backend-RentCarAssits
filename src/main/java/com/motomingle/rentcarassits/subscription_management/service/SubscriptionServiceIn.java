package com.motomingle.rentcarassits.subscription_management.service;

import com.motomingle.rentcarassits.iam_management.domain.model.entity.User;
import com.motomingle.rentcarassits.iam_management.domain.persistence.UserRepository;
import com.motomingle.rentcarassits.shared.exception.ResourceNotFoundException;
import com.motomingle.rentcarassits.shared.exception.ResourceValidationException;
import com.motomingle.rentcarassits.subscription_management.domain.model.entity.Subscription;
import com.motomingle.rentcarassits.subscription_management.domain.persistence.SubscriptionRepository;
import com.motomingle.rentcarassits.subscription_management.domain.service.SubscriptionService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SubscriptionServiceIn implements SubscriptionService {
    private static final String ENTITY = "Subscription";

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final Validator validator;

    public SubscriptionServiceIn(SubscriptionRepository subscriptionRepository, UserRepository userRepository, Validator validator) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @Override
    public List<Subscription> getAllByUserId(Long userId) {
        return subscriptionRepository.findAllByUserId(userId);
    }

    @Override
    public List<Subscription> getAll() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Page<Subscription> getAll(Pageable pageable) {
        return subscriptionRepository.findAll(pageable);
    }

    @Override
    public Subscription create(Long userId, Subscription subscription) {
        Set<ConstraintViolation<Subscription>> violations = validator.validate(subscription);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        return userRepository.findById(userId).map(user -> {
            subscription.setUser(user);
            return subscriptionRepository.save(subscription);
        }).orElseThrow(() -> new ResourceNotFoundException("User", userId));
    }

    @Override
    public Subscription update(Long userId, Long subscriptionId, Subscription request) {
        Set<ConstraintViolation<Subscription>> violations = validator.validate(request);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User", userId);
        }

        return subscriptionRepository.findById(subscriptionId).map(existingSubscription ->
                subscriptionRepository.save(existingSubscription.withPlans(request.getPlans())
                        .withPrice(request.getPrice())))
                .orElseThrow(() -> new ResourceNotFoundException("Subscription", subscriptionId));
    }

    @Override
    public ResponseEntity<?> delete(Long userId, Long subscriptionId) {
        return subscriptionRepository.findByIdAndUserId(subscriptionId, userId).map(
                subscription -> {
                    subscriptionRepository.delete(subscription);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, subscriptionId));
    }
}
