package com.dezena.meuBlog.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dezena.meuBlog.model.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long>{
	
	public List<Postagem> findAllByTituloContainingIgnoreCase(String titulo);

}
