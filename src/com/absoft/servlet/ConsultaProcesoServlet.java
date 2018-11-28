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

@SuppressWarnings("serial")
public class ConsultaProcesoServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");

		String codProceso = req.getParameter("codProceso");
		String codCiudad = req.getParameter("ciudad");
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

}
