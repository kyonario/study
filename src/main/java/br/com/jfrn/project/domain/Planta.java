package br.com.jfrn.project.domain;

import java.io.Serializable;
import java.util.Calendar;

public class Planta implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private Integer id_foto;
	private Integer id_irrigacao;
	private Integer id_proprietario;
	private String local;
	
	

	public Planta(Integer id, String nome, Integer id_foto, Integer id_irrigacao, Integer id_proprietario,
			String local) {
		super();
		this.id = id;
		this.nome = nome;
		this.id_foto = id_foto;
		this.id_irrigacao = id_irrigacao;
		this.id_proprietario = id_proprietario;
		this.local = local;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId_foto() {
		return id_foto;
	}

	public void setId_foto(Integer id_foto) {
		this.id_foto = id_foto;
	}

	public Integer getId_irrigacao() {
		return id_irrigacao;
	}

	public void setId_irrigacao(Integer id_irrigacao) {
		this.id_irrigacao = id_irrigacao;
	}

	public Integer getId_proprietario() {
		return id_proprietario;
	}

	public void setId_proprietario(Integer id_proprietario) {
		this.id_proprietario = id_proprietario;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planta other = (Planta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
