import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class GUI_Menu extends JFrame implements ActionListener {
    MyDirectedWeightedGraphAlgorithms graph = new MyDirectedWeightedGraphAlgorithms();

    static JLabel label;
    static JLabel answer;

    public static void menu()
    {
        GUI_Menu gui_menu = new GUI_Menu();
        JMenuBar menuBar = new JMenuBar();
        JMenu main_menu, load ,run_alogs;
        JMenuItem save, gson_g1, gson_g2, gson_g3, show_graph, edit;
        JMenuItem isConnected, shortestPathDist, shortestPath, center, tsp;
        JFrame frame = new JFrame("Ex2 - Data Structures and Algorithms On Graphs");
        label = new JLabel("Current Function: ");
        answer = new JLabel("");
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
        frame.setJMenuBar(menuBar);
        frame.add(label);
        //answer.setLocation(label.getX(), label.getY()-10);
        //frame.add(answer);
        save.addActionListener(gui_menu);
        gson_g1.addActionListener(gui_menu);
        gson_g2.addActionListener(gui_menu);
        gson_g3.addActionListener(gui_menu);
        edit.addActionListener(gui_menu);
        show_graph.addActionListener(gui_menu);
        isConnected.addActionListener(gui_menu);
        shortestPath.addActionListener(gui_menu);
        shortestPathDist.addActionListener(gui_menu);
        center.addActionListener(gui_menu);
        tsp.addActionListener(gui_menu);

        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        label.setText("Current Function: "+e.getActionCommand());
        switch (e.getActionCommand()) {
            case "G1" -> this.graph.load("data/G1.json");
            case "G2" -> this.graph.load("data/G2.json");
            case "G3" -> this.graph.load("data/G3.json");
            case "Save" -> this.graph.save("output.json");
            case "Edit" -> System.out.println("Edit");
            case "Show Graph" -> System.out.println("Show Graph");
            case "Is Connected" -> answer.setText("Is Connected: "+this.graph.isConnected());
            case "Shortest Path" -> System.out.println("Shortest Path");
            case "Shortest Path Distance" -> System.out.println("Shortest Path Distance");
            case "Center" -> System.out.println("Center");
            case "TSP" -> System.out.println("TSP");
            default -> throw new IllegalStateException("Unexpected value: " + e.getActionCommand());
        }
    }

    public static void main(String[] args) {
       menu();
    }
}