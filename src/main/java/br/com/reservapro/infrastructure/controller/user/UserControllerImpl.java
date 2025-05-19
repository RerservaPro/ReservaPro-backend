package br.com.reservapro.infrastructure.controller.user;


import br.com.reservapro.application.user.UserService;
import br.com.reservapro.domain.User;
import br.com.reservapro.infrastructure.controller.user.dto.UserDTO;
import br.com.reservapro.infrastructure.controller.user.dto.UserProfileDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<Void> save(@RequestBody @Valid UserDTO userDTO) {
        User user = userMapper.mapToDomain(userDTO);
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileDTO> findById(@PathVariable(name = "userId") String userId) {
        User user = userService.findById(userId);
        return ResponseEntity.ok().body(userMapper.mapToProfile(user));
    }

    @GetMapping()
    public ResponseEntity<Page<UserProfileDTO>> findAll(Pageable pageable) {
        Page<User> all = userService.findAll(pageable);
        return ResponseEntity.ok().body(userMapper.mapToDto(all));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<Void> update(@PathVariable(name = "userId") String userId, @RequestBody UserDTO userDTO) {
        User user = userMapper.mapToDomain(userDTO);
        userService.update(userId, user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable String userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDTO> getProfile(@RequestHeader(name = "Authorization") String token) {
        User profile = userService.getProfile(token);
        return ResponseEntity.ok().body(userMapper.mapToProfile(profile));
    }

}
