
public class Keyboard {
	
	private int numberOfKeys;
	private boolean hasBacklight;
	private int price;
	
	public Keyboard(int numberOfKeys, boolean hasBacklight, int price) {
		this.setNumberOfKeys(numberOfKeys);
		this.setHasBacklight(hasBacklight);
		this.setPrice(price);
	}

	public int getNumberOfKeys() {
		return numberOfKeys;
	}

	public void setNumberOfKeys(int numberOfKeys) {
		if (84 <= numberOfKeys && numberOfKeys <= 108)
			this.numberOfKeys = numberOfKeys;
	}

	public boolean isHasBacklight() {
		return hasBacklight;
	}

	public void setHasBacklight(boolean hasBacklight) {
		this.hasBacklight = hasBacklight;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Keyboard [numberOfKeys=" + numberOfKeys + ", hasBacklight=" + hasBacklight + ", price=" + price + "]";
	}

}
