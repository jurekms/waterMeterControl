package pl.rynekdebnicki.waterMeter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.ghgande.j2mod.modbus.procimg.DefaultProcessImageFactory;
import com.ghgande.j2mod.modbus.procimg.Register;

public class WaterMeterConfiguration
{

	private Register[] wmConfigurationRegisters;

	private ModbusString wmLabel;
	private ModbusLong wmSerialNumber;
	private ModbusInteger wmSoftVersion;
	private ModbusLong wmCorrectionCounter;
	private ModbusLong wmInitialCounter;
	private ModbusLong wmStoreCounter;
	private ModbusLong wmCurrentCounter;
	private ModbusLong wmPulseCounter;
	private ModbusInteger wmCheckYear;
	private ModbusInteger wmFirstTimeRunFlag;
	private ModbusInteger wmModbusSlaveAddress;
	private ModbusInteger wmContinuousWaterFlowAlarmAllow;
	private ModbusInteger wmContinuousWaterFlowAlarmTimes;
	private ModbusLong wmContinuousWaterFlowAlarmPeriod;
	private ModbusInteger wmNoWaterFlowAlarmAllow;
	private ModbusLong wmNoWaterFlowAlarmTime;
	private ModbusInteger wmOUTTransistorAlarmAllow;

	@JsonCreator
	public WaterMeterConfiguration()
	{
		this.wmConfigurationRegisters = new Register[32];

		DefaultProcessImageFactory dpiFactory = new DefaultProcessImageFactory();

		for (int i = 0; i < 32; i++)
		{
			this.wmConfigurationRegisters[i] = dpiFactory.createRegister(0);
		}
		WaterMeterInitConfiguration();
	}

	public WaterMeterConfiguration(Register[] wmConfigurationRegisters)
	{
		this.wmConfigurationRegisters = wmConfigurationRegisters;
		WaterMeterInitConfiguration();
	}

	private void WaterMeterInitConfiguration()
	{
		wmLabel = new ModbusString(this.wmConfigurationRegisters, 0, 7);
		wmSerialNumber = new ModbusLong(this.wmConfigurationRegisters, 8);
		wmSoftVersion = new ModbusInteger(this.wmConfigurationRegisters, 10);
		wmCorrectionCounter = new ModbusLong(this.wmConfigurationRegisters, 11);
		wmInitialCounter = new ModbusLong(this.wmConfigurationRegisters, 13);
		wmStoreCounter = new ModbusLong(this.wmConfigurationRegisters, 15);
		wmCurrentCounter = new ModbusLong(this.wmConfigurationRegisters, 17);
		wmPulseCounter = new ModbusLong(this.wmConfigurationRegisters, 19);
		wmCheckYear = new ModbusInteger(this.wmConfigurationRegisters, 21);
		wmFirstTimeRunFlag = new ModbusInteger(this.wmConfigurationRegisters, 22);
		wmModbusSlaveAddress = new ModbusInteger(this.wmConfigurationRegisters, 23);
		wmContinuousWaterFlowAlarmAllow = new ModbusInteger(this.wmConfigurationRegisters, 24);
		wmContinuousWaterFlowAlarmTimes = new ModbusInteger(this.wmConfigurationRegisters, 25);
		wmContinuousWaterFlowAlarmPeriod = new ModbusLong(this.wmConfigurationRegisters, 26);
		wmNoWaterFlowAlarmAllow = new ModbusInteger(this.wmConfigurationRegisters, 28);
		wmNoWaterFlowAlarmTime = new ModbusLong(this.wmConfigurationRegisters, 29);
		wmOUTTransistorAlarmAllow = new ModbusInteger(this.wmConfigurationRegisters, 31);
	}

	@JsonIgnore
	public Register[] getWmConfigurationRegisters()
	{
		return wmConfigurationRegisters;
	}

	@JsonIgnore
	public void setWmConfigurationRegisters(Register[] wmConfigurationRegisters)
	{
		this.wmConfigurationRegisters = wmConfigurationRegisters;
	}

	@JsonGetter
	public String getWmLabel()
	{
		return wmLabel.getString();
	}

	@JsonSetter
	public void setWmLabel(String wmLabel)
	{
		this.wmLabel.setString(wmLabel);
	}

	@JsonGetter("wmSerialNumber")
	public Long getWmSerialNumber()
	{
		return wmSerialNumber.getLong();
	}

	@JsonSetter("wmSerialNumber")
	public void setWmSerialNumber(Long wmSerialNumber)
	{
		this.wmSerialNumber.setLong(wmSerialNumber);
	}

	@JsonGetter("wmSoftVersion")
	public Integer getWmSoftVersion()
	{
		return wmSoftVersion.getInteger();
	}

	@JsonSetter("wmSoftVersion")
	public void setWmSoftVersion(Integer wmSoftVersion)
	{
		this.wmSoftVersion.setInteger(wmSoftVersion);
	}

	@JsonGetter("wmCorectionCounter")
	public Long getWmCorrectionCounter()
	{
		return wmCorrectionCounter.getLong();
	}

	@JsonSetter("wmCorectionCounter")
	public void setWmCorrectionCounter(Long wmCorrectionCounter)
	{
		this.wmCorrectionCounter.setLong(wmCorrectionCounter);
	}

	@JsonGetter("wmInitialCounter")
	public Long getWmInitialCounter()
	{
		return wmInitialCounter.getLong();
	}

	@JsonSetter("wmInitialCounter")
	public void setWmInitialCounter(Long wmInitialCounter)
	{
		this.wmInitialCounter.setLong(wmInitialCounter);
	}

