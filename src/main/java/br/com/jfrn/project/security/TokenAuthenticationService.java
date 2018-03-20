package br.com.jfrn.project.security;

import static java.util.Collections.emptyList;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

class TokenAuthenticationService {
	static final long TEMPO_EXPIRACAO_TOKEN = 864_000_000; // 10 days
	static final String CHAVE_ASSINATURA = "JFRN2018res"; //essa é a chave assinatura para descriptografar os dados do token
	static final String TOKEN_PREFIXO = "Portador"; //esse é o prefixo do token
	static final String CABECALHO_STRING = "Autorizacao"; // essa variável irá guardar o hash do token para ser utilizada na camada view

	//o metódo abaixo irá criar um token
	static void addAutenticacao(HttpServletResponse res, String nomeUsuario) {
		String JWT = Jwts.builder().setSubject(nomeUsuario)
				.setExpiration(new Date(System.currentTimeMillis() + TEMPO_EXPIRACAO_TOKEN))
				.signWith(SignatureAlgorithm.HS512, CHAVE_ASSINATURA).compact();
		res.addHeader(CABECALHO_STRING, TOKEN_PREFIXO + " " + JWT);
	}

	static Authentication getAutenticacao(HttpServletRequest request) {
		String token = request.getHeader(CABECALHO_STRING);
		if (token != null) {
			// parse the token.
			String usuario = Jwts.parser().setSigningKey(CHAVE_ASSINATURA).parseClaimsJws(token.replace(TOKEN_PREFIXO, "")).getBody()
					.getSubject();

			return usuario != null ? new UsernamePasswordAuthenticationToken(usuario, null, emptyList()) : null;
		}
		return null;
	}
}