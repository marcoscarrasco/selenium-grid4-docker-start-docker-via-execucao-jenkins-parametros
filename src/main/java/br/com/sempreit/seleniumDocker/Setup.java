package br.com.sempreit.seleniumDocker;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import br.com.sempreit.utils.Utils;

public class Setup {
	
	public WebDriver driver;
	static Utils utils = new Utils();
	
	//inicializa os containers a partir do arquivo docker-compose, atraves de linha de comando
	@BeforeSuite
	public void startContainer()
	{
		CommonMethods.runTerminalCommand("docker-compose up");
	}
	
	public void setupChome() throws MalformedURLException, InterruptedException {
		
		ChromeOptions cap = new ChromeOptions();
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		System.out.println("Solicitado navegador Chrome");
		
	}
	
	public void setupFirefox() throws MalformedURLException, InterruptedException {
		
		FirefoxOptions cap = new FirefoxOptions();
		driver = new RemoteWebDriver(new URL(" http://localhost:4444"),cap);
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		System.out.println("Solicitado navegador Firefox");
		
	}
	
	public void setupOpera() throws MalformedURLException, InterruptedException {
		
		OperaOptions cap = new OperaOptions();
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		System.out.println("Solicitado navegador Opera");
		
	}
	
	public void setupEdge() throws MalformedURLException, InterruptedException {
		
		EdgeOptions cap = new EdgeOptions();
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		System.out.println("Solicitado navegador Opera");
		
	}
	
	@AfterClass
	public void fecharNavegador() {
		System.out.println("Fechando driver!!!");
		driver.quit();
	}
	
	//desliga os containers a partir do arquivo docker-compose, atraves de linha de comando
	@AfterSuite
	public void stopContainer()
	{
		CommonMethods.runTerminalCommand("docker-compose down");
	}
}
