package ch.fhnw.colorpicker.util;

public interface Observable {
	void addObserver(Observer o);
	void removeObserver(Observer o);
	void notifyObservers();
}
