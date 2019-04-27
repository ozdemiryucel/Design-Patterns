
public class Headphone {
	private boolean isWireless;
	private boolean hasMicrophone;
	private int price;
	
	public Headphone(boolean isWireless, boolean hasMicrophone, int price) {
		this.setWireless(isWireless);
		this.setHasMicrophone(hasMicrophone);
		this.setPrice(price);
	}

	public boolean isWireless() {
		return isWireless;
	}

	public void setWireless(boolean isWireless) {
		this.isWireless = isWireless;
	}

	public boolean isHasMicrophone() {
		return hasMicrophone;
	}

	public void setHasMicrophone(boolean hasMicrophone) {
		this.hasMicrophone = hasMicrophone;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Headphone [isWireless=" + isWireless + ", hasMicrophone=" + hasMicrophone + ", price=" + price + "]";
	}
}
