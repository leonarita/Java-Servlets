package br.com.files;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NovaEmpresaServlet
 */
@WebServlet("/download")
@MultipartConfig
public class FileDownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L; 
	
	private final int ARBITARY_SIZE = 1048;
		
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try(
        		InputStream in = new BufferedInputStream(new FileInputStream("C:\\Teste\\Mult.cpp"));
        		OutputStream out = response.getOutputStream();
        ) {
        	String filename = "teste.txt";
        	
        	response.setContentType("text/plain");
        	response.setHeader("Content-disposition", "attachment; filename=" + filename);

            byte[] buffer = new byte[ARBITARY_SIZE];

            int numBytesRead;

            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        }
    }
}
