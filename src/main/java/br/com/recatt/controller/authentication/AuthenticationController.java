package br.com.recatt.controller.authentication;

import br.com.recatt.config.security.AuthenticationService;
import br.com.recatt.config.security.CustomUserDetailsService;
import br.com.recatt.domain.AuthRequest;
import br.com.recatt.domain.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static br.com.recatt.mapper.AuthResponseMapper.apply;

@RestController
@RequestMapping("/public/auth")
public class AuthenticationController implements AuthenticationContract {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    @PostMapping
    public AuthResponse authenticate(@Valid @RequestBody final AuthRequest authRequest) {
        final String token = authenticationService.authenticate(authRequest.getEmail(), authRequest.getPassword());
        return apply(token, CustomUserDetailsService.getUser());
    }
}
