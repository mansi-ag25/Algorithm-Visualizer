package src;
import javax.swing.*;
import java.awt.*;


public class BarGraphSelectionSort extends JFrame {
   private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 400;
    private static final int BAR_WIDTH = 30;
    private static final int DELAY = 1000; // Animation delay in milliseconds

    private int[] array; // Example array
    private int currentIndex = -1;
    private int minIndex = -1;

    private BarGraphPanel graphPanel;

    public BarGraphSelectionSort(int[] array) {
        this.array=array;
        setTitle("Selection Sort Example");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
         setLayout(new BorderLayout());
        graphPanel = new BarGraphPanel();
        add(graphPanel,BorderLayout.CENTER);

        JLabel content=new JLabel("Time Complexity in worst case: O(n^2)\n");
        content.setHorizontalAlignment(SwingConstants.CENTER);
        add(content,BorderLayout.NORTH);

        Thread sortingThread = new Thread(() -> {
            selectionSort(array);
            currentIndex = -1;
            minIndex = -1;
            repaint();
        });
        sortingThread.start();
    }

    private void selectionSort(int[] array) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < n; j++) {
                currentIndex = j;
                repaint();
                sleep(DELAY);
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            swap(i, minIndex);
        }
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class BarGraphPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int barHeightScale = getHeight() / (array.length + 1);
            int x = 0;

            for (int i = 0; i < array.length; i++) {
                int barHeight = array[i] * barHeightScale;
                int y = getHeight() - barHeight;

                if (i == currentIndex || i == minIndex) {
                    g.setColor(Color.gray); // Highlight the comparing indexes
                } else {
                    g.setColor(Color.yellow);
                }

                g.fillRect(x, y, BAR_WIDTH, barHeight);
                x += BAR_WIDTH + 10;
            }
        }
    }
}

