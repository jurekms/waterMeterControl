package trash;

import com.ghgande.j2mod.modbus.procimg.InputRegister;
import com.ghgande.j2mod.modbus.procimg.Register;

public class ModbusLongInputRegister implements ModbusLongInt
{
	private InputRegister[] modbusRegs;
	private int startReg;
	private Long modbusLongInteger;

	public ModbusLongInputRegister(InputRegister[] modbusRegs, int startReg)
	{
		this.modbusRegs = modbusRegs;
		this.startReg = startReg;
		toModbusLong();
	}

	private void toModbusLong()
	{
		modbusLongInteger = (long) (modbusRegs[startReg + 1].getValue()) * 65536
				+ (long) modbusRegs[startReg].getValue();

	}

	@Override
	public String toString()
	{
		return modbusLongInteger.toString();
	}

	@Override
	public long getLong()
	{
		return modbusLongInteger.longValue();
	}

}
