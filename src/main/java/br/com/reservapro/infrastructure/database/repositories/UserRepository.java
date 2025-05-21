package br.com.reservapro.infrastructure.database.repositories;

import br.com.reservapro.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    UserDetails findByEmail (String username);
    @Query("SELECT u FROM User u WHERE u.enabled = true")
    Page<User> findAll (Pageable pageable);

}
