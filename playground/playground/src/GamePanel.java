import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Pascal on 18.09.2015.
 */
public class GamePanel extends JPanel implements KeyListener, ActionListener {

    /**
     * Region Properties
     */
    private int fieldSize;
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private boolean groundFieldDrawed = false;
    private Field[][] fields;
    private int vikingX;
    private int vikingY;
    private int nrOfTarget = 2;
    private Map map;

    public GamePanel(Map map) {
        this.map=map;
        this.fieldSize= Math.max(this.map.getFields().length,this.map.getFields()[0].length);
        this.setPreferredSize(new Dimension(fieldSize * Field.width, fieldSize * Field.height));
        fields = new Field[fieldSize][fieldSize];


    }

    private void initGroundField(Graphics g, int[][] fields)
    {
        for(int i = 0; i < fieldSize; i++)
        {
            for(int z = 0; z < fieldSize; z++)
            {
                if(fields[i][z] == 1)
                {
                    this.fields[i][z] = new Stone(i, z);
                }
                else if(fields[i][z] == 2)
                {
                    vikingX = i;
                    vikingY = z;
                    this.fields[i][z] = new Player(i, z);
                }
                else if(fields[i][z] == 3)
                {
                    this.fields[i][z] = new Target(i, z);
                }
                else if(fields[i][z] == 4)
                {
                    this.fields[i][z] = new KeyField(i, z);
                }
                else
                {
                    this.fields[i][z] = new Ground(i, z);
                }
                this.fields[i][z].draw(g);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g){
        if(!groundFieldDrawed) {
            super.paintComponent(g);
            int[][] fields = new int[fieldSize][fieldSize];
            for(int z = 0; z < fieldSize; z++) {
                if(z == 0 || z == fieldSize -1)
                {
                    for(int i = 0; i < fieldSize; i++)
                    {
                        fields[z][i] =  1;
                    }
                }
                else
                {
                    fields[z][0] =  1;
                    fields[z][fieldSize -1] =  1;
                }
            }
            //fill in the map data
            for(int i=0; i<this.map.getFields().length;i++){
                for(int j=0; j<this.map.getFields()[0].length;j++){
                    fields[i][j]=this.map.getFields()[i][j];
                }
            }

            initGroundField(g, fields);
            groundFieldDrawed = true;
        }
        else
        {
            if(!up && !down && !left && !right)
            {
                //abort if not a ground field.
                return;
            }
            // move viking
            Player viking =  (Player) fields[vikingX][vikingY];
            if(up)
            {
                moveViking(viking, vikingX -= 1, vikingY, g);
            }
            if(down)
            {
                moveViking(viking, vikingX += 1, vikingY, g);
            }
            if(left)
            {
                moveViking(viking, vikingX, vikingY -= 1, g);
            }
            if(right)
            {
                moveViking(viking, vikingX, vikingY += 1, g);
            }
            up = down = left = right = false;
            if(nrOfTarget == 0){
                try {
                    URL url = getClass().getClassLoader().getResource("pics\\cong.gif");
                    BufferedImage img = ImageIO.read(url);
                    g.drawImage(img, 50, 50, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    private void moveViking(Player viking, int targetRow, int targetColumn, Graphics g) {
        if ((fields[targetRow][targetColumn] instanceof Ground)) {
            if(moveField(viking, targetRow, targetColumn, g)) {
                return;
            }
        }
        if ((fields[targetRow][targetColumn] instanceof KeyField)) {
            int tgRow = targetRow;
            int tgColumn = targetColumn;
            if (up) {
                tgRow -= 1;
            }
            if (down) {
                tgRow += 1;
            }
            if (left) {
                tgColumn -= 1;
            }
            if (right) {
                tgColumn += 1;
            }

            if (moveField(fields[targetRow][targetColumn], tgRow, tgColumn, g)) {
                moveField(viking, targetRow, targetColumn, g);
                return;
            }
        }
        vikingX = viking.getRow();
        vikingY = viking.getColumn();
        return;

    }
    private boolean moveField(Field field, int targetRow, int targetColumn, Graphics g)
    {
         if ((fields[targetRow][targetColumn] instanceof Target) && (field instanceof KeyField)) {
            System.out.println("hit");
            return hitTarget((KeyField) field, targetRow, targetColumn, g);
        }
        else if(fields[targetRow][targetColumn] instanceof Stone)
    {
        return false;
    }
        Field oldF = fields[targetRow][targetColumn];
        oldF.setColumn(field.getColumn());
        oldF.setRow(field.getRow());
        field.setColumn(targetColumn);
        field.setRow(targetRow);
        fields[field.getRow()][field.getColumn()] = field;
        fields[oldF.getRow()][oldF.getColumn()] = oldF;
        field.draw(g);
        oldF.draw(g);
        return true;
    }
    private boolean hitTarget(KeyField keyField, int targetRow, int targetColumn, Graphics g)
    {
        Field newF = new Ground(keyField.getRow(),keyField.getColumn());
        keyField.setColumn(targetColumn);
        keyField.setRow(targetRow);
        fields[keyField.getRow()][keyField.getColumn()] = keyField;
        fields[newF.getRow()][newF.getColumn()] = newF;
        keyField.draw(g);
        newF.draw(g);
        keyField.setBlocked(true);
        nrOfTarget -=1;
        return true;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_UP){
            up = true;
        }

        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            down = true;
        }

        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            left = true;
        }

        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        up = down = left = right = false;
    }
}


