package ch.fhnw.colorpicker.model;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.colorpicker.util.Observable;
import ch.fhnw.colorpicker.util.Observer;

public class ColorPickerModel implements Observable {
	private int red;
	private int green;
	private int blue;
	private List<Observer> observers;

	public ColorPickerModel() {
		red = 0;
		green = 0;
		blue = 0;
		observers = new ArrayList<>();
	}

	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		observers.forEach(o -> o.update(this));
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
		notifyObservers();
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
		notifyObservers();
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
		notifyObservers();
	}

}
