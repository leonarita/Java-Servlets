package controller.contacomum;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
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
 * Servlet implementation class CadastrarContaComumServlet
 */
@WebServlet("/contacomum/cadastrar")
public class CadastrarContaComumServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CadastrarContaComumServlet()
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
			// Recuperar os objetos do tipo Pessoa com situação = 1 (Cadastro ativo).
			Set<Pessoa> pessoas = PessoaRepository.recuperarPessoasPorStatus(1);
			
			request.setAttribute("pessoasCadastradas", pessoas);
			request.setAttribute("tituloPagina", "Cadastrar Nova Conta");
			request.setAttribute("pathPagina", "/contacomum/cadastrar.jsp");
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
			try
			{
				Date dateAbertura = null, dateFechamento = null;
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
				String[] titularesIds = request.getParameterValues("ddlTitulares");
				Set<Pessoa> titulares = new HashSet<Pessoa>();
				
				if (titulares != null)
				{
					for (String id : titularesIds)
					{
						if (id != null)
						{
							Pessoa p = PessoaRepository.recuperarPessoaPorId(Integer.parseInt(id));
							titulares.add(p);
						}
					}
				}
				
				String parAbertura = request.getParameter("dtmAbertura");
				
				if (parAbertura != null && !(parAbertura.trim().equals("")))
					dateAbertura = (Date) formatter.parse(parAbertura);
				
				String parFechamento = request.getParameter("dtmFechamento");
				
				if (parFechamento != null && !(parFechamento.trim().equals("")))
					dateFechamento = (Date) formatter.parse(parFechamento);
				
				ContaComum cc = new ContaComum();
				
				cc.setPessoas(titulares);
				
				if (dateAbertura != null)
				{
					Calendar abertura = Calendar.getInstance();
					abertura.setTime(dateAbertura);
					cc.setAberturaConta(abertura);
				}
				
				if (dateFechamento != null)
				{
					Calendar fechamento = Calendar.getInstance();
					fechamento.setTime(dateFechamento);
					cc.setFechamentoConta(fechamento);
				}
				
				cc.setSituacaoConta(Integer.parseInt(request.getParameter("ddlSituacao")));
				cc.setSenhaConta(Integer.parseInt(request.getParameter("txtSenha")));
				cc.setSaldoConta(Double.parseDouble(request.getParameter("numSaldoInicial").replace(',', '.')));
				
				ContaComumRepository.criarContaComum(cc);
				
				Set<ContaComum> contas = ContaComumRepository.recuperarContasComuns();
				
				ContaComumRepository.closeEntityManager();
				
				request.setAttribute("contasCadastradas", contas);
				request.setAttribute("tituloPagina", "Cadastro de Contas");
				request.setAttribute("pathPagina", "/contacomum/listar.jsp");
				request.setAttribute("mensagemAlerta", "Conta cadastrada com sucesso!");
			}
			catch (Exception e)
			{
				System.out.println("Erro ao tentar cadastrar a nova conta.");
				System.out.println(e.getMessage());
				System.out.println(e.getStackTrace().toString());
				
				for (StackTraceElement ste : e.getStackTrace())
				{
					System.out.println(ste.toString());
				}
			}
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
