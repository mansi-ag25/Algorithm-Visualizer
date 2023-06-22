package gui_option;
import javax.swing.*;
import src.linearSearchBar;
import java.awt.*;
import java.awt.event.*;

public class linearSearch extends JFrame{
    private JTextField arrayField;
    private JTextField searchField;
    private JButton searchButton;
    private JFrame parFrame;
   
   

    public linearSearch(JFrame parentFrame) {
        // Set up the JFrame
        setTitle("Linear Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        parentFrame.setVisible(false); 
        // Create the text fields
        arrayField = new JTextField(15);
        searchField = new JTextField(10);

        // Create the button for search
        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the array and search element from text fields
                String arrayText = arrayField.getText();
                String searchElementText = searchField.getText();
                if(arrayText.isEmpty() || searchElementText.isEmpty()){
                     JOptionPane.showMessageDialog(linearSearch.this,
                        "Array or search element can't be empty");
                }
                // Convert arrayText to integer array
                String[] arrayItems = arrayText.split(",");
                int[] array = new int[arrayItems.length];
                for (int i = 0; i < arrayItems.length; i++) {
                    array[i] = Integer.parseInt(arrayItems[i]);
                }

                // Convert searchElementText to integer
                int searchElement = Integer.parseInt(searchElementText);

                // Call the linear search method 
                linearSearchBar obj=new linearSearchBar(array, searchElement,parFrame);
                obj.setVisible(true);  
                            
            }
        });

        // Add the components to the JFrame
        add(new JLabel("Array (comma-separated):"));
        add(arrayField);
        add(new JLabel("Search Element:"));
        add(searchField);
        add(searchButton);

        // Set the JFrame size and make it visible
        setSize(300, 200);
        setVisible(true);
        parFrame=this;
    }
}
