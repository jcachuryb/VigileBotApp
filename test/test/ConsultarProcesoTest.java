package test;

import com.absoft.core.VigileApp;
import com.absoft.model.Message;
import com.absoft.model.Process;
import com.absoft.model.ProcessData;
import com.absoft.services.db.express.ProcessService;

public class ConsultarProcesoTest {
	public static void main(String[] args) {

		VigileApp.init();
		
		String codProceso = "11001400307120180002700";
		String codCiudad = "11001";
		String codEntidad = "288-True-4003-11001-Juzgado Municipal-Civil";
		ProcessData datos = new ProcessData();
		datos.setCiudad(codCiudad);
		datos.setEntidad(codEntidad);
		datos.setCodProceso(codProceso);
		Process entrada = new Process();
		entrada.setData(datos);
		ProcessService servicio = new ProcessService();
		
		Message<String> salida = servicio.validarProceso(entrada);

		System.out.println(salida.getMsg());
	}
}
