package com.agenda.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.agenda.model.Endereco;
import com.agenda.model.Pessoa;
import com.agenda.util.ConnectionFactory;
import com.mysql.jdbc.Connection;

public class EnderecoDAO {

	private Connection connection;

	public void cadastrar(Endereco endereco) {

		String SQL = "insert into endereco (logradouro) values (?)";

		try {

			this.connection = new ConnectionFactory().getConnection();
			PreparedStatement stmt = this.connection.prepareStatement(SQL);

			stmt.setString(1, endereco.getLogradouro());

			stmt.execute();
			
			ResultSet resultSet = stmt.executeQuery("SELECT LAST_INSERT_ID()");
			if (resultSet.next()) {
				int novoId = resultSet.getInt("LAST_INSERT_ID()");
				endereco.setId(novoId);
			}

			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void remover(Endereco endereco) {

		String SQL = "delete from endereco where id=?";

		try {
			this.connection = new ConnectionFactory().getConnection();
			PreparedStatement stmt = connection.prepareStatement(SQL);
			stmt.setLong(1, endereco.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void alterar(Endereco endereco) {

		String SQL = "update endereco set logradouro=? where id=?";

		try {

			this.connection = new ConnectionFactory().getConnection();
			PreparedStatement stmt = this.connection.prepareStatement(SQL);

			stmt.setString(1, endereco.getLogradouro());

			stmt.setLong(2, endereco.getId());
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
