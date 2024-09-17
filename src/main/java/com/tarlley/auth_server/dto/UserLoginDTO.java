package com.tarlley.auth_server.dto;

import com.tarlley.auth_server.enumerated.Role;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record UserLoginDTO(@NotBlank  String username, @NotBlank String password,  Set<Role> roles) {
}
