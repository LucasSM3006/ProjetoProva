package br.senai.sc.edu.Prova.repos.dto;

public class AlterarDisponibilidadeDTO {
	private int codigo;
	private boolean disponibilidade;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public boolean isDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

}
