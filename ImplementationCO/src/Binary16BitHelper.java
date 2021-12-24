
public class Binary16BitHelper {
	
	//used for unsigned extension for immediate arithmetic instructions
	public static String extendfrom7To16(String binaryStr) {
		String extraZeros = "";
		int numberOfZerosNeeded = 16 - binaryStr.length();
		for (int i=0; i<numberOfZerosNeeded; i++)
			extraZeros += "0";
		return extraZeros + binaryStr;
	}
	//used for signed extension for load immediate instruction
	public static String extendfrom10To16(String binaryStr) {
		if(binaryStr.charAt(0) == '0') {
			String extraZeros = "";
			int numberOfZerosNeeded = 16 - binaryStr.length();
			for (int i=0; i<numberOfZerosNeeded; i++)
				extraZeros += "0";
			return extraZeros + binaryStr;
		}
		else {
			String extraOnes = "";
			int numberOfZerosNeeded = 16 - binaryStr.length();
			for (int i=0; i<numberOfZerosNeeded; i++)
				extraOnes += "1";
			return extraOnes + binaryStr;
		}
			
	}
	
	public static String decimalTo16bits(int number) {
		if (number >= 0) {
			String binaryStr = Integer.toBinaryString(number);
			String extraZeros = "";
			int numberOfZerosNeeded = 16 - binaryStr.length();
			for (int i=0; i<numberOfZerosNeeded; i++)
				extraZeros += "0";
			return extraZeros + binaryStr;
		}
		else {
			String signed32bitString = Integer.toBinaryString(number); // number is now in a signed 32 bit binary string
			return signed32bitString.substring(16, signed32bitString.length());
		}
	}
	
	public static int binary16bitToDecimal(String binary16bit) {
		if (binary16bit.charAt(0) == '0') 
			return Integer.parseInt(binary16bit, 2);
		else {
			String invertedInt = invertDigits(binary16bit);
			int decimalValue = Integer.parseInt(invertedInt, 2);
			decimalValue = (decimalValue + 1) * -1;
			return decimalValue;
		}	
	}
	//helper for above function
	private static String invertDigits(String binaryInt) {
	    String result = binaryInt;
	    result = result.replace("0", " "); //temp replace 0s
	    result = result.replace("1", "0"); //replace 1s with 0s
	    result = result.replace(" ", "1"); //put the 1s back in
	    return result;
	}
}
