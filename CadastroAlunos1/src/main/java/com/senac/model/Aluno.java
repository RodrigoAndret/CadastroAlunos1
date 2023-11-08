package com.senac.model;

public class Aluno {

	private Integer id;
	private String nome;
	private String idade;	
	private String semestre;
	private String genero;
	private String matricula;


	public Aluno() {
	}

	public Aluno(Integer id, String nome, String idade, String semestre, String genero,String matricula) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.semestre = semestre;
		this.genero = genero;	
		this.matricula = matricula;		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}
	

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", idade=" + idade + ", genero=" + genero + ", semestre="
				+ semestre + "]";
	}
	
	

}
