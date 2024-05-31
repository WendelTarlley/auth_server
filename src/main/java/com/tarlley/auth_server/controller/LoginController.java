package com.tarlley.auth_server.controller;

import com.tarlley.auth_server.dto.TokenResponseDTO;
import com.tarlley.auth_server.dto.UserLoginDTO;
import com.tarlley.auth_server.services.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody UserLoginDTO usuarioLoginDTO){
        return ResponseEntity.ok(new TokenResponseDTO(loginService.processarLogin(usuarioLoginDTO)));
    }
}
