package assignmentPACKAGE;

// FAAIZ ABDULLAH


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

public class BubbleSortAnimation extends JFrame {
    private final int ARRAY_SIZE = 25;
    private final int BAR_WIDTH = 20;
    private final int BAR_HEIGHT_MULTIPLIER = 10;
    private final int DELAY_MS = 100; // Time delay for animation (in milliseconds)

    private int[] array;
    private int currentIndex;
    private boolean sortingInProgress;
    private JPanel histogramPanel;
    private JButton stepButton;
    private JButton resetButton;

    public BubbleSortAnimation() {
        // Set the title and default close operation of the frame
        setTitle("Bubble Sort Animation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the layout of the frame to a border layout
        setLayout(new BorderLayout());

        // Create the histogram panel to display bars
        histogramPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 20)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawHistogram(g); // Draw the histogram with bars and numbers
            }
        };
        add(histogramPanel, BorderLayout.CENTER);

        // Create the "Step" button
        stepButton = new JButton("Step");
        stepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sortingInProgress) {
                    performStep(); // Perform one step of the bubble sort algorithm
                    repaint(); // Redraw the histogram after the step
                }
            }
        });

        // Create the "Reset" button
        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateRandomArray(); // Generate a new random array
                currentIndex = array.length - 1;
                sortingInProgress = true; // Set the flag to indicate sorting is in progress
                repaint(); // Redraw the histogram with the new array
            }
        });

        // Create a panel for the buttons and add them to it
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(stepButton);
        buttonPanel.add(resetButton);

        // Add the button panel to the bottom of the frame
        add(buttonPanel, BorderLayout.SOUTH);

        // Initialize the array and set the current index for sorting
        generateRandomArray();
        currentIndex = array.length - 1;
        sortingInProgress = true;

        // Pack and display the frame at the center of the screen
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Method to draw the histogram with bars and numbers
    private void drawHistogram(Graphics g) {
        for (int i = 0; i < array.length; i++) {
            int barHeight = array[i] * BAR_HEIGHT_MULTIPLIER;
            int y = histogramPanel.getHeight() - barHeight;
            if (i == currentIndex || i == currentIndex + 1) {
                g.setColor(Color.RED); // Set the color to red for bars being compared
            } else {
                g.setColor(Color.BLACK); // Set the color to black for other bars
            }

            // Calculate the x-coordinate to center the bars horizontally in the panel
            int x = i * (BAR_WIDTH + 5) + (histogramPanel.getWidth() - (ARRAY_SIZE * (BAR_WIDTH + 5))) / 2;

            // Draw the bar and its border
            g.fillRect(x, y, BAR_WIDTH, barHeight);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, BAR_WIDTH, barHeight);

            // Write the number on top of the bar
            FontMetrics fontMetrics = g.getFontMetrics();
            String numberText = String.valueOf(array[i]);
            int numberWidth = fontMetrics.stringWidth(numberText);
            int numberX = x + (BAR_WIDTH - numberWidth) / 2;
            int numberY = y - 5; // Offset to position the number above the bar
            g.drawString(numberText, numberX, numberY);
        }
    }

    // Method to generate a new random array
    private void generateRandomArray() {
        array = new int[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = i + 1;
        }
        shuffleArray(); // Shuffle the array to make it random
    }

    // Method to shuffle the array to make it random
    private void shuffleArray() {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    // Method to perform one step of the bubble sort algorithm
    private void performStep() {
        if (currentIndex > 0) {
            for (int i = 0; i < currentIndex; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
            }
            currentIndex--;
        } else {
            sortingInProgress = false;
            JOptionPane.showMessageDialog(this, "Sorting Complete!");
        }
    }

    // Main function
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BubbleSortAnimation();
            }
        });
    }	// end main
    
} // end class
