package pl.rynekdebnicki.waterMeter;

import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghgande.j2mod.modbus.ModbusException;
import com.ghgande.j2mod.modbus.facade.AbstractModbusMaster;
import com.ghgande.j2mod.modbus.procimg.InputRegister;
import com.ghgande.j2mod.modbus.procimg.Register;

public class WaterMeter {

	private WaterMeterConfiguration wmConfiguration;
	private WaterMeterCounter wmCounter;
	
	private AbstractModbusMaster master;
	
	private Integer modbusID;
	private String wmConfigurationFileNameIN;
	private String wmConfigurationFileNameOUT;
	private String wmCounterFileNameOUT;
	
	private Register[] configurationRegs;
	private InputRegister[] counterRegs;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	

	public WaterMeter(AbstractModbusMaster master, WaterMeterConnectionConf wmConnectionConf) {
		this.master = master;
		modbusID = WaterMeterConnectionConf.getModbusIDs().get(0);
		wmConfigurationFileNameIN = modbusID.toString()+"_" + wmConnectionConf.getWmConfigurationFileNameIN();
		wmConfigurationFileNameOUT = modbusID.toString()+"_" +wmConnectionConf.getWmConfigurationFileNameOUT();
		wmCounterFileNameOUT = modbusID.toString()+"_" +wmConnectionConf.getWmCounterFileNameOUT();
		
		System.out.println(this.wmConfigurationFileNameIN);
	}

	public void ReadConfiguration() {
		try {
			configurationRegs = master.readMultipleRegisters(modbusID, 40001, 32);
			wmConfiguration = new WaterMeterConfiguration(configurationRegs);
			mapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get(wmConfigurationFileNameOUT).toFile(), wmConfiguration);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


	}
	
	
	public void WriteConfiguration() {
		try {
			wmConfiguration = mapper.readValue(Paths.get(wmConfigurationFileNameIN).toFile(), WaterMeterConfiguration.class );
			configurationRegs = wmConfiguration.getWmConfigurationRegisters();
			master.writeMultipleRegisters(modbusID, 40001, configurationRegs);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		
	}
	
	
	
	
	public void ReadCounter() {
		try {
			counterRegs = master.readInputRegisters(modbusID, 30001, 6);
			wmCounter = new WaterMeterCounter(counterRegs);
			mapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get(wmCounterFileNameOUT).toFile(), wmCounter);
			
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Problem z połaczeniem z urządzeniem na magistarali MODBUS");
			
			
		}
	}
	
	

	public void WriteConfigurationEeprom2Ram() {
		try {
			master.writeCoil(modbusID, 10001, true);
		}
		catch(ModbusException e) {
			
		}

	}

	
	public void WriteConfigurationRam2Eeprom() {
		try {
			master.writeCoil(modbusID, 10002, true);
		}
		catch(ModbusException e) {
			
		}
		
	}
	
	
	
	public void WriteCounterEeprom2Ram() {
		try {
			master.writeCoil(modbusID, 10003, true);
		}
		catch(ModbusException e) {
			
		}
		
	}
	
	
	public void WriteCounterRam2Eeprom() {
		try {
			master.writeCoil(modbusID, 10004, true);
		}
		catch(ModbusException e) {
			
		}
		
	}
	
	
	
	public void SetRedLed() {
		try {
			master.writeCoil(modbusID, 10005, true);
		}
		catch(ModbusException e) {
			
		}
		
	}
	
	public void ResetRedLed() {
		try {
			master.writeCoil(modbusID, 10005, false);
		}
		catch(ModbusException e) {
			
		}
				
	}
	
	
	public void SetGreenLed() {
		try {
			master.writeCoil(modbusID, 10006, true);
		}
		catch(ModbusException e) {
			
		}
		
	}
	
	public void ResetGreenLed() {
		try {
			master.writeCoil(modbusID, 10006, false);
		}
		catch(ModbusException e) {
			
		}
				
	}
	
	
	public void SetOUT() {
		try {
			master.writeCoil(modbusID, 10007, true);
		}
		catch(ModbusException e) {
			
		}
		
	}
	
	public void ResetOUT() {
		try {
			master.writeCoil(modbusID, 10007, false);
		}
		catch(ModbusException e) {
			
		}
				
	}
	
	
	public void ResetAlarms() {
		try {
			master.writeCoil(modbusID, 10008, true);
		}
		catch(ModbusException e) {
			
		}
		
	}
}
