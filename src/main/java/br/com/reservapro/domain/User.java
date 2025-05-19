package br.com.reservapro.domain;

import br.com.reservapro.domain.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "`id`")
    private String id;

    @Column(name = "`name`")
    private String name;

    @Column(name = "`password`")
    private String password;

    @Column(name = "document")
    private String document;

    @Column(name = "`role`")
    private Role role;

    @Column(name = "`enabled`")
    private boolean enabled;

    @Column(name = "`activation_date`")
    private Date activationDate;

    @Column(name = "`deactivation_date`")
    private Date deactivationDate;

    @Column(name = "`email`")
    private String email;

    @Column(name = "avatar")
    private String avatar;

    public
    User(String name, String password, Role role, String document, String email, boolean enabled, Date activationDate, Date deactivationDate) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.document = document;
        this.email = email;
        this.enabled = enabled;
        this.activationDate = activationDate;
        this.deactivationDate = deactivationDate;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == Role.ADMIN)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public boolean getEnabled(){
        return this.enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
