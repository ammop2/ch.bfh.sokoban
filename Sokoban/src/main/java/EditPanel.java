package main.java;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by u216070 on 16.10.2015.
 */
public class EditPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener {

    private boolean drawingBlank = false;
    private boolean drawingAvatar = false;
    private boolean drawingKey = false;
    private boolean drawingTarget = false;
    private boolean drawingStone = false;
    private boolean saved = false;
    private int x;
    private int y;
    private boolean mouseClicked = true;
    private boolean revertEdit = false;
    private JPanel controls;

    private JButton drawBlank;
    private JButton drawAvatar;
    private JButton drawKey;
    private JButton drawTarget;
    private JButton drawStone;
    private JButton unDo;
    private JButton saveMap;
    private JLabel choseField;


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
            unDo = new JButton("");
            saveMap = new JButton("Save");
            choseField = new JLabel("Chose Field");


            drawBlank.setPreferredSize(new Dimension(80, 20));
            drawAvatar.setPreferredSize(new Dimension(80, 20));
            drawKey.setPreferredSize(new Dimension(80, 20));
            drawTarget.setPreferredSize(new Dimension(80, 20));
            drawStone.setPreferredSize(new Dimension(80, 20));

            unDo.setPreferredSize(new Dimension(80, 40));
            try {
                URL url = getClass().getClassLoader().getResource("pics\\undo.png");
                Image img = ImageIO.read(url);
                Image newimg = img.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
                Icon newIcon = new ImageIcon(newimg);
                unDo.setIcon(newIcon);
            } catch (Exception e) {
                e.printStackTrace();
            }




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

            unDo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    revertEdit = true;
                }
            });

            saveMap.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!MapSaver.saveMap(originalMap.getName(), fieldList.getFields(), originalMap.getXSize(), originalMap.getYSize())) {
                        JOptionPane.showMessageDialog(null, "error saving");
                        return;
                    }
                    if (!saved) saveMap.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));

                }
            });

            controls.add(choseField);
            controls.add(drawBlank);
            controls.add(drawAvatar);
            controls.add(drawKey);
            controls.add(drawTarget);
            controls.add(drawStone);
            controls.add(unDo);
            controls.add(saveMap);


            this.add(controls);


            super.paintComponent(g);
            initGroundField(g);
            groundFieldDrawed = true;


        } else if (mouseClicked) {
            fieldList.setField(g, x, y, drawingAvatar, drawingStone, drawingTarget, drawingBlank, drawingKey);
            mouseClicked = false;
            saved = false;
        } else if (revertEdit) {
            fieldList.reverseEdit(g);
            fieldList.reverseEdit(g);
            revertEdit = false;
        }
    }

    private void toggleDrawAvatar() {
        resetButtons();
        if (!drawingAvatar) {
            drawingAvatar = true;
            drawAvatar.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        }
    }

    private void toggleDrawKey() {
        resetButtons();
        if (!drawingKey) {
            drawingKey = true;
            drawKey.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        }
    }


    private void toggleDrawBlanks() {
        resetButtons();
        if (!drawingBlank) {
            drawingBlank = true;
            drawBlank.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        }
    }

    private void toggleDrawTargets() {
        resetButtons();
        if (!drawingTarget) {
            drawingTarget = true;
            drawTarget.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        }
    }

    private void toggleDrawStones() {
        resetButtons();
        if (!drawingStone) {
            drawingStone = true;
            drawStone.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        }
    }

    private void resetButtons() {
        drawingAvatar = drawingBlank = drawingKey = drawingStone = drawingTarget = false;
        drawBlank.setBorder(null);
        drawTarget.setBorder(null);
        drawAvatar.setBorder(null);
        drawKey.setBorder(null);
        drawStone.setBorder(null);
    }

    private void mouseTrigger(int x, int y) {
        this.x = x / Field.ElementWidth;
        this.y = y / Field.ElementHeight - 1;
        mouseClicked = true;
        if (!saved) saveMap.setBorder(BorderFactory.createLineBorder(Color.red, 2));

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseTrigger(e.getX(), e.getY());

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseTrigger(e.getX(), e.getY());
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
        mouseTrigger(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
