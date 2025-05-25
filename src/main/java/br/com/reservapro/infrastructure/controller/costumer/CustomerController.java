package br.com.reservapro.infrastructure.controller.costumer;

import br.com.reservapro.application.costumer.CustomerService;
import br.com.reservapro.infrastructure.controller.costumer.dto.CustomerDto;
import br.com.reservapro.infrastructure.controller.costumer.mapper.CustomerWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerWebMapper webMapper;

    @Operation(summary = "List all customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All customers successfully retrieved", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public ResponseEntity<List<CustomerDto>> findAll() {
        return ResponseEntity.ok().body(this.customerService.findAll().stream()
                .map(this.webMapper::mapToDto)
                .collect(Collectors.toList()));
    }


    @Operation(summary = "it creates a new customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer successfully created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request payload", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict: customer may already exist", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping
    public ResponseEntity<CustomerDto> create(@RequestBody CustomerDto customerDto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri();
        return ResponseEntity.created(uri).body(
                this.webMapper.mapToDto(
                        this.customerService.create(this.webMapper.mapToDomain(customerDto)
                        )
                )
        );
    }

    @Operation(summary = "retrieve only one customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer successfully retrieved", content = @Content),
            @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findOne(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(this.webMapper.mapToDto(this.customerService.findById(id)));
    }



    @Operation(summary = "Update a customer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer successfully updated", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request payload", content = @Content),
            @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict with existing data", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> update(@PathVariable("id") String id, @RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok().body(
                this.webMapper.mapToDto(
                        this.customerService.update(id, this.webMapper.mapToDomain(customerDto)
                        )
                )
        );
    }

    @Operation(summary = "Delete a customer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Customer successfully deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable("id") String id) {
        this.customerService.findById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete multiple customers by IDs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Customers successfully deleted", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid list of IDs", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping("/deletemany")
    public ResponseEntity<Void> deleteMany(@RequestBody List<String> ids) {
        this.customerService.deleteMany(ids);
        return ResponseEntity.noContent().build();
    }
}
