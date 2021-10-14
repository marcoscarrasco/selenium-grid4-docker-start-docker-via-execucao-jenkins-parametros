package br.com.sempreit.seleniumDocker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;


public class CommonMethods {

	public static void runTerminalCommand(String command)
	{
		try {
			
			String os = System.getProperty("os.name");
			String path = System.getProperty("user.dir");
			
			if(os.startsWith("Windows")) {
				ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "cd \""+path+"\" && "+command);
				builder.redirectErrorStream(true);
				Process p = builder.start();
				BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				while (true) {
					line = r.readLine();
					/*quando encontrar "Registered a node" (Grid 3) ou 
					"Node has been added" (Grid4), entende que os containers subiram. Quando encontrar
					"Removing selenium-hub", entende que o container n�o est� mais em execu��o*/
					if (line.contains("Registered a node") || line.contains("Node has been added")) { 
						Thread.sleep(5000);
						System.out.println("Containers inicializados: " + line);
						break; 
					}else if (line.contains("Removing selenium-hub")){
						Thread.sleep(5000);
						System.out.println("Containers n�o est�o mais em execu��o: " + line);
						break; 
					}
				}	
			
			}else {
				
				String s;
		        Process p;
		        
		        System.out.println("EXECUCAO LINUX");
		        System.out.println("CAMINHO: " + "cd " + path + " && " + command);			
//		        p = Runtime.getRuntime().exec("cd " + path + " && " + command);
		        System.out.println(p = Runtime.getRuntime().exec("pwd"));
		        p = Runtime.getRuntime().exec("cd /var/lib/jenkins/workspace/execucao-grid4-docker-jenkins-com-parametros && docker-compose up");
		        
		        
	            BufferedReader br = new BufferedReader(
	                new InputStreamReader(p.getInputStream()));
	            while ((s = br.readLine()) != null)
	                System.out.println("line: " + s);
	            p.waitFor();
	            System.out.println ("exit: " + p.exitValue());
	            p.destroy();
	            
	            System.out.println("ESPERANDO .........30 SEGUNDOS");
	            Thread.sleep(30000);
	            System.out.println("TERMINOU .........30 SEGUNDOS");
					
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
