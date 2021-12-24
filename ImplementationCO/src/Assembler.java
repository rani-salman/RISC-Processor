
public class Assembler {
	private String opCode;
	private char format;
	
	public char getFormat() {
		return format;
	}
	public void setFormat(char format) {
		this.format = format;
	}
	public String getOpCode() {
		return opCode;
	}
	public void setOpCode(String opCode) {
		this.opCode = opCode; 
	}
	
	// main assembler function
	public String assemble(String expression) throws NumberOutOfAllowedRangeException{
		String machineCode = ""; // our return
		String[] opCode__RegistersOrConstants = expression.split(" "); // separating opcode from registers and constant
		// getting opCode and adding to machineCod
		setOpCode(opCodeToBinary(opCode__RegistersOrConstants[0])); 
		machineCode += opCode;
		// parsing registers and constant in order to assemble according to the different formats
		String registersandConstantsString = opCode__RegistersOrConstants[1];
		String[] registersandConstants = registersandConstantsString.split(",");
		
		if(opCode.charAt(0) == '0') { // Z format which includes: add/sub immediate, load, store
			setFormat('Z');
			machineCode += regToBinary(registersandConstants[0]);
			machineCode += regToBinary(registersandConstants[1]);
			if (opCode.charAt(1) == '0') { // convert unsigned constant to binary and, add it to machine code (in case of add/sub immediate instructions) 
				int constant = Integer.parseInt(registersandConstants[2]);
				machineCode += extendTo7(constant);
			}
			if (opCode.charAt(1) == '1') // add 7 unused bits to complete 16 bit instruction (in case of load/store instruction)
				machineCode += "0000000";
		}
		if (opCode.substring(0, 2).equals("10")) { // A format which includes: addition subtraction
			setFormat('A');
			machineCode += regToBinary(registersandConstants[0]);
			machineCode += regToBinary(registersandConstants[1]);
			machineCode += regToBinary(registersandConstants[2]);
			machineCode += "0000"; //add last 4 unused bits to complete 16 bit instruction
		}
		if(opCode.equals("110")) { // L format which includes only the load immediate instruction
			setFormat('L');
			machineCode += regToBinary(registersandConstants[0]);
			int constant = Integer.valueOf(registersandConstants[1]);
			// constant in case of load immediate is signed
			if(constant >= 0) //converting positive constant to binary then, add it to machine code 
				machineCode += extendTo10(constant);
			else  // converting negative constant to binary then, add it to machine code
				machineCode += convertNegativeNumberto10(constant);
		}
		return machineCode;
	}
	
	private String opCodeToBinary(String instruction) {
		switch(instruction) {
			case "i+":
				return "000";
			case "i-":
				return "001";
			case "ld":
				return "010";
			case "str":
				return "011";
			case "+":
				return "100";
			case "-":
				return "101";
			case "ild":
				return "110";
			default:
				return "Instruction not found";
		}
	}
	
	//helper functions
	private String regToBinary(String name) {
		for(Register reg : Registers.registers)
			if(name.equals(reg.getName()))
				return reg.getBinaryRepresentation();
		return "";
	}

	private String extendTo7(int constant) throws NumberOutOfAllowedRangeException { // function to extend unsigned constant to 7 bits in A format
		if (!(0 <= constant && constant <= 127)) { // number smaller than 0 or bigger than 127, this means it is out of the supported range 
			System.out.printf( "(%d) ", constant);
			throw new NumberOutOfAllowedRangeException();
		}
		String binaryStr = Integer.toBinaryString(constant);
		String extraZeros = "";
		int numberOfZerosNeeded = 7 - binaryStr.length();
		for (int i=0; i<numberOfZerosNeeded; i++)
			extraZeros += "0";
		return extraZeros + binaryStr;
		
	}
	private String extendTo10(int constant) throws NumberOutOfAllowedRangeException{ // function to extend signed constant to 10 bits in L format
		if (!(constant <= 511)) { // number bigger than 512, this means it is out of the supported range
			System.out.printf( "(%d) ", constant);
			throw new NumberOutOfAllowedRangeException();
		}
		String binaryStr = Integer.toBinaryString(constant);
		String extraZeros = "";
		int numberOfZerosNeeded = 10 - binaryStr.length();
		for (int i=0; i<numberOfZerosNeeded; i++)
			extraZeros += "0";
		return extraZeros + binaryStr;
	}
	private String convertNegativeNumberto10(int constant) throws NumberOutOfAllowedRangeException {
		if (!(-512 <= constant)) { // number smaller than -512, this means it is out of the supported range
			System.out.printf( "(%d) ", constant);
			throw new NumberOutOfAllowedRangeException();
		}
		String signed32bitString = Integer.toBinaryString(constant); // number is now in a signed 32 bit binary string
		return signed32bitString.substring(22, signed32bitString.length()); //eliminate the extra 1s to make it a 10 bit string
	}
}