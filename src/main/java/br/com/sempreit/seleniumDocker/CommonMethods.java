package br.com.sempreit.seleniumDocker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;


public class CommonMethods {

	public static void runTerminalCommand(String command)
	{
		try {
			
			Scanner scanner = null;
			String os = System.getProperty("os.name");
			String path = System.getProperty("user.dir");
			ProcessBuilder builder = null;
			
			if(os.startsWith("Windows")) {
				builder = new ProcessBuilder("cmd.exe", "/c", "cd \""+path+"\" && "+command);
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
				System.out.println("TESTE NO LINUX!!!!!!!!!!!!!!!");
				System.out.println("path:" + path);
				System.out.println("command:" + command);
				System.out.println("cd " + path + " && " + command);
				System.out.println(System.getProperty("user.dir"));
				
				scanner = new Scanner(Runtime.getRuntime().exec(new String[] {"/bin/bash", "-l", "-c", "cd " + path + " && " + command}).getInputStream());
				
				System.out.println("Esperando 30 segundos !!!!!!!!");
				Thread.sleep(30000);
				System.out.println("Terminou a espera!!!!!!!!");
				
				while (true) {
					System.out.println("TESTETSTETSTETTSTE");
					System.out.println(scanner.nextLine());
				}
					
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
