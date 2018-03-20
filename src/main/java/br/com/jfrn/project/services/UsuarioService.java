package br.com.jfrn.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.jfrn.project.security.Usuario;




@Service
public class UsuarioService {
	@Autowired
	private SegUsuarioDetails repo;

	public List<Usuario> findAll(){
		return repo.findAll();
	}		
}
