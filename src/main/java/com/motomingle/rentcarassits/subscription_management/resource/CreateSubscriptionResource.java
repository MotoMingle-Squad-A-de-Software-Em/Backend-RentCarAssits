package com.motomingle.rentcarassits.subscription_management.resource;

import com.motomingle.rentcarassits.subscription_management.domain.model.entity.Plans;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateSubscriptionResource {
    @NotNull
    private Plans plans;

    @NotNull
    private Float price;
}
