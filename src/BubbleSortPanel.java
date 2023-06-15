package src;

import javax.swing.JPanel;
import java.awt.*;

public class BubbleSortPanel extends JPanel {
    private int currentIndex = -1; // Index currently being compared during sorting
    private int nextIndex = -1; // Next index to be compared during sorting
    private int animationSpeed = 1000; // Animation speed in milliseconds
    private int[] array;
    public BubbleSortPanel(int[] array) {
        setBackground(Color.WHITE);
        this.array=array;
    }

    public void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                currentIndex = j;
                nextIndex = j + 1;
                try {
                    Thread.sleep(animationSpeed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (array[j] > array[j + 1]) {
                    // Swap the elements
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
                repaint();
            }
        }
        currentIndex = -1; // Reset the currentIndex after sorting completes
        nextIndex = -1; // Reset the nextIndex after sorting completes
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int barWidth = getWidth() / array.length;
        int barHeight = getHeight() / (array.length + 1);

        for (int i = 0; i < array.length; i++) {
            int x = i * barWidth;
            int y = getHeight() - array[i] * barHeight;

            if (i == currentIndex || i == nextIndex) {
                g.setColor(Color.gray); // Highlight the comparing bars
            } else {
                g.setColor(Color.yellow);
            }

            g.fillRect(x, y, barWidth, array[i] * barHeight);
        }
    }
}


