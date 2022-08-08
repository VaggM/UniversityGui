package University;

import University.domains.*;
import University.gui.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.*;

public class Main implements ActionListener{

    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private ArrayList<JButton> buttons = new ArrayList<JButton>();
    private ArrayList<GridBagConstraints> constraints = new ArrayList<GridBagConstraints>();

    private int count = 0;

    public Main(){

        frame = new JFrame("University");

        var university = new JLabel("\"University\"", SwingConstants.CENTER);
        var student = new JLabel("Student", SwingConstants.CENTER);
        var teacher = new JLabel("Teacher", SwingConstants.CENTER);
        var subject = new JLabel("Subject", SwingConstants.CENTER);
        var department = new JLabel("Department", SwingConstants.CENTER);

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));
        panel.setLayout(new GridBagLayout());

        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.BOTH;                    //autofill space in grid
        cons.insets = new Insets(5,5,5,5);
        cons.weightx = 0.5;                                     //row or column size (x,y)
        cons.weighty = 0.5;
        cons.gridx = 0;                                         //location(x,y)
        cons.gridy = 0;
        cons.gridwidth = 4;                                     //size(x,y)
        cons.gridheight = 1;
        panel.add(university, cons);

        cons.gridwidth = 1;
        cons.gridheight = 1;
        cons.gridy = 2;
        panel.add(student, cons);

        cons.gridx = 1;
        panel.add(teacher, cons);

        cons.gridx = 2;
        panel.add(subject, cons);

        cons.gridx = 3;
        panel.add(department, cons);

        //main buttons

        var strings = new ArrayList<String>();
        strings.add("Add");
        strings.add("Read");
        strings.add("Show");
        strings.add("Clear");

        JButton button;

        count = 0;
        for(int i=0; i<4; i++) {

            for (int j = 0; j < 4; j++) {

                button = new JButton(strings.get(j));
                cons.gridx = i;
                cons.gridy = 4 + j;
                button.setActionCommand("" + ++count);
                button.addActionListener(this);
                panel.add(button, cons);

                buttons.add(button);
                constraints.add(cons);
            }
        }

        //window end

        cons.fill = GridBagConstraints.NONE;
        cons.gridwidth = 2;
        cons.gridx = 0;
        cons.gridy = 8;
        panel.add(new JLabel(""), cons);

        cons.gridy = 9;
        button = new JButton("Show Log");
        button.setActionCommand("" + ++count);
        button.addActionListener(this);
        panel.add(button, cons);

        buttons.add(button);
        constraints.add(cons);

        cons.gridx = 2;
        button = new JButton("Order Lists");
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(Storage.students.size() > 0){
                    Storage.orderStudents();
                    GUI.windowln("Ordering Complete", "Students are in order.", "");
                }
                else{
                    GUI.windowln("Error", "No students to order.", "error");
                }

                if(Storage.teachers.size() > 0){
                    Storage.orderTeachers();
                    GUI.windowln("Ordering Complete", "Teachers are in order.", "");
                }
                else{
                    GUI.windowln("Error", "No teachers to order.", "error");
                }

                if(Storage.subjects.size() > 0){
                    Storage.orderSubjects();
                    GUI.windowln("Ordering Complete", "Subjects are in order.", "");
                }
                else{
                    GUI.windowln("Error", "No subjects to order.", "error");
                }

                if(Storage.departments.size() > 0){
                    Storage.orderDepartments();
                    GUI.windowln("Ordering Complete", "Departments are in order.", "");
                }
                else{
                    GUI.windowln("Error", "No departments to order.", "error");
                }
            }
        });
        panel.add(button, cons);

        cons.gridx = 0;
        cons.gridy = 10;
        button = new JButton("Clear Log");
        button.setActionCommand("" + ++count);
        button.addActionListener(this);
        panel.add(button, cons);

        buttons.add(button);
        constraints.add(cons);

        label = new JLabel();
        cons.gridx = 2;
        panel.add(label, cons);

        buttons.add(button);
        constraints.add(cons);

        cons.gridy = 11;
        cons.gridx = 0;
        button = new JButton("Clear All Files");
        button.setActionCommand("" + ++count);
        button.addActionListener(this);
        panel.add(button, cons);

        buttons.add(button);
        constraints.add(cons);

        cons.gridy = 12;
        panel.add(new JLabel(""), cons);


        frame.add(panel, BorderLayout.CENTER);
        frame.setPreferredSize(new Dimension(400, 400));
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

                int input = JOptionPane.showConfirmDialog(null, "Do you want to close the app?", "Exiting App", JOptionPane.YES_NO_OPTION);
                if (input == 0){
                    frame.dispose();
                }

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    public static void main(String [] args){

        Logger.logInit("*New session*\n","");
        new Main();

    }

    @Override
    public void actionPerformed(ActionEvent event) {

        int action = Integer.parseInt(((JButton) event.getSource()).getActionCommand());
        String message = "";

        switch (action){

            case 1 :

                new GuiStudent(GuiStudent.ADD);

                break;

            case 2 :

                message = Logger.readStudents();
                GUI.windowln("Read Result", message, "");

                break;

            case 3 :

                new GuiStudent(GuiStudent.LIST);

                break;

            case 4 :

                message = Logger.clearFiles("StudentLog.txt");
                GUI.windowln("Clear Result", message, "");

                break;

            case 5 :

                new GuiTeacher(GuiTeacher.ADD);

                break;

            case 6 :

                message = Logger.readTeachers();
                GUI.windowln("Read Result", message, "");

                break;

            case 7 :

                new GuiTeacher(GuiTeacher.LIST);

                break;

            case 8 :

                message = Logger.clearFiles("TeacherLog.txt");
                GUI.windowln("Clear Result", message, "");

                break;

            case 9 :

                new GuiSubject(GuiSubject.ADD);

                break;

            case 10 :

                message = Logger.readSubjects();
                GUI.windowln("Read Result", message, "");

                break;

            case 11 :

                new GuiSubject(GuiSubject.LIST);

                break;

            case 12 :

                message = Logger.clearFiles("SubjectLog.txt");
                GUI.windowln("Clear Result", message, "");

                break;

            case 13 :

                new GuiDepartment(GuiDepartment.ADD);

                break;

            case 14 :

                message = Logger.readDepartments();
                GUI.windowln("Read Result", message, "");

                break;

            case 15 :

                new GuiDepartment(GuiDepartment.LIST);

                break;

            case 16 :

                message = Logger.clearFiles("DepartmentLog.txt");
                GUI.windowln("Clear Result", message, "");

                break;

            case 17 :

                Logger.showLog();

                break;

            case 18 :

                message = Logger.clearFiles("Log.txt");
                GUI.windowln("Clear Result", message, "");
                Logger.logInit("*New session*","");

                break;

            case 19 :

                message = Logger.clearFiles("Log.txt", "StudentLog.txt", "TeacherLog.txt",
                        "SubjectLog.txt", "DepartmentLog.txt");

                GUI.windowln("Clear Result", message, "");
                Logger.logInit("*New session*","");

                break;
        }

    }
}
