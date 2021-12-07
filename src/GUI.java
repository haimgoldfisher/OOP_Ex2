import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener { // , JButton

    public GUI()
    {
        super("Menu");
        this.setSize(1000, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        Button loadButton = new Button("Load");
        this.add(loadButton);
        loadButton.setLocation(100, 400);
        loadButton.setSize(100, 100);
        loadButton.setVisible(true);
        loadButton.addActionListener(this);

        Button saveButton = new Button("Save");
        this.add(saveButton);
        saveButton.setLocation(400, 400);
        saveButton.setSize(100, 100);
        saveButton.setVisible(true);
        saveButton.addActionListener(this);

        Button editButton = new Button("Edit");
        this.add(editButton);
        editButton.setLocation(400, 100);
        editButton.setSize(100, 100);
        editButton.setVisible(true);
        editButton.addActionListener(this);

        Button algoButton = new Button("Run Algorithms");
        this.add(algoButton);
        algoButton.setLocation(100, 100);
        algoButton.setSize(100, 100);
        algoButton.setVisible(true);
        algoButton.addActionListener(this);

        Button showButton = new Button("Show Graph");
        this.add(showButton);
        showButton.setLocation(250, 250);
        showButton.setSize(100, 100);
        showButton.setVisible(true);
        showButton.addActionListener(this);

        this.add(new JLabel("welcome"));
        this.pack();
        this.setVisible(true);
        this.setBackground(Color.BLACK);
    }

    public static void main(String[] args) {
        new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Clicked " + e.getActionCommand() + " !");
    }

}
