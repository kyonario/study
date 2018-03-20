package br.com.jfrn.project.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.event.LoggerListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration //indica que o spring invoque aquele método e gerencie o objeto retornado por ele. Isso quer dizer que
			   //o objeto pode ser injetado em qualquer ponto de sua aplicação
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	// cria um provedor de acesso com protocolo LDAP para o AD
	@Bean
	public ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
		ActiveDirectoryLdapAuthenticationProvider provider = new ActiveDirectoryLdapAuthenticationProvider(
				"jfrn.gov.br", "ldap://jfrn.gov.br/"); // Não é necessário informar a porta.
		provider.setConvertSubErrorCodesToExceptions(true);
		provider.setUseAuthenticationRequestCredentials(true);
		return provider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Create a default account
			 
			//this.adAuth = auth.authenticationProvider(activeDirectoryLdapAuthenticationProvider());
			//Authentication autenticado = provedor.authenticate(this.adAuth);
			
			auth.authenticationProvider(activeDirectoryLdapAuthenticationProvider());
			 
			
			//System.out.println("Dados do usuario: "+ auth.getDefaultUserDetailsService().toString());
			 // como está no
		//auth.jdbcAuthentication().usersByUsernameQuery(getUserQuery());//.authoritiesByUsernameQuery(getAuthoritiesQuery()); 																								// pc do
																										// trabalho
		// https://auth0.com/blog/securing-spring-boot-with-jwts/
	/*	}
		if(jdbcAuthEnabled) {
			System.out.println(">>> [JDBC] Provedor de Autenticacao está habilitado: "+ jdbcAuthEnabled);
			auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(jdbcAuthQuery).authoritiesByUsernameQuery("select nome, roleuser from role where username=?");
			System.out.println(auth.getDefaultUserDetailsService());
		} */
	}

	
/*	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authDB) throws Exception {
		authDB.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(getUserQuery());//.authoritiesByUsernameQuery(getAuthoritiesQuery())
	} */

	@Bean
	public LoggerListener loggerListener() {
		return new LoggerListener();
	}

		
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	/*	http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, "/login").permitAll().antMatchers() //defaultSucessUrl("/home",true)
				.permitAll().anyRequest().authenticated().and()
				// We filter the api/login requests */
		http.csrf().disable().authorizeRequests().anyRequest().fullyAuthenticated().antMatchers(HttpMethod.POST, "/login").permitAll()
				.and().addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
						UsernamePasswordAuthenticationFilter.class)
				// And filter other requests to check the presence of JWT in header
				.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
/*
	private String getUserQuery() {
		return "SELECT username" + "FROM account_credentials " + "WHERE username = ?";
	}

	private String getAuthoritiesQuery() {
		return "SELECT DISTINCT usuario.login as username, autorizacao.descricao as authority "
				+ "FROM usuario, autorizacao_usuario, autorizacao "
				+ "WHERE usuario.id = autorizacao_usuario.fk_usuario "
				+ "AND autorizacao.id = autorizacao_usuario.fk_autorizacao " + "AND usuario.login = ? ";
	} */
	
}
