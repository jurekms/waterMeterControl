package pl.rynekdebnicki.waterMeter;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.ghgande.j2mod.modbus.facade.AbstractModbusMaster;
import com.ghgande.j2mod.modbus.facade.ModbusSerialMaster;
import com.ghgande.j2mod.modbus.facade.ModbusTCPMaster;

public class WaterMeterConnection {
	private WaterMeterConnectionConf wmConnectionConf;
	private AbstractModbusMaster wmConnection;

	public WaterMeterConnection() {
		
		wmConnectionConf = WaterMeterConnectionConf.getWaterMeterConnectionConf();

	}

	public AbstractModbusMaster getTCPModbusMaster() {
		try {
			this.wmConnection = new ModbusTCPMaster(wmConnectionConf.getWmTCPIPAddress(), wmConnectionConf.getWmTCPport(),
					wmConnectionConf.getWmTCPtimeout(), wmConnectionConf.isWmTCPreconnect(),
					wmConnectionConf.isWmTCPuseRtuOverTcp());
			this.wmConnection.connect();

		} catch (Exception e) {

		}
		return this.wmConnection;
	}

	public AbstractModbusMaster getRSModbusMaster() {

		try {
			this.wmConnection = new ModbusSerialMaster(wmConnectionConf.getWmRSparameters(), 1000);
			this.wmConnection.connect();
		} catch (Exception e) {

		}
		return this.wmConnection;
	}

	public WaterMeterConnectionConf getWmConnectionConf() {
		return wmConnectionConf;
	}

	public void setWmConnectionConf(WaterMeterConnectionConf wmConnectionConf) {
		this.wmConnectionConf = wmConnectionConf;
	}

}
