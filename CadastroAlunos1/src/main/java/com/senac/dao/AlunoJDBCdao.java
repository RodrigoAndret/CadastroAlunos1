package com.senac.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.senac.model.Aluno;

import jakarta.servlet.http.HttpSession;



@Component
public class AlunoJDBCdao {	
	
	private HttpSession session;
	
	public AlunoJDBCdao (HttpSession session) {
		this.session = session;
	}
	
	public HttpSession getSession() {
		return session;
	}
	
	
	// Conectando com o banco de dados
	public Connection getConexao() throws SQLException {	
		
		// Driver
		String driver = "com.mysql.cj.jdbc.Driver";
		
		// Banco de dados
		String url = "jdbc:mysql://localhost:3306/cadastroalunos?useTimezone=true&serverTimezone=UTC";
		
		// Usuario
		String user = "root";
		
		// Senha
		String password = "root";			
		
		
		Connection con = null;	
		
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, user, password);
				System.out.println("Conectado ao banco de dados");
			} catch (ClassNotFoundException e) {				
				e.printStackTrace();
			}
			
			
		
		return con;
	}
		

			
	public int cadastrarAluno(Aluno aluno) {
		String insert = "INSERT INTO alunos (nome, idade, semestre, genero, matricula) VALUES (?, ?, ?, ?, ?)";
		try {
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, aluno.getNome());
			pst.setString(2, aluno.getIdade());
			pst.setString(3, aluno.getSemestre());
			pst.setString(4, aluno.getGenero());
			pst.setString(5, aluno.getMatricula());
			pst.executeUpdate();
			
			
			ResultSet generatedKeys = pst.getGeneratedKeys();

			if (generatedKeys.next()) {
				int chaveGerada = generatedKeys.getInt(1);
				return chaveGerada;
			}			 
			pst.close();
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}
	
	
	
	
	
	public ArrayList<Aluno> listarAlunos() {
		ArrayList<Aluno> alunos = new ArrayList<>();
		String query = "SELECT * FROM alunos";
		try {
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String idade = rs.getString(3);
				String semestre = rs.getString(4);
				String genero = rs.getString(5);
				String matricula = rs.getString(6);
				alunos.add(new Aluno(id, name, idade, semestre,genero,matricula));
			}
			rs.close();
			pst.close();
			con.close();			
		} catch (Exception e) {
			System.out.println(e);			
		}
		return alunos;
	}
	
	
	
	public Aluno pesquisarPorId(Aluno aluno) {
		String query = "SELECT * FROM alunos WHERE Id = ?";
		try {
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, aluno.getId());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				aluno.setId(rs.getInt(1));
				aluno.setNome(rs.getString(2));
				aluno.setIdade(rs.getString(3));
				aluno.setSemestre(rs.getString(4));
				aluno.setGenero(rs.getString(5));
				aluno.setMatricula(rs.getString(6));
			}
			rs.close();
			pst.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return aluno;
	}
	
	
	
	
	
	public void alterarAluno(Aluno aluno) {
		String update = "UPDATE alunos SET nome = ?, idade = ?, semestre = ?, genero = ? WHERE Id = ?";
		try {
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, aluno.getNome());
			pst.setString(2, aluno.getIdade());
			pst.setString(3, aluno.getSemestre());
			pst.setString(4, aluno.getGenero());
			pst.setInt(5, aluno.getId());
			pst.executeUpdate();
			
			
			pst.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	public ArrayList<Aluno>  pesquisar(String valor, String tipo) {
		ArrayList<Aluno> alunos = new ArrayList<>();
		
		String query;		
		if (tipo.equals("matricula")) {
			query = "SELECT * FROM alunos WHERE Matricula = " + valor;
		} else {
			query = "SELECT * FROM alunos WHERE nome LIKE '%" + valor + "%'";
		}		
		
		try {
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String idade = rs.getString(3);
				String semestre = rs.getString(4);
				String genero = rs.getString(5);
				String matricula = rs.getString(6);
				alunos.add(new Aluno(id, name, idade, semestre,genero,matricula));
			}
			rs.close();
			pst.close();
			con.close();			
		} catch (Exception e) {
			System.out.println(e);			
		}
		return alunos;
	}
	
	
	public void excluirAluno(Aluno aluno) {
		String delete = "Delete FROM alunos WHERE (Id = ?)";
		try {
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setInt(1, aluno.getId());
			pst.executeUpdate();
			pst.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
		


}
