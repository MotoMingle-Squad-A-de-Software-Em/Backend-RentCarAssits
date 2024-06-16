package com.motomingle.rentcarassits.subscription_management.api;

import com.motomingle.rentcarassits.subscription_management.domain.model.entity.Subscription;
import com.motomingle.rentcarassits.subscription_management.domain.service.SubscriptionService;
import com.motomingle.rentcarassits.subscription_management.mapping.SubscriptionMapper;
import com.motomingle.rentcarassits.subscription_management.resource.CreateSubscriptionResource;
import com.motomingle.rentcarassits.subscription_management.resource.SubscriptionResource;
import com.motomingle.rentcarassits.subscription_management.resource.UpdateSubscriptionResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Tag(name = "Subscriptions", description = "Create, read, update and delete subscription")
@RestController
@RequestMapping(value = "api/v1/users/{userId}/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final SubscriptionMapper mapper;

    public SubscriptionController(SubscriptionService subscriptionService, SubscriptionMapper mapper) {
        this.subscriptionService = subscriptionService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get All Subscriptions for a User", description = "Get all subscriptions for a specific user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscriptions found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SubscriptionResource.class))})
    })
    @GetMapping
    public Page<SubscriptionResource> getAllSubscriptions(@PathVariable Long userId, Pageable pageable) {
        return mapper.modelListPage(subscriptionService.getAllByUserId(userId), pageable);
    }

    @Operation(summary = "Create a Subscription for a User", description = "Create a new subscription for a specific user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscription created successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SubscriptionResource.class))})
    })
    @PostMapping
    public SubscriptionResource createSubscription(@PathVariable Long userId, @RequestBody CreateSubscriptionResource resource) {
        return mapper.toResource(subscriptionService.create(userId, mapper.toModel(resource)));
    }

    @Operation(summary = "Update a Subscription for a User", description = "Update an existing subscription for a specific user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscription updated successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SubscriptionResource.class))})
    })
    @PutMapping("{subscriptionId}")
    public SubscriptionResource updateSubscription(@PathVariable Long userId, @PathVariable Long subscriptionId, @RequestBody UpdateSubscriptionResource resource) {
        return mapper.toResource(subscriptionService.update(userId, subscriptionId, mapper.toModel(resource)));
    }

    @Operation(summary = "Delete a Subscription for a User", description = "Delete an existing subscription for a specific user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscription deleted successfully")
    })
    @DeleteMapping("{subscriptionId}")
    public ResponseEntity<?> deleteSubscription(@PathVariable Long userId, @PathVariable Long subscriptionId) {
        return subscriptionService.delete(userId, subscriptionId);
    }
}
