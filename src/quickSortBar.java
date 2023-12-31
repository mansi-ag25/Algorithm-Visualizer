package src;
import javax.swing.*;

import gui_option.Main;

import java.awt.*;
import java.util.Arrays;

public class quickSortBar extends JFrame {

    private static final int WIDTH = 350;
    private static final int HEIGHT = 350;
    private static final int BAR_WIDTH = 30;

    private int[] array;
    private int currentPivot;
    private int currentMin;
    private int currentMax;

    public quickSortBar(int[] array,JFrame parFrame) {
        this.array = array;
        this.currentPivot = -1;
        this.currentMin = -1;
        this.currentMax = -1;

        setTitle("Quick Sort Visualization");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        parFrame.setVisible(false);
        JLabel content=new JLabel("Time Complexity in worst case: O(nlogn)\n");
        content.setHorizontalAlignment(SwingConstants.CENTER);
        add(content,BorderLayout.NORTH);
        setVisible(true);

        JButton backButton =new JButton("Back");
        backButton.setPreferredSize(new Dimension(30, 30));
        backButton.addActionListener(e -> {
            Main obj=new Main();
            obj.setVisible(true);
            dispose();
        });
        add(backButton,BorderLayout.SOUTH);

        Thread sortingThread = new Thread(() -> {
            quickSort();
           
            // currentIndex = -1;
            // minIndex = -1;
            repaint();
        });
        sortingThread.start();
    }

    public void quickSort() {
        quickSort(0, array.length - 1);
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int partitionIndex = partition(low, high);
            quickSort(low, partitionIndex - 1);
            quickSort(partitionIndex + 1, high);
        }
    }

    private int partition(int low, int high) {
        int pivot = array[high];
        currentPivot = high;
        currentMin = low;
        currentMax = high;

        repaint();

        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                swap(i, j);
            }
        }

        swap(i + 1, high);
        currentPivot = -1;
        currentMin = -1;
        currentMax = -1;
        repaint();

        return i+1;
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;

        try {
            Thread.sleep(1000); // Delay to visualize the sorting process
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

          int x = (getWidth() - array.length * BAR_WIDTH) / 2;
            int y = getHeight() / 2;
        // int x = (getWidth() - (array.length * (BAR_WIDTH + BAR_GAP))) / 2;
        // int maxHeight = getHeight() - 50;

        for (int i = 0; i < array.length; i++) {
              int barHeight = array[i] * 10;
           // int barHeight = (int) (((double) array[i] / Arrays.stream(array).max().getAsInt()) * maxHeight);
           // int y = getHeight() - barHeight;

            if (i == currentPivot) {
                g2d.setColor(Color.gray);
            } else if (i >= currentMin && i <= currentMax) {
                g2d.setColor(Color.GREEN);
            } else {
                g2d.setColor(Color.yellow);
            }

            g2d.fillRect(x, y-barHeight, BAR_WIDTH, barHeight);

            g2d.setColor(Color.BLACK);
            g2d.drawRect(x, y-barHeight, BAR_WIDTH, barHeight);
              g.drawString(Integer.toString(array[i]), x, y - barHeight - 5);
            x += BAR_WIDTH ;
        }
    }
}
