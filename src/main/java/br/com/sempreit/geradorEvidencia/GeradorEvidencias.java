package br.com.sempreit.geradorEvidencia;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import br.com.sempreit.utils.Utils;

public class GeradorEvidencias {
	
	public void takeSnapShot(WebDriver webdriver, String nomeCenario) throws Exception{
		
		if(Utils.getProp().get("web.gerar.evidencia").equals("true")) {
			
			DateTimeFormatter dtfData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String dataExecucao = dtfData.format(LocalDateTime.now());
			
			DateTimeFormatter dtfDataEHora = DateTimeFormatter.ofPattern("dd-MM-yyyy_HHmmss");
			String dataEHoraExecucao = dtfDataEHora.format(LocalDateTime.now());
			
			
			//Convert web driver object to TakeScreenshot
			TakesScreenshot scrShot =((TakesScreenshot)webdriver);
			
			//Call getScreenshotAs method to create image file
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			
			//Move image file to new destination
			File DestFile=new File(".//Evidencias//" + dataExecucao + "//" + nomeCenario + "_" + dataEHoraExecucao + ".png");
			
			//Copy file at destination
			FileUtils.copyFile(SrcFile, DestFile);
			
		}else {
			System.out.println("*********GERADOR DE EVIDENCIA DESABILITADO NO PROPERTIES**********");
			System.out.println("*********GERADOR DE EVIDENCIA DESABILITADO NO PROPERTIES**********");
		}
		

    }
	
	public static void main(String[] args) {
       
	}

}
