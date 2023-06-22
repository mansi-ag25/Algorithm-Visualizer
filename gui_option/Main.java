package gui_option;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {

    private JComboBox<String> optionComboBox;
    private JButton continueButton;
    private JFrame parentFrame;

    public Main() {
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
                    linearSearch obj=new linearSearch(parentFrame);
                   obj.setVisible(true);
                }

                else if(selectedOption.equals("Binary Search")){
                    binarySearch obj=new binarySearch(parentFrame);
                    obj.setVisible(true);
                    
                }
                else if(selectedOption.equals("Insertion Sort")){
                    insertionSort obj=new insertionSort(parentFrame);
                    obj.setVisible(true);
                    
                }
                else if(selectedOption.equals("Bubble Sort")){
                    bubbleSort obj=new bubbleSort(parentFrame);
                    obj.setVisible(true);
                    
                }

                else if(selectedOption.equals("Selection Sort")){
                    selectionSort obj=new selectionSort(parentFrame);
                    obj.setVisible(true);
                    
                }

                    else if(selectedOption.equals("Merge Sort")){
                    mergeSortGui obj=new mergeSortGui(parentFrame);
                    obj.setVisible(true);
                    
                }

                else{
                    quickSortGui obj=new quickSortGui(parentFrame);
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
        parentFrame=this;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}

