package br.com.sempreit.seleniumDocker;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class CommonMethods {

	public static void runTerminalCommand(String command)
	{
		
		try {
			
			String os = System.getProperty("os.name");
			String path = System.getProperty("user.dir");
			
			ProcessBuilder builder = null;
			
			if(os.startsWith("Windows")) {
				System.out.println("Execução via terminal Windows");
				builder = new ProcessBuilder("cmd.exe", "/c", "cd \""+path+"\" && "+command);
			}else {
				System.out.println("Execução via terminal: " + os.toString());
				builder = new ProcessBuilder("/bin/bash", "-l", "-c", command);
			}
			
			builder.redirectErrorStream(true);
			Process p = builder.start();
			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while (true) {
				line = r.readLine();
				System.out.println("LOGS: " + line);
				/*quando encontrar "Registered a node" (Grid 3) ou 
				"from DOWN to UP" (Grid4), entende que os containers subiram. Quando encontrar
				"Removing selenium-hub", entende que os containers não estão mais em execução*/
				if (line.contains("Registered a node") || line.contains("from DOWN to UP")) { 
					Thread.sleep(10000);
					System.out.println("Containers inicializados: " + line);
					break; 
				}else if (line.contains("Removing selenium-hub")){
					Thread.sleep(10000);
					System.out.println("Containers não estão mais em execução : " + line);
					break; 
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public static void main(String[] args) {
		runTerminalCommand("docker-compose up");
	}
}
