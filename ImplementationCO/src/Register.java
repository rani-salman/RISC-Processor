
public class Register {
	private String name;
	private String binaryRepresentation;
	private String value;
	
	public Register(String name, String binaryRep) {
		setName(name);
		setBinaryRepresentation(binaryRep);
		setValue("0000000000000000");
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBinaryRepresentation() {
		return binaryRepresentation;
	}
	public void setBinaryRepresentation(String binaryRepresentation) {
		this.binaryRepresentation = binaryRepresentation;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public String toString() {
		return String.format("%s: %s=%d", name, value, Binary16BitHelper.binary16bitToDecimal(value));
	}
}