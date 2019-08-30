package com.agenda.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.agenda.model.Contato;
import com.agenda.model.Endereco;
import com.agenda.util.ConnectionFactory;
import com.mysql.jdbc.Connection;

public class ContatoDAO {
	
	private Connection connection;

	public Contato cadastrar(Contato contato) {

		String SQL = "insert into contato (email,telefone) values (?,?)";

		try {

			this.connection = new ConnectionFactory().getConnection();
			PreparedStatement stmt = this.connection.prepareStatement(SQL);

			stmt.setString(1, contato.getEmail());
			stmt.setString(2, contato.getTelefone());

			stmt.execute();
			
			ResultSet resultSet = stmt.executeQuery("SELECT LAST_INSERT_ID()");
			if (resultSet.next()) {
				int  novoId = resultSet.getInt("LAST_INSERT_ID()");
				contato.setId(novoId);
			}
			
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return contato;

	}
	
	
	public void remover(Contato contato) {

		String SQL = "delete from contato where id=?";

		try {
			this.connection = new ConnectionFactory().getConnection();
			PreparedStatement stmt = connection.prepareStatement(SQL);
			stmt.setLong(1, contato.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void alterar(Contato contato) {
		
	    String SQL = "update contato set email=?,telefone=? where id=?";
	    
	    try {
	    	
	    	this.connection = new ConnectionFactory().getConnection();
	        PreparedStatement stmt = this.connection.prepareStatement(SQL);
	        
	        stmt.setString(1, contato.getEmail());
	        stmt.setString(2, contato.getTelefone());
	        
	        stmt.setLong(3, contato.getId());
	        stmt.execute();
	        stmt.close();
	        
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}

}
