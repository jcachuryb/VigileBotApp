package test;

import com.absoft.core.VigileApp;
import com.absoft.model.Response;
import com.absoft.services.db.express.AccountService;

public class DesactivarCuentasTest {

	public static void main(String[] args) {
		VigileApp.init();

		AccountService accService = new AccountService();
		Response res = accService.deactivateAccounts();
		System.out.println(res.getMsg());
	}
}
