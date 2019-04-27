
public class SilverCableComputer implements IComputer {

	protected IComputer computer;
	
	public SilverCableComputer(IComputer computer) {
		this.computer = computer;
	}

	@Override
	public void setCable() {
		this.computer.setCable(Cable.SILVER);
		
	}

	@Override
	public void setCable(Cable cable) {
		// Do nothing
		this.setCable();
		
	}
	
}
