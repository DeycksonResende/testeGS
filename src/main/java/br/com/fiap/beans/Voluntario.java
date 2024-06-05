package br.com.fiap.beans;

public class Voluntario {

	private int id;
	private int idUsuario;
	private int idEvento;
	private String dataInscricao;
	private String status;

	// Construtor vazio
	public Voluntario() {
		super();
	}

	// Construtor cheio
	public Voluntario(int id, int idUsuario, int idEvento, String dataInscricao, String status) {
		this.id = id;
		this.idUsuario = idUsuario;
		this.idEvento = idEvento;
		this.dataInscricao = dataInscricao;
		this.status = status;
	}

	// Getters e Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getEventoId() {
		return idEvento;
	}

	public void setEventoId(int idEvento) {
		this.idEvento = idEvento;
	}

	public String getDataInscricao() {
		return dataInscricao;
	}

	public void setDataInscricao(String dataInscricao) {
		this.dataInscricao = dataInscricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
