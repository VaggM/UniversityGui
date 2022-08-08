package University.gui;

import University.domains.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public final class GuiTeacher extends GUI{

    private ArrayList<JTextField> winText = new ArrayList<>();
    private ArrayList<JLabel> infoTeacherLabels = new ArrayList<>();
    private DefaultListModel listModel;
    private ArrayList<Subject> tempSubs = new ArrayList<>();

    public static final int ADD = 0;
    public static final int LIST = 1;


    public GuiTeacher(int type){
        super();

        if(type == ADD)
            newTeacher();

        else if (type == LIST)
            listTeacher();
    }

    private GuiTeacher(Teacher teacher){
        editTeacher(teacher);
    }


    private void newTeacher(){

        var frame = getFrame();
        var panel = getPanel();
        var layout = getLayout();
        var cons = getCons();

        frame.setTitle("Teacher Creation");


        var strings = new ArrayList<String>();
        strings.add("First Name :");
        strings.add("Last Name :");
        strings.add("Email :");


        for(int i=0; i<strings.size(); i++) {

            cons.gridx = 0;
            cons.gridy++;
            panel.add(new JLabel(strings.get(i)), cons);

            cons.gridx = 1;
            var text = new JTextField();
            panel.add(text, cons);

            winText.add(text);
        }

        cons.gridy++;
        cons.gridx = 0;
        panel.add(new JLabel("Subjects :"),cons);

        cons.gridx = 1;
        var listModel = new DefaultListModel();
        var list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(3);
        var scroll = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(150, 55));
        panel.add(scroll, cons);


        cons.gridy++;
        cons.fill = GridBagConstraints.NONE;

        cons.gridx = 1;
        var button = new JButton("\u002b");
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                new GuiSubject(listModel, tempSubs);
            }
        });
        panel.add(button, cons);


        cons.gridy++;
        panel.add(new JLabel(""),cons);


        cons.fill = GridBagConstraints.NONE;
        cons.gridy++;
        cons.gridx = 0;
        button = new JButton("Clear Subjects");

        button.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent event){
                tempSubs.clear();
                listModel.clear();
            }
        });
        panel.add(button, cons);

        cons.gridx = 1;
        button = new JButton("Complete");
        button.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent event){

                String missing = textCheck();

                if(missing.length() == 0)
                {
                    var firstName   = winText.get(0).getText();
                    var lastName    = winText.get(1).getText();
                    var email       = winText.get(2).getText();

                    try{
                        if(listModel.size() > 0){

                            Teacher.newTeacher(firstName, lastName, email, tempSubs);
                        }
                        else
                            Teacher.newTeacher(firstName, lastName, email);

                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                        windowln("Registration Complete", lastName + " " + firstName + " has been added.", "");
                    }
                    catch (Exceptions e)
                    {
                        windowln("Error", e.getMessage(), "error");
                    }
                }
                else
                    windowln("Error", "Some fields aren't complete.", "error");

            }
        });

        panel.add(button, cons);

        frame.setSize(new Dimension(400, 400));
    }
    private void listTeacher(){

        var frame = getFrame();
        var panel = getPanel();
        var layout = getLayout();
        var cons = getCons();

        frame.setTitle("Teacher List");

        String[] teachNames = new String[Storage.teachers.size() + 1];
        teachNames[0] = "";

        for(int i=0; i<Storage.teachers.size(); i++)
        {
            teachNames[i+1] = Storage.teachers.get(i).getLastName() + " " + Storage.teachers.get(i).getFirstName();
        }
        JComboBox comboBox = new JComboBox(teachNames);

        cons.gridwidth = 2;
        panel.add(comboBox, cons);
        cons.gridwidth = 1;

        comboBox.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent event){

                listModel.clear();

                int selection = comboBox.getSelectedIndex();

                if (selection != 0){

                    String firstName = Storage.teachers.get(selection-1).getFirstName();
                    String lastName  = Storage.teachers.get(selection-1).getLastName();
                    String email     = Storage.teachers.get(selection-1).getEmail();

                    infoTeacherLabels.get(0).setText(firstName);
                    infoTeacherLabels.get(1).setText(lastName);
                    infoTeacherLabels.get(2).setText(email);

                    ArrayList<Subject> subjects = Storage.teachers.get(selection-1).getSubjects();

                    for(Subject sub : subjects)
                        listModel.addElement(sub.getName());
                }

            }
        });

        cons.fill = GridBagConstraints.BOTH;
        cons.gridwidth = 1;

        var strings = new ArrayList<String>();
        strings.add("First Name :");
        strings.add("Last Name :");
        strings.add("Email :");

        for(int i=0; i<strings.size(); i++) {

            cons.gridx = 0;
            cons.gridy++;
            panel.add(new JLabel(strings.get(i)), cons);

            cons.gridx = 1;
            infoTeacherLabels.add(new JLabel(""));
            panel.add(infoTeacherLabels.get(i), cons);

        }

        cons.gridy++;
        cons.gridx = 0;
        panel.add(new JLabel("Subjects :"), cons);


        cons.gridx = 1;
        listModel = new DefaultListModel();
        var list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(3);
        var scroll = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(150, 55));
        panel.add(scroll, cons);

        cons.gridy++;
        cons.gridx = 0;
        panel.add(new JLabel("Students :"), cons);


        cons.gridx = 1;
        var listModelSub = new DefaultListModel();
        var listSub = new JList(listModelSub);
        listSub.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSub.setLayoutOrientation(JList.VERTICAL);
        listSub.setVisibleRowCount(3);

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                listModelSub.clear();

                try {

                    int select = list.getSelectedIndex();
                    var subName = (String) listModel.getElementAt(select);
                    var sub = Storage.searchSubject(subName);
                    var students = sub.getStudents();

                    for(Student student : students){
                        listModelSub.addElement(student.getLastName() + " " + student.getFirstName());
                    }

                }
                catch (Exceptions event) {
                    windowln("Error", event.getMessage(), "error");
                }


            }
        });

        scroll = new JScrollPane(listSub, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(150, 55));
        panel.add(scroll, cons);


        cons.gridy++;
        cons.gridx = 0;
        cons.gridwidth = 2;
        var button = new JButton("Edit");
        button.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent event){
                int selection = comboBox.getSelectedIndex();

                if(selection != 0){
                    var teacher = Storage.teachers.get(selection-1);
                    new GuiTeacher(teacher);

                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                }
            }
        });
        panel.add(button, cons);


        cons.gridy++;
        button = new JButton("Delete");
        button.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent event){

                int selection = comboBox.getSelectedIndex();

                if(selection != 0){
                    var teacher = Storage.teachers.get(selection-1);
                    Storage.teachers.remove(teacher);
                    Storage.orderTeachers();

                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    windowln("Teacher removed", teacher.getLastName() + " " + teacher.getFirstName() + " has been deleted.", "error");
                }
            }
        });
        panel.add(button, cons);

        frame.setSize(new Dimension(400, 400));
    }
    private void editTeacher(Teacher teacher){

        var frame = getFrame();
        var panel = getPanel();
        var layout = getLayout();
        var cons = getCons();

        frame.setTitle(teacher.getUniqueID());


        var strings = new ArrayList<String>();
        strings.add("First Name :");
        strings.add("Last Name :");
        strings.add("Email :");

        var teacherInfo = new ArrayList<String>();
        teacherInfo.add(teacher.getFirstName());
        teacherInfo.add(teacher.getLastName());
        teacherInfo.add(teacher.getEmail());

        for(int i=0; i<strings.size(); i++) {

            cons.gridx = 0;
            cons.gridy++;
            panel.add(new JLabel(strings.get(i)), cons);

            cons.gridx = 1;
            if(i != 2)
            {
                var text = new JLabel(teacherInfo.get(i));
                panel.add(text, cons);
            }
            else{
                var text = new JTextField(teacherInfo.get(i));
                panel.add(text, cons);
                winText.add(text);              //without AM
            }
        }

        cons.gridy++;
        cons.gridx = 0;
        panel.add(new JLabel("Subjects :"),cons);

        cons.gridx = 1;
        var listModel = new DefaultListModel();
        var list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(3);
        var scroll = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(150, 55));
        panel.add(scroll, cons);


        for(Subject sub : teacher.getSubjects()){
            listModel.addElement(sub.getName() + "(" + sub.getSemester() + ")");
            tempSubs.add(sub);
        }

        cons.gridy++;
        cons.fill = GridBagConstraints.NONE;

        cons.gridx = 1;
        var button = new JButton("\u002b");
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                new GuiSubject(listModel, tempSubs);
            }
        });
        panel.add(button, cons);


        cons.gridy++;
        panel.add(new JLabel(""),cons);


        cons.fill = GridBagConstraints.NONE;
        cons.gridy++;
        cons.gridx = 0;
        button = new JButton("Clear Subjects");

        button.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent event){
                tempSubs.clear();
                listModel.clear();
            }
        });
        panel.add(button, cons);

        cons.gridx = 1;
        button = new JButton("Complete");
        button.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent event){

                String missing = textCheck();

                if(missing.length() == 0)
                {
                    var email       = winText.get(0).getText();


                    teacher.setEmail(email);

                    if(tempSubs.size() > 0)
                        teacher.setSubjects(tempSubs);
                    else
                        teacher.getSubjects().clear();


                    Storage.rewriteTeachers();
                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    windowln("Registration Complete", teacher.getLastName() + " " + teacher.getFirstName() + " has been adjusted.", "");

                }
                else
                    windowln("Error", "Some fields aren't complete.", "error");

            }
        });

        panel.add(button, cons);

        frame.setSize(new Dimension(400, 400));
    }

    private String textCheck() {
        String missingText = "";

        for(int i=0; i<winText.size(); i++)
        {
            if (winText.get(i).getText().length() == 0)
                missingText += i;
        }

        return missingText;
    }

}
