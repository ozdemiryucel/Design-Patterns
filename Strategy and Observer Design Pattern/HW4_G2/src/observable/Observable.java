package observable;

import observer.*;
import domain.Process;

public interface Observable {
//	public void addObserver(Observer observer);
//	public void removeObserver(Observer observer);

	public void notifyObserver(Observer observer, Process process);

}
