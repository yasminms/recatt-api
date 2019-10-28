package br.com.recatt.config.security;

import br.com.recatt.entity.User;
import br.com.recatt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Optional.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = ofNullable(userRepository.findByEmail(email)).orElseThrow(() -> new UsernameNotFoundException("Usuário não cadastrado"));
        return UserPrincipal.create(user);
    }

    public UserDetails loadUserById(String email) {
        User user = ofNullable(userRepository.findByEmail(email)).orElseThrow(() -> new UsernameNotFoundException("Usuário não cadastrado"));
        return UserPrincipal.create(user);
    }

    public static UserPrincipal getUser() {
        return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
