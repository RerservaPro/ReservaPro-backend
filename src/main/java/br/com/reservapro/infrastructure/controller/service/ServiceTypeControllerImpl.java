package br.com.reservapro.infrastructure.controller.service;

import br.com.reservapro.application.service.ServiceTypeService;
import br.com.reservapro.domain.ServiceType;
import br.com.reservapro.domain.pagination.PageResponseDTO;
import br.com.reservapro.infrastructure.controller.service.dto.ServiceTypeDTO;
import br.com.reservapro.infrastructure.controller.service.mapper.ServiceTypeWebMapper;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service")
@RequiredArgsConstructor
public class ServiceTypeControllerImpl implements ServiceTypeController {
    private final ServiceTypeService serviceTypeService;
    private final ServiceTypeWebMapper mapper;

    @Override
    @GetMapping("/search")
    public ResponseEntity<PageResponseDTO<ServiceTypeDTO>> search(
            @RequestParam(name = "isActive", defaultValue = "true", required = false)
            Boolean isActive,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false)
            @Min(value = 1, message = "O número mínimo de elementos da página é 1")
            @Max(value = 30, message = "O número máximo de elementos da página é 30")
            Integer pageSize,
            @RequestParam(name = "page", defaultValue = "0", required = false)
            Integer page,
            @RequestParam(name = "name", required = false)
            String name
    ) {
        return ResponseEntity.ok().body(
                mapper.mapToPageResponseDTO(serviceTypeService.search(isActive, page, pageSize, name))
        );
    }

    @Override
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<ServiceTypeDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(
                mapper.mapToDTO(serviceTypeService.findById(id))
        );
    }

    @Override
    @PostMapping("/insert")
    public ResponseEntity<ServiceTypeDTO> insert(@Validated(ServiceTypeDTO.OnCreate.class) @RequestBody ServiceTypeDTO serviceTypeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                mapper.mapToDTO(serviceTypeService.insert(mapper.mapToDomain(serviceTypeDTO)))
        );
    }

    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<ServiceTypeDTO> update(@PathVariable String id, @Validated(ServiceTypeDTO.OnUpdate.class) @RequestBody ServiceTypeDTO serviceTypeDTO) {
        return ResponseEntity.ok().body(
                mapper.mapToDTO(serviceTypeService.update(id, mapper.mapToDomain(serviceTypeDTO)))
        );
    }

    @Override
    @DeleteMapping("/deactivate/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable String id) {
        serviceTypeService.deactivate(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        serviceTypeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
