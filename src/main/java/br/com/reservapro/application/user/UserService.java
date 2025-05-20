package br.com.reservapro.application.user;

import br.com.reservapro.application.auth.JwtService;
import br.com.reservapro.domain.StatusAtivacao;
import br.com.reservapro.domain.User;
import br.com.reservapro.exceptions.UnauthorizedException;
import br.com.reservapro.exceptions.UnprocessableEntityException;
import br.com.reservapro.infrastructure.database.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public void save(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null)
            throw new UnprocessableEntityException("User "+user.getEmail()+"Usuario já existe");
        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        User newUser = new User(user.getName(), encryptedPassword, user.getRole(), user.getDocument(), user.getEmail(), StatusAtivacao.builder()
                .dataCriacao(Instant.now())
                .estaAtivo(true)
                .build());
         userRepository.save(newUser);
    }

    public User getProfile(String token) {
        String username = jwtService.validateToken(token);
        if (username == null) throw new UnauthorizedException("Invalid token");
        User user = (User) userRepository.findByEmail(username);
        if (user == null) {
            throw new ObjectNotFoundException(User.class, "User not found ");
        }
        return user;
    }

    public User findById(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElseThrow(() -> new ObjectNotFoundException(optionalUser,"User not found. "));
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public void delete(String userId) {
        User byId = findById(userId);
        byId.setStatusAtivacao(StatusAtivacao.builder()
                .dataDesativacao(Instant.now())
                .estaAtivo(false)
                .build());
        userRepository.save(byId);
    }

    public void update(String userId, User userUpdated)  {

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            UserDetails userByEmail = userRepository.findByEmail(optionalUser.get().getEmail());
            if (userByEmail != null) {
                User existingUser = optionalUser.get();
                if(userUpdated.getName() != null)
                    existingUser.setName(userUpdated.getName());
                if(userUpdated.getAvatar() != null)
                    existingUser.setAvatar(userUpdated.getAvatar());
                userRepository.save(existingUser);
            } else throw new UnprocessableEntityException("User "+userUpdated.getEmail()+" already exists");
        } else throw new ObjectNotFoundException(User.class, "Username not found.");
    }
}
