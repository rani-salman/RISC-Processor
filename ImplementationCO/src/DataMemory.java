import java.util.Arrays;

public class DataMemory {
	static String[] dataMemory = new String[65536];

	public static void initializeMemory() {
		Arrays.fill(dataMemory, "00000000");
	}
	
	public static String loadFromMemory(String address) throws IndexOutOfBoundsException{
		int index = Binary16BitHelper.binary16bitToDecimal(address);
		if (index%2 == 1) {
			try {
				throw new OddNumberedAddressException();
			}
			catch (OddNumberedAddressException e) {
				System.out.println("load error \n*************\n"+e.getMessage()+"\n************\n");
			}
		}
		try {
			return dataMemory[index] + dataMemory[index + 1];
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("\n*************\nindex out of bounds exception, address given is not a valid address in memory\n*************\n");
			return"";
		} 
	}
	public static void saveToMemory(String address, String binaryValue) throws IndexOutOfBoundsException{
		int index = Binary16BitHelper.binary16bitToDecimal(address);
		if (index%2 == 1) {
			try {
				throw new OddNumberedAddressException();
			}
			catch (OddNumberedAddressException e) {
				System.out.println("\n*************\n"+e.getMessage()+"\n************\n");
			}
		}
		else {
			try {
				dataMemory[index] = binaryValue.substring(0, 8);
				dataMemory[index + 1] = binaryValue.substring(8, 16);
			}
			catch(IndexOutOfBoundsException e) {
				System.out.println("\n*************\nindex out of bounds exception, address given is not a valid address in memory\n*************\n");
				return;
			} 
		}
	}
	public static String printReturn() {
		String output = "";
		for (int i=0; i < dataMemory.length-1; i+=2) {
			if(!(dataMemory[i].equals("00000000")) || !(dataMemory[i+1].equals("00000000"))) {
				output += "\n";
				output += String.format("memory[%d] : %s \n", i, dataMemory[i], Binary16BitHelper.binary16bitToDecimal(dataMemory[i]));
				output += String.format("memory[%d] : %s \n", i+1, dataMemory[i+1], Binary16BitHelper.binary16bitToDecimal(dataMemory[i+1]));
				output += String.format("Value = %d\n", (Binary16BitHelper.binary16bitToDecimal(dataMemory[i] + dataMemory[i+1]))) ;
			}
		}
		return output;
	}
	
}
