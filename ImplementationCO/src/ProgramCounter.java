
public class ProgramCounter extends Register{
	
	public ProgramCounter() {
		super("PC", null);
	}
	
	public void increment() {
		int temp = Binary16BitHelper.binary16bitToDecimal(getValue());
		temp += 2;
		setValue(Binary16BitHelper.decimalTo16bits(temp));
	}
	
	public String toString() {
		return String.format("Value in PC: %d = (%s)", Binary16BitHelper.binary16bitToDecimal(getValue()), getValue());
	}
}
