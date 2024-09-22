package com.sisAluguel.sisAluguel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sisAluguel.sisAluguel.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
