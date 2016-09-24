package ch.fhnw.colorpicker.ui;

import java.awt.Color;

import ch.fhnw.colorpicker.model.ColorPickerModel;

public class ColorPickerController {
	private ColorPickerModel model;

	public void initializeView() {
		ColorPickerView colorPickerView = new ColorPickerView(this);
		model.addObserver(colorPickerView);
	}

	public ColorPickerController(ColorPickerModel model) {
		this.model = model;
	}

	public void updateRedValue(int red) {
		model.setRed(red);
	}

	public void updateGreenValue(int green) {
		model.setGreen(green);
	}

	public void updateBlueValue(int blue) {
		model.setBlue(blue);
	}

	public void brightenColor() {
		Color currentColor = getCurrentColor();
		Color brighter = currentColor.brighter();
		model.setRed(brighter.getRed());
		model.setGreen(brighter.getGreen());
		model.setBlue(brighter.getBlue());
	}

	public void darkenColor() {
		Color currentColor = getCurrentColor();
		Color darker = currentColor.darker();
		model.setRed(darker.getRed());
		model.setGreen(darker.getGreen());
		model.setBlue(darker.getBlue());
	}
	
	public boolean canBrighter() {
		Color currentColor = getCurrentColor();
		return !currentColor.equals(currentColor.brighter());
	}
	
	public boolean canDarker() {
		Color currentColor = getCurrentColor();
		return !currentColor.equals(currentColor.darker());
	}

	private Color getCurrentColor() {
		Color currentColor = new Color(model.getRed(), model.getGreen(), model.getBlue());
		return currentColor;
	}

	public void closeApp() {
		System.exit(0);
		}

}
