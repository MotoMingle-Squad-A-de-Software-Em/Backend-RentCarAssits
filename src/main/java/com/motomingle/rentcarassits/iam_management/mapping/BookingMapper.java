package com.motomingle.rentcarassits.iam_management.mapping;

import com.motomingle.rentcarassits.iam_management.domain.model.entity.Booking;
import com.motomingle.rentcarassits.iam_management.resource.*;
import com.motomingle.rentcarassits.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class BookingMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    public BookingResource toResource(Booking model) {
        return mapper.map(model, BookingResource.class);
    }

    public Booking toModel(CreateBookingResource resource) {
        return mapper.map(resource, Booking.class);
    }

    public Booking toModel(UpdateBookingResource resource) {
        return mapper.map(resource, Booking.class);
    }

    public Page<BookingResource> modelListPage(List<Booking> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, BookingResource.class), pageable, modelList.size());
    }
}