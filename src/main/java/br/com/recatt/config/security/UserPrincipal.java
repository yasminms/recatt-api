package br.com.recatt.config.security;

import br.com.recatt.domain.Gender;
import br.com.recatt.domain.Role;
import br.com.recatt.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ImmutableList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@EqualsAndHashCode(of = "id")
public class UserPrincipal implements UserDetails {

    private String email;

    private String fullName;

    private LocalDate birthDate;

    private Gender gender;

    private Role role;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(String email, String password, String fullName, LocalDate birthDate, Gender gender, Role role, Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.role = role;
        this.authorities = authorities;
    }

    public static UserPrincipal create(User user) {

        List<GrantedAuthority> authorities = ImmutableList.of(
                new SimpleGrantedAuthority(user.getRole().name())
        );

        return new UserPrincipal(
                user.getEmail(),
                user.getPassword(),
                user.getFullName(),
                user.getBirthDate(),
                user.getGender(),
                user.getRole(),
                authorities
        );
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}