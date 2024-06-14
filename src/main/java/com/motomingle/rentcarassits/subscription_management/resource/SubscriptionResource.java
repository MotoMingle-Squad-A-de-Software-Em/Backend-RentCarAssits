package com.motomingle.rentcarassits.subscription_management.resource;

import com.motomingle.rentcarassits.subscription_management.domain.model.entity.Plans;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionResource {
    private Long subscriptionId;
    private Plans plans;
    private Float price;
}
