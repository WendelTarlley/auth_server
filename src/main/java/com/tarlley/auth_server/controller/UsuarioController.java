package com.tarlley.auth_server.controller;

import com.tarlley.auth_server.dto.UsuarioRegistroDTO;
import com.tarlley.auth_server.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> registrarNovoUsuarioAdmin(@RequestBody @Valid UsuarioRegistroDTO usuarioRegistroDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.registrarUsuarioAdmin(usuarioRegistroDTO));
    }

}
