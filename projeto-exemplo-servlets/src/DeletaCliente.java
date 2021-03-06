import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cliente/deletar")
public class DeletaCliente extends HttpServlet {
	
	private static final long serialVersionUID = 1L; 
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
		String id = req.getParameter("id");
		
		Banco banco = new Banco();
		banco.deletaCliente(Integer.valueOf(id));
		
		
	}

}
