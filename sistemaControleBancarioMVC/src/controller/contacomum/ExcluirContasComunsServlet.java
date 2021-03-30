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
import model.Pessoa;
import model.repository.ContaComumRepository;
import model.repository.PessoaRepository;
import utils.cookie.CookieUtils;

/**
 * Servlet implementation class ExcluirPessoaServlet
 */
@WebServlet("/contacomum/excluir")
public class ExcluirContasComunsServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExcluirContasComunsServlet()
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
			int idconta = 0;
			ContaComum c = null;
			String cIdconta = request.getParameter("idconta");
			
			if (cIdconta != null)
			{
				try
				{
					idconta = Integer.parseInt(cIdconta);
				}
				catch (Exception e)
				{
					System.out.println("ID de Pessoa inválido!");
					System.out.println(e.getMessage());
					System.out.println(e.getStackTrace().toString());
					
					for (StackTraceElement ste : e.getStackTrace())
					{
						System.out.println(ste.toString());
					}
				}
			}
			
			if (idconta > 0)
			{
				c = ContaComumRepository.recuperarContaComumPorNumeroConta(idconta);
			}
			
			request.setAttribute("tituloPagina", "Confirmar Exclusão da Conta");
			request.setAttribute("pathPagina", "/contacomum/excluir.jsp");
			request.setAttribute("conta", c);
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
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		
		if ("OK".equals(session.getAttribute("usuarioAutenticado")) && CookieUtils.temAutorizacao(request.getCookies()))
		{
			long idConta = 0;
			String pIdConta = request.getParameter("numIdPessoa");
			
			if (pIdConta != null)
			{
				try
				{
					idConta = Long.parseLong(pIdConta);
				}
				catch (Exception e)
				{
					System.out.println("ID de Pessoa inválido!");
					System.out.println(e.getMessage());
					System.out.println(e.getStackTrace().toString());
					
					for (StackTraceElement ste : e.getStackTrace())
					{
						System.out.println(ste.toString());
					}
				}
			}
			
			if (idConta > 0)
			{
				ContaComum c = ContaComumRepository.recuperarContaComumPorNumeroConta(idConta);
				
				if (c != null)
				{
					ContaComumRepository.excluirContaComum(c);
					request.setAttribute("mensagemAlerta", "Conta excluída com sucesso!");
				}
			}
			
			Set<Pessoa> pessoas = PessoaRepository.recuperarPessoas();
			
			PessoaRepository.closeEntityManager();
			
			request.setAttribute("pessoasCadastradas", pessoas);
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
