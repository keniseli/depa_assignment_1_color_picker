package ch.fhnw.colorpicker.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.function.Consumer;

import javax.swing.JTextField;

public class ColorPickerTextFieldChangedListener implements KeyListener {

	private JTextField textField;
	private Consumer<Integer> action;

	public ColorPickerTextFieldChangedListener(Consumer<Integer> action, JTextField textField) {
		this.textField = textField;
		this.action = action;
	}

	@Override
	public void keyReleased(KeyEvent event) {
		String text = textField.getText();
		try {
		int value = Integer.valueOf(text);
		action.accept(value);
		} catch(NumberFormatException exception) {
			// do nothing
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

}
