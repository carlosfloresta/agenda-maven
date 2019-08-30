package com.agenda.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.agenda.model.Contato;
import com.agenda.model.Endereco;
import com.agenda.model.Pessoa;
import com.agenda.util.ConnectionFactory;
import com.mysql.jdbc.Connection;

public class BuscaDAO {
	
	
	Connection connection;
	
	public List<Pessoa> buscarPessoas() {

		String SQL = "select pessoas.nome, endereco.logradouro, contato.telefone, contato.email from pessoas inner join endereco on endereco.id = pessoas.id_endereco inner join contato on contato.id = pessoas.id_contato where pessoas.id=?";

		try {

			this.connection = new ConnectionFactory().getConnection();
			PreparedStatement stmt = this.connection.prepareStatement(SQL);

			List<Pessoa> pessoas = new ArrayList<Pessoa>();

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Pessoa pessoa = new Pessoa();
				Contato contato = new Contato();
				Endereco endereco = new Endereco();
				pessoa.setId(rs.getLong("id"));
				pessoa.setNome(rs.getString("nome"));
				endereco.setLogradouro(rs.getString("logradouro"));
				contato.setEmail(rs.getString("telefone"));
				contato.setEmail(rs.getString("email"));

				pessoas.add(pessoa);
				
				System.out.println(pessoas);
			}

			stmt.close();
			this.connection.close();
			return pessoas;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	

}
