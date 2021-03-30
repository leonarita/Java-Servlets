package controller.fotoperfil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.FotoPerfil;
import model.repository.FotoPerfilRepository;
import utils.cookie.CookieUtils;

/**
 * Servlet implementation class DownloadFotoPerfilServlet
 */
@WebServlet("/fotoperfil/download")
public class DownloadFotoPerfilServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownloadFotoPerfilServlet()
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
		boolean sucesso = false;
		
		if ("OK".equals(session.getAttribute("usuarioAutenticado")) && CookieUtils.temAutorizacao(request.getCookies()))
		{
			long idFotoPerfil = 0;
			FotoPerfil fp = null;
			String pIdFotoPerfil = request.getParameter("idfotoperfil");
			
			if (pIdFotoPerfil != null)
			{
				try
				{
					idFotoPerfil = Long.parseLong(pIdFotoPerfil);
				}
				catch (Exception e)
				{
					System.out.println("ID de Foto de Perfil inválido!");
					System.out.println(e.getMessage());
					System.out.println(e.getStackTrace().toString());
					
					for (StackTraceElement ste : e.getStackTrace())
					{
						System.out.println(ste.toString());
					}
				}
			}
			
			if (idFotoPerfil > 0)
			{
				fp = FotoPerfilRepository.recuperarFotoPerfilPorId(idFotoPerfil);
			}
			
			if (fp != null)
			{
				// Cria um InputStream a partir dos bytes do arquivo 
				InputStream inStream = new ByteArrayInputStream(fp.getArquivo());
				
				// Obtém o content type do arquivo
				String contentType = fp.getContentType();
				
				// Modifica o tipo e o tamanho do arquivo de resposta
				response.setContentType(contentType);
				response.setContentLength(fp.getArquivo().length);
				
				// Força o download
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\"%s\"", fp.getNome());
				response.setHeader(headerKey, headerValue);
				
				// Obtém o OutputStream da resposta
				OutputStream outStream = response.getOutputStream();
				
				byte[] buffer = new byte[4096];
				int bytesRead = -1;
				
				while ((bytesRead = inStream.read(buffer)) != -1)
				{
					outStream.write(buffer, 0, bytesRead);
				}
				
				inStream.close();
				outStream.close();
				
				sucesso = true;
			}
		}
		
		if(!sucesso)
		{
			request.setAttribute("tituloPagina", "Sistema de Controle Bancário");
			request.setAttribute("subtituloPagina", "Seja bem-vindo!");
			request.setAttribute("doServidor", true);
			request.setAttribute("pathPagina", "/home.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/template.jsp");
			rd.forward(request, response);
		}
	}
}
