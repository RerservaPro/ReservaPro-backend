package br.com.reservapro.infrastructure.controller.email;

import br.com.reservapro.application.email.EmailService;
import br.com.reservapro.infrastructure.controller.email.dto.EmailDTO;
import br.com.reservapro.infrastructure.controller.email.mapper.EmailWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {
    private final EmailService emailService;
    private final EmailWebMapper emailWebMapper;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmailForTest(@RequestBody EmailDTO emailDTO) {
        emailService.sendEmail(emailWebMapper.mapToDomain(emailDTO));
        return ResponseEntity.ok().body("EMAIL ENVIADO COM SUCESSO!");
    }

}
