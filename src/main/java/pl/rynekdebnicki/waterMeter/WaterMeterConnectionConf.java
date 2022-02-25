package pl.rynekdebnicki.waterMeter;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.ghgande.j2mod.modbus.net.TCPMasterConnection;
import com.ghgande.j2mod.modbus.util.SerialParameters;

public class WaterMeterConnectionConf {
	private static WaterMeterConnectionConf wmConnectionConf;
	@JsonProperty
	private String wmTCPIPAddress;
	@JsonProperty
	private int wmTCPport;
	@JsonProperty
	private int wmTCPtimeout;
	@JsonProperty
	private boolean wmTCPreconnect;
	@JsonProperty
	private boolean wmTCPuseRtuOverTcp;
	@JsonProperty
	private String wmConfigurationFileNameOUT;
	@JsonProperty
	private String wmConfigurationFileNameIN;
	@JsonProperty
	private String wmCounterFileNameOUT;

	@JsonFilter("wmRSFilter")
	@JsonProperty
	private SerialParameters wmRSparameters = new SerialParameters();

	@JsonProperty
	private static List<Integer> modbusIDs = new ArrayList<Integer>();

	public static WaterMeterConnectionConf getWaterMeterConnectionConf() {

		if (wmConnectionConf == null) {

			ObjectMapper mapper = new ObjectMapper();
			try {
				wmConnectionConf = mapper.readValue(Paths.get("ConnectionConfig.json").toFile(),
						WaterMeterConnectionConf.class);
			} catch (IOException e) {
				System.out.println(e.getMessage());

				try {
					wmConnectionConf = new WaterMeterConnectionConf();
					wmConnectionConf.setWmTCPIPAddress("10.11.22.152");
					wmConnectionConf.setWmTCPport(502);

					wmConnectionConf.getWmRSparameters().setBaudRate(19200);
					wmConnectionConf.getWmRSparameters().setPortName("COM3");

					wmConnectionConf.setWmConfigurationFileNameIN("wmConfigIN.json");
					wmConnectionConf.setWmConfigurationFileNameOUT("wmConfigOUT.json");
					wmConnectionConf.setWmCounterFileNameOUT("wmCounter.json");

					wmConnectionConf.setModbusID(16);

					SimpleFilterProvider filterProvider = new SimpleFilterProvider();

					filterProvider.addFilter("wmRSFilter",
							SimpleBeanPropertyFilter.serializeAllExcept("parityString", "stopbitsString",
									"flowControlOutString", "flowControlInString", "databitsString", "baudRateString"));
					mapper.setFilterProvider(filterProvider);
					mapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get("ConnectionConfig.json").toFile(),
							wmConnectionConf);
					System.out.println("utworzono plik wzorcowy ... ConnectionConfig.json");

				} catch (Exception e1) {
					System.out.println(e1.getMessage());

				}

			}

		}

		return wmConnectionConf;
	}

	
	
	
	@JsonCreator
	public WaterMeterConnectionConf() {

	}

	public String getWmTCPIPAddress() {
		return wmTCPIPAddress;
	}

	public void setWmTCPIPAddress(String wmIPAddress) {
		this.wmTCPIPAddress = wmIPAddress;
	}

	public int getWmTCPport() {
		return wmTCPport;
	}

	public void setWmTCPport(int wmTCPport) {
		this.wmTCPport = wmTCPport;
	}

	public int getWmTCPtimeout() {
		return wmTCPtimeout;
	}

	public void setWmTCPtimeout(int wmTCPtimeout) {
		this.wmTCPtimeout = wmTCPtimeout;
	}

	public boolean isWmTCPreconnect() {
		return wmTCPreconnect;
	}

	public void setWmTCPreconnect(boolean wmTCPreconnect) {
		this.wmTCPreconnect = wmTCPreconnect;
	}

	public boolean isWmTCPuseRtuOverTcp() {
		return wmTCPuseRtuOverTcp;
	}

	public void setWmTCPuseRtuOverTcp(boolean wmTCPuseRtuOverTcp) {
		this.wmTCPuseRtuOverTcp = wmTCPuseRtuOverTcp;
	}

	public String getWmConfigurationFileNameOUT() {
		return wmConfigurationFileNameOUT;
	}

	public void setWmConfigurationFileNameOUT(String wmConfigurationFileNameOUT) {
		this.wmConfigurationFileNameOUT = wmConfigurationFileNameOUT;
	}

	public String getWmConfigurationFileNameIN() {
		return wmConfigurationFileNameIN;
	}

	public void setWmConfigurationFileNameIN(String wmConfigurationFileNameIN) {
		this.wmConfigurationFileNameIN = wmConfigurationFileNameIN;
	}

	public String getWmCounterFileNameOUT() {
		return wmCounterFileNameOUT;
	}

	public void setWmCounterFileNameOUT(String wmCounterFileNameOUT) {
		this.wmCounterFileNameOUT = wmCounterFileNameOUT;
	}

	public SerialParameters getWmRSparameters() {
		return wmRSparameters;
	}

	public void setWmRSparameters(SerialParameters wmRSparameters) {
		this.wmRSparameters = wmRSparameters;
	}

	public static List<Integer> getModbusIDs() {
		return modbusIDs;
	}

	public void setModbusIDs(List<Integer> modbusIDs) {
		this.modbusIDs = modbusIDs;
	}

	public void setModbusID(Integer modbusID) {
		this.modbusIDs.add(modbusID);
	}

}
