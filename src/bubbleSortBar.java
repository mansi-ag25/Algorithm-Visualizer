package src;
import java.awt.BorderLayout;

import javax.swing.*;

import gui_option.Main;

public class bubbleSortBar extends JFrame {
    private BubbleSortPanel sortPanel;
    private int[] array;

    public bubbleSortBar(int[] array,JFrame parFrame) {
        this.array=array;
        setTitle("Bubble Sort Visualization");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
         setLayout(new BorderLayout());
         parFrame.setVisible(false);
        sortPanel = new BubbleSortPanel(array);
        add(sortPanel,BorderLayout.CENTER);

         JLabel content=new JLabel("Time Complexity in worst case: O(n^2)\n");

        
        content.setHorizontalAlignment(SwingConstants.CENTER);
     
        add(content,BorderLayout.NORTH);
        JButton backButton =new JButton("Back");
       
        backButton.addActionListener(e -> {
            Main obj=new Main();
            obj.setVisible(true);
            dispose();
        });
        add(backButton,BorderLayout.SOUTH);
    }

    public void startSorting() {
        Thread sortingThread = new Thread(() -> {
            sortPanel.bubbleSort(array);
        });
        sortingThread.start();
    }
}
