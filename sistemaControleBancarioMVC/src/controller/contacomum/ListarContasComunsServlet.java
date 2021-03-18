package controller.contacomum;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ContaComum;
import model.repository.ContaComumRepository;
import utils.cookie.CookieUtils;

/**
 * Servlet implementation class ListarContasComunsServlet
 */
@WebServlet({ "/contacomum", "/contacomum/listar" })
public class ListarContasComunsServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarContasComunsServlet()
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
		HttpSession session = request.getSession();
		
		if ("OK".equals(session.getAttribute("usuarioAutenticado")) && CookieUtils.temAutorizacao(request.getCookies()))
		{
			Set<ContaComum> pessoas = ContaComumRepository.recuperarContasComuns();
			
			request.setAttribute("contasCadastradas", pessoas);
			request.setAttribute("tituloPagina", "Cadastro de Contas");
			
			request.setAttribute("pathPagina", "/contacomum/listar.jsp");
		}
		else
		{
			request.setAttribute("tituloPagina", "Acesso Negado!");
			request.setAttribute("pathPagina", "/unauthorized.jsp");
		}
		
		request.setAttribute("doServidor", true);
		
		RequestDispatcher rd = request.getRequestDispatcher("/template.jsp");
		
		rd.forward(request, response);
	}
	
}
