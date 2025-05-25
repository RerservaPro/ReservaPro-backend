package br.com.reservapro.infrastructure.controller.schedule;

import br.com.reservapro.application.schedule.SchedullingService;
import br.com.reservapro.domain.Schedulling;
import br.com.reservapro.infrastructure.controller.schedule.dto.SchedulingDto;
import br.com.reservapro.infrastructure.controller.schedule.mapper.SchedulingWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/scheduling")
public class ScheduleController {
    private final SchedullingService schedullingService;
    private final SchedulingWebMapper mapper;


    @Operation(summary = "Get all schedule services")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "all data was successfully retrieved", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error(system found an error while operations)", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Schedulling>> findAll() {
        return ResponseEntity.ok().body(this.schedullingService.findAll());
    }


    @Operation(summary = "create a new schedule")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully created", content = @Content),
            @ApiResponse(responseCode = "404", description = "Bad request(maybe you send an invalid field)", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden or access denied by the system", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict, perhaps this entity already exists into the database", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping
    public ResponseEntity<SchedulingDto> create(SchedulingDto schedulingDto) {
        return ResponseEntity.ok().body(
                this.mapper.mapToDto(
                        this.schedullingService.create(this.mapper.mapToDomain(schedulingDto))
                )
        );
    }

    @Operation(summary = "Find only one schedule")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find the schedule created", content = @Content),
            @ApiResponse(responseCode = "404", description = "The resource wasn't found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content),
    })
    @GetMapping("/{id}")
    public ResponseEntity<SchedulingDto> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(this.mapper.mapToDto(this.schedullingService.findById(id)));
    }

    @Operation(summary = "Update one schedule")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully created", content = @Content),
            @ApiResponse(responseCode = "404", description = "Bad request(maybe you send an invalid field)", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden or access denied by the system", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict, perhaps this entity already exists into the database", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<SchedulingDto> update(@PathVariable("id") String id, @RequestBody SchedulingDto schedulingDto) {
        return ResponseEntity.ok().body(
                this.mapper.mapToDto(
                        this.schedullingService.update(id, this.mapper.mapToDomain(schedulingDto))
                )
        );
    }

    @Operation(summary = "Delete one schedule")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find the schedule created", content = @Content),
            @ApiResponse(responseCode = "404", description = "The resource wasn't found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        this.schedullingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete many schedule")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find the schedule created", content = @Content),
            @ApiResponse(responseCode = "404", description = "The resource wasn't found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content),
    })
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody List<String> ids) {
        this.schedullingService.deleteMany(ids);
        return ResponseEntity.noContent().build();
    }
}
