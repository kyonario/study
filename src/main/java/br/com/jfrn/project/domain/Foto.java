package br.com.jfrn.project.domain;

import java.util.Calendar;

public class Foto {
		private Long id_foto;
		private String registro;
		private Calendar foto_data;
		private Long id_irrigacao;
		
		
		
		
		public Foto(Long id_foto, String registro, Calendar foto_data, Long id_irrigacao) {
			super();
			this.id_foto = id_foto;
			this.registro = registro;
			this.foto_data = foto_data;
			this.id_irrigacao = id_irrigacao;
		}
		
		public Long getId_irrigacao() {
			return id_irrigacao;
		}
		public void setId_irrigacao(Long id_irrigacao) {
			this.id_irrigacao = id_irrigacao;
		}
		public Long getId_foto() {
			return id_foto;
		}
		public void setId_foto(Long id_foto) {
			this.id_foto = id_foto;
		}
		public String getRegistro() {
			return registro;
		}
		public void setRegistro(String registro) {
			this.registro = registro;
		}
		public Calendar getFoto_data() {
			return foto_data;
		}
		public void setFoto_data(Calendar foto_data) {
			this.foto_data = foto_data;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((foto_data == null) ? 0 : foto_data.hashCode());
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
			Foto other = (Foto) obj;
			if (foto_data == null) {
				if (other.foto_data != null)
					return false;
			} else if (!foto_data.equals(other.foto_data))
				return false;
			return true;
		}
		
		
		
}
