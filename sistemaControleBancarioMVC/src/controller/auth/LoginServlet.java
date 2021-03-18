package controller.auth;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Pessoa;
import model.repository.PessoaRepository;
import utils.cookie.CookieUtils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet()
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
		RequestDispatcher rd;
		
		if ("OK".equals(session.getAttribute("usuarioAutenticado")) && CookieUtils.temAutorizacao(request.getCookies()))
		{
			Pessoa p = (Pessoa) session.getAttribute("pessoa");
			request.setAttribute("pessoa", p);
			request.setAttribute("tituloPagina", "Perfil de " + p.getNomePessoa());
			request.setAttribute("pathPagina", "/pessoa/ver.jsp");
		}
		else
		{
			request.setAttribute("tituloPagina", "Autenticação no Sistema");
			request.setAttribute("subtituloPagina", "Utilize suas credenciais para acessar!");
			request.setAttribute("pathPagina", "/auth/login.jsp");
		}
		
		request.setAttribute("doServidor", true);
		
		rd = request.getRequestDispatcher("/template.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		boolean sucessoAuth = false;
		HttpSession session = request.getSession();
		RequestDispatcher rd;
		String email = request.getParameter("txtEmail");
		String senha = request.getParameter("txtSenha");
		
		Pessoa p = PessoaRepository.recuperarPessoaPorEmail(email);
		
		if (p != null && p.getSituacaoPessoa() == 1 && p.getEmail().equals(email))
		{
			if (p.getSenha().equals(senha))
			{
				session.setAttribute("usuarioAutenticado", "OK");
				session.setAttribute("pessoa", p);
				
				request.setAttribute("pessoa", p);
				request.setAttribute("mensagemAlerta", "Login realizado com sucesso!");
				
				request.setAttribute("tituloPagina", "Perfil de " + p.getNomePessoa());
				request.setAttribute("pathPagina", "/pessoa/ver.jsp");
				
				sucessoAuth = true;
				
				Cookie cookie = new Cookie("id", ((Integer)p.getIdPessoa()).toString());
				cookie.setMaxAge(60 *  30);
				response.addCookie(cookie);
			}
		}
		
		if (!sucessoAuth)
		{
			request.setAttribute("mensagemAlerta", "E-mail ou senha inválido/a!");
			request.setAttribute("tituloPagina", "Autenticação no Sistema");
			request.setAttribute("subtituloPagina", "Utilize suas credenciais para acessar!");
			request.setAttribute("pathPagina", "/auth/login.jsp");
		}
		
		request.setAttribute("doServidor", true);
		
		rd = request.getRequestDispatcher("/template.jsp");
		rd.forward(request, response);
	}
}
