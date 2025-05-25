package br.com.reservapro.infrastructure.controller.service;

import br.com.reservapro.domain.pagination.PageResponseDTO;

import br.com.reservapro.infrastructure.controller.service.dto.ServiceTypeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface ServiceTypeController {

    @Operation(summary = "Search services")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Services found", content = @Content),
            @ApiResponse(responseCode = "403", description = "Service unauthorized", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content),
    })
    ResponseEntity<PageResponseDTO<ServiceTypeDTO>> search(Boolean isActive, Integer pageSize, Integer page, String name);

    @Operation(summary = "Find a service by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service found", content = @Content),
            @ApiResponse(responseCode = "403", description = "Service unauthorized", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content),
    })
    ResponseEntity<ServiceTypeDTO> findById(String id);

    @Operation(summary = "Save a service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Service saved", content = @Content),
            @ApiResponse(responseCode = "403", description = "Service unauthorized", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content),
    })
    ResponseEntity<ServiceTypeDTO> insert(ServiceTypeDTO serviceTypeDTO);

    @Operation(summary = "Update a service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service updated", content = @Content),
            @ApiResponse(responseCode = "403", description = "Service unauthorized", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content),
    })
    ResponseEntity<ServiceTypeDTO> update(String id, ServiceTypeDTO serviceTypeDTO);

    @Operation(summary = "Deactivate a service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Service deactivated", content = @Content),
            @ApiResponse(responseCode = "403", description = "Service unauthorized", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content),
    })
    ResponseEntity<Void> deactivate(String id);

    @Operation(summary = "Delete a service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Service deleted", content = @Content),
            @ApiResponse(responseCode = "403", description = "Service unauthorized", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content),
    })
    ResponseEntity<Void> delete(String id);
}
