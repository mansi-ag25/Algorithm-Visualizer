package gui_option;

import javax.swing.*;

import src.QuickSortVisualization;

import java.awt.*;
import java.awt.event.*;


public class quickSortGui extends JFrame{
    private JTextField arrayField;
    private JButton sortButton;

    public quickSortGui() {
        // Set up the JFrame
        setTitle("Quick Sort");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Create the text fields
        arrayField = new JTextField(15);
        sortButton = new JButton("Sort");
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the array and search element from text fields
                String arrayText = arrayField.getText();
                if(arrayText.isEmpty()){
                     JOptionPane.showMessageDialog(quickSortGui.this,
                        "Array can't be empty");
                }
                // Convert arrayText to integer array
                String[] arrayItems = arrayText.split(",");
                int[] array = new int[arrayItems.length];
                for (int i = 0; i < arrayItems.length; i++) {
                    array[i] = Integer.parseInt(arrayItems[i]);
                }
                QuickSortVisualization frame = new QuickSortVisualization(array);
        frame.quickSort();
                frame.setVisible(true);
            }
        });

        // Add the components to the JFrame
        add(new JLabel("Array (comma-separated):"));
        add(arrayField);
      
        add(sortButton);

        // Set the JFrame size and make it visible
        setSize(300, 200);
        setVisible(true);
    } 
}
