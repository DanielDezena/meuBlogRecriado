package com.dezena.meuBlog.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dezena.meuBlog.model.UserLogin;
import com.dezena.meuBlog.model.Usuario;
import com.dezena.meuBlog.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> CadastrarUsuario(Usuario usuario){
		if (usuarioRepository.findByEmail(usuario.getEmail_usuario()).isPresent())
			return Optional.empty();
		usuario.setSenha_usuario(CriptografarSenha(usuario.getSenha_usuario()));
		return Optional.of(usuarioRepository.save(usuario));
	}
	
	public Optional<UserLogin> AutenticarUsuario(Optional<UserLogin> userLogin){
		Optional<Usuario> usuario = usuarioRepository.findByEmail(userLogin.get().getUsuario());
		
		if (usuario.isPresent()) {
			if(CompararSenhas(userLogin.get().getSenha(), usuario.get().getSenha_usuario())) {
				String token = GerarBasicToken(userLogin.get().getUsuario(), userLogin.get().getSenha());
				
				userLogin.get().setId(usuario.get().getId());
				userLogin.get().setNome(usuario.get().getNome_usuario());
				userLogin.get().setUsuario(usuario.get().getEmail_usuario());
				userLogin.get().setSenha(usuario.get().getSenha_usuario());
				userLogin.get().setTipo(usuario.get().getTipo_usuario());
				userLogin.get().setFoto(usuario.get().getPfp_usuario());
				userLogin.get().setToken(token);
			}
		}
		return Optional.empty();
	}
	
	public Optional<Usuario> AtualizarUsuario(Usuario usuario){
		if (usuarioRepository.findByEmail(usuario.getEmail_usuario()).isPresent()) {
			Optional<Usuario> buscaUsuario = usuarioRepository.findByEmail(usuario.getEmail_usuario());
			if(buscaUsuario.isPresent()) {
				if(buscaUsuario.get().getId() != usuario.getId()) {
					return Optional.empty();
				}
				usuario.setSenha_usuario(CriptografarSenha(usuario.getSenha_usuario()));
				return Optional.of(usuarioRepository.save(usuario));
			}
		}
		return Optional.empty();
	}
	
	
	private boolean CompararSenhas(String senhaDigitada, String senhaBanco) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.matches(senhaDigitada, senhaBanco);
	}
	
	private String GerarBasicToken(String email, String password) {
		String tokenBase = email + ":" + password;
		byte[] tokenBase64 = Base64.encodeBase64(tokenBase.getBytes(Charset.forName("US-ASCII")));
		
		return "Basic " + new String(tokenBase64);
	}
	
	private String CriptografarSenha(String senha) {
		BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
		return encoder.encode(senha);
	}

}
