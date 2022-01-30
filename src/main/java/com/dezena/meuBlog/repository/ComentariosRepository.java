package com.dezena.meuBlog.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dezena.meuBlog.model.Comentarios;

public interface ComentariosRepository extends JpaRepository<Comentarios, Long>{
	
	public List<Comentarios> findAllByComentarioContainingIgnoreCase(String comentarios);

}
