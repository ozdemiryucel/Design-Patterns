import java.util.Random;

public class RegularComputerFactory extends AComputerFactory {
	
	private Random randomNum = new Random();
	
	@Override
	public Keyboard makeKeyboard() {
		int numberOfKeys = getRandomNumberBetween(84, 108);
		int price = getRandomNumberBetween(80, 450);
		return new Keyboard(numberOfKeys, false, price);
	}

	@Override
	public Mouse makeMouse() {
		int price = getRandomNumberBetween(27, 78);
		return new Mouse(true, price);
	}

	@Override
	public Monitor makeMonitor() {
		int price = getRandomNumberBetween(900, 1500);
		if (Math.random() < 0.5)
			return new Monitor(18, price);
		else
			return new Monitor(21, price);
	}

	@Override
	public Headphone makeHeadphone() {
		int price = getRandomNumberBetween(40, 400);
		return new Headphone(false, false, price);
	}

	@Override
	public CPU makeCPU() {
		int price = getRandomNumberBetween(8000, 12000);
		
		if (Math.random() < 0.5)
			return new CPU(CPUVersion.i3, price);
		else
			return new CPU(CPUVersion.i5, price);
	}

	@Override
	public RAM makeRAM() {
		int price = getRandomNumberBetween(1500, 3500);
		
		double rnd = Math.random();
		if (rnd < 0.33)
			return new RAM(32, price);
		else if (rnd < 0.66)
			return new RAM(64, price);
		else
			return new RAM(128, price);
	}
	
	private int getRandomNumberBetween(int min, int max) {
		return randomNum.nextInt(max + 1 - min) + min;
	}

}
