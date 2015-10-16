package main.java;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Menu {

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private Map[] maps;

    public Menu(Map[] maps) {
        this.maps = maps;
        prepareGUI();
        showButtons();

    }


    private void prepareGUI() {
        mainFrame = new JFrame("Sokoban Game");
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);

        statusLabel.setSize(350, 100);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }


    public void showButtons() {

        headerLabel.setText("Please chose a game");


        JComboBox box = new JComboBox();
        for (final Map map : this.maps) {
            box.addItem(map.getName());
        }
        box.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gameName = (String) box.getSelectedItem();
                Map gameMap = getMapByName(gameName);
                GameContainer game = new GameContainer(gameMap);
            }
        });
        controlPanel.add(box);
//adding the edit functionality
        JCheckBox edit =new JCheckBox();
        edit.setText("Edit a Map");
        controlPanel.add(edit);


        JScrollPane panelPane = new JScrollPane(controlPanel);
        mainFrame.add(panelPane);

        mainFrame.setVisible(true);
    }

    private Map getMapByName(String name) {

        for (Map map : this.maps) {
            if (name.equalsIgnoreCase(map.getName())) return map;
        }
        return null;
    }
}