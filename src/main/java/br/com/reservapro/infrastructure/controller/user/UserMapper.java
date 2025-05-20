package br.com.reservapro.infrastructure.controller.user;


import br.com.reservapro.domain.User;
import br.com.reservapro.infrastructure.controller.user.dto.UserDTO;
import br.com.reservapro.infrastructure.controller.user.dto.UserProfileDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public User mapToDomain(UserDTO userDTO) {
        return new User(userDTO.name(), userDTO.password(),null, userDTO.cpfOuCnpj(), userDTO.email(), null);
    }

    public UserProfileDTO mapToProfile(User user) {
        return  UserProfileDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .avatar(user.getAvatar())
                .build();
    }

    public Page<UserProfileDTO> mapToDto(Page<User> users) {
        List<UserProfileDTO> userProfiles = users.getContent().stream()
                .map(this::mapToProfile)
                .collect(Collectors.toList());
        return new PageImpl<>(userProfiles, users.getPageable(), users.getTotalElements());
    }
}
