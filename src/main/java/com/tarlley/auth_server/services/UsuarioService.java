package com.tarlley.auth_server.services;

import com.tarlley.auth_server.config.exceptions.BadRequestException;
import com.tarlley.auth_server.dto.UsuarioRegistroDTO;
import com.tarlley.auth_server.enumerated.Role;
import com.tarlley.auth_server.mapper.UsuarioMapper;
import com.tarlley.auth_server.model.Usuario;
import com.tarlley.auth_server.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioService {


    private final UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Usuario> buscarUsuarioPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }



    public Optional<Usuario> buscarUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    public Usuario registrarUsuario(UsuarioRegistroDTO usuarioRegistroDTO) {
        validarUsuarioExistente(usuarioRegistroDTO);

        usuarioRegistroDTO.setPassword(encodeSenha(usuarioRegistroDTO.getPassword()));
        return salvarNovoUsuario(usuarioRegistroDTO);
    }

    public Usuario registrarUsuarioAdmin(UsuarioRegistroDTO usuarioRegistroDTO) {
        validarUsuarioExistente(usuarioRegistroDTO);

        usuarioRegistroDTO.setPassword(encodeSenha(usuarioRegistroDTO.getPassword()));
        return salvarNovoUsuarioAdmin(usuarioRegistroDTO);
    }

    private void validarUsuarioExistente(UsuarioRegistroDTO usuarioRegistroDTO) {
        Optional<Usuario> usuarioPorEmail = buscarUsuarioPorEmail(usuarioRegistroDTO.getEmail());
        Optional<Usuario> usuarioPorUsernameio = buscarUsuarioPorUsername(usuarioRegistroDTO.getUsername());
        if(usuarioPorEmail.isPresent()){
            throw new BadRequestException("Email informado já cadastrado!");
        }
        if (usuarioPorUsernameio.isPresent()){
            throw new BadRequestException("Username informado já cadastrado!");
        }
    }

    private Usuario salvarNovoUsuario(UsuarioRegistroDTO usuarioRegistroDTO){
        Usuario entity = usuarioMapper.registroToEntity(usuarioRegistroDTO);
        entity.setRoles(Collections.singleton(Role.ROLE_USER));
        return  usuarioRepository.save(entity);
    }

    private Usuario salvarNovoUsuarioAdmin(UsuarioRegistroDTO usuarioRegistroDTO){
        Usuario entity = usuarioMapper.registroToEntity(usuarioRegistroDTO);
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_ADMIN);
        roles.add(Role.ROLE_USER);
        entity.setRoles(roles);
        return usuarioRepository.save(entity);
    }

    private String encodeSenha(String password){
        return passwordEncoder.encode(password);
    }
}
