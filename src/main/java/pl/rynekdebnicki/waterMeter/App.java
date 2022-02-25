package pl.rynekdebnicki.waterMeter;

import picocli.CommandLine;

public class App
{

	static public void main(String[] args) throws Exception
	{

		WaterMeterCLI wmCLI = new WaterMeterCLI();
		

	//	int exitCode = new CommandLine(wmCLI).setExecutionStrategy(wmCLI::executionStrategy).execute("-RS","rLED","-OFF", "wConfig", "rConfig", "rCounter");
		int exitCode = new CommandLine(wmCLI).setExecutionStrategy(wmCLI::executionStrategy).execute();	



		
			
		
		
	}


	
}