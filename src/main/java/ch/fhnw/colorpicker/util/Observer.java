package ch.fhnw.colorpicker.util;

import ch.fhnw.colorpicker.model.ColorPickerModel;

public interface Observer {
	void update(ColorPickerModel colorModel);
}
