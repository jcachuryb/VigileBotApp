package com.absoft.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormatSymbols;
import java.util.Locale;
import java.util.Properties;

import com.absoft.oper.ConsultaProcedure;
import com.absoft.services.db.ParseServer;

public class VigileApp {

	private static Properties prop;

	public static void init() {
		prop = new Properties();
		InputStream input = null;

		String path = "";
		try {
			path = System.getenv("LAWPROX_HOME");

			input = new FileInputStream(path + "/config.properties");
			prop.load(input);

			ParseServer.initialize();

			VigileBotProperties.parse_user = getProperty("parse_user");
			VigileBotProperties.parse_password = getProperty("parse_password");
			VigileBotProperties.timer_consulta_cuentas = getProperty("timer_consulta_cuentas");
			VigileBotProperties.timer_consulta_procesos = getProperty("timer_consulta_procesos");

			// Iniciar Timers
			VigileBotApp.init();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		config();

	}

	public static void start() {
		ConsultaProcedure procedimiento = new ConsultaProcedure();
		procedimiento.inciaConsultaGeneralProcesos();
	}

	public static String getProperty(String property) {
		try {
			return prop.getProperty(property);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	private static void config() {

		// ---------------------------------------------------------------
		DateFormatSymbols sym = DateFormatSymbols.getInstance(new Locale("es", "co"));
		sym.setMonths(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
				"Septiembre", "Octubre", "Noviembre", "Diciembre" });
		sym.setShortMonths(
				new String[] { "Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic" });
		Locale.setDefault(new Locale("es", "CO"));

		System.setProperty("webdriver.chrome.driver", getProperty("chromedriver.path"));
	}

	public static void main(String[] args) {
		init();

		start();
	}
}
