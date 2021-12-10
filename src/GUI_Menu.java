import api.NodeData;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


public class GUI_Menu extends JFrame implements ActionListener {
    public MyDirectedWeightedGraphAlgorithms graph = new MyDirectedWeightedGraphAlgorithms();
    static JLabel label;
    String name;
    int scrW;
    int scrH;
    int innerW;
    int innerH;

    public GUI_Menu(String path) {
        if (!path.isEmpty())
            this.graph.load(path);
        else {
            MyDirectedWeightedGraph newGraph = new MyDirectedWeightedGraph();
            MyDirectedWeightedGraphAlgorithms algo = new MyDirectedWeightedGraphAlgorithms();
            algo.init(newGraph);
            this.graph = algo;
        }
        init();
    }

    private void init() {
        JMenuBar menuBar = new JMenuBar();
        JMenu main_menu, load, run_alogs, edit;
        JMenuItem save, gson_g1, gson_g2, gson_g3, saved, show_graph;
        JMenuItem insert_node, insert_edge, remove_node, remove_edge;
        JMenuItem isConnected, shortestPathDist, shortestPath, center, tsp;
//        JFrame frame = new JFrame("Ex2 - Data Structures and Algorithms On Graphs");
//        this.setName("Ex2 - Data Structures and Algorithms On Graphs");
        this.setTitle("Ex2 - Data Structures and Algorithms On Graphs");
        label = new JLabel("Please select a function from the menu bar");
        //label.setLocation((int)this.graph.center().getLocation().x(), (int)this.graph.center().getLocation().y());
        main_menu = new JMenu("Menu");
        load = new JMenu("Load");
        run_alogs = new JMenu("Run Algorithms");
        save = new JMenuItem("Save");
        gson_g1 = new JMenuItem("G1");
        gson_g2 = new JMenuItem("G2");
        gson_g3 = new JMenuItem("G3");
        saved = new JMenuItem("Saved G");
        //show_graph = new JMenuItem("Show Graph");
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
        //main_menu.add(show_graph);
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
        //show_graph.addActionListener(this);
        isConnected.addActionListener(this);
        shortestPath.addActionListener(this);
        shortestPathDist.addActionListener(this);
        center.addActionListener(this);
        tsp.addActionListener(this);
        insert_edge.addActionListener(this);
        insert_node.addActionListener(this);
        remove_edge.addActionListener(this);
        remove_node.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        pack(); // Need this, otherwise insets() show as 0.
        scrW = (int) screenSize.getWidth();
        scrH = (int) screenSize.getHeight();
        innerW = scrW - getInsets().left - getInsets().right;
        innerH = scrH - getInsets().top - getInsets().bottom;
        setSize(scrW, scrH);
        drawGraph();
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand() + " selected");
        JLabel answer = new JLabel(e.getActionCommand() + " selected. please wait");
        JPanel answer_panel = new JPanel();
        answer.setFont(new Font("Italian", Font.BOLD, 20));
        //answer_panel.setLocation();
        //this.setContentPane(answer_panel);
        answer.setSize(400,400);
        this.setLocationRelativeTo(null);
        answer_panel.add(answer);
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
            case "Is Connected" -> this.graph.isConnected();
            case "Shortest Path" -> fills_funcs("Shortest Path");
            case "Shortest Path Distance" -> fills_funcs("Shortest Path Distance");
            case "Center" -> this.graph.center();
            case "TSP" -> fills_funcs("TSP");
        }
        answer.setText("Done");
        answer_panel.add(answer);
    }

    private boolean drawGraph() {
        label.setText("Drawing " + this.name + " it might take a while");
        try {
            this.setContentPane(new GUI_Graph((MyDirectedWeightedGraph) this.graph.getGraph(), innerH, innerW));
            label.setText("Succeeded drawing " + this.name);
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
            label.setText("Error: couldn't draw " + this.name);
            return false;
        }

    }

    public boolean load_graph(String name) {
        this.setContentPane(new JPanel());
        String fullPath;
        if (name.startsWith("G"))
            fullPath = "data/" + name + ".json";
        else
            fullPath = "output.json";
        String message = "The Graph " + name + " has uploaded!";
        try {
            this.graph.load(fullPath);
            this.name = name;
            label.setText(message);
            label.setVisible(true);
            drawGraph();
            return true;
        } catch (NullPointerException e) {
            e.printStackTrace();
            label.setText("Error: The Graph " + name + " couldn't be uploaded!");
            label.setVisible(true);
            return false;
        }
    }

    public void fills_funcs(String functionName) {
        label.setText(functionName);
        TextBox tb = new TextBox(functionName, this.graph);
    }

