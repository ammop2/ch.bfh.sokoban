package main.java;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by u216070 on 16.10.2015.
 */
public class EditPanel extends JPanel implements ActionListener, MouseListener , MouseMotionListener{

    private boolean drawingBlank = false;
    private boolean drawingAvatar = false;
    private boolean drawingKey = false;
    private boolean drawingTarget = false;
    private boolean drawingStone = false;
    private int x;
    private int y;
    private boolean mouseClicked = true;
    private JPanel controls;

    private JButton drawBlank;
    private JButton drawAvatar;
    private JButton drawKey;
    private JButton drawTarget;
    private JButton drawStone;
    private JButton saveMap;


    private boolean groundFieldDrawed = false;
    private Map originalMap;
    private FieldList fieldList;

    public EditPanel(Map map) {

        if (map != null) {
            this.setLayout(null);
            this.originalMap = map;
            fieldList = new FieldList(map);
            this.setPreferredSize(new Dimension(map.getXSize() * Field.ElementHeight + 100, map.getYSize() * Field.ElementHeight));
        }
    }


    private void initGroundField(Graphics g) {
        this.fieldList.draw(g);
    }

    @Override
    public void paintComponent(Graphics g) {
        if (!groundFieldDrawed) {

            controls = new JPanel();
            controls.setLocation(originalMap.getXSize() * Field.ElementHeight, 0);
            controls.setSize(100, originalMap.getYSize() * Field.ElementHeight);
            controls.setLayout(new FlowLayout(FlowLayout.LEFT));
            controls.setBorder(BorderFactory.createLineBorder(Color.black));

            drawBlank = new JButton("Blanks");
            drawAvatar = new JButton("Avatar");
            drawKey = new JButton("Keys");
            drawTarget = new JButton("Targets");
            drawStone = new JButton("Stones");
            saveMap = new JButton ("Save");

            drawBlank.setPreferredSize(new Dimension (80,20));
            drawAvatar.setPreferredSize(new Dimension(80, 20));
            drawKey.setPreferredSize(new Dimension(80, 20));
            drawTarget.setPreferredSize(new Dimension(80, 20));
            drawStone.setPreferredSize(new Dimension(80, 20));
            saveMap.setPreferredSize(new Dimension(80, 40));

            drawBlank.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    toggleDrawBlanks();
                }
            });

            drawAvatar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    toggleDrawAvatar();
                }
            });
            drawKey.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    toggleDrawKey();
                }
            });

            drawTarget.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    toggleDrawTargets();
                }
            });

            drawStone.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    toggleDrawStones();
                }
            });

            saveMap.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!MapSaver.saveMap(originalMap.getName(),fieldList.getFields(),originalMap.getXSize(),originalMap.getYSize())){
                            JOptionPane.showMessageDialog(null, "error saving");
                    }
                }
            });


            controls.add(drawBlank);
            controls.add(drawAvatar);
            controls.add(drawKey);
            controls.add(drawTarget);
            controls.add(drawStone);
            controls.add(saveMap);

            this.add(controls);


            super.paintComponent(g);
            initGroundField(g);
            groundFieldDrawed = true;


        } else if (mouseClicked) {
            fieldList.setField(g, x, y, drawingAvatar, drawingStone, drawingTarget, drawingBlank, drawingKey);
            mouseClicked = false;
        } else {

        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }


    private void toggleDrawAvatar() {
        if (drawingAvatar) {
            drawingAvatar = false;
            drawingBlank = false;
            drawingKey = false;
            drawingStone = false;
            drawingTarget = false;

            drawBlank.setBorder(null);
            drawTarget.setBorder(null);
            drawAvatar.setBorder(null);
            drawKey.setBorder(null);
            drawStone.setBorder(null);

        } else {
            drawingAvatar = true;
            drawingBlank = false;
            drawingStone = false;
            drawingTarget = false;
            drawingKey = false;

            drawBlank.setBorder(null);
            drawTarget.setBorder(null);
            drawAvatar.setBorder(BorderFactory.createLineBorder(Color.red,3));
            drawKey.setBorder(null);
            drawStone.setBorder(null);

        }
    }

    private void toggleDrawKey() {
        if (drawingKey) {
            drawingAvatar = false;
            drawingBlank = false;
            drawingKey = false;
            drawingStone = false;
            drawingTarget = false;

            drawBlank.setBorder(null);
            drawTarget.setBorder(null);
            drawAvatar.setBorder(null);
            drawKey.setBorder(null);
            drawStone.setBorder(null);

        } else {
            drawingKey = true;
            drawingAvatar = false;
            drawingBlank = false;
            drawingStone = false;
            drawingTarget = false;

            drawBlank.setBorder(null);
            drawTarget.setBorder(null);
            drawAvatar.setBorder(null);
            drawKey.setBorder(BorderFactory.createLineBorder(Color.red,3));
            drawStone.setBorder(null);

        }
    }

    private void toggleDrawBlanks() {
        if (drawingBlank) {
            drawingAvatar = false;
            drawingBlank = false;
            drawingKey = false;
            drawingStone = false;
            drawingTarget = false;

            drawBlank.setBorder(null);
            drawTarget.setBorder(null);
            drawAvatar.setBorder(null);
            drawKey.setBorder(null);
            drawStone.setBorder(null);

        } else {
            drawingBlank = true;
            drawingAvatar = false;
            drawingKey = false;
            drawingStone = false;
            drawingTarget = false;

            drawBlank.setBorder(BorderFactory.createLineBorder(Color.red,3));
            drawTarget.setBorder(null);
            drawAvatar.setBorder(null);
            drawKey.setBorder(null);
            drawStone.setBorder(null);
        }
    }

    private void toggleDrawTargets() {
        if (drawingTarget) {
            drawingAvatar = false;
            drawingBlank = false;
            drawingKey = false;
            drawingStone = false;
            drawingTarget = false;

            drawBlank.setBorder(null);
            drawTarget.setBorder(null);
            drawAvatar.setBorder(null);
            drawKey.setBorder(null);
            drawStone.setBorder(null);

        } else {
            drawingTarget = true;
            drawingAvatar = false;
            drawingBlank = false;
            drawingKey = false;
            drawingStone = false;

            drawBlank.setBorder(null);
            drawTarget.setBorder(BorderFactory.createLineBorder(Color.red,3));
            drawAvatar.setBorder(null);
            drawKey.setBorder(null);
            drawStone.setBorder(null);
        }
    }

    private void toggleDrawStones() {
        if (drawingStone) {
            drawingAvatar = false;
            drawingBlank = false;
            drawingKey = false;
            drawingStone = false;
            drawingTarget = false;

            drawBlank.setBorder(null);
            drawTarget.setBorder(null);
            drawAvatar.setBorder(null);
            drawKey.setBorder(null);
            drawStone.setBorder(null);

        } else {
            drawingTarget = false;
            drawingAvatar = false;
            drawingBlank = false;
            drawingKey = false;
            drawingStone = true;

            drawBlank.setBorder(null);
            drawTarget.setBorder(null);
            drawAvatar.setBorder(null);
            drawKey.setBorder(null);
            drawStone.setBorder(BorderFactory.createLineBorder(Color.red,3));

        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX() / 40;
        y = e.getY() / 40 - 1;
        mouseClicked = true;


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX() / 40;
        y = e.getY() / 40 - 1;
        mouseClicked = true;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
