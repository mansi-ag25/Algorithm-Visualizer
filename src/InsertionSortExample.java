package src;
import javax.swing.*;
import java.awt.*;

public class InsertionSortExample extends JFrame {
    private static final int BAR_WIDTH = 40;
    private static final int BAR_HEIGHT_MULTIPLIER = 15;
    private static final int DELAY_MS = 500;

    private int[] array; // Example array
    private int currentIndex = -1; // Index currently being processed during sorting
    private int comparingIndex = -1; // Index being compared with the currentIndex during sorting

    private JPanel panel;

    public InsertionSortExample(int[] array) {
        this.array=array;
        setTitle("Insertion Sort");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

         setLayout(new BorderLayout());

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBars(g);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(BAR_WIDTH * array.length, getHeight());
            }
        };
        add(panel,BorderLayout.CENTER);

         JLabel content=new JLabel("Time Complexity in worst case: O(n^2)\n");
        content.setHorizontalAlignment(SwingConstants.CENTER);
        add(content,BorderLayout.NORTH);

        sortArray(array);
        setVisible(true);
    }

    private void sortArray(int[] array) {
        Thread sortThread = new Thread(() -> {
            for (int i = 1; i < array.length; i++) {
                currentIndex = i;
                comparingIndex = i - 1;
                repaint();

                int currentValue = array[i];
                int j = i - 1;
                while (j >= 0 && array[j] > currentValue) {
                    array[j + 1] = array[j];
                    j--;
                    comparingIndex = j;
                    repaint();

                    try {
                        Thread.sleep(DELAY_MS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                array[j + 1] = currentValue;

                currentIndex = -1;
                comparingIndex = -1;
                repaint();

                try {
                    Thread.sleep(DELAY_MS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        sortThread.start();
    }

    private void drawBars(Graphics g) {
        for (int i = 0; i < array.length; i++) {
            int barHeight = array[i] * BAR_HEIGHT_MULTIPLIER;
            int x = i * BAR_WIDTH;
            int y = panel.getHeight() - barHeight;

            if (i == currentIndex || i == comparingIndex) {
                g.setColor(Color.GRAY);
            } else {
                g.setColor(Color.YELLOW);
            }

            g.fillRect(x, y, BAR_WIDTH, barHeight);
        }
    }
}


