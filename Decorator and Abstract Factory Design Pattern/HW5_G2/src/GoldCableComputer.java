
public class GoldCableComputer implements IComputer {

	protected IComputer computer;
	
	public GoldCableComputer(IComputer computer) {
		this.computer = computer;
	}

	@Override
	public void setCable() {
		this.computer.setCable(Cable.GOLD);
		
	}

	@Override
	public void setCable(Cable cable) {
		// Do nothing
		this.setCable();
		
	}

}
