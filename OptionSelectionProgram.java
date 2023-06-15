import javax.swing.*;
import gui_option.*;
import java.awt.*;
import java.awt.event.*;

public class OptionSelectionProgram extends JFrame {

    private JComboBox<String> optionComboBox;
    private JButton continueButton;

    public OptionSelectionProgram() {
        // Set up the JFrame
        setTitle("Option Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        JLabel content=new JLabel("Select an option");
        // Create the JComboBox with options
        optionComboBox = new JComboBox<>();
        optionComboBox.addItem("Linear Search");
        optionComboBox.addItem("Binary Search");
        optionComboBox.addItem("Bubble Sort");
        optionComboBox.addItem("Insertion Sort");
        optionComboBox.addItem("Selection Sort");
        optionComboBox.addItem("Merge Sort");
        optionComboBox.addItem("Quick Sort");

        // Create the button to select the option
        continueButton = new JButton("Continue");
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected option
                String selectedOption = (String) optionComboBox.getSelectedItem();
                if(selectedOption.equals("Linear Search")){
                    linearSearch obj=new linearSearch();
                   obj.setVisible(true);
                }

                else if(selectedOption.equals("Binary Search")){
                    binarySearch obj=new binarySearch();
                    obj.setVisible(true);
                    
                }
                else if(selectedOption.equals("Insertion Sort")){
                    insertionSort obj=new insertionSort();
                    obj.setVisible(true);
                    
                }
                else if(selectedOption.equals("Bubble Sort")){
                    bubbleSort obj=new bubbleSort();
                    obj.setVisible(true);
                    
                }

                else if(selectedOption.equals("Selection Sort")){
                    selectionSort obj=new selectionSort();
                    obj.setVisible(true);
                    
                }

                    else if(selectedOption.equals("Merge Sort")){
                    mergeSortGui obj=new mergeSortGui();
                    obj.setVisible(true);
                    
                }

                else{
                    quickSortGui obj=new quickSortGui();
                    obj.setVisible(true);
                }

            }
        });

        // Add the components to the JFrame
         add(content,FlowLayout.LEFT);
        add(optionComboBox,FlowLayout.LEFT);
       
        add(continueButton,FlowLayout.RIGHT);

        // Set the JFrame size and make it visible
        setSize(400, 400);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new OptionSelectionProgram();
            }
        });
    }
}

