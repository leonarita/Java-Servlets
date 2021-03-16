package controller.pessoa;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pessoa;
import model.repository.PessoaRepository;

/**
 * Servlet implementation class ExcluirPessoaServlet
 */
@WebServlet("/pessoa/excluir")
public class ExcluirPessoaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExcluirPessoaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idPessoa = 0;
		Pessoa p = null;
		String pIdPessoa = request.getParameter("idpessoa");
		
		if (pIdPessoa != null)
		{
			try
			{
				idPessoa = Integer.parseInt(pIdPessoa);
			} catch (Exception e)
			{
				System.out.println("ID de Pessoa inválido!");
			}
		}
		
		if (idPessoa > 0)
		{
			p = PessoaRepository.recuperarPessoaPorId(idPessoa);
		}
		
		request.setAttribute("tituloPagina", "Confirmar Exclusão de Pessoa");
		request.setAttribute("pathPagina", "/pessoa/excluir.jsp");
		request.setAttribute("pessoa", p);
		
		RequestDispatcher rd = request.getRequestDispatcher("/template.jsp");
		
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idPessoa = 0;
		String pIdPessoa = request.getParameter("numIdPessoa");
		
		if (pIdPessoa != null)
		{
			try
			{
				idPessoa = Integer.parseInt(pIdPessoa);
			}
			catch (Exception e)
			{
				System.out.println("ID de Pessoa inválido!");
			}
		}
		
		if (idPessoa > 0)
		{
			Pessoa p = PessoaRepository.recuperarPessoaPorId(idPessoa);
			
			if (p != null)
			{
				PessoaRepository.excluirPessoa(p);
				request.setAttribute("mensagemAlerta", "Pessoa excluída com sucesso!");
			}
		}
		
		Set<Pessoa> pessoas = PessoaRepository.recuperarPessoas();
		
		PessoaRepository.closeEntityManager();
		
		request.setAttribute("pessoasCadastradas", pessoas);
		request.setAttribute("tituloPagina", "Cadastro de Pessoas");
		request.setAttribute("pathPagina", "/pessoa/listar.jsp");
		
		RequestDispatcher rd = request.getRequestDispatcher("/template.jsp");
		
		rd.forward(request, response);
	}

}
