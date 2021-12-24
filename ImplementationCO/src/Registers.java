
public class Registers {
	static Register R0 = new Register("#R0", "000");
	static Register R1 = new Register("#R1", "001");
	static Register R2 = new Register("#R2", "010");
	static Register R3 = new Register("#R3", "011");
	static Register R4 = new Register("#R4", "100");
	static Register R5 = new Register("#R5", "101");
	static Register R6 = new Register("#R6", "110");
	static Register R7 = new Register("#R7", "111");
	
	static Register[] registers = {R0, R1, R2, R3, R4, R5, R6, R7};
	
	public static void writeToRegister(String value, String binaryRepresentation) {
		for(Register register : Registers.registers)
			if(binaryRepresentation.equals(register.getBinaryRepresentation()))
				register.setValue(value);
	}
	public static String readFromRegister(String binaryRepresentation) {
		for(Register register : Registers.registers)
			if(binaryRepresentation.equals(register.getBinaryRepresentation()))
				return register.getValue();
		return null;
	}
	public static String printReturn() {
		String output = "";
		for (Register reg : registers) {
			String line = String.format("Value stored in register %s: %s = %d\n", reg.getName(), reg.getValue(), Binary16BitHelper.binary16bitToDecimal(reg.getValue()));
			output += line;
		}
		return output;
	}
}
