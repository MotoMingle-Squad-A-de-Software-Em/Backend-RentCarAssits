package com.motomingle.rentcarassits.subscription_management.api;

import com.motomingle.rentcarassits.iam_management.resource.UserResource;
import com.motomingle.rentcarassits.subscription_management.domain.service.SensorDataService;
import com.motomingle.rentcarassits.subscription_management.mapping.SensorDataMapper;
import com.motomingle.rentcarassits.subscription_management.resource.CreateSensorDataResource;
import com.motomingle.rentcarassits.subscription_management.resource.SensorDataResource;
import com.motomingle.rentcarassits.subscription_management.resource.SubscriptionResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Tag(name = "Sensors Data", description = "Create, read, update and delete sensor data")
@RestController
@RequestMapping(value = "api/v1/sensors-data")
public class SensorDataController {
    private final SensorDataService sensorDataService;
    private final SensorDataMapper mapper;

    public SensorDataController(SensorDataService sensorDataService, SensorDataMapper mapper) {
        this.sensorDataService = sensorDataService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get All Sensors Data", description = "Get all sensors data.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sensors data found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SubscriptionResource.class))})
    })
    @GetMapping
    public Page<SensorDataResource> getAllSensors(Pageable pageable) {
        return mapper.modelListPage(sensorDataService.readSensorData(), pageable);
    }

    @Operation(summary = "Create Sensor Data", description = "Create Sensor Data in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sensor Data created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResource.class)
                    ))
    })
    @PostMapping
    public SensorDataResource createSensorData(@RequestBody CreateSensorDataResource resource) {
        return mapper.toResource(sensorDataService.create(mapper.toModel(resource)));
    }

    @Operation(summary = "Delete a Sensor Data", description = "Delete a Sensor Data from database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sensor Data deleted", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("{sensorDataId}")
    public ResponseEntity<?> deleteSensorData(@PathVariable Long sensorDataId) {
        return sensorDataService.delete(sensorDataId);
    }
}
