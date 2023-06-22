package src;
import javax.swing.*;

import gui_option.Main;

import java.awt.*;


public class selectionSortBar extends JFrame {
   private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 400;
    private static final int BAR_WIDTH = 30;
    private static final int DELAY = 1000; // Animation delay in milliseconds

    private int[] array; // Example array
    private int currentIndex = -1;
    private int minIndex = -1;

    private BarGraphPanel graphPanel;

    public selectionSortBar(int[] array,JFrame parFrame) {
        this.array=array;
        setTitle("Selection Sort Example");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
         setLayout(new BorderLayout());
         parFrame.setVisible(false);
        graphPanel = new BarGraphPanel();
        add(graphPanel,BorderLayout.CENTER);

        JLabel content=new JLabel("Time Complexity in worst case: O(n^2)\n");
        content.setHorizontalAlignment(SwingConstants.CENTER);
        add(content,BorderLayout.NORTH);

        JButton backButton =new JButton("Back");
        backButton.setPreferredSize(new Dimension(30, 30));
        backButton.addActionListener(e -> {
            Main obj=new Main();
            obj.setVisible(true);
            dispose();
        });
        add(backButton,BorderLayout.SOUTH);

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
            Graphics2D g2d = (Graphics2D) g;

            int x = (getWidth() - array.length * BAR_WIDTH) / 2;
            int y = getHeight() / 2;

            for (int i = 0; i < array.length; i++) {
                int barHeight = array[i] * 10;
                if (i == currentIndex || i == minIndex) {
                    g.setColor(Color.gray); // Highlight the comparing indexes
                } else {
                    g.setColor(Color.yellow);
                }

                g2d.fillRect(x, y - barHeight, BAR_WIDTH, barHeight);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(x, y - barHeight, BAR_WIDTH, barHeight);
                  g.drawString(Integer.toString(array[i]), x, y - barHeight - 5);
                x += BAR_WIDTH;
            }
        }
    }
}

