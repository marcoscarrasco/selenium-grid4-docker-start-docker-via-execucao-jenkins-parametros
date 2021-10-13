package br.com.maven.sempreit.pages;


import static org.testng.Assert.*;


import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;

public class HomePage {

	public void acessarGoogle(WebDriver driver) throws MalformedURLException, InterruptedException {
		
		System.out.println("Acessando url .....");
		driver.get("https://www.google.com.br");
		System.out.println("Get da url realizado!!!");
		assertEquals(driver.getTitle(), "qwqwddsdsdsdsddddsssddddssssqwqwq");
//		Thread.sleep(5000); // slep apenas para testar comportamento ao startar v�rios navegadores
		System.out.println("Acessou o google");
		
	}
	
}
