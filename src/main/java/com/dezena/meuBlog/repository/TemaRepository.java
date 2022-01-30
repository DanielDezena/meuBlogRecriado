package com.dezena.meuBlog.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dezena.meuBlog.model.Tema;

public interface TemaRepository extends JpaRepository<Tema, Long>{
	
	public List<Tema> findAllByTituloContainingIgnoreCase(String titulo);

}
