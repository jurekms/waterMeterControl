package pl.rynekdebnicki.waterMeter;

import com.ghgande.j2mod.modbus.procimg.InputRegister;
import com.ghgande.j2mod.modbus.procimg.Register;

public class ModbusLong
{
	private InputRegister[] modbusRegs;
	private int startReg;
	private Long modbusLongInteger;

	public ModbusLong(InputRegister[] modbusRegs, int startReg)
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

	private void toModbusRegisters()
	{
		((Register) modbusRegs[startReg + 1]).setValue((int) (modbusLongInteger / 65536));
		((Register) modbusRegs[startReg]).setValue((int) (modbusLongInteger % 65536));
	}

	@Override
	public String toString()
	{
		return modbusLongInteger.toString();
	}

	public long getLong()
	{
		return modbusLongInteger.longValue();
	}

	public void setLong(long modbusLongInteger)
	{
		this.modbusLongInteger = modbusLongInteger;
		toModbusRegisters();
	}

}
