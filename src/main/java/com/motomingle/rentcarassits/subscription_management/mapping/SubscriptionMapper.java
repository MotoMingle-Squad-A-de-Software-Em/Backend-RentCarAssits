package com.motomingle.rentcarassits.subscription_management.mapping;

import com.motomingle.rentcarassits.shared.mapping.EnhancedModelMapper;
import com.motomingle.rentcarassits.subscription_management.domain.model.entity.Subscription;
import com.motomingle.rentcarassits.subscription_management.resource.CreateSubscriptionResource;
import com.motomingle.rentcarassits.subscription_management.resource.SubscriptionResource;
import com.motomingle.rentcarassits.subscription_management.resource.UpdateSubscriptionResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class SubscriptionMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public SubscriptionResource toResource(Subscription model) {
        return mapper.map(model, SubscriptionResource.class);
    }

    public Subscription toModel(CreateSubscriptionResource resource) {
        return mapper.map(resource, Subscription.class);
    }

    public Subscription toModel(UpdateSubscriptionResource resource) {
        return mapper.map(resource, Subscription.class);
    }

    public Page<SubscriptionResource> modelListPage(List<Subscription> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, SubscriptionResource.class), pageable, modelList.size());
    }
}
