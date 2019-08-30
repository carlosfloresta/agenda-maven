package com.agenda.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.agenda.model.Contato;
import com.agenda.model.Endereco;
import com.agenda.model.Pessoa;
import com.agenda.util.ConnectionFactory;
import com.mysql.jdbc.Connection;

public class PessoaDAO {

	private Connection connection;

	public void cadastrar(Pessoa pessoa) {

		String SQL = "insert into pessoas (nome,id_contato,id_endereco) values (?,?,?)";

		try {

			this.connection = new ConnectionFactory().getConnection();
			PreparedStatement stmt = this.connection.prepareStatement(SQL);

			stmt.setString(1, pessoa.getNome());
			stmt.setLong(2, pessoa.getContato().getId());
			stmt.setLong(3, pessoa.getEndereco().getId());			

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	

	public void remover(Pessoa pessoa) {

		String SQL = "delete from pessoas where id=?";

		try {
			this.connection = new ConnectionFactory().getConnection();
			PreparedStatement stmt = connection.prepareStatement(SQL);
			stmt.setLong(1, pessoa.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void alterar(Pessoa pessoa) {
		
	    String SQL = "update pessoas set nome=? where id=?";
	    
	    try {
	    	
	    	this.connection = new ConnectionFactory().getConnection();
	        PreparedStatement stmt = this.connection.prepareStatement(SQL);
	        
	        stmt.setString(1, pessoa.getNome());
	        
	        stmt.setLong(2, pessoa.getId());
	        stmt.execute();
	        stmt.close();
	        
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}

}
