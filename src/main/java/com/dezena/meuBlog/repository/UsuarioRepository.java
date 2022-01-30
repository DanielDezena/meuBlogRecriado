package com.dezena.meuBlog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dezena.meuBlog.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	public Optional<Usuario> findByEmail(String usuario);
	public List<Usuario> findAllByEmailContainingIgnoreCase(String string);

}
