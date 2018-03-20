package br.com.jfrn.project.security;


import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.jfrn.project.services.SegUsuarioDetails;


//esse metódo vai interceptar as requisições do post no caminho /login e tentar autenticar o usuário
//Quando o usuário é autenticado com sucesso, retornará um JWT no Authorizationcabeçalho da resposta.
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter{

	@Autowired
	private SegUsuarioDetails segUsuario;
	
	public JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}
	//Durante a tentativa de autenticação, que é tratada pelo método attemptAuthentication , recuperamos o nome de usuário e a senha da solicitação.
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) 
		        throws AuthenticationException, IOException, ServletException {
					Usuario creds = new ObjectMapper().readValue(req.getInputStream(), Usuario.class); //aqui é onde ocorre a autenticação
					// getAuthenticationManager();
					 
					 
					 Authentication autenticado = getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(creds.getNome(),creds.getSenha(), Collections.emptyList()));
					 String nomeAutenticado = autenticado.getName();
					 
					 System.out.println("Nome autenticado dentro do attempAuthentication "+nomeAutenticado);
					String nomeUsuarioBanco = "";
					
					List<Usuario> listUsuario = segUsuario.findAll();
					
							 
					for(Usuario lista : listUsuario ) {
								 if(lista.getNome() == autenticado.getName()) {
									 System.out.println("o nome está no banco");
									 nomeUsuarioBanco = lista.getNome();
							}
					} 		 
					
					if(nomeUsuarioBanco == autenticado.getName()) {
						System.out.println("o nome está no banco");
						return autenticado;
					}
					
					return autenticado;
							 
							 
							 
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		
		 
		 String nomeUser = auth.getName();
		 System.out.println(nomeUser);

		TokenAuthenticationService.addAutenticacao(res, auth.getName());
	
	}
	
}
