package University.gui;

import University.domains.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public final class GuiStudent extends GUI{

    private ArrayList<JTextField> winText = new ArrayList<>();
    private ArrayList<JLabel> infoStudentLabels = new ArrayList<>();
    private DefaultListModel listModel;
    private ArrayList<Subject> tempSubs = new ArrayList<>();

    public static final int ADD = 0;
    public static final int LIST = 1;

    public GuiStudent(int type) {
        super();

        if(type == ADD)
            newStudent();

        else if (type == LIST)
            listStudent();
    }

    private GuiStudent(Student student) {
        super();

        editStudent(student);
    }


    private void newStudent(){

        var frame = getFrame();
        var panel = getPanel();
        var layout = getLayout();
        var cons = getCons();

        frame.setTitle("Student Creation");

        var strings = new ArrayList<String>();
        strings.add("First Name :");
        strings.add("Last Name :");
        strings.add("Semester :");
        strings.add("AM :");
        strings.add("Email :");


        for(int i=0; i<strings.size(); i++) {

            cons.gridy++;
            cons.gridx = 0;
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
        cons.fill = GridBagConstraints.NONE;

        cons.gridx = 0;
        var button = new JButton("\u002b Semester");
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                try{
                    var semester = Integer.parseInt(winText.get(2).getText());
                    int cnt = tempSubs.size();

                    for(Subject sub : Storage.subjects)

                        if((semester == sub.getSemester()) && (!Subject.checkDuplicates(sub, tempSubs))){

                            listModel.addElement(sub.getName() + "(" + sub.getSemester() + ")");
                            tempSubs.add(sub);
                        }

                    if ((cnt == tempSubs.size()) && (tempSubs.size() != 0))
                        windowln("Error", "Semester " + semester + " subjects added already.", "error");

                    else if (tempSubs.size() == 0)
                        windowln("Error", "No subjects saved so far.", "Error");

                }
                catch (NumberFormatException e){
                    windowln("Error", "Semester should be a number", "error");
                }
            }
        });
        panel.add(button, cons);

        cons.gridx = 1;
        button = new JButton("\u002b");
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                new GuiSubject(listModel, tempSubs);
            }
        });
        panel.add(button, cons);


        cons.gridy++;
        panel.add(new JLabel(""), cons);

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
                    var semester           = 0;
                    var am                 = 0;
                    var email       = winText.get(4).getText();

                    try{
                        semester      = Integer.parseInt(winText.get(2).getText());
                        am            = Integer.parseInt(winText.get(3).getText());
                    }
                    catch (NumberFormatException e){
                        windowln("Error", "Semester and AM should be numbers", "error");
                    }

                    try{
                        if(tempSubs.size() > 0){

                            Student.newStudent(firstName, lastName, (short) semester, am, email, tempSubs);
                        }
                        else
                            Student.newStudent(firstName, lastName, (short) semester, am, email);

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


        frame.setSize(new Dimension(400, 500));
    }

    private void listStudent(){

        var frame = getFrame();
        var panel = getPanel();
        var layout = getLayout();
        var cons = getCons();

        frame.setTitle("Student List");

        String[] studNames = new String[Storage.students.size() + 1];
        studNames[0] = "";

        for(int i=0; i<Storage.students.size(); i++)
        {
            studNames[i+1] = Storage.students.get(i).getLastName() + " " + Storage.students.get(i).getFirstName();
        }
        JComboBox comboBox = new JComboBox(studNames);


        cons.gridwidth = 2;
        panel.add(comboBox, cons);
        cons.gridwidth = 1;
        comboBox.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent event){

                listModel.clear();
                int selection = comboBox.getSelectedIndex();

                if (selection != 0){

                    String firstName = Storage.students.get(selection-1).getFirstName();
                    String lastName  = Storage.students.get(selection-1).getLastName();
                    String semester  = "" + Storage.students.get(selection-1).getSemester();
                    String am        = "" + Storage.students.get(selection-1).getAm();
                    String email     = Storage.students.get(selection-1).getEmail();

                    infoStudentLabels.get(0).setText(firstName);
                    infoStudentLabels.get(1).setText(lastName);
                    infoStudentLabels.get(2).setText(semester);
                    infoStudentLabels.get(3).setText(am);
                    infoStudentLabels.get(4).setText(email);

                    ArrayList<Subject> subjects = Storage.students.get(selection-1).getSubjects();

                    for(Subject sub : subjects)
                    {
                        listModel.addElement(sub.getName());
                    }
                }

            }
        });

        cons.fill = GridBagConstraints.BOTH;
        cons.gridwidth = 1;

        var strings = new ArrayList<String>();
        strings.add("First Name :");
        strings.add("Last Name :");
        strings.add("Semester :");
        strings.add("AM :");
        strings.add("Email :");

        for(int i=0; i<strings.size(); i++) {

            cons.gridx = 0;
            cons.gridy++;
            panel.add(new JLabel(strings.get(i)), cons);

            cons.gridx = 1;
            infoStudentLabels.add(new JLabel(""));
            panel.add(infoStudentLabels.get(i), cons);

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
        cons.gridwidth = 2;
        var button = new JButton("Edit");
        button.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent event){
                int selection = comboBox.getSelectedIndex();

                if(selection != 0){
                    var student = Storage.students.get(selection-1);
                    new GuiStudent(student);

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
                    var student = Storage.students.get(selection-1);

                    for(Subject subject : student.getSubjects())
                        subject.getStudents().remove(student);

                    Storage.students.remove(student);
                    Storage.orderStudents();

                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    windowln("Student removed", student.getLastName() + " " + student.getFirstName() + " has been deleted.", "error");
                }
            }
        });
        panel.add(button, cons);


        frame.setSize(new Dimension(400, 400));
    }

    private void editStudent(Student student){

        var frame = getFrame();
        var panel = getPanel();
        var layout = getLayout();
        var cons = getCons();

        frame.setTitle(student.getUniqueID());

        var strings = new ArrayList<String>();
        strings.add("First Name :");
        strings.add("Last Name :");
        strings.add("Semester :");
        strings.add("AM :");
        strings.add("Email :");

        var studentInfo = new ArrayList<String>();
        studentInfo.add(student.getFirstName());
        studentInfo.add(student.getLastName());
        studentInfo.add(String.valueOf(student.getSemester()));
        studentInfo.add(String.valueOf(student.getAm()));
        studentInfo.add(student.getEmail());


        for(int i=0; i<strings.size(); i++) {

            cons.gridy++;
            cons.gridx = 0;
            panel.add(new JLabel(strings.get(i)), cons);

            cons.gridx = 1;
            if(i == 3)
            {
                var text = new JLabel(studentInfo.get(i));
                panel.add(text, cons);
            }
            else{
                var text = new JTextField(studentInfo.get(i));
                panel.add(text, cons);
                winText.add(text);              //without AM
            }
        }

        cons.gridy++;
        cons.gridx = 0;
        panel.add(new JLabel("Subjects :"),cons);

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


        for(Subject sub : student.getSubjects()){
            listModel.addElement(sub.getName() + "(" + sub.getSemester() + ")");
            tempSubs.add(sub);
        }


        cons.gridy++;
        cons.fill = GridBagConstraints.NONE;

        cons.gridx = 0;
        var button = new JButton("\u002b Semester");
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                try{
                    var semester = Integer.parseInt(winText.get(2).getText());
                    int cnt = tempSubs.size();

                    for(Subject sub : Storage.subjects)

                        if((semester == sub.getSemester()) && (!Subject.checkDuplicates(sub, tempSubs))){

                            listModel.addElement(sub.getName() + "(" + sub.getSemester() + ")");
                            tempSubs.add(sub);
                        }

                    if (cnt == tempSubs.size())
                        windowln("Error", "Semester " + semester + " subjects added already.", "error");

                }
                catch (NumberFormatException e){
                    windowln("Error", "Semester should be a number", "error");
                }
            }
        });
        panel.add(button, cons);

        cons.gridx = 1;
        button = new JButton("\u002b");
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                new GuiSubject(listModel, tempSubs);
            }
        });
        panel.add(button, cons);


        cons.gridy++;
        panel.add(new JLabel(""), cons);

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
                    var semester           = 0;
                    var am            = student.getAm();
                    var email       = winText.get(3).getText();

                    try{
                        semester      = Integer.parseInt(winText.get(2).getText());
                    }
                    catch (NumberFormatException e){
                        windowln("Error", "Semester should be a number", "error");
                    }

                    try{

                        student.setFirstName(firstName);
                        student.setLastName(lastName);
                        student.setSemester((short) semester);
                        student.setEmail(email);

                        for (Subject subjectOld : student.getSubjects())
                            subjectOld.getStudents().remove(student);

                        for (Subject subjectNew : tempSubs)
                            subjectNew.getStudents().add(student);

                        if(tempSubs.size() > 0)
                            student.setSubjects(tempSubs);
                        else
                            student.getSubjects().clear();

                        Storage.rewriteStudents();
                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                        windowln("Edit Complete", lastName + " " + firstName + " has been adjusted.", "");
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


        frame.setSize(new Dimension(400, 500));
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