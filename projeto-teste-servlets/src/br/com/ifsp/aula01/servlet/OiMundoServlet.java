package br.com.ifsp.aula01.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/oi")
public class OiMundoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	// http://localhost:8080/projeto-teste-servlets/oi
	// http://localhost:8080/projeto-teste-servlets/bem-vindo.html

	@Override 
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		
		PrintWriter out = resp.getWriter(); 
		out.println("<html>"); 
		out.println("<body>"); 
		out.println("oi mundo, parabens vc escreveu o primeiro servlets."); 
		out.println("</body>"); 
		out.println("</html>");
		
		System.out.println("o servlet OiMundoServlet foi chamado");
	}

}
