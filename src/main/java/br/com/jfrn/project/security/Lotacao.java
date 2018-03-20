package br.com.jfrn.project.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;


	@Entity
	public class Lotacao implements Serializable { 
		
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id;
		private String nome;
		private String descricao;
		
		@JsonManagedReference
		@OneToMany(mappedBy="lotacao")
		private List<Usuario> usuarios = new ArrayList<>();

		public Lotacao() {
			
		}
		
		public Lotacao(Long id, String nome, String descricao) {
			super();
			this.id = id;
			this.nome = nome;
			this.descricao = descricao;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		
		public void setUsuarios(List<Usuario> usuarios) {
			this.usuarios = usuarios;
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
			Lotacao other = (Lotacao) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}
}