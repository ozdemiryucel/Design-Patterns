
public class Computer implements IComputer {
	
	private Keyboard keyboard;
	private Mouse mouse;
	private Monitor monitor;
	private Headphone headphone;
	private CPU cpu;
	private RAM ram;
	private int price;
	private Cable cable;
	
	public Computer(Keyboard keyboard, Mouse mouse, Monitor monitor,
			Headphone headphone, CPU cpu, RAM ram) {
		this.keyboard = keyboard;
		this.mouse = mouse;
		this.monitor = monitor;
		this.headphone = headphone;
		this.cpu = cpu;
		this.ram = ram;
		this.cable = Cable.COPPER;
		this.price = getPrice();
	}
		

	public int getPrice() {
		
		this.setPrice();
		
		return this.price;
	}
	

	private void setPrice() {
		this.price = keyboard.getPrice() + mouse.getPrice() + monitor.getPrice()
				+ headphone.getPrice() + cpu.getPrice() + ram.getPrice()
				+ this.cable.price();
	}

	@Override
	public void setCable(Cable cable) {
		this.cable = cable;
	}
	
	@Override
	public void setCable() {
		// Do nothing
	}

	@Override
	public String toString() {
		return "Computer [keyboard=" + keyboard + ", mouse=" + mouse + ", monitor=" + monitor 
				+ ", headphone=" + headphone + ", cpu=" + cpu + ", ram=" + ram 
				+ ", price=" + getPrice() + ", cable=" + this.cable;
	}

}
