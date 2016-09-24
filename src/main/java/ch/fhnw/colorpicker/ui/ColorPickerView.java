package ch.fhnw.colorpicker.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;

import static java.awt.Color.*;

import ch.fhnw.colorpicker.model.ColorPickerModel;
import ch.fhnw.colorpicker.util.Observer;

public class ColorPickerView extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;

	private static final String COLOR_PICKER_VIEW_TITLE = "Color Picker";
	private static final Dimension PREFERRED_SIZE = new Dimension(400, 400);

	private static final Dimension TEXT_FIELD_SIZE = new Dimension(50, 25);
	private static final int ZERO = 0;
	private static final int TWO_HUNDRED_FIFTY_FIVE = 255;

	private ColorPickerController controller;
	private JPanel previewPanel;

	private JSlider redSlider;
	private JTextField redValueTextField;
	private JTextField redHexValueTextField;
	private JSlider greenSlider;
	private JTextField greenValueTextField;
	private JTextField greenHexValueTextField;
	private JSlider blueSlider;
	private JTextField blueValueTextField;
	private JTextField blueHexValueTextField;
	private JRadioButton redRadioButton;
	private JRadioButton blueRadioButton;
	private JRadioButton greenRadioButton;
	private JRadioButton yellowRadioButton;
	private JRadioButton cyanRadioButton;
	private JRadioButton orangeRadioButton;
	private JRadioButton blackRadioButton;
	private JButton darkerButton;
	private JButton brighterButton;

	public ColorPickerView(ColorPickerController controller) {
		this.controller = controller;
		initializeComponents();
		addEvents();
		layoutComponents();

		setTitle(COLOR_PICKER_VIEW_TITLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setPreferredSize(PREFERRED_SIZE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void initializeComponents() {
		redSlider = initializeSlider();
		greenSlider = initializeSlider();
		blueSlider = initializeSlider();
		redValueTextField = new JTextField();
		greenValueTextField = new JTextField();
		blueValueTextField = new JTextField();
		redHexValueTextField = new JTextField();
		greenHexValueTextField = new JTextField();
		blueHexValueTextField = new JTextField();
		previewPanel = new JPanel();
		redRadioButton = new JRadioButton("red");
		blueRadioButton = new JRadioButton("blue");
		greenRadioButton = new JRadioButton("green");
		yellowRadioButton = new JRadioButton("yellow");
		cyanRadioButton = new JRadioButton("cyan");
		orangeRadioButton = new JRadioButton("orange");
		blackRadioButton = new JRadioButton("black");
		brighterButton = new JButton("Brighter");
		darkerButton = new JButton("Darker");
	}

	private JSlider initializeSlider() {
		JSlider slider = new JSlider();
		slider.setValue(ZERO);
		slider.setMinimum(ZERO);
		slider.setMaximum(TWO_HUNDRED_FIFTY_FIVE);
		return slider;
	}

	private void addEvents() {
		redSlider.addChangeListener(e -> controller.updateRedValue(redSlider.getValue()));
		greenSlider.addChangeListener(e -> controller.updateGreenValue(greenSlider.getValue()));
		blueSlider.addChangeListener(e -> controller.updateBlueValue(blueSlider.getValue()));

		addKeyListener(value -> controller.updateRedValue(value), redValueTextField);
		addKeyListener(value -> controller.updateGreenValue(value), greenValueTextField);
		addKeyListener(value -> controller.updateBlueValue(value), blueValueTextField);

		addRadioButtonListener(redRadioButton, RED);
		addRadioButtonListener(blueRadioButton, BLUE);
		addRadioButtonListener(greenRadioButton, GREEN);
		addRadioButtonListener(yellowRadioButton, YELLOW);
		addRadioButtonListener(cyanRadioButton, CYAN);
		addRadioButtonListener(orangeRadioButton, ORANGE);
		addRadioButtonListener(blackRadioButton, BLACK);

		brighterButton.addActionListener(e -> controller.brightenColor());
		darkerButton.addActionListener(e -> controller.darkenColor());
	}

	private void addRadioButtonListener(JRadioButton radioButton, Color color) {
		radioButton.addActionListener(e -> {
			controller.updateRedValue(color.getRed());
			controller.updateGreenValue(color.getGreen());
			controller.updateBlueValue(color.getBlue());
		});
	}

	private void addKeyListener(Consumer<Integer> action, JTextField textField) {
		textField.addKeyListener(new ColorPickerTextFieldChangedListener(action, textField));
	}

	private void layoutComponents() {
		int zero = 0;
		int one = 1;
		int two = 2;

		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		int inset = 2;
		constraints.insets = new Insets(inset, inset, inset, inset);
		constraints.anchor = GridBagConstraints.NORTHWEST;

		constraints.gridx = zero;
		constraints.gridy = zero;
		constraints.gridwidth = two;
		add(redSlider, constraints);

		constraints.gridx = two;
		constraints.gridwidth = one;
		layoutTextField(redValueTextField, constraints);

		constraints.gridx++;
		layoutTextField(redHexValueTextField, constraints);

		constraints.gridx = zero;
		constraints.gridy++;
		constraints.gridwidth = two;
		add(greenSlider, constraints);

		constraints.gridx = two;
		constraints.gridwidth = one;
		layoutTextField(greenValueTextField, constraints);

		constraints.gridx++;
		layoutTextField(greenHexValueTextField, constraints);

		constraints.gridx = zero;
		constraints.gridy++;
		constraints.gridwidth = two;
		add(blueSlider, constraints);

		constraints.gridx = two;
		layoutTextField(blueValueTextField, constraints);

		constraints.gridx++;
		layoutTextField(blueHexValueTextField, constraints);

		constraints.gridwidth = one;
		constraints.gridheight = 7;
		constraints.gridx = zero;
		constraints.gridy++;
		constraints.ipadx = 150;
		constraints.ipady = 150;
		previewPanel.setBackground(new Color(0, 0, 0));
		add(previewPanel, constraints);

		constraints.gridheight = one;
		constraints.gridx++;
		constraints.ipadx = zero;
		constraints.ipady = zero;
		add(redRadioButton, constraints);

		constraints.gridy++;
		add(blueRadioButton, constraints);

		constraints.gridy++;
		add(greenRadioButton, constraints);

		constraints.gridy++;
		add(yellowRadioButton, constraints);

		constraints.gridy++;
		add(cyanRadioButton, constraints);

		constraints.gridy++;
		add(orangeRadioButton, constraints);

		constraints.gridy++;
		add(blackRadioButton, constraints);

		constraints.gridx++;
		constraints.gridy -= 4;
		add(brighterButton, constraints);
		
		constraints.gridy += 2;
		add(darkerButton, constraints);
	}

	private void layoutTextField(JTextField textField, GridBagConstraints constraints) {
		textField.setPreferredSize(TEXT_FIELD_SIZE);
		add(textField, constraints);
	}

	@Override
	public void update(ColorPickerModel colorModel) {
		int red = colorModel.getRed();
		redSlider.setValue(red);
		redValueTextField.setText(String.valueOf(red));
		redHexValueTextField.setText(Integer.toHexString(red));

		int green = colorModel.getGreen();
		greenSlider.setValue(green);
		greenValueTextField.setText(String.valueOf(green));
		greenHexValueTextField.setText(Integer.toHexString(green));

		int blue = colorModel.getBlue();
		blueSlider.setValue(blue);
		blueValueTextField.setText(String.valueOf(blue));
		blueHexValueTextField.setText(Integer.toHexString(blue));

		Color newColor = new Color(red, green, blue);
		previewPanel.setBackground(newColor);

		redRadioButton.setSelected(newColor.equals(RED));
		blueRadioButton.setSelected(newColor.equals(BLUE));
		greenRadioButton.setSelected(newColor.equals(GREEN));
		yellowRadioButton.setSelected(newColor.equals(YELLOW));
		cyanRadioButton.setSelected(newColor.equals(CYAN));
		orangeRadioButton.setSelected(newColor.equals(ORANGE));
		blackRadioButton.setSelected(newColor.equals(BLACK));

		brighterButton.setEnabled(controller.canBrighter());
		darkerButton.setEnabled(controller.canDarker());
	}

}
