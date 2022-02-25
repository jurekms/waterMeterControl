package pl.rynekdebnicki.waterMeter;

import java.util.concurrent.Callable;

import com.ghgande.j2mod.modbus.facade.AbstractModbusMaster;

import picocli.CommandLine;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.HelpCommand;
import picocli.CommandLine.Option;
import picocli.CommandLine.ParseResult;

@Command(name = "waterMeter", subcommandsRepeatable = true, mixinStandardHelpOptions = true, subcommands = HelpCommand.class)
public class WaterMeterCLI implements Callable<Integer> {
	WaterMeterConnection wmConnection;
	WaterMeter waterMeter;

	public int executionStrategy(ParseResult parseResult) {
		if(connectionOptions != null) {
			init();
		}
		return new CommandLine.RunLast().execute(parseResult);
	}

	private void init() {
		System.out.println("Podłaczenie do urzadzenia MODBUS...");
		wmConnection = new WaterMeterConnection();
		AbstractModbusMaster master = null;
		if (connectionOptions.connectionRS) {
			master = wmConnection.getRSModbusMaster();
		}

		if (connectionOptions.connectionTCP) {
			master = wmConnection.getTCPModbusMaster();
		}
		waterMeter = new WaterMeter(master, wmConnection.getWmConnectionConf());

	}

	@ArgGroup(exclusive = true, multiplicity = "1")
	ConnectionOptions connectionOptions;

	static class ConnectionOptions {
		@Option(names = "-TCP", description = "TCP connection to the water meter.")
		boolean connectionTCP;
		@Option(names = "-RS", description = "Serial connection to the water meter.")
		boolean connectionRS;
	}

	@Command(name = "ledRED", description = "Switching off or on red LED.")
	void ledRED(@ArgGroup(exclusive = true, multiplicity = "1") LedRedOptions ledRedOptions) {

		if (ledRedOptions.ledRedON) {
			waterMeter.SetRedLed();
		}

		if (ledRedOptions.ledRedOFF) {
			waterMeter.ResetRedLed();
		}

	}

	static class LedRedOptions {
		@Option(names = "-ON")
		boolean ledRedON;
		@Option(names = "-OFF")
		boolean ledRedOFF;
	}

	@Command(name = "gLED", description = "Switching off or on green LED.")
	void gLED(@ArgGroup(exclusive = true, multiplicity = "1") GreenLedOptions greenLedOptions) {
		if (greenLedOptions.greenON) {
			waterMeter.SetGreenLed();
		}

		if (greenLedOptions.greenOFF) {
			waterMeter.ResetGreenLed();
		}
	}

	static class GreenLedOptions {
		@Option(names = "-ON")
		boolean greenON;
		@Option(names = "-OFF")
		boolean greenOFF;
	}

	@Command(name = "OUT", description = "Switching off or on transistor output (usage: OUT (-ON = connect to GND| -OFF = high impedance))")
	void tOUT(@ArgGroup(exclusive = true, multiplicity = "1") OutOptions outOptions) {
		if (outOptions.OUTon) {
			waterMeter.SetOUT();
			;
		}

		if (outOptions.OUToff) {
			waterMeter.ResetOUT();
			;
		}
	}

	static class OutOptions {
		@Option(names = "-ON")
		boolean OUTon;
		@Option(names = "-OFF")
		boolean OUToff;
	}

	@Command(name = "configRead", description = "read configuration from water meter and store in nnn_ConfigIN.json file, nnn - modbusID of water meter")
	void ConfigRead() {
		waterMeter.ReadConfiguration();

	}

	@Command(name = "configWrite", description = "write configuration from file nnn_ConfigOUT.json to water meter, nnn - modbusID of water meter")
	void configWrite() {
		waterMeter.WriteConfiguration();
	}

	@Command(name = "counterRead", description = "read counter from water meter and store in nnn_Counter.json file, nnn - modbusID of water meter")
	void CounterRead() {
		waterMeter.ReadCounter();

	}

	@Command(name = "configStore", description = "store the configuration of the water meter in permanet memeory")
	void configStore() {
		waterMeter.WriteConfigurationRam2Eeprom();

	}

	@Command(name = "configRestore", description = "restore the configuration of the water meter from permanent memeory")
	void configRestore() {
		waterMeter.WriteConfigurationEeprom2Ram();

	}

	public WaterMeterCLI() {

	}

	@Override
	public Integer call() throws Exception {

		return 1;
	}

}
