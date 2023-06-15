package gui_option;
import javax.swing.*;
import src.BubbleSortVisualization;
import java.awt.*;
import java.awt.event.*;

public class bubbleSort extends JFrame{
     private JTextField arrayField;
    private JButton sortButton;

    public bubbleSort() {
        // Set up the JFrame
        setTitle("Bubble Sort");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Create the text field
        arrayField = new JTextField(15);
        sortButton = new JButton("Sort");


        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the array and search element from text fields
                String arrayText = arrayField.getText();
                if(arrayText.isEmpty()){
                     JOptionPane.showMessageDialog(bubbleSort.this,
                        "Array can't be empty");
                }
                // Convert arrayText to integer array
                String[] arrayItems = arrayText.split(",");
                int[] array = new int[arrayItems.length];
                for (int i = 0; i < arrayItems.length; i++) {
                    array[i] = Integer.parseInt(arrayItems[i]);
                }

                //call the bubble sort method 
                BubbleSortVisualization visualization = new BubbleSortVisualization(array);
                visualization.setVisible(true);
                visualization.startSorting();
               
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
