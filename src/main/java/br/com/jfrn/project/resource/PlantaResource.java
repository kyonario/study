package br.com.jfrn.project.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.jfrn.project.domain.Planta;

@RestController
@RequestMapping("/usuario")
/* Faz o mapeamento dos verbos HTTP padrões (GET,POST,..)*/
public class PlantaResource {
	
	@RequestMapping(method=RequestMethod.GET)
    public List<Planta> listar() {
		
		Planta planta1 = new Planta(1,"arbo",001,001,001,"estante");
		Planta planta2 = new Planta(2,"sulina",002,002,002,"patio");
		
		List<Planta> lista = new ArrayList<>();
		lista.add(planta1);
		lista.add(planta2);
		
        return lista;
    }
}