	@JsonGetter("wmStoreCounter")
	public Long getWmStoreCounter()
	{
		return wmStoreCounter.getLong();
	}

	@JsonSetter("wmStoreCounter")
	public void setWmStoreCounter(Long wmStoreCounter)
	{
		this.wmStoreCounter.setLong(wmStoreCounter);
	}

	@JsonGetter("wmCurrentCounter")
	public Long getWmCurrentCounter()
	{
		return wmCurrentCounter.getLong();
	}

	@JsonSetter("wmCurrentCounter")
	public void setWmCurrentCounter(Long wmCurrentCounter)
	{
		this.wmCurrentCounter.setLong(wmCurrentCounter);
	}

	@JsonGetter("wmPulseCounter")
	public Long getWmPulseCounter()
	{
		return wmPulseCounter.getLong();
	}

	@JsonSetter("wmPulseCounter")
	public void setWmPulseCounter(Long wmPulseCounter)
	{
		this.wmPulseCounter.setLong(wmPulseCounter);
	}

	@JsonGetter("wmCheckYear")
	public Integer getWmCheckYear()
	{
		return wmCheckYear.getInteger();
	}

	@JsonSetter("wmCheckYear")
	public void setWmCheckYear(Integer wmCheckYear)
	{
		this.wmCheckYear.setInteger(wmCheckYear);
	}

	@JsonGetter("wmFirstTimeRunFlag")
	public Integer getWmFirstTimeRunFlag()
	{
		return wmFirstTimeRunFlag.getInteger();
	}

	@JsonSetter("wmFirstTimeRunFlag")
	public void setWmFirstTimeRunFlag(Integer wmFirstTimeRunFlag)
	{
		this.wmFirstTimeRunFlag.setInteger(wmFirstTimeRunFlag);
	}

	@JsonGetter("wmModbusSlaveAddress")
	public Integer getWmModbusSlaveAddress()
	{
		return wmModbusSlaveAddress.getInteger();
	}

	@JsonSetter("wmModbusSlaveAddress")
	public void setWmModbusSlaveAddress(Integer wmModbusSlaveAddress)
	{
		this.wmModbusSlaveAddress.setInteger(wmModbusSlaveAddress);
	}

	@JsonGetter("wmContinuousWaterFlowAlarmAllow")
	public Integer getWmContinuousWaterFlowAlarmAllow()
	{
		return wmContinuousWaterFlowAlarmAllow.getInteger();
	}

	@JsonSetter("wmContinuousWaterFlowAlarmAllow")
	public void setWmContinuousWaterFlowAlarmAllow(Integer wmContinuousWaterFlowAlarmAllow)
	{
		this.wmContinuousWaterFlowAlarmAllow.setInteger(wmContinuousWaterFlowAlarmAllow);
	}

	@JsonGetter("wmContinuousWaterFlowAlarmTimes")
	public Integer getWmContinuousWaterFlowAlarmTimes()
	{
		return wmContinuousWaterFlowAlarmTimes.getInteger();
	}

	@JsonSetter("wmContinuousWaterFlowAlarmTimes")
	public void setWmContinuousWaterFlowAlarmTimes(Integer wmContinuousWaterFlowAlarmTimes)
	{
		this.wmContinuousWaterFlowAlarmTimes.setInteger(wmContinuousWaterFlowAlarmTimes);
	}

	@JsonGetter("wmContinuousWaterFlowAlarmPeriod")
	public Long getWmContinuousWaterFlowAlarmPeriod()
	{
		return wmContinuousWaterFlowAlarmPeriod.getLong();
	}

	@JsonSetter("wmContinuousWaterFlowAlarmPeriod")
	public void setWmContinuousWaterFlowAlarmPeriod(Long wmContinuousWaterFlowAlarmPeriod)
	{
		this.wmContinuousWaterFlowAlarmPeriod.setLong(wmContinuousWaterFlowAlarmPeriod);
	}

	@JsonGetter("wmNoWaterFlowAlarmAllow")
	public Integer getWmNoWaterFlowAlarmAllow()
	{
		return wmNoWaterFlowAlarmAllow.getInteger();
	}

	@JsonSetter("wmNoWaterFlowAlarmAllow")
	public void setWmNoWaterFlowAlarmAllow(Integer wmNoWaterFlowAlarmAllow)
	{
		this.wmNoWaterFlowAlarmAllow.setInteger(wmNoWaterFlowAlarmAllow);
	}

	@JsonGetter("wmNoWaterFlowAlarmTime")
	public Long getWmNoWaterFlowAlarmTime()
	{
		return wmNoWaterFlowAlarmTime.getLong();
	}

	@JsonSetter("wmNoWaterFlowAlarmTime")
	public void setWmNoWaterFlowAlarmTime(Long wmNoWaterFlowAlarmTime)
	{
		this.wmNoWaterFlowAlarmTime.setLong(wmNoWaterFlowAlarmTime);
	}

	@JsonGetter("wmOUTTransistorAlarmAllow")
	public Integer getWmOUTTransistorAlarmAllow()
	{
		return wmOUTTransistorAlarmAllow.getInteger();
	}

	@JsonSetter("wmOUTTransistorAlarmAllow")
	public void setWmOUTTransistorAlarmAllow(Integer wmOUTTransistorAlarmAllow)
	{
		this.wmOUTTransistorAlarmAllow.setInteger(wmOUTTransistorAlarmAllow);
	}

}
