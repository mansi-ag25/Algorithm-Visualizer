package src;
import javax.swing.*;
import java.awt.*;

public class BinarySearchBarGraph extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private int[] array; // Sorted array for binary search
    private int target; // Example target value
    private int startIndex; // Start index for the current binary search iteration
    private int endIndex ; // End index for the current binary search iteration
    private int currentIndex = -1; // Index currently being checked during animation
    private int animationSpeed = 500; // Animation speed in milliseconds

    private BarGraphPanel barGraphPanel;

    public BinarySearchBarGraph(int[] array,int target,int startIndex,int endIndex) {
        this.array=array;
        this.target=target;
        this.startIndex=startIndex;
        this.endIndex=endIndex;
        setTitle("Binary Search Bar Graph");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
         setLayout(new BorderLayout());

        barGraphPanel = new BarGraphPanel();
        add(barGraphPanel,BorderLayout.CENTER);

         JLabel content=new JLabel("Time Complexity: O(logn)\n");
        content.setHorizontalAlignment(SwingConstants.CENTER);
        add(content,BorderLayout.NORTH);

        Thread animationThread = new Thread(() -> {
            binarySearch(array,target,0, array.length - 1);
        });
        animationThread.start();
    }

    private void binarySearch(int[] aray,int target,int start, int end) {
        if (start <= end) {
            int mid = (start + end) / 2;
            currentIndex = mid;
            try {
                Thread.sleep(animationSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            barGraphPanel.repaint();

            if (array[mid] == target) {
                currentIndex = mid; // Highlight the target index
                barGraphPanel.repaint();
                return;
            }

            if (array[mid] < target) {
                binarySearch(array,target,mid + 1, end);
            } else {
                binarySearch(array,target,start, mid - 1);
            }
        } else {
            currentIndex = -1; // Reset the currentIndex after the animation completes
            barGraphPanel.repaint();
        }
    }

    private class BarGraphPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int barWidth = getWidth() / array.length;
            int barHeight = getHeight() / (array.length + 1);

            for (int i = 0; i < array.length; i++) {
                int x = i * barWidth;
                int y = getHeight() - array[i] * barHeight;

                if (i == currentIndex) {
                    g.setColor(Color.gray); // Highlight the current index being checked
                } else if (i >= startIndex && i <= endIndex) {
                    g.setColor(Color.yellow); // Color the current binary search range
                } else {
                    g.setColor(Color.yellow); // Color the remaining array elements
                }

                g.fillRect(x, y, barWidth, array[i] * barHeight);
            }
        }
    }
}

