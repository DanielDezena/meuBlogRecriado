package com.dezena.meuBlog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dezena.meuBlog.model.Comentarios;
import com.dezena.meuBlog.repository.ComentariosRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/comentarios")
public class ComentariosController {
	
	@Autowired
	private ComentariosRepository comentariosRepository;
	
	//pega todos os comentarios
	@GetMapping("/comentarios")
	public ResponseEntity<List<Comentarios>> getAll(){
		return ResponseEntity.ok(comentariosRepository.findAll());
	}
	
	//pega o comentario pelo id
	@GetMapping("/{id}")
	public ResponseEntity<Comentarios> getById(@PathVariable long id){
		return comentariosRepository.findById(id)
		.map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	//encontra o comentario com o conteúdo que se tem por dentro
	@GetMapping("/pesquisa/{comentario}")
	public ResponseEntity<List<Comentarios>> getAllByComentario(@PathVariable String comentario){
		return ResponseEntity.ok(comentariosRepository.findAllByComentarioContainingIgnoreCase(comentario));
	}
	
	//cria o comentario
	@PostMapping
	public ResponseEntity<Comentarios> post(@RequestBody Comentarios comentario){
		return ResponseEntity.status(HttpStatus.CREATED).body(comentariosRepository.save(comentario));
	}
	
	//atualiza o comentario
	@PutMapping
	public ResponseEntity<Comentarios> put(@RequestBody Comentarios comentario){
		return ResponseEntity.status(HttpStatus.CREATED).body(comentariosRepository.save(comentario));
	}
	
	//apaga o comentario
	@DeleteMapping
	public void delete(@PathVariable long id) {
		comentariosRepository.deleteById(id);
	}

}
