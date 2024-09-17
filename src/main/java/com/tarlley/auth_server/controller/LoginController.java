package com.tarlley.auth_server.controller;

import com.tarlley.auth_server.dto.TokenResponseDTO;
import com.tarlley.auth_server.dto.UserLoginDTO;
import com.tarlley.auth_server.dto.UsuarioRegistroDTO;
import com.tarlley.auth_server.services.LoginService;
import com.tarlley.auth_server.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid UserLoginDTO usuarioLoginDTO){
        return ResponseEntity.ok(new TokenResponseDTO(loginService.processarLogin(usuarioLoginDTO)));
    }

    @GetMapping("/validate/{token}")
    public ResponseEntity<?> validarToken(@PathVariable("token") String token){
        return ResponseEntity.ok().body(loginService.validarToken(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registrarNovoUsuario(@RequestBody @Valid UsuarioRegistroDTO usuarioRegistroDTO){
        return ResponseEntity.ok().body(usuarioService.registrarUsuario(usuarioRegistroDTO));
    }
}
