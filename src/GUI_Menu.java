import api.EdgeData;
import api.NodeData;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;

public class GUI_Menu extends JFrame implements ActionListener {
    public MyDirectedWeightedGraphAlgorithms graph = new MyDirectedWeightedGraphAlgorithms();
    int scrW;
    int scrH;
    int innerW;
    int innerH;

    public GUI_Menu(){
        init();
    }

    private void init() {
//        GUI_Menu gui_menu = new GUI_Menu();
        JMenuBar menuBar = new JMenuBar();
        JMenu main_menu, load ,run_alogs;
        JMenuItem save, gson_g1, gson_g2, gson_g3, show_graph, edit;
        JMenuItem isConnected, shortestPathDist, shortestPath, center, tsp;
//        JFrame frame = new JFrame("Ex2 - Data Structures and Algorithms On Graphs");
        //JLabel label = new JLabel("Current Function: ");
//        this.setName("Ex2 - Data Structures and Algorithms On Graphs");
        this.setTitle("Ex2 - Data Structures and Algorithms On Graphs");
        main_menu = new JMenu("Menu");
        load = new JMenu("Load");
        run_alogs = new JMenu("Run Algorithms");
        save = new JMenuItem("Save");
        gson_g1 = new JMenuItem("G1");
        gson_g2 = new JMenuItem("G2");
        gson_g3 = new JMenuItem("G3");
        show_graph = new JMenuItem("Show Graph");
        edit = new JMenuItem("Edit");
        isConnected = new JMenuItem("Is Connected");
        shortestPath = new JMenuItem("Shortest Path");
        shortestPathDist = new JMenuItem("Shortest Path Distance");
        center = new JMenuItem("Center");
        tsp = new JMenuItem("TSP");

        load.add(gson_g1);
        load.add(gson_g2);
        load.add(gson_g3);

        run_alogs.add(isConnected);
        run_alogs.add(shortestPath);
        run_alogs.add(shortestPathDist);
        run_alogs.add(center);
        run_alogs.add(tsp);

        main_menu.add(save);
        main_menu.add(load);
        main_menu.add(edit);
        main_menu.add(show_graph);
        main_menu.add(run_alogs);


        menuBar.add(main_menu);
        this.setJMenuBar(menuBar);
        //frame.add(label);

        save.addActionListener(this);
        gson_g1.addActionListener(this);
        gson_g2.addActionListener(this);
        gson_g3.addActionListener(this);
        edit.addActionListener(this);
        show_graph.addActionListener(this);
        isConnected.addActionListener(this);
        shortestPath.addActionListener(this);
        shortestPathDist.addActionListener(this);
        center.addActionListener(this);
        tsp.addActionListener(this);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        pack(); // Need this, otherwise insets() show as 0.
        scrW = (int)screenSize.getWidth();
        scrH = (int)screenSize.getHeight();
        innerW = scrW - getInsets().left - getInsets().right;
        innerH = scrH - getInsets().top - getInsets().bottom;
        setSize(scrW, scrH);
        setVisible(true);

    }


    public void actionPerformed(ActionEvent e)
    {
        System.out.println(e.getActionCommand() + " selected");
        switch (e.getActionCommand()) {
            case "G1" -> this.graph.load("data/G1.json");
            case "G2" -> this.graph.load("data/G2.json");
            case "G3" -> this.graph.load("data/G3.json");
            case "Save" -> this.graph.save("output.json");
            case "Edit" -> System.out.println("Edit");
            case "Show Graph" -> this.setContentPane(new GUI_Graph((MyDirectedWeightedGraph) this.graph.getGraph(),innerH,innerW));
            case "Is Connected" -> System.out.println("Is Connected");
            case "Shortest Path" -> System.out.println("Shortest Path");
            case "Shortest Path Distance" -> System.out.println("Shortest Path Distance");
            case "Center" -> System.out.println("Center");
            case "TSP" -> System.out.println("TSP");
        }
    }

    public static void main(String[] args) {
       new GUI_Menu();
    }
}