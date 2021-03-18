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

import utils.cookie.CookieUtils;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutServlet()
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
		
		if ("OK".equals(session.getAttribute("usuarioAutenticado"))  && CookieUtils.temAutorizacao(request.getCookies()))
		{
			request.setAttribute("tituloPagina", "Sair");
			request.setAttribute("pathPagina", "/auth/logout.jsp");
		}
		else
		{
			request.setAttribute("tituloPagina", "Sistema de Controle Bancário");
			request.setAttribute("subtituloPagina", "Seja bem-vindo!");
			request.setAttribute("pathPagina", "/home.jsp");
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
		HttpSession session = request.getSession();
		RequestDispatcher rd;
		
		if ("OK".equals(session.getAttribute("usuarioAutenticado")) && CookieUtils.temAutorizacao(request.getCookies()))
		{
			session.removeAttribute("usuarioAutenticado");
			session.removeAttribute("pessoa");
			
			request.setAttribute("mensagemAlerta", "Logout realizado com sucesso!");
			
			Cookie[] cookies = request.getCookies();

			if (cookies != null) {
				for (Cookie cookie : cookies) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}

		}
		
		request.setAttribute("tituloPagina", "Sistema de Controle Bancário");
		request.setAttribute("subtituloPagina", "Seja bem-vindo!");
		request.setAttribute("pathPagina", "/home.jsp");
		
		request.setAttribute("doServidor", true);
		
		rd = request.getRequestDispatcher("/template.jsp");
		rd.forward(request, response);
	}
}
