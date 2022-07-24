package University.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private GridBagLayout layout;
    private GridBagConstraints cons;


    public GUI() {

        frame = new JFrame("Default");
        panel = new JPanel();
        layout = new GridBagLayout();
        cons = new GridBagConstraints();

        panel.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));
        panel.setLayout(layout);

        cons.fill = GridBagConstraints.HORIZONTAL;                    //autofill space in grid
        cons.insets = new Insets(5,5,5,5);
        cons.weightx = 0.5;                                     //row or column size (x,y)
        cons.weighty = 0.5;
        cons.gridx = 0;                                         //location(x,y)
        cons.gridy = 0;
        cons.gridwidth = 1;                                     //size(x,y)
        cons.gridheight = 1;


        frame.add(panel, BorderLayout.CENTER);
        frame.setPreferredSize(new Dimension(400, 400));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JFrame getFrame(){
        return this.frame;
    }

    public JPanel getPanel(){
        return this.panel;
    }

    public GridBagLayout getLayout(){
        return this.layout;
    }

    public GridBagConstraints getCons(){
        return this.cons;
    }


    @Override
    public void actionPerformed(ActionEvent event) {

    }


    public static void windowln(String title, String message, String messType) {

        if(message.length() > 0){

            int type = JOptionPane.INFORMATION_MESSAGE;

            if ( messType.equals("error") )
                type = JOptionPane.ERROR_MESSAGE;

            JFrame frame = new JFrame("");
            JOptionPane.showMessageDialog(frame, message, title, type);
        }
    }



}