

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListaCliente
 */
@WebServlet("/cliente/lista")
public class ListaCliente extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		Banco banco = new Banco(); 
		List<Cliente> lista = banco.getListaCliente(); 
		req.setAttribute("clientes", lista); 
		RequestDispatcher rd = req.getRequestDispatcher("/cliente-lista.jsp"); 
		rd.forward(req, resp);
	}

}
