package com.tarlley.auth_server.mapper;


import com.tarlley.auth_server.dto.UsuarioRegistroDTO;
import com.tarlley.auth_server.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioRegistroDTO usuarioToRegistroDTO(Usuario usuario);

    Usuario registroToEntity(UsuarioRegistroDTO usuarioRegistroDTO);
}
