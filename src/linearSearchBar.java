package src;
import javax.swing.*;

import gui_option.Main;

import java.awt.*;
import java.awt.event.*;

public class linearSearchBar extends JFrame{
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final int BAR_WIDTH = 30;
    private static final int ANIMATION_DELAY = 500;
    private static final Font NUMBER_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 12);

    private int[] array;
    private int target;
    private int currentIndex;

    public linearSearchBar(int[] array, int target,JFrame parFrame) {
        this.array = array;
        this.target = target;
        this.currentIndex = 0;

        setTitle("Animated Linear Search");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
         setLayout(new BorderLayout());
        parFrame.setVisible(false);

         // creates an object for search panel
        SearchPanel searchPanel = new SearchPanel();
        add(searchPanel,BorderLayout.CENTER);

        //display the time complexity of the function
        JLabel content=new JLabel("Time Complexity: O(n)");
        content.setHorizontalAlignment(SwingConstants.CENTER);
        add(content,BorderLayout.NORTH);

        JButton backButton =new JButton("Back");
        backButton.setPreferredSize(new Dimension(30, 30));
        backButton.addActionListener(e -> {
            Main obj=new Main();
            obj.setVisible(true);
            dispose();
        });
        add(backButton,BorderLayout.SOUTH);


     
        Timer timer = new Timer(ANIMATION_DELAY, new TimerListener());
        timer.start();
    }

    private class SearchPanel extends JPanel {

        // perform the graphical function i.e. bar graph plotting
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

           int x = (getWidth() - array.length * BAR_WIDTH) / 2;
           int y = getHeight() / 2;
        
            for (int i = 0; i < array.length; i++) {
                int barHeight = array[i] * 10;
                if (i == currentIndex) {
                    g2d.setColor(Color.gray);
                } else {
                    g2d.setColor(Color.yellow);
                }
                g2d.fillRect(x, y - barHeight, BAR_WIDTH, barHeight);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(x, y - barHeight, BAR_WIDTH, barHeight);


          g.drawString(Integer.toString(array[i]), x, y - barHeight - 5);
                
                x += BAR_WIDTH;
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(WIDTH, HEIGHT);
        }
    }

    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentIndex >= array.length) {
                ((Timer) e.getSource()).stop();
                JOptionPane.showMessageDialog(linearSearchBar.this,
                        "Element not found in the array.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            if (array[currentIndex] == target) {
                ((Timer) e.getSource()).stop();
                JOptionPane.showMessageDialog(linearSearchBar.this,
                        "Target found at index " + currentIndex + ".", "Search Result", JOptionPane.INFORMATION_MESSAGE);
            }

            currentIndex++;
            repaint();
        }
    }
}
