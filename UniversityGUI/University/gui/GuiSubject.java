package University.gui;

import University.domains.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public final class GuiSubject extends GUI{

    private ArrayList<JTextField> winText = new ArrayList<>();
    private ArrayList<JLabel> infoSubjectLabels = new ArrayList<>();

    private DefaultListModel listModel;

    public static final int ADD = 0;
    public static final int LIST = 1;


    public GuiSubject(int type){
        super();

        switch (type){
            case ADD:
                newSubject();
                break;

            case LIST:
                listSubject();
                break;
        }
    }

    public GuiSubject(Subject subject){
        super();

        editSubject(subject);
    }

    public GuiSubject(DefaultListModel listModel,ArrayList<Subject> subjects){
        selectSubject(listModel, subjects);
    }

    private void newSubject(){

        var frame = getFrame();
        var panel = getPanel();
        var layout = getLayout();
        var cons = getCons();

        frame.setTitle("Subject Creation");


        var strings = new ArrayList<String>();
        strings.add("Name :");
        strings.add("ID :");
        strings.add("Semester :");


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
                    var id     = winText.get(1).getText();
                    var semester      = 0;

                    try{

                        semester      = Integer.parseInt(winText.get(2).getText());

                        Subject.newSubject(name, id, (short) semester);

                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                        windowln("Registration Complete", name + " has been added.", "");
                    }
                    catch (Exceptions e)
                    {
                        windowln("Error", e.getMessage(), "error");
                    }
                    catch (NumberFormatException e){
                        windowln("Error", "Semester and AM should be numbers", "error");
                    }

                }
                else
                    windowln("Error", "Some fields aren't complete.", "error");
            }
        });

        panel.add(button, cons);

        frame.setSize(new Dimension(150*2, 50*5));

    }
    private void listSubject(){

        var frame = getFrame();
        var panel = getPanel();
        var layout = getLayout();
        var cons = getCons();

        frame.setTitle("Subject List");

        String[] subNames = new String[Storage.subjects.size() + 1];
        subNames[0] = "";

        for(int i=0; i<Storage.subjects.size(); i++)
        {
            subNames[i+1] = Storage.subjects.get(i).getName() + "(" + Storage.subjects.get(i).getSemester() +")";
        }
        JComboBox comboBox = new JComboBox(subNames);

        cons.gridwidth = 2;
        panel.add(comboBox, cons);
        cons.gridwidth = 1;
        comboBox.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent event){

                listModel.clear();

                int selection = comboBox.getSelectedIndex();

                if (selection != 0){

                    String name = Storage.subjects.get(selection-1).getName();
                    String id = Storage.subjects.get(selection-1).getUnique_id();
                    String semester = "" + Storage.subjects.get(selection-1).getSemester();

                    infoSubjectLabels.get(0).setText(name);
                    infoSubjectLabels.get(1).setText(id);
                    infoSubjectLabels.get(2).setText(semester);

                    ArrayList<Student> students = Storage.subjects.get(selection-1).getStudents();

                    for(Student stud : students)
                    {
                        listModel.addElement(stud.getLastName() + " " + stud.getFirstName());
                    }
                }

            }
        });

        cons.fill = GridBagConstraints.BOTH;
        cons.gridwidth = 1;

        var strings = new ArrayList<String>();
        strings.add("Name :");
        strings.add("ID :");
        strings.add("Semester :");


        for(int i=0; i<strings.size(); i++) {

            cons.gridx = 0;
            cons.gridy++;
            panel.add(new JLabel(strings.get(i)), cons);

            cons.gridx = 1;
            infoSubjectLabels.add(new JLabel(""));
            panel.add(infoSubjectLabels.get(i), cons);

        }

        cons.gridy++;
        cons.gridx = 0;
        panel.add(new JLabel("Students :"), cons);


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
                    var subject = Storage.subjects.get(selection-1);
                    new GuiSubject(subject);

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
                    var subject = Storage.subjects.get(selection-1);
                    Storage.subjects.remove(subject);
                    Storage.orderSubjects();

                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    windowln("Subject removed", subject.getName() + " has been deleted.", "error");
                }
            }
        });
        panel.add(button, cons);

        frame.setSize(new Dimension(400, 400));
    }
    private void editSubject(Subject subject){

        var frame = getFrame();
        var panel = getPanel();
        var layout = getLayout();
        var cons = getCons();

        frame.setTitle(subject.getUniqueID());


        var strings = new ArrayList<String>();
        strings.add("Name :");
        strings.add("ID :");
        strings.add("Semester :");

        var subjectInfo = new ArrayList<String>();
        subjectInfo.add(subject.getName());
        subjectInfo.add(subject.getUnique_id());
        subjectInfo.add(String.valueOf(subject.getSemester()));

        for(int i=0; i<strings.size(); i++) {

            cons.gridy++;
            cons.gridx = 0;
            panel.add(new JLabel(strings.get(i)), cons);

            cons.gridx = 1;
            if(i == 1)
            {
                var text = new JLabel(subjectInfo.get(i));
                panel.add(text, cons);
            }
            else{
                var text = new JTextField(subjectInfo.get(i));
                panel.add(text, cons);
                winText.add(text);              //without AM
            }
        }

        cons.gridy++;
        panel.add(new JLabel(""),cons);

        cons.fill = GridBagConstraints.NONE;
        cons.gridy++;
        JButton button = new JButton("Complete");
        button.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent event){

                String missing = textCheck();

                if (missing.length() == 0)
                {
                    var name = winText.get(0).getText();
                    var semester = 0;

                    try{
                        semester = Integer.parseInt(winText.get(1).getText());
                    }
                    catch(NumberFormatException e)
                    {
                        windowln("Error", "Semester must be a number.", "error");
                    }

                    try{

                        subject.setName(name);
                        subject.setSemester((short)semester);

                        Storage.rewriteSubjects();
                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                        windowln("Edit Complete", name + " has been adjusted.", "");

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

        frame.setSize(new Dimension(150*2, 300));
    }

    private void selectSubject(DefaultListModel listModelOld, ArrayList<Subject> subjects){

        var frame = getFrame();
        var panel = getPanel();
        var layout = getLayout();
        var cons = getCons();

        frame.setTitle("Subject Selection");

        var listModel = new DefaultListModel();

        for(Subject sub : Storage.subjects)
            listModel.addElement(sub.getName() + "(" + sub.getSemester() + ")");


        var list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(3);
        list.setSelectedIndex(0);
        panel.add(new JScrollPane(list), cons);

        cons.gridy++;
        var button = new JButton("Add");
        button.setActionCommand("Add");
        button.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent event){

                int select = list.getSelectedIndex();

                try {
                    String subname = (String) listModel.getElementAt(select);
                    subname = subname.substring(0, subname.indexOf("("));

                    for(int i=0; i<listModelOld.size(); i++){

                        var subnameOld = (String) listModelOld.getElementAt(i);
                        subnameOld = subnameOld.substring(0, subnameOld.indexOf("("));

                        if (subname.equals(subnameOld))
                            throw new Exceptions(subname + " is added already.");
                    }

                    listModelOld.addElement(subname + "(" + Storage.searchSubject(subname).getSemester() +")");
                    subjects.add(Storage.searchSubject(subname));
                }
                catch (Exceptions e) {
                    windowln("Error", e.getMessage(), "error");
                }
                catch (ArrayIndexOutOfBoundsException e){
                    windowln("Error", "No Subjects saved.", "error");
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
