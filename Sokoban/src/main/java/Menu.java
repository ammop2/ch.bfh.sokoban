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

    public Menu(Map[] maps){
        prepareGUI();
        showButtons(maps);
    }


    private void prepareGUI(){
        mainFrame = new JFrame("Java Swing Examples");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("",JLabel.CENTER);

        statusLabel.setSize(350,100);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    private static ImageIcon createImageIcon(String path,
                                             String description) {
        java.net.URL imgURL = Menu.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public void showButtons(Map[] maps){

        headerLabel.setText("THIS IS THE MOST BADASS MENU I'VE EVER SEEN");

        //resources folder should be inside SWING folder.
        ImageIcon icon = createImageIcon("/resources/java_icon.png","Java");



        for(final Map map : maps){
            JButton newbutton = new JButton(map.getName());
            newbutton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    statusLabel.setText(map.getName());
                    GameContainer game = new GameContainer(map);
                }
            });

            controlPanel.add(newbutton);

        }
        JScrollPane panelPane = new JScrollPane(controlPanel);
        mainFrame.add(panelPane);

        mainFrame.setVisible(true);
    }
}