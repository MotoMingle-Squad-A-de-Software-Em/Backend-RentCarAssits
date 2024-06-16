package com.motomingle.rentcarassits.iam_management.api;

import com.motomingle.rentcarassits.iam_management.domain.service.VehicleService;
import com.motomingle.rentcarassits.iam_management.mapping.VehicleMapper;
import com.motomingle.rentcarassits.iam_management.resource.CreateVehicleResource;
import com.motomingle.rentcarassits.iam_management.resource.UpdateVehicleResource;
import com.motomingle.rentcarassits.iam_management.resource.VehicleResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Tag(name = "Vehicles", description = "Create, read, update and delete vehicles")
@RestController
@RequestMapping(value = "api/v1/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;
    private final VehicleMapper vehicleMapper;

    public VehicleController(VehicleService vehicleService, VehicleMapper vehicleMapper) {
        this.vehicleService = vehicleService;
        this.vehicleMapper = vehicleMapper;
    }

    @Operation(summary = "Get all vehicles", description = "Get all vehicles stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle found",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = VehicleResource.class))})
    })
    @GetMapping
    public Page<VehicleResource> getAllVehicles(Pageable pageable) {
        return vehicleMapper.modelListPage(vehicleService.getAllVehicles(), pageable);
    }

    @Operation(summary = "Get vehicle by Id", description = "Get vehicle by Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle found",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = VehicleResource.class))})
    })
    @GetMapping("{vehicleId}")
    public VehicleResource getVehicleById(@PathVariable Long vehicleId) {
        return vehicleMapper.toResource(vehicleService.getVehicleById(vehicleId));
    }

    @Operation(summary = "Create vehicle", description = "Create vehicle in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle created",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = VehicleResource.class))})
    })
    @PostMapping()
    public VehicleResource createVehicle(@RequestBody CreateVehicleResource resource) {
        return vehicleMapper.toResource(vehicleService.createVehicle(vehicleMapper.toModel(resource)));
    }

    @Operation(summary = "Update vehicle", description = "Update vehicle in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle updated",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = VehicleResource.class))})
    })
    @PutMapping()
    public VehicleResource updateVehicle(@PathVariable Long vehicleId, @RequestBody UpdateVehicleResource resource) {
        return vehicleMapper.toResource(vehicleService.updateVehicle(vehicleId, vehicleMapper.toModel(resource)));
    }

    @Operation(summary = "Delete vehicle", description = "Delete vehicle in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle deleted",
            content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping()
    public ResponseEntity<?> deleteVehicle(@PathVariable Long vehicleId) {
        return vehicleService.deleteVehicle(vehicleId);
    }
}
