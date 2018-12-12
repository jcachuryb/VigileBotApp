package com.absoft.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.absoft.model.Account;
import com.absoft.model.Message;
import com.absoft.oper.ConsultaProcedure;
import com.absoft.oper.interfaces.TaskProcessObserver;
import com.absoft.services.db.express.TaskService;

@SuppressWarnings("serial")
public class ConsultaCompletaServlet extends HttpServlet implements TaskProcessObserver {

	String idTask;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");

		String message = "";
		String idTask = "";
		String status = "fail";
		try {
			this.idTask = TaskService.initTask("Consulta total por demanda.");
			if (this.idTask == null) {
				throw new Exception("No se pudo crear el registro de ejecución.");
			}
			ConsultaProcedure procedimiento = new ConsultaProcedure(this);
			Message<List<Account>> msg = procedimiento.inciaConsultaGeneralProcesos();
			if (msg.getValid()) {
				message = "Había " + msg.getData().size() + " firmas para ser consultadas.";
				status = "success";

			} else {
				TaskService.endTask(idTask, false, msg.getMsg(), false);
				throw new Exception(msg.getMsg());
			}

		} catch (Exception e) {
			status = "fail";
			message = e.getMessage();
		}

		PrintWriter out = resp.getWriter();

		out.println("{\"status\": \"" + status + "\", \"message\": \"" + message + "\"}");

	}

	@Override
	public String iniciarTarea(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void consultaCompletada(Boolean exito, String mensaje) {
		TaskService.endTask(idTask, exito, mensaje, false);
	}

}
