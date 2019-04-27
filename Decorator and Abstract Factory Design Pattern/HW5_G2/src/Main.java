//Yucel Ozdemir
//Cansin Koc
//Tayfun Yonka

public class Main {

	public static void main(String[] args) {
		
		RegularComputerFactory regularComputerFactory = new RegularComputerFactory();
		GamingComputerFactory gamingComputerFactory = new GamingComputerFactory();

		ComputerCreator computerCreator = new ComputerCreator();
		
		IComputer regularComputer = computerCreator.createComputerOn(regularComputerFactory);
		IComputer gamingComputer = computerCreator.createComputerOn(gamingComputerFactory);
		
		System.out.println("Before decorating:");
		System.out.println(regularComputer.toString());
		System.out.println(gamingComputer.toString());
		
		
		//////////////////////////////////////////////////////////////////////////////////////////
		
		
		System.out.println("\nAfter decorating with Gold:");
		IComputer goldCableRegularComputer = new GoldCableComputer(regularComputer);
		IComputer goldCableGamingComputer = new GoldCableComputer(gamingComputer);
		
		goldCableRegularComputer.setCable();
		goldCableGamingComputer.setCable();
		
		System.out.println(regularComputer.toString());
		System.out.println(gamingComputer.toString());
		

		//////////////////////////////////////////////////////////////////////////////////////////
		
		
		System.out.println("\nAfter decorating with Silver:");
		IComputer silverCableRegularComputer = new SilverCableComputer(regularComputer);
		IComputer silverCableGamingComputer = new SilverCableComputer(gamingComputer);
		
		silverCableRegularComputer.setCable();
		silverCableGamingComputer.setCable();
		
		System.out.println(regularComputer.toString());
		System.out.println(gamingComputer.toString());		
		
		
		
	}

}
