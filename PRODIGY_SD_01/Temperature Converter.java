package TemperatureConverter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.*;

public class TemperatureConverter {
    private JFrame frame;
    private JPanel panel;
    private JTextField inputField;
    private JComboBox<String> unitComboBox;
    private JButton convertButton;
    private JButton refreshButton;
    private JTextArea resultArea;

    public TemperatureConverter() {
        frame = new JFrame("Temperature Converter");
        panel = new JPanel();
        inputField = new JTextField(10);
        unitComboBox = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        convertButton = new JButton("Convert");
        refreshButton = new JButton("Refresh");
        resultArea = new JTextArea(10, 25);
        resultArea.setEditable(false);

        panel.add(new JLabel("Enter temperature: "));
        panel.add(inputField);
        panel.add(unitComboBox);
        panel.add(convertButton);
        panel.add(refreshButton);
        panel.add(resultArea);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputField.setText("");
                resultArea.setText("");
            }
        });
    }

    private void convertTemperature() {
        try {
            double temperature = Double.parseDouble(inputField.getText());
            String selectedUnit = (String) unitComboBox.getSelectedItem();
            String result = "";

            if (selectedUnit.equals("Celsius")) {
                double fahrenheit = celsiusToFahrenheit(temperature);
                double kelvin = celsiusToKelvin(temperature);
                result = String.format("%.2f degrees Celsius is equal to %.2f degrees Fahrenheit and %.2f Kelvin.", temperature, fahrenheit, kelvin);
            } else if (selectedUnit.equals("Fahrenheit")) {
                double celsius = fahrenheitToCelsius(temperature);
                double kelvin = fahrenheitToKelvin(temperature);
                result = String.format("%.2f degrees Fahrenheit is equal to %.2f degrees Celsius and %.2f Kelvin.", temperature, celsius, kelvin);
            } else if (selectedUnit.equals("Kelvin")) {
                double celsius = kelvinToCelsius(temperature);
                double fahrenheit = kelvinToFahrenheit(temperature);
                result = String.format("%.2f Kelvin is equal to %.2f degrees Celsius and %.2f degrees Fahrenheit.", temperature, celsius, fahrenheit);
            }

            resultArea.setText(result);
        } catch (NumberFormatException ex) {
            resultArea.setText("Please enter a valid temperature value.");
        }
    }

    private double celsiusToFahrenheit(double celsius) {
        return (celsius * 9/5) + 32;
    }

    private double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    private double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5/9;
    }

    private double fahrenheitToKelvin(double fahrenheit) {
        return (fahrenheit + 459.67) * 5/9;
    }

    private double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    private double kelvinToFahrenheit(double kelvin) {
        return (kelvin * 9/5) - 459.67;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TemperatureConverter();
            }
        });
    }
}


