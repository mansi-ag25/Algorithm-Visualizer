package src;
import java.awt.BorderLayout;

import javax.swing.*;

public class BubbleSortVisualization extends JFrame {
    private BubbleSortPanel sortPanel;
    private int[] array;

    public BubbleSortVisualization(int[] array) {
        this.array=array;
        setTitle("Bubble Sort Visualization");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
         setLayout(new BorderLayout());
        sortPanel = new BubbleSortPanel(array);
        add(sortPanel,BorderLayout.CENTER);

         JLabel content=new JLabel("Time Complexity in worst case: O(n^2)\n");

        
        content.setHorizontalAlignment(SwingConstants.CENTER);
     
        add(content,BorderLayout.NORTH);
    }

    public void startSorting() {
        Thread sortingThread = new Thread(() -> {
            sortPanel.bubbleSort(array);
        });
        sortingThread.start();
    }
}
