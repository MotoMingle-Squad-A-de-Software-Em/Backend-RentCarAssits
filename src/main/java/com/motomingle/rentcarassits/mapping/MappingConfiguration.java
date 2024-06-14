package com.motomingle.rentcarassits.mapping;

import com.motomingle.rentcarassits.iam_management.mapping.UserMapper;
import com.motomingle.rentcarassits.subscription_management.mapping.SubscriptionMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("RentCarAssitsMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public UserMapper UserMapper() { return new UserMapper(); }
    @Bean
    public SubscriptionMapper SubscriptionMapper() { return new SubscriptionMapper(); }
}
