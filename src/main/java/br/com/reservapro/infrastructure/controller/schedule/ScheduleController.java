package br.com.reservapro.infrastructure.controller.schedule;

import br.com.reservapro.application.schedule.SchedullingService;
import br.com.reservapro.domain.Schedulling;
import br.com.reservapro.infrastructure.controller.schedule.dto.SchedullingDto;
import br.com.reservapro.infrastructure.controller.schedule.mapper.SchedullingWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/schedulling")
public class ScheduleResource {
    private SchedullingService schedullingService;
    private SchedullingWebMapper mapper;


    @GetMapping
    public ResponseEntity<List<Schedulling>> findAll() {
        return ResponseEntity.ok().body(this.schedullingService.findAll());
    }

    @PostMapping
    public ResponseEntity<SchedullingDto> create(SchedullingDto schedullingDto) {
        return ResponseEntity.ok().body(
                this.mapper.mapToDto(
                        this.schedullingService.create(this.mapper.mapToDomain(schedullingDto))
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchedullingDto> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(this.mapper.mapToDto(this.schedullingService.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchedullingDto> update(@PathVariable("id") String id, @RequestBody SchedullingDto schedullingDto) {
        return ResponseEntity.ok().body(
                this.mapper.mapToDto(
                        this.schedullingService.update(id, this.mapper.mapToDomain(schedullingDto))
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        this.schedullingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody List<String> ids) {
        this.schedullingService.deleteMany(ids);
        return ResponseEntity.noContent().build();
    }
}
