package gui_option;
import javax.swing.*;
import src.mergeSortBar;
import java.awt.*;
import java.awt.event.*;

public class mergeSortGui extends JFrame{
     private JTextField arrayField;
    private JButton sortButton;
    private JFrame parFrame;

    public mergeSortGui(JFrame parentFrame) {
        // Set up the JFrame
        setTitle("Merge Sort");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        parentFrame.setVisible(false);
        // Create the text fields
        arrayField = new JTextField(15);
        sortButton = new JButton("Sort");
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the array and search element from text fields
                String arrayText = arrayField.getText();
                if(arrayText.isEmpty()){
                     JOptionPane.showMessageDialog(mergeSortGui.this,
                        "Array can't be empty");
                }
                // Convert arrayText to integer array
                String[] arrayItems = arrayText.split(",");
                int[] array = new int[arrayItems.length];
                for (int i = 0; i < arrayItems.length; i++) {
                    array[i] = Integer.parseInt(arrayItems[i]);
                }
               mergeSortBar mergeSortVisualization = new mergeSortBar(array,parFrame);
               mergeSortVisualization.setVisible(true);
              // mergeSortVisualization.mergeSort(array, 0, array.length - 1);
               
                
            }
        });

        // Add the components to the JFrame
        add(new JLabel("Array (comma-separated):"));
        add(arrayField);
      
        add(sortButton);

        // Set the JFrame size and make it visible
        setSize(300, 200);
        setVisible(true);
        parFrame=this;
    }
}
