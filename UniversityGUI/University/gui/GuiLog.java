package University.gui;

import University.domains.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public final class GuiLog extends GUI{

    private ArrayList<JTextField> winText = new ArrayList<>();
    private ArrayList<JLabel> infoSubjectLabels = new ArrayList<>();

    private DefaultListModel listModel;


    public GuiLog(ArrayList<String> logs){
        super();
        listLog(logs);
    }


    private void listLog(ArrayList<String> logs){

        var frame = getFrame();
        var panel = getPanel();
        var layout = getLayout();
        var cons = getCons();

        frame.setTitle("Logs");

        listModel = new DefaultListModel();
        var list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(5);
        var scroll = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(300, 300));
        panel.add(scroll, cons);

        for(String string : logs)
            listModel.addElement(string);

        frame.setSize(new Dimension(400, 400));
    }
}
