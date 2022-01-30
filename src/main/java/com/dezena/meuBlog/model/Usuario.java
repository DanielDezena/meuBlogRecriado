package com.dezena.meuBlog.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name= "tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message= "nome obrigatorio")
	@Size(min = 3, max = 255, message = "deve haver entre 3 a 255 caracteres")
	private String nome;
	
	@NotNull(message= "email obrigatorio")
	@Email(message= "deve ser um email válido")
	private String email;
	
	@NotNull(message= "senha é obrigatória")
	@Size(min = 8, max = 255, message = "deve conter entre 8 a 255 caracteres")
	private String senha;
	
	private String pfp;
	
	private String tipo;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("usuario")
	private List<Comentarios> comentarios;
	
	@OneToMany(mappedBy= "usuario", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("usuario")
	private List<Postagem> postagem;
	
	@OneToMany(mappedBy= "usuario", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("usuario")
	private List<Tema> tema;
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome_usuario() {
		return nome;
	}

	public void setNome_usuario(String nome_usuario) {
		this.nome = nome_usuario;
	}

	public String getEmail_usuario() {
		return email;
	}

	public void setEmail_usuario(String email_usuario) {
		this.email = email_usuario;
	}

	public String getSenha_usuario() {
		return senha;
	}

	public void setSenha_usuario(String senha_usuario) {
		this.senha = senha_usuario;
	}

	public String getPfp_usuario() {
		return pfp;
	}

	public void setPfp_usuario(String pfp_usuario) {
		this.pfp = pfp_usuario;
	}

	public String getTipo_usuario() {
		return tipo;
	}

	public void setTipo_usuario(String tipo_usuario) {
		this.tipo = tipo_usuario;
	}

	public List<Comentarios> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentarios> comentarios) {
		this.comentarios = comentarios;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}

	public List<Tema> getTema() {
		return tema;
	}

	public void setTema(List<Tema> tema) {
		this.tema = tema;
	}

	
	
	

}
