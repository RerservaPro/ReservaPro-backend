package br.com.reservapro.infrastructure.controller.auth;

import br.com.reservapro.application.auth.JwtService;
import br.com.reservapro.domain.User;
import br.com.reservapro.infrastructure.controller.auth.dto.LoginDTO;
import br.com.reservapro.infrastructure.controller.auth.dto.LoginResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Operation(summary = "Login User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Logged", content = @Content),
            @ApiResponse(responseCode = "401", description = "User Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "User unauthorized",
                    content = @Content)

    })
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = jwtService.createJwtToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
