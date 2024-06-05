package br.com.fiap.beans;

public class Suporte {

	private int id;
	private int idUsuario;
	private String mensagem;
	private String data;
	private String status;

	// Construtor sem argumentos
	public Suporte() {
		super();
	}

	// Construtor com argumentos
	public Suporte(int id, int idUsuario, String mensagem, String data, String status) {
		this.id = id;
		this.idUsuario = idUsuario;
		this.mensagem = mensagem;
		this.data = data;
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

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
