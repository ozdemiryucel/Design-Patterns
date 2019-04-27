
public enum Cable {
	COPPER (0),
	SILVER (350),
	GOLD (500);
	
	private final int price;
	
	Cable(int price) {
		this.price = price;
	}
	
	public int price() {
		return price;
	}
	
}
