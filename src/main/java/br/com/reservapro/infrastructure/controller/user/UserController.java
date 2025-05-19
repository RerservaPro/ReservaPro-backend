package br.com.reservapro.infrastructure.controller.user;

import br.com.reservapro.infrastructure.controller.user.dto.UserDTO;
import br.com.reservapro.infrastructure.controller.user.dto.UserProfileDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface UserController {


    @Operation(summary = "Register user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Logged", content = @Content),
            @ApiResponse(responseCode = "401", description = "User Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content),
    })
    ResponseEntity<Void> save(UserDTO userDTO);

    @Operation(summary = "Find an user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User returned", content = @Content),
            @ApiResponse(responseCode = "401", description = "User Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content),
    })
    ResponseEntity<UserProfileDTO> findById(String userId);
    @Operation(summary = "Find All Users in pages ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Logged", content = @Content),
            @ApiResponse(responseCode = "401", description = "User Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content),
    })
    ResponseEntity<Page<UserProfileDTO>> findAll(Pageable pageable);
    @Operation(summary = "Update an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user Updated", content = @Content),
            @ApiResponse(responseCode = "401", description = "User Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content),
    })
    ResponseEntity<Void> update(String userId, UserDTO userDTO);
    @Operation(summary = "delete an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user deleted", content = @Content),
            @ApiResponse(responseCode = "401", description = "User Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content),
    })
    ResponseEntity<Void> delete(String userId);
    @Operation(summary = "get a profile of an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User returned", content = @Content),
            @ApiResponse(responseCode = "401", description = "User Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content),
    })
    ResponseEntity<UserProfileDTO> getProfile(String token);

}