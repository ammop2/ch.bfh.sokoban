package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class Menu implements Runnable {

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JCheckBox edit = new JCheckBox();
    private JComboBox box = new JComboBox();
    private JButton newMap = new JButton();
    private Map[] maps;
    private String path;
    Thread thread;

    //components for new map dialog
    private JTextField mapName = new JTextField();
    private JTextField mapWidth = new JTextField();
    private JTextField mapHeight = new JTextField();
    private final JComponent[] inputs = new JComponent[]{
            new JLabel("Chose the Name of your map"),
            mapName,
            new JLabel("Chose the width of your map"),
            mapWidth,
            new JLabel("Chose the height of your map "),
            mapHeight
    };

    public Menu(String path) {
        this.path = path;
        try {
            this.maps = MapReader.load(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        prepareGUI();
        initButtons();
    }

    private String findMissingMap(String[] mapsInGUI,String[] mapsInDIR){
        for(int i=0;i<mapsInDIR.length;i++){
        if(!Arrays.asList(mapsInGUI).contains(mapsInDIR[i]))return mapsInDIR[i];
        }
        return "ERROR";
    }
    private void refreshMaps() {
        if(box.getItemCount()<maps.length) {
            String[] mapsInGUI=new String[box.getItemCount()];
            String[] mapsInDIR= new String[maps.length];

            for(int i=0;i<box.getItemCount();i++){
                mapsInGUI[i]=box.getItemAt(i).toString();
            }
            for(int i=0;i<maps.length;i++){
                mapsInDIR[i]=maps[i].getName();
            }
            box.addItem(findMissingMap(mapsInGUI,mapsInDIR));
            box.repaint();
}
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
        controlPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        mainFrame.add(headerLabel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    public static String convertToMultiline(String orig) {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }

    public void initButtons() {

        headerLabel.setText(convertToMultiline("Please chose a game\nUse arrow key to navigate\nUse Z Key to undo your move"));


        for (final Map map : this.maps) {
            box.addItem(map.getName());
        }
        box.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gameName = (String) box.getSelectedItem();
                Map gameMap = getMapByName(gameName);
                GameContainer game = new GameContainer(gameMap, edit.isSelected());
            }
        });
        controlPanel.add(box);
//adding the edit functionality
        edit.setText("Check to edit map");
        controlPanel.add(edit);

//adding button for new maps
        newMap.setText("Create New Map");

        newMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null, inputs, "Please enter data to create your map", JOptionPane.PLAIN_MESSAGE);
                int newMapHeight = 0;
                int newMapWidth = 0;
                try {
                    newMapHeight = Integer.parseInt(mapHeight.getText());
                    newMapWidth = Integer.parseInt(mapWidth.getText());
                } catch (Exception ex) {
                    infoBox("You did not enter numbers for width or height. Try again.", "Error");
                    ex.printStackTrace();
                    return;
                }

                int[] newFields = new int[newMapHeight * newMapWidth];
                String newMapName = mapName.getText();
                newMapName = newMapName.replaceAll("[^a-zA-Z0-9\\s]", "");
                Arrays.fill(newFields, 0);
                System.out.println(newFields.length);
                Map newMap = new Map(newMapName, newFields, newMapWidth, newMapHeight);
                GameContainer game = new GameContainer(newMap, true);
            }
        });

        controlPanel.add(newMap);
        mainFrame.add(controlPanel);
        mainFrame.setVisible(true);
    }

    private Map getMapByName(String name) {

        for (Map map : this.maps) {
            if (name.equalsIgnoreCase(map.getName())) return map;
        }
        return null;
    }

    private static void infoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("refreshed maps");
            try {
                this.maps = MapReader.load(path);
                refreshMaps();
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void init() {
        this.thread = new Thread(this);
        this.thread.start();
    }
}