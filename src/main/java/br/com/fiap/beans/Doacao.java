package br.com.fiap.beans;

public class Doacao {

	private int id;
	private int idUsuario;
	private double valor;
	private String data;
	private int empresaId;

	// Construtor vazio
	public Doacao() {
		super();
	}

	public Doacao(int id, int idUsuario, double valor, String data, int empresaId) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.valor = valor;
		this.data = data;
		this.empresaId = empresaId;
	}

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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(int empresaId) {
		this.empresaId = empresaId;
	}

}
