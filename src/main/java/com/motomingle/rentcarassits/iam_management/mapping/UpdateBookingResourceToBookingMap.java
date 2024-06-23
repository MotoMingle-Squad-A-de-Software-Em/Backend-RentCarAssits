package com.motomingle.rentcarassits.iam_management.mapping;

import com.motomingle.rentcarassits.iam_management.domain.model.entity.Booking;
import com.motomingle.rentcarassits.iam_management.resource.UpdateBookingResource;
import org.modelmapper.PropertyMap;


public class UpdateBookingResourceToBookingMap extends
        PropertyMap<UpdateBookingResource, Booking> {
    @Override
    protected void configure() {
        map().setUserId(source.getUserId());
        map().setVehicleId(source.getVehicleId());
        skip(destination.getId());
    }
}