package trash;

import com.ghgande.j2mod.modbus.procimg.Register;

public class ModbusConverter
{

	public static String RegisterToString(Register[] modbusRegs, int startReg, int endReg)
	{
		StringBuilder sb = new StringBuilder();
		int i;

		for (i = startReg; i < endReg; i++)
		{
			sb.append((Character.toChars(modbusRegs[i].toBytes()[1])[0]));
			sb.append((Character.toChars(modbusRegs[i].toBytes()[0])[0]));
		}
		return sb.toString();
	}

	public static void StringToRegister(String modbusString, Register[] modbusRegs, int startReg, int endReg)
	{

		StringBuilder sb = new StringBuilder();
		byte[] byteRegs = new byte[2];
		int i, sbLength;

		sb.append(modbusString);
		sbLength = sb.length();

		for (i = sbLength; i < (endReg - startReg) * 2; i++)
		{
			sb.append(" ");
		}

		for (i = startReg; i < endReg; i++)
		{
			byteRegs[1] = (byte) sb.substring(2 * i, 2 * i + 1).toCharArray()[0];
			byteRegs[0] = (byte) sb.substring(2 * i + 1, 2 * i + 2).toCharArray()[0];
			modbusRegs[i].setValue(byteRegs);
		}
	}

	public static void IntegerToRegister(Integer modbusInteger, Register[] modbusRegs, int startReg)
	{
		modbusRegs[startReg].setValue(modbusInteger);
	}

	public static Integer RegisterToInteger(Register[] modbusRegs, int startReg)
	{
		return modbusRegs[startReg].getValue();
	}

	public static void LongToRegister(Long modbusLong, Register[] modbusRegs, int startReg)
	{
		modbusRegs[startReg + 1].setValue((int) (modbusLong / 65356));
		modbusRegs[startReg].setValue((int) (modbusLong % 2));
	}

	public static Long RegisterToLong(Register[] modbusRegs, int startReg)
	{
		return (long) (modbusRegs[startReg + 1].getValue()) * 65536 + (long) modbusRegs[startReg].getValue();
	}

}
