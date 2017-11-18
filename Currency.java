package ac.shenkar.Calc;


//Class that present currency
public class Currency {
	
	private String name;
	private int unit;
	private String code;
	private String country;
	private double rate;
	private double change;
	
	public Currency(String name, int unit, String code, String country, double rate, double change) {
		this.name = name;
		this.unit = unit;
		this.code = code;
		this.country = country;
		this.rate = rate;
		this.change = change;
	}

	
	public int getUnit() {
		return unit;
	}


	public double getChange() {
		return change;
	}


	public String getCode() {
		return code;
	}



	public double getRate() {
		return rate;
	}


}
