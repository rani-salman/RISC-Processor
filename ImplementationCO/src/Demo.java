
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Demo {

	public static void main(String[] args) throws IOException, NumberFormatException, NumberOutOfAllowedRangeException {
		File input = new File("assembly language code");
		File output = new File("machine language code");
		FileWriter fw = new FileWriter(output);
		BufferedWriter bw = new BufferedWriter(fw);
		Scanner scanner = new Scanner(input);
		Scanner pauser = new Scanner(System.in);
		
		Assembler assembler = new Assembler();
		Control control = new Control();
		ProgramCounter pc = new ProgramCounter();
		MicroProcessor processor = new MicroProcessor();
	
		while(scanner.hasNextLine()) {
			try {
				String instruction  = scanner.nextLine();
				String machineCode;
				machineCode = assembler.assemble(instruction);
				String opCode = assembler.getOpCode();
				bw.write(machineCode);
				bw.newLine();
				
				System.out.println("Instruction in assembly: " + instruction);
				System.out.println("Instruction format: " + assembler.getFormat());
				System.out.println("Instruction in machine code: " + machineCode);
				
				System.out.println();
				// for demonstration only ******
				System.out.println("Value of operation code: " + opCode);
				control.setControlSignals(opCode);
				
				System.out.println("Control values:");
				System.out.println(control);
				// ******
				processor.execute(machineCode);
				
				System.out.println(Registers.printReturn());
				pc.increment();
				System.out.println(pc);
				
				System.out.print(DataMemory.printReturn());
				
				pauser.nextLine();
				System.out.println("\n----------------------------------------------------\n");
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("\n----------------------------------------------------\n");
				continue;
			}
		}
		pauser.close();
		bw.close();
		fw.close();
		scanner.close();
	}

}
