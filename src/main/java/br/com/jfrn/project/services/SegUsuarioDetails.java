package br.com.jfrn.project.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jfrn.project.security.Usuario;


	@Repository
	public interface SegUsuarioDetails extends JpaRepository<Usuario, Long> {

	
}
