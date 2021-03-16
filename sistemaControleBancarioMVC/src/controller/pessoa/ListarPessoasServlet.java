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
 * Servlet implementation class PessoaServlet
 */
@WebServlet(
{ "/pessoa", "/pessoa/listar" })
public class ListarPessoasServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarPessoasServlet()
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
		Set<Pessoa> pessoas = PessoaRepository.recuperarPessoas();
		
		request.setAttribute("pessoasCadastradas", pessoas);
		request.setAttribute("tituloPagina", "Cadastro de Pessoas");
		request.setAttribute("pathPagina", "/pessoa/listar.jsp");
		
		RequestDispatcher rd = request.getRequestDispatcher("/template.jsp");
		
		rd.forward(request, response);
	}
	
}
