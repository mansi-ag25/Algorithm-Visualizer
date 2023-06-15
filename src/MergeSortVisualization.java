package src;
import java.awt.*;
import java.util.Arrays;
import javax.swing.*;

public class MergeSortVisualization extends JFrame {
    
    private static final int BAR_WIDTH = 40;
    private static final int BAR_HEIGHT_MULTIPLIER = 20;
    private static final int DELAY_MS = 500;
    
    private int[] array;
    private MergeSortPanel panel;
    
    public MergeSortVisualization(int[] array) {
        this.array = array;
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Merge Sort Visualization");
        setSize(350, 350);
         setLocationRelativeTo(null);
         setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        panel = new MergeSortPanel();
        add(panel);

         JLabel content=new JLabel("Time Complexity in worst case: O(nlogn)\n");
        content.setHorizontalAlignment(SwingConstants.CENTER);
        add(content,BorderLayout.NORTH);

        setVisible(true);
       
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
            } else {
                arr[k++] = temp[j++];
            }
            panel.setCurrentIndices(i, j);
            panel.repaint();
            sleep();
        }
        
        while (i <= mid) {
            arr[k++] = temp[i++];
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
            setBackground(Color.WHITE);
            
           
            int x = 10;
            
            for (int i = 0; i < array.length; i++) {
                int barHeight = array[i] * BAR_HEIGHT_MULTIPLIER;
                int y = getHeight() - barHeight;
                
                if (i == currentIndex1 || i == currentIndex2) {
                    g.setColor(Color.gray);
                } else {
                    g.setColor(Color.yellow);
                }
                
                g.fillRect(x, y, BAR_WIDTH, barHeight);
                x += BAR_WIDTH + 2;
            }
        }
        
       
    }
}
