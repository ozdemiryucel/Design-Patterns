
public class Mouse {
	
	private boolean isWireless;
	private int price;
	
	public Mouse(boolean isWireless, int price) {
		this.setWireless(isWireless);
		this.setPrice(price);
	}

	public boolean isWireless() {
		return isWireless;
	}

	public void setWireless(boolean isWireless) {
		this.isWireless = isWireless;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Mouse [isWireless=" + isWireless + ", price=" + price + "]";
	}

}
