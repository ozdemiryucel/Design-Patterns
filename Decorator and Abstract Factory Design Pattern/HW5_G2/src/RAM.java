
public class RAM {
	private int capacity;
	private int price;
	
	public RAM(int capacity, int price) {
		this.setCapacity(capacity);
		this.setPrice(price);
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		if (capacity == 32 || capacity == 64 ||capacity == 128 || capacity == 256)
			this.capacity = capacity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "RAM [capacity=" + capacity + ", price=" + price + "]";
	}
}
