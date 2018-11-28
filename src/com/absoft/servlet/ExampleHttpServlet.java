package com.absoft.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.absoft.model.Message;
import com.absoft.model.Process;
import com.absoft.model.ProcessData;
import com.absoft.services.db.express.ProcessService;

// Creating Http Servlet by Extending HttpServlet class
@SuppressWarnings("serial")
public class ExampleHttpServlet extends HttpServlet {
	private String mymsg;

	public void init() throws ServletException {
		mymsg = "Http Servlet Demo";
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Setting up the content type of web page
		response.setContentType("text/html");
		// Writing the message on the web page
		PrintWriter out = response.getWriter();
		out.println("<h1>" + mymsg + "</h1>");
		out.println("<p>" + "Hello Friends!" + "</p>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		
		String codProceso = req.getParameter("codProceso"); 
		String codCiudad =  req.getParameter("ciudad");
		String codEntidad = req.getParameter("entidad");
		
		ProcessData datos = new ProcessData();
		datos.setCiudad(codCiudad);
		datos.setEntidad(codEntidad);
		datos.setCodProceso(codProceso);
		Process entrada = new Process();
		entrada.setData(datos);
		ProcessService servicio = new ProcessService();
		
		Message<String> salida = servicio.validarProceso(entrada);
		PrintWriter out = resp.getWriter();
		out.println(salida.getData());
		
	}

	public void destroy() {
		// Leaving empty. Use this if you want to perform
		// something at the end of Servlet life cycle.
	}
}