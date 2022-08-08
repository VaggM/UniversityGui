package University.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public final class GuiLog extends GUI{

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

        var listModel = new DefaultListModel();
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
