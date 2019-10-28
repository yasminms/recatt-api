package br.com.recatt.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private static final String headerPrefix = "Bearer ";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public String authenticate(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return headerPrefix + jwtTokenProvider.generateToken(authentication);
    }
}
