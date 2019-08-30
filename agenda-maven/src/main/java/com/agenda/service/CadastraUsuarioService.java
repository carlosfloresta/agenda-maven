package com.agenda.service;

import java.util.List;

import com.agenda.dao.BuscaDAO;
import com.agenda.dao.ContatoDAO;
import com.agenda.dao.EnderecoDAO;
import com.agenda.dao.PessoaDAO;
import com.agenda.model.Contato;
import com.agenda.model.Endereco;
import com.agenda.model.Pessoa;

public class CadastraUsuarioService {

	private PessoaDAO dao;
	private BuscaDAO dao2;
	private ContatoDAO dao3;
	private EnderecoDAO dao4;

	public CadastraUsuarioService() {
		this.dao = new PessoaDAO();
		this.dao2 = new BuscaDAO();
		this.dao3 = new ContatoDAO();
		this.dao4 = new EnderecoDAO();
	}

	public void salvarOuAtualizar(Contato contato,Endereco endereco,Pessoa pessoa) {

		if (pessoa.getId() == 0) {
			
			this.dao3.cadastrar(contato);
			this.dao4.cadastrar(endereco);
			
			pessoa.setEndereco(endereco);
			pessoa.setContato(contato);
			
			this.dao.cadastrar(pessoa);
		} else {
			this.dao.alterar(pessoa);
			this.dao3.alterar(contato);
			this.dao4.alterar(endereco);
		}
	}

	public List<Pessoa> buscaPessoas() {
		return this.dao2.buscarPessoas();
	}

	public void remover(Pessoa pessoa,Contato contato,Endereco endereco) {
		this.dao.remover(pessoa);
		this.dao3.remover(contato);
		this.dao4.remover(endereco);
	}

}