/* ***********************************************************
        This is TextBox class for algorithm inputs
 */

    static class TextBox extends JFrame implements ActionListener {
        public MyDirectedWeightedGraphAlgorithms graph;
        public static String algorithm_name;
        public static String algorithm_input;
        public static JTextField textField;
        public static JPanel panel;
        public static JButton button;
        public static JLabel label;

        public TextBox(String functionName, MyDirectedWeightedGraphAlgorithms graph) {
            this.graph = graph;
            algorithm_name = functionName;
            panel = new JPanel();
            label = new JLabel("Please enter your input to " + functionName + ". \n");
            switch (functionName){
                case "Insert Node" -> label.setText("<html>"+label.getText() + "<br/>" +
                        "Enter the node name, X value and<br/> Y value, split them by ','" +
                        "<br/>If you like to edit node's <br/>location, enter it name.</html>");
                case "Remove Node" -> label.setText("<html>"+label.getText() + "<br/>" +
                        "Enter the name of the<br/> node which you like to remove.</html>");
                case "Insert Edge" -> label.setText("<html>"+label.getText() + "<br/>Select two node keys" +
                        " of the<br/> edge first for source, second for<br/> destination, then add the wanted weight" +
                        " <br/>split them by ','</html>");
                case "Remove Edge" -> label.setText("<html>"+label.getText() + "<br/>" +
                        "Select two node keys of the<br/> edge first for source, second for" +
                        "destination,<br/> split them by ','</html>");
                case "Shortest Path" -> label.setText("<html>"+label.getText() + "<br/>" +
                        "Select two node keys,<br/> split them by ','</html>");
                case "Shortest Path Distance" -> label.setText("<html>"+label.getText() + "<br/>" +
                        "Select two node keys, <br/>split them by ','</html>");
                case "TSP" -> label.setText("<html>"+label.getText() + "<br/>" +
                        "Select the wanted nodes,<br/> split them by ','</html>");
            }
            button = new JButton("Send");
            button.addActionListener(this);
            textField = new JTextField(16);
            JPanel panel = new JPanel();
            this.setContentPane(panel);
            panel.add(textField);
            panel.add(button);
            panel.add(label);
            this.setSize(300, 300);
            panel.show();
            this.setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Send")) {
                // set the text of the label to the text of the field
                algorithm_input = textField.getText();
                textField.setText("");
                String input = this.algorithm_input;
                System.out.println(input);
                String[] in = input.split(",");
                switch (algorithm_name) {
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
                        } catch (IllegalArgumentException | NullPointerException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "Insert Edge":
                        try {
                            int src_new = Integer.parseInt(in[0]);
                            int dest_new = Integer.parseInt(in[1]);
                            double weight_new = Double.parseDouble(in[2]);
                            this.graph.getGraph().connect(src_new, dest_new, weight_new);
                        } catch (IllegalArgumentException | NullPointerException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "Remove Node":
                        try {
                            int node_to_del = Integer.parseInt(in[0]);
                            this.graph.getGraph().removeNode(node_to_del);
                        } catch (IllegalArgumentException | NullPointerException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "Remove Edge":
                        try {
                            int src_del = Integer.parseInt(in[0]);
                            int dest_del = Integer.parseInt(in[1]);
                            this.graph.getGraph().removeEdge(src_del, dest_del);
                        } catch (IllegalArgumentException | NullPointerException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "Shortest Path":
                        try {
                            int src = Integer.parseInt(in[0]);
                            int dest = Integer.parseInt(in[1]);
                            this.graph.shortestPath(src, dest);
                        } catch (IllegalArgumentException | NullPointerException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "Shortest Path Distance":
                        try {
                            int src = Integer.parseInt(in[0]);
                            int dest = Integer.parseInt(in[1]);
                            System.out.println(this.graph.shortestPathDist(src, dest));
                        } catch (IllegalArgumentException | NullPointerException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "TSP":
                        try {
                            List<NodeData> cities = null;
                            for (String str : in) {
                                int key = Integer.parseInt(str);
                                cities.add(graph.getGraph().getNode(key));
                            }
                            this.graph.tsp(cities);
                        } catch (IllegalArgumentException ex) {
                            ex.printStackTrace();
                        }
                        break;
                }
            }
        }
    }

    public static void main(String[] args) {
        if(args.length > 0){
            MyDirectedWeightedGraphAlgorithms graphAlgorithms = new MyDirectedWeightedGraphAlgorithms();
            new GUI_Menu(args[0]);
        }
        else
            new GUI_Menu("");
    }
}

