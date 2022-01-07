package ePlattform.Client.domainObjects;

public class Gutschein {
	

	private int gutscheinId;
	private int code;
	private double wert;
	
	
	public Gutschein(int code, double wert) {
		super();
		this.code = code;
		this.wert = wert;
	}
	public int getGutscheinId() {
		return gutscheinId;
	}
	public void setGutscheinId(int gutscheinId) {
		this.gutscheinId = gutscheinId;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public double getWert() {
		return wert;
	}
	public void setWert(double wert) {
		this.wert = wert;
	}
	
}
