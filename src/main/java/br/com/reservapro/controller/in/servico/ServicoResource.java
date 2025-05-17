package br.com.reservapro.controller.in.servico;

import br.com.reservapro.application.servico.ServicoService;
import br.com.reservapro.controller.in.PaginaResponseDTO;
import br.com.reservapro.controller.in.servico.dto.ServicoDTO;
import br.com.reservapro.controller.in.servico.mapper.ServicoWebMapper;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servico")
@RequiredArgsConstructor
public class ServicoResource {
    private final ServicoService servicoService;
    private final ServicoWebMapper mapper;

    @GetMapping("/procurar")
    public ResponseEntity<PaginaResponseDTO<ServicoDTO>> procurar(
            @RequestParam(name = "estaAtivo", defaultValue = "true", required = false)
            Boolean estaAtivo,
            @RequestParam(name = "tamanhoPagina", defaultValue = "10", required = false)
            @Min(value = 1, message = "O número mínimo de elementos da página é 1")
            @Max(value = 30, message = "O número máximo de elementos da página é 30")
            Integer tamanhoPagina,
            @RequestParam(name = "pagina", defaultValue = "0", required = false)
            Integer pagina,
            @RequestParam(name = "nome", required = false)
            String nome
    ) {
        return ResponseEntity.ok().body(
                mapper.mapToPaginaResponseDTO(servicoService.procurar(estaAtivo, pagina, tamanhoPagina, nome))
        );
    }

    @GetMapping("/procurarporid/{id}")
    public ResponseEntity<ServicoDTO> procurarPorId(@PathVariable String id) {
        return ResponseEntity.ok().body(
                mapper.mapToDTO(servicoService.procurarPorId(id))
        );
    }

    @PostMapping("/inserir")
    public ResponseEntity<ServicoDTO> inserir(@Validated(ServicoDTO.OnCreate.class) @RequestBody ServicoDTO servicoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                mapper.mapToDTO(servicoService.inserir(mapper.mapToDomain(servicoDTO)))
        );
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ServicoDTO> atualizar(@PathVariable String id, @Validated(ServicoDTO.OnUpdate.class) @RequestBody ServicoDTO servicoDTO) {
        return ResponseEntity.ok().body(
                mapper.mapToDTO(servicoService.atualizar(id, mapper.mapToDomain(servicoDTO)))
        );
    }

    @DeleteMapping("/desativar/{id}")
    public ResponseEntity<Void> desativar(@PathVariable String id) {
        servicoService.desativar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        servicoService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
