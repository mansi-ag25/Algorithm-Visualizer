package src;
import java.awt.*;
import java.util.Arrays;
import javax.swing.*;

import gui_option.Main;

public class mergeSortBar extends JFrame {
    
    private static final int BAR_WIDTH = 30;
    private static final int DELAY_MS = 500;
    
    private int[] array;
    private MergeSortPanel panel;
    
    public mergeSortBar(int[] array,JFrame parFrame) {
        this.array = array;
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Merge Sort Visualization");
        setSize(350, 350);
         setLocationRelativeTo(null);
         setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        parFrame.setVisible(false);
        panel = new MergeSortPanel();
        add(panel);

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
            mergeSort(array, 0, array.length - 1);
            repaint();
        });
        sortingThread.start();
       
    }
    
    public void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
            panel.repaint();
           sleep();
        }
    }
    
    private void merge(int[] arr, int left, int mid, int right) {
        int[] temp = Arrays.copyOf(arr, arr.length);
        int i = left;
        int j = mid + 1;
        int k = left;
        
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                 arr[k++] = temp[i++];
               // temp[k++]=arr[i++];
            } else {
                arr[k++] = temp[j++];
                //temp[k++]=arr[j++];
            }
            panel.setCurrentIndices(i, j);
            panel.repaint();
            sleep();
        }
        
        while (i <= mid) {
            arr[k++] = temp[i++];
          // temp[k++]=arr[i++];
            panel.setCurrentIndices(i, j);
            panel.repaint();
            sleep();
        }

        
    }
    
    private void sleep() {
        try {
            Thread.sleep(DELAY_MS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private class MergeSortPanel extends JPanel {
        private int currentIndex1;
        private int currentIndex2;
        
        public MergeSortPanel() {
            this.currentIndex1 = -1;
            this.currentIndex2 = -1;
        }
        
        public void setCurrentIndices(int index1, int index2) {
            currentIndex1 = index1;
            currentIndex2 = index2;
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            int x = (getWidth() - array.length * BAR_WIDTH) / 2;
            int y = getHeight() / 2;
            
            for (int i = 0; i < array.length; i++) {
                int barHeight = array[i] * 10;
                if (i == currentIndex1 || i == currentIndex2) {
                    g.setColor(Color.gray);
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
    // public static void main(String args[]){
    //     int array[]={2,7,4,9,1,8,11,3,5};
    //     mergeSortBar obj=new mergeSortBar(array);
    //     obj.mergeSort(array, 0, array.length - 1);
    // }    
       
    
}
