package pl.rynekdebnicki.waterMeter;

import com.ghgande.j2mod.modbus.procimg.InputRegister;
import com.ghgande.j2mod.modbus.procimg.Register;

public class ModbusInteger
{
	private InputRegister[] modbusRegs;
	private int startReg;
	private Integer modbusInteger;

	public ModbusInteger(InputRegister[] modbusRegs, int startReg)
	{
		this.modbusRegs = modbusRegs;
		this.startReg = startReg;
		toModbusInteger();
	}

	public ModbusInteger()
	{

	}

	private void toModbusRegisters()
	{
		((Register) modbusRegs[startReg]).setValue(modbusInteger);
	}

	private void toModbusInteger()
	{
		modbusInteger = modbusRegs[startReg].getValue();
	}

	public int getInteger()
	{
		return modbusInteger.intValue();
	}

	public void setInteger(int modbusInteger)
	{
		this.modbusInteger = modbusInteger;
		toModbusRegisters();
	}

	public String toString()
	{
		return modbusInteger.toString();
	}

}
