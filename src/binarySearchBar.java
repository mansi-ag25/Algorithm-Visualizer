package src;
import javax.swing.*;

import gui_option.Main;

import java.awt.*;
import java.awt.event.*;

public class binarySearchBar extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final int BAR_WIDTH = 30;
    private int[] array; // Sorted array for binary search
    private int target; // Example target value
    private int startIndex; // Start index for the current binary search iteration
    private int endIndex ; // End index for the current binary search iteration
    private int currentIndex = -1; // Index currently being checked during animation
    private int animationSpeed = 500; // Animation speed in milliseconds

    private BarGraphPanel barGraphPanel;

    public binarySearchBar(int[] array,int target,int startIndex,int endIndex,JFrame parFrame) {
        this.array=array;
        this.target=target;
        this.startIndex=startIndex;
        this.endIndex=endIndex;
        setTitle("Binary Search Bar Graph");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
         setLayout(new BorderLayout());
        parFrame.setVisible(false);
        barGraphPanel = new BarGraphPanel();
        add(barGraphPanel,BorderLayout.CENTER);

         JLabel content=new JLabel("Time Complexity: O(logn)\n");
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

        Thread animationThread = new Thread(() -> {
            binarySearch(array,target,0, array.length - 1);
        });
        animationThread.start();

         Timer timer = new Timer(animationSpeed, new TimerListener());
        timer.start();
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
             Graphics2D g2d = (Graphics2D) g;

            int x = (getWidth() - array.length * BAR_WIDTH) /2;
                int y = getHeight()/2;

            for (int i = 0; i < array.length; i++) {
                int barHeight=array[i] * 10;
                if (i == currentIndex) {
                    g.setColor(Color.gray); // Highlight the current index being checked
                } else if (i >= startIndex && i <= endIndex) {
                    g.setColor(Color.yellow); // Color the current binary search range
                } else {
                    g.setColor(Color.yellow); // Color the remaining array elements
                }
                g2d.fillRect(x, y-barHeight, BAR_WIDTH, barHeight);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(x, y - barHeight, BAR_WIDTH, barHeight);
                g.drawString(Integer.toString(array[i]), x, y - barHeight - 5);
                x += BAR_WIDTH;
            }
        }
    }

    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentIndex >= array.length) {
                ((Timer) e.getSource()).stop();
                JOptionPane.showMessageDialog(binarySearchBar.this,
                        "Element not found in the array.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            if (array[currentIndex] == target) {
                ((Timer) e.getSource()).stop();
                JOptionPane.showMessageDialog(binarySearchBar.this,
                        "Target found at index " + currentIndex + ".", "Search Result", JOptionPane.INFORMATION_MESSAGE);
            }

            currentIndex++;
            repaint();
        }
    }
}

