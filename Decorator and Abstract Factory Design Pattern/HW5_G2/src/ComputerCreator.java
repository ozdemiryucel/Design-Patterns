
public class ComputerCreator {
	
	public Computer createComputerOn(AComputerFactory factory) {
		
		Keyboard keyboard = factory.makeKeyboard();
		Mouse mouse = factory.makeMouse();
		Monitor monitor = factory.makeMonitor();
		Headphone headphone = factory.makeHeadphone();
		CPU cpu = factory.makeCPU();
		RAM ram = factory.makeRAM();
		
		
		return new Computer(keyboard, mouse, monitor, headphone, cpu, ram);
	}

}
