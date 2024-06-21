package com.motomingle.rentcarassits.iam_management.mapping;

import com.motomingle.rentcarassits.iam_management.domain.model.entity.Vehicle;
import com.motomingle.rentcarassits.iam_management.resource.CreateVehicleResource;
import com.motomingle.rentcarassits.iam_management.resource.UpdateVehicleResource;
import com.motomingle.rentcarassits.iam_management.resource.VehicleResource;
import com.motomingle.rentcarassits.shared.mapping.EnhancedModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class VehicleMapper implements Serializable {
    final
    EnhancedModelMapper mapper;

    public VehicleMapper(EnhancedModelMapper mapper) {
        this.mapper = mapper;
    }

    public VehicleResource toResource(Vehicle model){ return mapper.map(model, VehicleResource.class); };
    public Vehicle toModel(CreateVehicleResource resource){return mapper.map(resource, Vehicle.class);}
    public Vehicle toModel(UpdateVehicleResource resource){return mapper.map(resource, Vehicle.class);}
    public Page<VehicleResource> modelListPage(List<Vehicle> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, VehicleResource.class), pageable, modelList.size());
    }
}
