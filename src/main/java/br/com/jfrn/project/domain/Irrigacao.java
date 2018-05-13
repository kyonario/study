package br.com.jfrn.project.domain;

public class Irrigacao {
	private Long id_irrigacao;
	private Integer tipo;
	private float qtd_agua;
	
	public Irrigacao(Long id_irrigacao, Integer tipo, float qtd_agua) {
		super();
		this.id_irrigacao = id_irrigacao;
		this.tipo = tipo;
		this.qtd_agua = qtd_agua;
	}

	public Long getId_irrigacao() {
		return id_irrigacao;
	}

	public void setId_irrigacao(Long id_irrigacao) {
		this.id_irrigacao = id_irrigacao;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public float getQtd_agua() {
		return qtd_agua;
	}

	public void setQtd_agua(float qtd_agua) {
		this.qtd_agua = qtd_agua;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_irrigacao == null) ? 0 : id_irrigacao.hashCode());
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
		Irrigacao other = (Irrigacao) obj;
		if (id_irrigacao == null) {
			if (other.id_irrigacao != null)
				return false;
		} else if (!id_irrigacao.equals(other.id_irrigacao))
			return false;
		return true;
	}
	
	
}
