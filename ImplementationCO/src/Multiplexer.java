
public class Multiplexer {
	private String select, in1, in2;
	
	public Multiplexer(String select, String in1, String in2) {
		setSelect(select);
		setIn1(in1);
		setIn2(in2);
	}
	
	public String getSelect() {
		return select;
	}
	public void setSelect(String select) {
		this.select = select;
	}
	public String getIn2() {
		return in2;
	}
	public void setIn2(String in2) {
		this.in2 = in2;
	}
	public String getIn1() {
		return in1;
	}
	public void setIn1(String in1) {
		this.in1 = in1;
	}
	
	public String select() {
		if(select.equals("0"))
			return in1;
		return in2;
	}
}