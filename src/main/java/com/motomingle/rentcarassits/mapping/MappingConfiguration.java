package com.motomingle.rentcarassits.mapping;

import com.motomingle.rentcarassits.iam_management.mapping.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("RentCarAssitsMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public UserMapper UserMapper() { return new UserMapper(); }
}
