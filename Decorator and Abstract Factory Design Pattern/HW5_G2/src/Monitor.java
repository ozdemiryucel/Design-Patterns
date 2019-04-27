
public class Monitor {
	
	private int inch;
	private int price;
	
	public Monitor(int inch, int price) {
		this.setInch(inch);
		this.setPrice(price);
	}

	public int getInch() {
		return inch;
	}

	public void setInch(int inch) {
		if (inch == 18 || inch == 21 || inch == 25 || inch == 27)
			this.inch = inch;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Monitor [inch=" + inch + ", price=" + price + "]";
	}
}
