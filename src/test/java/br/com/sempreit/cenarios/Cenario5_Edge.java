package br.com.sempreit.cenarios;

import java.net.MalformedURLException;

import org.testng.annotations.Test;

import br.com.maven.sempreit.pages.HomePage;
import br.com.sempreit.geradorEvidencia.GeradorEvidencias;
import br.com.sempreit.seleniumDocker.Setup;



public class Cenario5_Edge extends Setup {
	
	GeradorEvidencias evidencia = new GeradorEvidencias();
	HomePage homePage = new HomePage();
	
	@Test
	public void executorCenario() throws MalformedURLException, InterruptedException {
		
		try {
			
			setupEdge();
			System.out.println("INICIANDO EXECUÇÃO.....");
			
			homePage.acessarGoogle(driver);
			evidencia.takeSnapShot(driver, "Cenario1_Chrome");
			System.out.println("FIMMMMMMMM!!!!!");
			Thread.sleep(10000);
	
		} catch (Exception e) {
			System.out.println("Exceção apresentada: " + e.getMessage());
			driver.close();
		}

	}
	
}
