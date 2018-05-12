package br.com.jfrn.project.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
/* Faz o mapeamento dos verbos HTTP padrões (GET,POST,..)*/
public class UsuarioResource {
	
	@RequestMapping(method=RequestMethod.GET)
    public @ResponseBody String getUsuarios() {
        return "{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"}," +
                "{\"firstname\":\"Marie\",\"lastname\":\"Curie\"}]}";
    }
}