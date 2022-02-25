package trash;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.ghgande.j2mod.modbus.procimg.DefaultProcessImageFactory;
import com.ghgande.j2mod.modbus.procimg.Register;

public class WaterMeterConfig {


	private StringBuilder	wmLabel;
	private Long			wmSerialNumber;
	private Integer			wmSoftVersion ;
	private Long			wmCorrectionCounter ;
	private Long			wmInitialCounter;

	@JsonIgnore
	private Long			wmStoreCounter;
	@JsonIgnore
	private Long			wmCurrentCounter;
	@JsonIgnore
	private Long			wmPulseCounter;
	@JsonIgnore
	private Integer			wmCheckYear;
	@JsonIgnore
	private Integer			wmFirstTimeRunFlag;
	@JsonIgnore
	private Integer			wmModbusSlaveAddress;
	@JsonIgnore
	private Integer			wmContinuousWaterFlowAlarmAllow;
	@JsonIgnore
	private Integer			wmContinuousWaterFlowAlarmTimes;
	@JsonIgnore
	private Long			wmContinuousWaterFlowAlarmPeriod;
	@JsonIgnore
	private Integer			wmNoWaterFlowAlarmAllow;
	@JsonIgnore
	private Long			wmNoWaterFlowAlarmTime;
	@JsonIgnore
	private Integer			wmOUTTransistorAlarmAllow;

	@JsonIgnore
	private Register[]		wmRegisters;


	@JsonCreator()
	public WaterMeterConfig() {
		this.wmRegisters = new Register[32];
		this.wmLabel = new StringBuilder();

		DefaultProcessImageFactory dpiFactory = new DefaultProcessImageFactory();

		for(int i = 0;i < 32; i++) {
			this.wmRegisters[i] = dpiFactory.createRegister(0);
		}
	}

	public WaterMeterConfig(Register[] wmRegisters) {
		this.wmRegisters = wmRegisters;
		wmLabel = new StringBuilder();
		wmLabel.append(ModbusConverter.RegisterToString(wmRegisters, 0, 8));
		wmSerialNumber = ModbusConverter.RegisterToLong(this.wmRegisters, 8);
		wmSoftVersion = ModbusConverter.RegisterToInteger(this.wmRegisters, 10);
		wmCorrectionCounter = ModbusConverter.RegisterToLong(this.wmRegisters, 11);
		wmInitialCounter = ModbusConverter.RegisterToLong(this.wmRegisters, 13);
		wmStoreCounter = ModbusConverter.RegisterToLong(this.wmRegisters,15);
		wmCurrentCounter = ModbusConverter.RegisterToLong(this.wmRegisters,17);
		wmPulseCounter = ModbusConverter.RegisterToLong(this.wmRegisters,19);
  		wmCheckYear = ModbusConverter.RegisterToInteger(this.wmRegisters,21);
  		wmFirstTimeRunFlag = ModbusConverter.RegisterToInteger(this.wmRegisters,22);
		wmModbusSlaveAddress = ModbusConverter.RegisterToInteger(this.wmRegisters,23);
		wmContinuousWaterFlowAlarmAllow = ModbusConverter.RegisterToInteger(this.wmRegisters,24);
		wmContinuousWaterFlowAlarmTimes = ModbusConverter.RegisterToInteger(this.wmRegisters,25);
		wmContinuousWaterFlowAlarmPeriod = ModbusConverter.RegisterToLong(this.wmRegisters,26);
		wmNoWaterFlowAlarmAllow = ModbusConverter.RegisterToInteger(this.wmRegisters, 28);
		wmNoWaterFlowAlarmTime = ModbusConverter.RegisterToLong(this.wmRegisters,29);
		wmOUTTransistorAlarmAllow = ModbusConverter.RegisterToInteger(this.wmRegisters, 31);
	}







	@JsonGetter("wmLabel")
	public String getWmLabel() {
		return wmLabel.toString();
	}

	@JsonSetter("wmLabel")
	public void setWmLabel(String wmLabel) {
		ModbusConverter.StringToRegister(wmLabel.toString(), this.wmRegisters, 0, 8);
		this.wmLabel.setLength(0);
		this.wmLabel.append(ModbusConverter.RegisterToString(this.wmRegisters, 0, 8));
	}

	@JsonGetter("wmSerialNumber")
	public Long getWmSerialNumber() {
		return wmSerialNumber;
	}

	@JsonSetter("wmSerialNumber")
	public void setWmSerialNumber(Long wmSerialNumber) {
		this.wmSerialNumber = wmSerialNumber;
		ModbusConverter.LongToRegister(wmSerialNumber, wmRegisters, 8);
	}

	@JsonGetter("wmSoftVersion")
	public Integer getWmSoftVersion() {
		return this.wmSoftVersion;
	}

	@JsonSetter("wmSoftVersion")
	public void setWmSoftVersion(Integer wmSoftVersion) {
		this.wmSoftVersion = wmSoftVersion;
		ModbusConverter.IntegerToRegister(this.wmSoftVersion, this.wmRegisters, 10);
	}




}


