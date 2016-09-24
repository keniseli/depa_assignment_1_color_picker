package ch.fhnw.colorpicker;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import ch.fhnw.colorpicker.model.ColorPickerModel;
import ch.fhnw.colorpicker.ui.ColorPickerController;

public class App {
	public static void main(String[] args) {
		setSystemLookAndFeel();
		ColorPickerModel colorPickerModel = new ColorPickerModel();
		ColorPickerController colorPickerController = new ColorPickerController(colorPickerModel);
		SwingUtilities.invokeLater(colorPickerController::initializeView);
	}

	public static void setSystemLookAndFeel() {
		try {
			String LookAndFeel = UIManager.getSystemLookAndFeelClassName();
			UIManager.setLookAndFeel(LookAndFeel);
		} catch (Exception e) {
		}
	}
}
