package com.tarlley.auth_server.services;

import com.tarlley.auth_server.config.TokenService;
import com.tarlley.auth_server.dto.UserLoginDTO;
import com.tarlley.auth_server.model.Usuario;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    public LoginService(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }


    public String processarLogin(UserLoginDTO usuarioLoginDTO){
        var usernamePassword = new UsernamePasswordAuthenticationToken(usuarioLoginDTO.username(),usuarioLoginDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        return tokenService.generateToken((Usuario) auth.getPrincipal());
    }

    public Usuario validarToken(String token) {
        return this.tokenService.validarAuth(token);
    }
}
