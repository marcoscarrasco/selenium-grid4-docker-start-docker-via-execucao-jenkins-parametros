package br.com.sempreit.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class Utils {
	
	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream("resource/application.properties");
		props.load(file);
		
		return props;
	}
	
	public static String gerarNomeParaVideoDoDockerDinamico() throws IOException {
		
		DateTimeFormatter dtfDataEHora = DateTimeFormatter.ofPattern("dd-MM-yyyy_HHmmss");
		String dataEHoraExecucao = dtfDataEHora.format(LocalDateTime.now());
		
		
		return dataEHoraExecucao;
	}
	
	public static Properties getenv() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream(".env");
		props.load(file);
		
		return props;
	}
	
}
