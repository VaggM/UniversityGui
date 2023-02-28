package University.gui;

import University.domains.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public final class GuiDepartment extends GUI {


    private ArrayList<JTextField> winText = new ArrayList<>();
    private ArrayList<JLabel> infoDepartmentLabels = new ArrayList<>();

    public static final int ADD = 0;
    public static final int LIST = 1;


    public GuiDepartment(int type){
        super();

        if(type == ADD)
            newDepartment();

        else if (type == LIST)
            listDepartment();
    }
    private void newDepartment(){

        var frame = getFrame();
        var panel = getPanel();
        var layout = getLayout();
        var cons = getCons();

        frame.setTitle("Department Creation");


        var strings = new ArrayList<String>();
        strings.add("Name :");


        for(int i=0; i<strings.size(); i++) {

            cons.gridx = 0;
            cons.gridy++;
            panel.add(new JLabel(strings.get(i)), cons);

            cons.gridx = 2;
            var text = new JTextField();
            panel.add(text, cons);

            winText.add(text);
        }

        cons.gridy++;
        panel.add(new JLabel(""),cons);

        cons.fill = GridBagConstraints.NONE;
        cons.gridy++;
        JButton button = new JButton("Complete");
        button.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent event){

                String missing = textCheck();

                if(missing.length() == 0)
                {
                    var name   = winText.get(0).getText();

                    try{
                        Department.newDepartment(name);

                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                        windowln("Registration Complete", name + " has been added.", "");
                    }
                    catch (Exceptions e)
                    {
                        windowln("Error", e.getMessage(), "error");
                    }
                }
                else
                    windowln("Error", "Add a name first.", "error");

            }
        });

        panel.add(button, cons);


        frame.setSize(new Dimension(150*2, 50*3));
    }
    private void listDepartment(){

        var frame = getFrame();
        var panel = getPanel();
        var layout = getLayout();
        var cons = getCons();

        frame.setTitle("Department List");

        String[] depNames = new String[Storage.departments.size() + 1];
        depNames[0] = "";

        for(int i=0; i<Storage.departments.size(); i++)
        {
            depNames[i+1] = Storage.departments.get(i).getName();
        }
        JComboBox comboBox = new JComboBox(depNames);

        cons.gridwidth = 2;
        panel.add(comboBox, cons);
        cons.gridwidth = 1;

        comboBox.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent event){

                int selection = comboBox.getSelectedIndex();

                if (selection != 0){

                    String name = Storage.departments.get(selection-1).getName();

                    infoDepartmentLabels.get(0).setText(name);

                }

            }
        });

        cons.fill = GridBagConstraints.BOTH;
        cons.gridwidth = 1;

        var strings = new ArrayList<String>();
        strings.add("Name :");


        for(int i=0; i<strings.size(); i++) {

            cons.gridx = 0;
            cons.gridy++;
            panel.add(new JLabel(strings.get(i)), cons);

            cons.gridx = 1;
            infoDepartmentLabels.add(new JLabel(""));
            panel.add(infoDepartmentLabels.get(i), cons);

        }

        cons.gridy++;
        cons.gridx = 0;
        cons.gridwidth = 2;
        var button = new JButton("Delete");
        button.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent event){
                int selection = comboBox.getSelectedIndex();

                if(selection != 0){
                    var department = Storage.departments.get(selection-1);
                    Storage.departments.remove(department);
                    Storage.orderDepartments();

                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    windowln("Department removed", department.getName() + " has been deleted.", "error");
                }
            }
        });
        panel.add(button, cons);

        frame.setSize(new Dimension(150*2, 50*4));
    }

    private String textCheck(){
        String missingText = "";

        for(int i=0; i<winText.size(); i++)
        {
            if (winText.get(i).getText().length() == 0)
                missingText += i;
        }

        return missingText;
    }

}
