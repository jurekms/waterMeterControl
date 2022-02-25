package pl.rynekdebnicki.waterMeter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ghgande.j2mod.modbus.procimg.AbstractRegister;
import com.ghgande.j2mod.modbus.procimg.DefaultProcessImageFactory;
import com.ghgande.j2mod.modbus.procimg.InputRegister;

public class WaterMeterCounter
{
	private InputRegister[] wmCounterRegisters;

	private ModbusLong wmCurrentCounter;
	private ModbusLong wmPulseCounter;
	private ModbusInteger wmContinuousWaterFlowAlarmFlag;
	private ModbusInteger wmNoWaterFlowAlarmFlag;

	@JsonCreator
	public WaterMeterCounter()
	{
		this.wmCounterRegisters = new AbstractRegister[6];

		DefaultProcessImageFactory dpiFactory = new DefaultProcessImageFactory();

		for (int i = 0; i < 6; i++)
		{
			this.wmCounterRegisters[i] = dpiFactory.createRegister(0);
		}
		WaterMeterInitCounter();
	}

	public WaterMeterCounter(InputRegister[] wmCounterRegisters)
	{
		this.wmCounterRegisters = wmCounterRegisters;
		WaterMeterInitCounter();
	}

	private void WaterMeterInitCounter()
	{
		this.wmCurrentCounter = new ModbusLong(this.wmCounterRegisters, 0);
		this.wmPulseCounter = new ModbusLong(this.wmCounterRegisters, 2);
		this.wmContinuousWaterFlowAlarmFlag = new ModbusInteger(this.wmCounterRegisters, 4);
		this.wmNoWaterFlowAlarmFlag = new ModbusInteger(this.wmCounterRegisters, 5);
	}

	@JsonIgnore
	public InputRegister[] getWmCounterRegisters()
	{
		return wmCounterRegisters;
	}

	@JsonGetter
	public Long getWmCurrentCounter()
	{
		return wmCurrentCounter.getLong();
	}

	@JsonGetter
	public Long getWmPulseCounter()
	{
		return wmPulseCounter.getLong();
	}

	@JsonGetter
	public Integer getWmContinuousWaterFlowAlarmFlag()
	{
		return wmContinuousWaterFlowAlarmFlag.getInteger();
	}

	@JsonGetter
	public Integer getWmNoWaterFlowAlarmFlag()
	{
		return wmNoWaterFlowAlarmFlag.getInteger();
	}

}
