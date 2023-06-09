package com.application.a3.domain.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.a3.domain.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
