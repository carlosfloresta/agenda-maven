package com.agenda.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agenda.model.Contato;
import com.agenda.model.Endereco;
import com.agenda.model.Pessoa;
import com.agenda.service.CadastraUsuarioService;

public class CadastraUsuarioServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private CadastraUsuarioService service;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
		Pessoa pessoa = new Pessoa();
		Contato contato = new Contato();
		Endereco endereco = new Endereco();
		
		if(null != request.getParameter("id") && !"".equals(request.getParameter("id"))) {
			pessoa.setId(Long.parseLong(request.getParameter("id")));
		}
		
		
		
		contato.setEmail(request.getParameter("email"));
		endereco.setLogradouro(request.getParameter("endereco"));
		contato.setTelefone(request.getParameter("telefone"));
		pessoa.setNome(request.getParameter("nome"));
		this.service = new CadastraUsuarioService();
		
		PrintWriter out = response.getWriter();

		try {
			
			this.service.salvarOuAtualizar(contato,endereco,pessoa);
			
			response.sendRedirect("busca-contatos");
			
		} catch (Exception e) {
			out.println("<html>");
			out.println("<body>");
			out.println("Falha ao realizar o cadastro!"+ e);
			out.println("</body>");
			out.println("</html>");
		}
		
	}
	
}
