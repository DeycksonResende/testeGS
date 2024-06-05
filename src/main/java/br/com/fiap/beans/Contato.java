package br.com.fiap.beans;

public class Contato {

	private int id;
	private int idUsuario;
	private String telefone;
	private String email;

	// Construtor vazio
	public Contato() {
		super();
	}

	// Construtor cheio
	public Contato(int id, int idUsuario, String telefone, String email) {
		this.id = id;
		this.idUsuario = idUsuario;
		this.telefone = telefone;
		this.email = email;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
