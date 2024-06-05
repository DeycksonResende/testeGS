package br.com.fiap.beans;

public class EmpresaParceira {

	private int id;
	private String nome;
	private String descricao;
	private String contato;

	// Construtor vazio
	public EmpresaParceira() {
		super();
	}

	// Construtor cheio
	public EmpresaParceira(int id, String nome, String descricao, String contato) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.contato = contato;
	}

	// Getters e Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}
}
