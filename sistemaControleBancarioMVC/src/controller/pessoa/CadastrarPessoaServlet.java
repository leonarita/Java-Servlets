package controller.pessoa;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pessoa;
import model.repository.PessoaRepository;

/**
 * Servlet implementation class CadastrarPessoaServlet
 */
@WebServlet("/pessoa/cadastrar")
public class CadastrarPessoaServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CadastrarPessoaServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setAttribute("tituloPagina", "Cadastrar Nova Pessoa");
		request.setAttribute("pathPagina", "/pessoa/cadastrar.jsp");
		RequestDispatcher rd = request.getRequestDispatcher("/template.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Pessoa p = new Pessoa();
		
		p.setNomePessoa(request.getParameter("txtNome"));
		p.setEnderecoPessoa(request.getParameter("txtEndereco"));
		p.setCepPessoa(Long.parseLong(request.getParameter("numCep")));
		p.setTelefonePessoa(request.getParameter("txtTelefone"));
		p.setEmail(request.getParameter("txtEmail"));
		p.setRendaPessoa(Double.parseDouble(request.getParameter("numRenda").replace(',', '.')));
		p.setSituacaoPessoa("on".equals(request.getParameter("chkAtivo")) ? 1 : 0);
		p.setSenha(request.getParameter("txtSenha"));
		
		PessoaRepository.criarPessoa(p);
		
		Set<Pessoa> pessoas = PessoaRepository.recuperarPessoas();
		
		PessoaRepository.closeEntityManager();
		
		request.setAttribute("pessoasCadastradas", pessoas);
		request.setAttribute("tituloPagina", "Cadastro de Pessoas");
		request.setAttribute("pathPagina", "/pessoa/listar.jsp");
		request.setAttribute("mensagemAlerta", "Pessoa cadastrada com sucesso!");
		
		RequestDispatcher rd = request.getRequestDispatcher("/template.jsp");
		
		rd.forward(request, response);
	}
	
}
