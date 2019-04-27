import java.util.Random;

public class GamingComputerFactory extends AComputerFactory {
	
	private Random randomNum = new Random();
	
	@Override
	public Keyboard makeKeyboard() {
		int numberOfKeys = getRandomNumberBetween(84, 108);
		int price = getRandomNumberBetween(500, 1500);
		return new Keyboard(numberOfKeys, true, price);
	}

	@Override
	public Mouse makeMouse() {
		int price = getRandomNumberBetween(300, 850);
		return new Mouse(false, price);
	}

	@Override
	public Monitor makeMonitor() {
		int price = getRandomNumberBetween(2500, 3500);
		double rnd = Math.random();
		
		if (rnd < 0.25)
			return new Monitor(18, price);
		else if (rnd < 0.5)
			return new Monitor(21, price);
		else if (rnd < 0.75)
			return new Monitor(25, price);
		else
			return new Monitor(27, price);
	}

	@Override
	public Headphone makeHeadphone() {
		int price = getRandomNumberBetween(600, 1900);
		return new Headphone(true, true, price);
	}

	@Override
	public CPU makeCPU() {
		int price = getRandomNumberBetween(14000, 24000);
		
		if (Math.random() < 0.5)
			return new CPU(CPUVersion.i5, price);
		else
			return new CPU(CPUVersion.i7, price);
	}

	@Override
	public RAM makeRAM() {
		int price = getRandomNumberBetween(7000, 19000);
		
		if (Math.random() < 0.5)
			return new RAM(128, price);
		else
			return new RAM(256, price);
	}
	
	private int getRandomNumberBetween(int min, int max) {
		return randomNum.nextInt(max + 1 - min) + min;
	}
	
	
}
