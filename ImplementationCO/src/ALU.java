
public class ALU {
	
	public String compute(String value1, String value2, String ALUop) {
		if(ALUop == "1") {
			int sum = Binary16BitHelper.binary16bitToDecimal(value1) + Binary16BitHelper.binary16bitToDecimal(value2);
	        return Binary16BitHelper.decimalTo16bits(sum);
	    }
	    else {
	    	int dif = Binary16BitHelper.binary16bitToDecimal(value1) - Binary16BitHelper.binary16bitToDecimal(value2);
	        return Binary16BitHelper.decimalTo16bits(dif);
	    }
	}
	
}