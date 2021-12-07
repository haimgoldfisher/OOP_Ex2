import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener { // , JButton

    public GUI()
    {
        super("Graph");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        Button loadButton = new Button("Load");
        this.add(loadButton);
        loadButton.setLocation(250, 250);
        loadButton.setSize(200, 200);
        loadButton.setVisible(true);
        loadButton.addActionListener(this);
        this.add(new JLabel("welcome"));
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Clicked " + e.getActionCommand() + " !");
        // should load a Json
    }
}
