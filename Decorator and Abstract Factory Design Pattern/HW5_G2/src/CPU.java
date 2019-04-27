
public class CPU {
	
	private CPUVersion cpuVersion;
	private double frequency;
	private int price;
	
	public CPU(CPUVersion cpuVersion, int price) {
		this.setCpuVersion(cpuVersion);
		this.setFrequency();
		this.setPrice(price);
	}

	public CPUVersion getCpuVersion() {
		return cpuVersion;
	}

	public void setCpuVersion(CPUVersion cpuVersion) {
		this.cpuVersion = cpuVersion;
	}

	public double getFrequency() {
		return frequency;
	}

	public void setFrequency() {
		if (cpuVersion == CPUVersion.i3)
			this.frequency = 2.0;
		else if (cpuVersion == CPUVersion.i5)
			this.frequency = 3.0;
		else if (cpuVersion == CPUVersion.i7)
			this.frequency = 4.0;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "CPU [cpuVersion=" + cpuVersion + ", frequency=" + frequency + ", price=" + price + "]";
	}
	
	
}
