
public class MicroProcessor {
	public Control control;
	public ALU alu;
	
	public MicroProcessor() {
		alu = new ALU();
		control = new Control();
		DataMemory.initializeMemory();
	}

	public void execute(String machineCode) {
		String opCode = machineCode.substring(0, 3);
		control.setControlSignals(opCode);
		
		String writeRegisterReference = machineCode.substring(3,6);
		
		String readRegister1Reference = machineCode.substring(6,9);
		
		//code to know what bits will reference the read register 2
		Multiplexer M1 = new Multiplexer(control.getRegDst(), machineCode.substring(9,12), machineCode.substring(3,6)); 
		String readRegister2Reference = M1.select();
		
		String constant7Bits = machineCode.substring(9, 16);
		String constants10Bits = machineCode.substring(6, 16);
		
		//set up to know what will be the 2nd input of ALU
		String valueOfReadRegister2 = Registers.readFromRegister(readRegister2Reference);
		String extend7BitConstant = Binary16BitHelper.extendfrom7To16(constant7Bits);
		Multiplexer M2 = new Multiplexer(control.getALUSource(), valueOfReadRegister2, extend7BitConstant);
		String secondAluInput = M2.select();
	
		//ALU operation and saving its result temporarily
		String valueOfReadRegister1 = Registers.readFromRegister(readRegister1Reference);
		String aluResult = alu.compute(valueOfReadRegister1, secondAluInput, control.getALUOp());
	
		//setup for load store instructions
		String addressInMemory = valueOfReadRegister1;
		
		//store code
		String valueToBeStored = valueOfReadRegister2;
		if(control.getMemoryWrite() == "1")
			DataMemory.saveToMemory(addressInMemory, valueToBeStored);
		
		//load value from memory and save it temporarily
		String loadedValue = null;
		if(control.getMemoryRead() == "1")
			loadedValue = DataMemory.loadFromMemory(addressInMemory);
		
		//setup for a multiplexer which helps us know what value we will write to memory 
		Multiplexer M3 = new Multiplexer(control.getMemoryToRegister(), loadedValue, aluResult);
		String mux3Output = M3.select();
		
		//final multiplexer that selects which value will be written to memory
		String extend10BitConstant = Binary16BitHelper.extendfrom10To16(constants10Bits);
		Multiplexer M4 = new Multiplexer(control.getWriteSource(), extend10BitConstant, mux3Output);
		String writeValue = M4.select();
		
		//writing writeValue to write Register
		if (control.getRegWrite() == "1") 
			Registers.writeToRegister(writeValue, writeRegisterReference);
	}
	
}
//****notes to help build logic of processor*****
//***********************************************
//1
//MUX1 controls input of read register2:
//�	Value 0: takes value from read register2
//�	Value 1: takes value from supposed write register
//�	Name of signal: RegDst
//2
//MUX2 controls 2nd input of ALU:
//�	Value 0: takes input from 2nd read register
//�	Value 1: takes input from 7bit constant
//�	Name of signal: AluSource
//3
//ALUOp signal: parameter for ALU component
//�	Value 1: add operation
//�	Value 0: subtraction operation
//�	Name of signal: write source
//4
//MemoryWrite signal: 
//�	Value 1 result will be written to memory, store
//�	Value 0 result will be written to write register
//5
//MemoryRead signal:
//�	Value 1 load from memory the write value
//�	Value 0 in case of store
//�	Value X in case of arithmetic instruction +, -, -i, +i
//6
//MUX3 gets result from either memory or ALU
//�	Value 0: takes input from memory load
//�	Value 1: takes input from result of the computation of the ALU
//�	Name of signal: Memory to register
//7
//MUX4 gets result either from 10 bit constant or MUX3
//�	Value 0: takes input from 10bit constant
//�	Value 1: takes input from MUX3
//�	Name of signal: Write Source
//8
//RegWrite signal: 
//�	Value 1 write the result to the write register
//�	Value 0 result would be written in memory, store