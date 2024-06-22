package com.motomingle.rentcarassits.iam_management.mapping;

import com.motomingle.rentcarassits.iam_management.domain.model.entity.Booking;
import com.motomingle.rentcarassits.iam_management.resource.CreateBookingResource;
import org.modelmapper.PropertyMap;

public class CreateBookingResourceToBookingMap extends PropertyMap<CreateBookingResource, Booking> {
    @Override
    protected void configure() {
        map().setUserId(source.getUserId());
        map().setVehicleId(source.getVehicleId());
        map().setId(null);
    }
}