package br.com.helloworld;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWorld
 */
@WebServlet({ "/HelloWorld", "/Index" })
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorld() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
//		response.getOutputStream();
//		request.getInputStream();
		
		Date data = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		out.println("<!doctype html>");
		out.println("<html lang='pt-br'");
		out.println("<head>");
		out.println("<title>Hello World em Servlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Hello World em Servlet</h1>");
		out.println("<p>LP2A4 - Linguagem de Programação 2</p>");
		out.println("<p>Data: ");
		out.println(df.format(data));
		out.println("</p>");
		out.println("</body>");
		out.println("</html>");
	}

}
