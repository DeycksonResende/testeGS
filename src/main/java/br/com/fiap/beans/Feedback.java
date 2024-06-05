package br.com.fiap.beans;

public class Feedback {

	private int id;
	private int idUsuario;
	private String comentario;
	private String data;
	private int nota;

	// Construtor vazio
	public Feedback() {
		super();
	}

	// Construtor cheio
	public Feedback(int id, int idUsuario, String comentario, String data, int nota) {
		this.id = id;
		this.idUsuario = idUsuario;
		this.comentario = comentario;
		this.data = data;
		this.nota = nota;
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

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}
}
