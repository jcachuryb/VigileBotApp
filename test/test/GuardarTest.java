package test;

import com.absoft.core.VigileApp;
import com.absoft.services.ConsultaFirmaService;
import com.absoft.services.db.ProcessDao;
import com.absoft.services.db.express.ProcessService;

public class GuardarTest {

	public static void main(String[] args) {
		VigileApp.init();

		ProcessDao processDao = new ProcessService();

//		List<Process> procesos = processDao.getProcesosByCompanyId("gFLBnLcFqF", 0, 30);
		
		ConsultaFirmaService.consultarFirma("gFLBnLcFqF");
		
//
//		Process[] arrayActualizados = new Process[procesos.size()];
//		arrayActualizados = procesos.toArray(arrayActualizados);
//		processDao.updateProcesos(arrayActualizados);
	}
}
