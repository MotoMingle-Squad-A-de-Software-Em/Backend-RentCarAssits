package com.motomingle.rentcarassits.subscription_management.mapping;

import com.motomingle.rentcarassits.shared.mapping.EnhancedModelMapper;
import com.motomingle.rentcarassits.subscription_management.domain.model.entity.SensorData;
import com.motomingle.rentcarassits.subscription_management.resource.CreateSensorDataResource;
import com.motomingle.rentcarassits.subscription_management.resource.SensorDataResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class SensorDataMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public SensorDataResource toResource(SensorData model) {
        return mapper.map(model, SensorDataResource.class);
    }
    public SensorData toModel(CreateSensorDataResource resource) {
        return mapper.map(resource, SensorData.class);
    }
    public Page<SensorDataResource> modelListPage(List<SensorData> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, SensorDataResource.class), pageable, modelList.size());
    }
}
