package pl.rynekdebnicki.waterMeter;

import com.ghgande.j2mod.modbus.procimg.Register;

public class ModbusString
{
	private Register[] modbusRegs;
	private int startReg, endReg;
	private StringBuilder modbusString = new StringBuilder();

	public ModbusString(Register[] regs, int start, int end)
	{
		modbusRegs = regs;
		startReg = start;
		endReg = end;
	}

	public ModbusString(String string, Register[] regs, int start, int end)
	{
		modbusString.append(string);
		modbusRegs = regs;
		startReg = start;
		endReg = end;
		toRegisters();
	}

	private void toRegisters()
	{
		StringBuilder sb = new StringBuilder();
		byte[] byteRegs = new byte[2];
		int i, sbLength;

		sb.append(modbusString);
		sbLength = sb.length();

		for (i = sbLength; i < ((endReg - startReg)+1) * 2; i++)
		{
			sb.append(" ");
		}

		modbusString.delete(0, modbusString.length());
		modbusString.append(sb.toString());

		for (i = startReg; i <= endReg; i++)
		{
			byteRegs[1] = (byte) modbusString.substring(2 * i, 2 * i + 1).toCharArray()[0];
			byteRegs[0] = (byte) modbusString.substring(2 * i + 1, 2 * i + 2).toCharArray()[0];
			modbusRegs[i].setValue(byteRegs);
		}
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		int i;

		for (i = startReg; i <= endReg; i++)
		{
			sb.append((Character.toChars(modbusRegs[i].toBytes()[1])[0]));
			sb.append((Character.toChars(modbusRegs[i].toBytes()[0])[0]));
		}

		modbusString = sb;
		return modbusString.toString();
	}

	public String getString()
	{
		toString();
		return modbusString.toString();
	}

	public void setString(String string)
	{
		modbusString.delete(0, modbusString.length());
		modbusString.append(string);
		toRegisters();
	}

}
