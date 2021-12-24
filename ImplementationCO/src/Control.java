
public class Control {
	private String ALUOp;
	private String MemoryWrite;
	private String ALUSource;
	private String RegWrite;
	private String RegDst;
	private String MemoryRead;
	private String MemoryToRegister;
	private String WriteSource;
	
	// control main function
	public void setControlSignals(String opCode) {
		if(opCode.substring(0, 2).equals("00")) { //immediate addition/subtraction
			setMemoryWrite("0");
			setALUSource("1");
			setRegWrite("1");
			setRegDst("X");
			setMemoryRead("X");
			setMemoryToRegister("1");
			setWriteSource("1");
			if(opCode.charAt(2) == '0')
				setALUOp("1");
			if(opCode.charAt(2) == '1')
				setALUOp("0");
		}
		if(opCode.equals("010")) { // load
			setALUOp("X");
			setMemoryWrite("0");
			setALUSource("X");
			setRegWrite("1");
			setRegDst("X");
			setMemoryRead("1");
			setMemoryToRegister("0");
			setWriteSource("1");
		}
		if(opCode.equals("011")) { // store
			setALUOp("X");
			setMemoryWrite("1");
			setALUSource("X");
			setRegWrite("0");
			setRegDst("1");
			setMemoryRead("0");
			setMemoryToRegister("X");
			setWriteSource("X");
		}
		if(opCode.substring(0, 2).equals("10")) { //addition subtraction
			setMemoryWrite("0");
			setALUSource("0");
			setRegWrite("1");
			setRegDst("0");
			setMemoryRead("X");
			setMemoryToRegister("1");
			setWriteSource("1");
			if(opCode.charAt(2) == '0')
				setALUOp("1");
			if(opCode.charAt(2) == '1')
				setALUOp("0");
		}
		if(opCode.equals("110")) { // load immediate
			setALUOp("X");
			setMemoryWrite("0");
			setALUSource("X");
			setRegWrite("1");
			setRegDst("X");
			setMemoryRead("0");
			setMemoryToRegister("X");
			setWriteSource("0");
		}
	}
	public String getALUOp() {
		return ALUOp;
	}
	public void setALUOp(String aLUOp) {
		ALUOp = aLUOp;
	}
	public String getMemoryWrite() {
		return MemoryWrite;
	}
	public void setMemoryWrite(String memoryWrite) {
		MemoryWrite = memoryWrite;
	}
	public String getALUSource() {
		return ALUSource;
	}
	public void setALUSource(String aLUSource) {
		ALUSource = aLUSource;
	}
	public String getRegWrite() {
		return RegWrite;
	}
	public void setRegWrite(String regWrite) {
		RegWrite = regWrite;
	}
	public String getRegDst() {
		return RegDst;
	}
	public void setRegDst(String regDst) {
		RegDst = regDst;
	}
	public String getMemoryRead() {
		return MemoryRead;
	}
	public void setMemoryRead(String memoryRead) {
		MemoryRead = memoryRead;
	}
	public String getMemoryToRegister() {
		return MemoryToRegister;
	}
	public void setMemoryToRegister(String memoryToRegister) {
		MemoryToRegister = memoryToRegister;
	}
	public String getWriteSource() {
		return WriteSource;
	}
	public void setWriteSource(String writeSource) {
		WriteSource = writeSource;
	}
	
	public String toString() {
		return ("ALUOp: "+ALUOp 
			+"\nMemoryWrite: "+MemoryWrite
			+"\nALUSource: "+ALUSource
			+"\nRegWrite: "+RegWrite
			+"\nRegDst: "+RegDst
			+"\nMemoryRead: "+MemoryRead
			+"\nMemoryToRegister: "+MemoryToRegister
			+"\nWriteSource: "+WriteSource);
	}
}
