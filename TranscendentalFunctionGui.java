package d3_delivarable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * The TranscendentalFunctionGUI class creates a GUI application
 * to calculate and display the result of the transcendental function B(x, y).
 * @Tawhidur Rahman
 */
public class TranscendentalFunctionGui {
    private Map<String, Double> cache = new HashMap<>();

    /**
     * The main method to launch the GUI application.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TranscendentalFunctionGui().createAndShowGUI());
    }

    /**
     * Creates and displays the GUI.
     */
    private void createAndShowGUI() {
        JFrame frame = new JFrame("Transcendental Function B(x, y)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        JLabel labelX = new JLabel("Enter x:");
        labelX.setToolTipText("Input field for x");
        labelX.getAccessibleContext().setAccessibleName("Label for X");
        JTextField fieldX = new JTextField(10);
        fieldX.setToolTipText("Enter the value for x here");
        fieldX.getAccessibleContext().setAccessibleName("Field for X");

        JLabel labelY = new JLabel("Enter y:");
        labelY.setToolTipText("Input field for y");
        labelY.getAccessibleContext().setAccessibleName("Label for Y");
        JTextField fieldY = new JTextField(10);
        fieldY.setToolTipText("Enter the value for y here");
        fieldY.getAccessibleContext().setAccessibleName("Field for Y");

        JButton button = new JButton("Calculate");
        button.setToolTipText("Click to calculate the result");
        button.getAccessibleContext().setAccessibleName("Calculate Button");

        JLabel resultLabel = new JLabel("Result:");
        resultLabel.setToolTipText("Displays the result");
        resultLabel.getAccessibleContext().setAccessibleName("Result Label");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double x = Double.parseDouble(fieldX.getText());
                    double y = Double.parseDouble(fieldY.getText());
                    String cacheKey = x + "," + y;
                    double result;

                    if (cache.containsKey(cacheKey)) {
                        result = cache.get(cacheKey);
                    } else {
                        result = transcendentalFunctionB(x, y);
                        cache.put(cacheKey, result);
                    }

                    resultLabel.setText("Result: " + result);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid input. Please enter numeric values.");
                } catch (IllegalArgumentException ex) {
                    resultLabel.setText("Error: " + ex.getMessage());
                }
            }
        });

        panel.add(labelX);
        panel.add(fieldX);
        panel.add(labelY);
        panel.add(fieldY);
        panel.add(button);
        panel.add(resultLabel);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    /**
     * Calculates the transcendental function transcendentalFunctionB(x, y).
     * 
     * @param x the x value
     * @param y the y value
     * @return the result of the function
     * @throws IllegalArgumentException if x or y are negative
     */
    private double transcendentalFunctionB(double x, double y) throws IllegalArgumentException {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Inputs must be non-negative.");
        }
        return sin(x) + cos(y); // Example of transcendental function calculation
    }

    /**
     * Computes the sine of a value using the Taylor series expansion.
     * 
     * @param x the value to compute the sine of
     * @return the sine of x
     */
    private double sin(double x) {
        double result = 0;
        double term = x;

        for (int i = 1; i <= 15; i++) {
            result += term;
            term *= -x * x / (2 * i * (2 * i + 1));
        }

        return result;
    }

    /**
     * Computes the cosine of a value using the Taylor series expansion.
     * 
     * @param y the value to compute the cosine of
     * @return the cosine of x
     */
    private double cos(double y) {
        double result = 1;
        double term = 1;

        for (int i = 1; i <= 15; i++) {
            term *= -y * y / (2 * i * (2 * i - 1));
            result += term;
        }

        return result;
    }
}
