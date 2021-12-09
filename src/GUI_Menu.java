import api.GeoLocation;
import api.NodeData;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class GUI_Menu extends JFrame implements ActionListener {
    public MyDirectedWeightedGraphAlgorithms graph = new MyDirectedWeightedGraphAlgorithms();
    static JLabel label;
    String name;
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
        JMenu main_menu, load ,run_alogs, edit;
        JMenuItem save, gson_g1, gson_g2, gson_g3, saved, show_graph;
        JMenuItem insert_node, insert_edge, remove_node, remove_edge;
        JMenuItem isConnected, shortestPathDist, shortestPath, center, tsp;
//        JFrame frame = new JFrame("Ex2 - Data Structures and Algorithms On Graphs");
//        this.setName("Ex2 - Data Structures and Algorithms On Graphs");
        this.setTitle("Ex2 - Data Structures and Algorithms On Graphs");
        label = new JLabel("Please select a function from the menu bar");
        main_menu = new JMenu("Menu");
        load = new JMenu("Load");
        run_alogs = new JMenu("Run Algorithms");
        save = new JMenuItem("Save");
        gson_g1 = new JMenuItem("G1");
        gson_g2 = new JMenuItem("G2");
        gson_g3 = new JMenuItem("G3");
        saved = new JMenuItem("Saved G");
        show_graph = new JMenuItem("Show Graph");
        edit = new JMenu("Edit");
        insert_node = new JMenuItem("Insert Node");
        insert_edge = new JMenuItem("Insert Edge");
        remove_node = new JMenuItem("Remove Node");
        remove_edge = new JMenuItem("Remove Edge");
        isConnected = new JMenuItem("Is Connected");
        shortestPath = new JMenuItem("Shortest Path");
        shortestPathDist = new JMenuItem("Shortest Path Distance");
        center = new JMenuItem("Center");
        tsp = new JMenuItem("TSP");

        load.add(gson_g1);
        load.add(gson_g2);
        load.add(gson_g3);
        load.add(saved);

        run_alogs.add(isConnected);
        run_alogs.add(shortestPath);
        run_alogs.add(shortestPathDist);
        run_alogs.add(center);
        run_alogs.add(tsp);

        edit.add(insert_node);
        edit.add(insert_edge);
        edit.add(remove_node);
        edit.add(remove_edge);

        main_menu.add(save);
        main_menu.add(load);
        main_menu.add(edit);
        main_menu.add(show_graph);
        main_menu.add(run_alogs);

        menuBar.add(main_menu);
        this.setJMenuBar(menuBar);
        this.add(label);

        save.addActionListener(this);
        gson_g1.addActionListener(this);
        gson_g2.addActionListener(this);
        gson_g3.addActionListener(this);
        saved.addActionListener(this);
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
            case "G1" -> load_graph("G1");
            case "G2" -> load_graph("G2");
            case "G3" -> load_graph("G3");
            case "Saved G" -> load_graph("Saved G");
            case "Save" -> this.graph.save("output.json");
            case "Insert Node" -> fills_funcs("Insert Node");
            case "Insert Edge" -> fills_funcs("Insert Edge");
            case "Remove Node" -> fills_funcs("Remove Node");
            case "Remove Edge" -> fills_funcs("Remove Edge");
            case "Show Graph" -> drawGraph();
            case "Is Connected" -> this.graph.isConnected();
            case "Shortest Path" -> fills_funcs("Shortest Path");
            case "Shortest Path Distance" -> fills_funcs("Shortest Path Distance");
            case "Center" -> this.graph.center();
            case "TSP" -> fills_funcs("TSP");
            default -> throw new IllegalStateException("This option doesn't exist!");
        }
    }

    private boolean drawGraph()
    {
        label.setText("Drawing "+this.name+" it might take a while");
        try {
            this.setContentPane(new GUI_Graph((MyDirectedWeightedGraph) this.graph.getGraph(),innerH,innerW));
            label.setText("Succeeded drawing "+ this.name);
            return true;
        }
        catch (RuntimeException e) {
            e.printStackTrace();
            label.setText("Error: couldn't draw "+ this.name);
            return false;
        }
    }

    public boolean load_graph(String name)
    {
        this.setContentPane(new JPanel());
        String fullPath;
        if (name.startsWith("G"))
            fullPath = "data/" + name + ".json";
        else
            fullPath = "output.json";
        String message = "The Graph "+name+" has uploaded!";
        try {
            this.graph.load(fullPath);
            this.name = name;
            label.setText(message);
            label.setVisible(true);
            return true;
        }
        catch (NullPointerException e){
            e.printStackTrace();
            label.setText("Error: The Graph "+name+" couldn't be uploaded!");
            label.setVisible(true);
            return false;
        }
    }

    public boolean fills_funcs(String functionName)
    {
        label.setText(functionName);
        TextBox.TextBoxInit(this.name);
        String input = TextBox.algorithm_input;
        String[] in = input.split(" ");
        switch (functionName){
            case "Insert Node":
                try {
                    int key_node = Integer.parseInt(in[0]); // if we have a node with this key???
                    double x = Double.parseDouble(in[1]);
                    double y = Double.parseDouble(in[2]);
                    MyGeoLocation location = new MyGeoLocation(x, y, 0);
                    Node newNode = new Node(key_node, location);
                    if (this.graph.getGraph().getNode(key_node) == null)
                        this.graph.getGraph().addNode(newNode); // new node
                    else // change exist
                        this.graph.getGraph().getNode(key_node).setLocation(location);
                    return true;
                }
                catch (IllegalArgumentException | NullPointerException e){
                e.printStackTrace();
                return false;
            }
            case "Insert Edge":
                try {
                    int src_new = Integer.parseInt(in[0]);
                    int dest_new = Integer.parseInt(in[1]);
                    double weight_new = Double.parseDouble(in[2]);
                    this.graph.getGraph().connect(src_new, dest_new, weight_new);
                    return true;
                }
                catch (IllegalArgumentException | NullPointerException e){
                    e.printStackTrace();
                    return false;
                }
            case "Remove Node":
                try {
                    int node_to_del = Integer.parseInt(in[0]);
                    this.graph.getGraph().removeNode(node_to_del);
                    return true;
                }
                catch (IllegalArgumentException | NullPointerException e){
                    e.printStackTrace();
                    return false;
                }
            case "Remove Edge":
                try {
                    int src_del = Integer.parseInt(in[0]);
                    int dest_del = Integer.parseInt(in[1]);
                    this.graph.getGraph().removeEdge(src_del, dest_del);
                    return true;
                }
                catch (IllegalArgumentException | NullPointerException e){
                    e.printStackTrace();
                    return false;
                }
            case "Shortest Path":
                try {
                    int src = Integer.parseInt(in[0]);
                    int dest = Integer.parseInt(in[1]);
                    this.graph.shortestPath(src, dest);
                    return true;
                }
                catch (IllegalArgumentException | NullPointerException e){
                e.printStackTrace();
                return false;
            }
            case "Shortest Path Distance":
                try {
                    int src = Integer.parseInt(in[0]);
                    int dest = Integer.parseInt(in[1]);
                    this.graph.shortestPathDist(src, dest);
                    return true;
                }
                catch (IllegalArgumentException | NullPointerException e) {
                    e.printStackTrace();
                    return false;
                }
            case "TSP":
                try {
                    List<NodeData> cities = null;
                    for (String str : in) {
                        int key = Integer.parseInt(str);
                        cities.add(graph.getGraph().getNode(key));
                    }
                    this.graph.tsp(cities);
                    return true;
                }
                catch (IllegalArgumentException e){
                    e.printStackTrace();
                    return false;
                }
            default:
                throw new IllegalStateException("This option doesn't exist!");
        }
    }

/* ***********************************************************
        This is TextBox class for algorithm inputs
 */

    static class TextBox extends JFrame implements ActionListener {
        static String algorithm_input;
        static JTextField textField;
        static JPanel panel;
        static JButton button;
        static JLabel label;

        TextBox() {
        }

        public static void TextBoxInit(String functionName) {
            panel = new JPanel();
            label = new JLabel("Please enter your input to "+functionName+".");
            button = new JButton("Send");
            TextBox textBox = new TextBox();
            button.addActionListener(textBox);
            textField = new JTextField(16);
            JPanel panel = new JPanel();
            panel.add(textField);
            panel.add(button);
            panel.add(label);
            panel.add(panel);
            panel.setSize(300, 300);
            panel.show();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Send")) {
                // set the text of the label to the text of the field
                algorithm_input = textField.getText();
                textField.setText("");
            }
        }
    }

    public static void main(String[] args) {
        new GUI_Menu();
    }
}

