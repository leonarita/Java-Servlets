import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cliente/novo")
public class NovoCliente extends HttpServlet {
	
	private static final long serialVersionUID = 1L; 
	
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/cliente-novo.jsp"); 
		rd.forward(req, resp); 
	}
	
	@Override 
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		
		System.out.println("Cadastrando novo cliente");
		
		req.setCharacterEncoding("UTF-8"); 
		resp.setCharacterEncoding("UTF-8");
		
		String id = req.getParameter("id"); 
		
		if (id == null || id.isEmpty()) 
			id ="0"; 
		
		String nome = req.getParameter("nome"); 
		String tipo = req.getParameter("tipo");
		String email = req.getParameter("email");
		String cpf_cnpj = req.getParameter("cpf_cnpj"); 
		String telefone = req.getParameter("telefone"); 
		
		List<String> mensagens = new ArrayList<String>(); 
		
		//valida os dados obrigatórios 
		if(nome.isEmpty()) {
			mensagens.add("Campo nome é obrigatório"); 
		} if(tipo==null || tipo.isEmpty()) {
			mensagens.add("Campos tipo é obrigatório");
		} if(email.isEmpty()) {
			mensagens.add("Campos e-mail é obrigatório"); 
		} if(cpf_cnpj.isEmpty()) {
			mensagens.add("Campos CPF/CNPJ é obrigatório"); 
		} if(telefone.isEmpty()) { 
			mensagens.add("Campos telefone é obrigatório");
		}
		
		if (mensagens.size() > 0) { 
			req.setAttribute("mensagens", mensagens); 
			req.setAttribute("id", id); 
			req.setAttribute("nome", nome);
			req.setAttribute("email", email);
			req.setAttribute("cpf_cnpj", cpf_cnpj);
			req.setAttribute("telefone", telefone);
			req.setAttribute("tipo", tipo); 
		} else { 
			Cliente cliente = new Cliente(); 
			cliente.setId(Integer.valueOf(id)); 
			cliente.setNome(nome);
			cliente.setEmail(email);
			cliente.setCpf_cnpj(cpf_cnpj);
			cliente.setTelefone(telefone); 
			cliente.setTipo(tipo); 
			
			Banco banco = new Banco(); 
			banco.salvaCliente(cliente);
			
			req.setAttribute("sucess", "Cliente cadastrado com sucesso!"); 
		}
		
		doGet(req, resp);

	}

}
