import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cliente/editar")
public class EditaCliente extends HttpServlet {
	
	private static final long serialVersionUID = 1L; 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		String id = request.getParameter("id"); 
		Banco banco = new Banco();
		
		Cliente cliente = banco.getClientebyId(Integer.valueOf(id));
		request.setAttribute("id", cliente.getId()); 
		request.setAttribute("nome", cliente.getNome());
		request.setAttribute("email", cliente.getEmail());
		request.setAttribute("cpf_cnpj", cliente.getCpf_cnpj()); 
		request.setAttribute("telefone", cliente.getTelefone());
		request.setAttribute("tipo", cliente.getTipo()); 
		
		RequestDispatcher rd = request.getRequestDispatcher("/cliente-novo.jsp");
		rd.forward(request, response);
	}

}
