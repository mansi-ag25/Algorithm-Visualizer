package src;
import javax.swing.*;

import gui_option.Main;

import java.awt.*;

public class insertionSortBar extends JFrame {
    private static final int BAR_WIDTH = 40;
    private static final int BAR_HEIGHT_MULTIPLIER = 15;
    private static final int DELAY_MS = 500;

    private int[] array; // Example array
    private int currentIndex = -1; // Index currently being processed during sorting
    private int comparingIndex = -1; // Index being compared with the currentIndex during sorting

    private JPanel panel;

    public insertionSortBar(int[] array,JFrame parFrame) {
        this.array=array;
        setTitle("Insertion Sort");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

         setLayout(new BorderLayout());
         parFrame.setVisible(false);

          SortPanel searchPanel = new SortPanel();
        add(searchPanel,BorderLayout.CENTER);

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


    private class SortPanel extends JPanel {

        // perform the graphical function i.e. bar graph plotting
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            int x = (getWidth() - array.length * BAR_WIDTH) / 2;
            int y = getHeight() / 2;

            for (int i = 0; i < array.length; i++) {
                int barHeight = array[i] * 10;

                 if (i == currentIndex || i == comparingIndex) {
                        g.setColor(Color.GRAY);
                    } else {
                        g.setColor(Color.YELLOW);
                  }
                g2d.fillRect(x, y - barHeight, BAR_WIDTH, barHeight);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(x, y - barHeight, BAR_WIDTH, barHeight);
                g2d.drawString(Integer.toString(array[i]), x, y - barHeight - 5);
                x += BAR_WIDTH;
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(WIDTH, HEIGHT);
        }
    }
}


