package trash;

import com.ghgande.j2mod.modbus.procimg.InputRegister;
import com.ghgande.j2mod.modbus.procimg.Register;

public class ModbusIntegerInputRegister implements ModbusIntegerInt
{
	private InputRegister[] modbusRegs;
	private int startReg;
	private Integer modbusInteger;

	public ModbusIntegerInputRegister(InputRegister[] modbusRegs, int startReg)
	{
		this.modbusRegs = modbusRegs;
		this.startReg = startReg;
		toModbusInteger();
	}



	public ModbusIntegerInputRegister()
	{

	}


	private void toModbusInteger()
	{
		modbusInteger = modbusRegs[startReg].getValue();
	}

	public int getInteger()
	{
		return modbusInteger.intValue();
	}


	public String toString()
	{
		return modbusInteger.toString();
	}
}


