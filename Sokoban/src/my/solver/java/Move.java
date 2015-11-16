package my.solver.java;

import main.java.Direction;

import java.awt.event.KeyListener;

/**
 * Created by Pascal on 06.11.2015.
 *
 * This class represent a state after Move
 */



public class Move {

    private int xPos;
    private int yPos;
    private boolean solved;
    private Direction move;
    private int[][] playGround;

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public boolean isSolved() {
        return solved;
    }

    public Direction getMove() {
        return move;
    }

    public int[][] getPlayGround() {
        return playGround;
    }

    public Move(int xOriginalPos, int yOriginalPos)
    {
        this.solved = false;
        this.xPos = xOriginalPos;
        this.yPos = yOriginalPos;
    }

    private int getNewYPos(Direction direction)
    {
        switch (direction)
        {
            case TOP:
                return  yPos - 1;
            case BOTTOM:
                return  yPos + 1;
            default: return yPos;
        }
    }

    private int getNewXPos(Direction direction)
    {
        switch (direction)
        {
            case LEFT:
                return  xPos - 1;
            case RIGHT:
                return  xPos +1;
            default: return xPos;
        }
    }

    public boolean makeMove(Direction direction, int[][] playGround)
    {
        this.playGround = playGround;
        move = direction;
        // set current player position to playground || empty box
        this.playGround[yPos][xPos] = (Solver.getMinimalField().getMinimalField()[yPos][xPos] == 3) ? 3 : 0;
        xPos = getNewXPos(direction);
        yPos = getNewYPos(direction);

        // if new Field a Wall this move is not allowed
        if(this.playGround[yPos][xPos] == 1) return false;

        switch (this.playGround[yPos][xPos])
        {
            case 1 :
                return false;
            // if empty field -> go to it
            case 0 :
                this.playGround[yPos][xPos] = 2;
                break;
            // if key -> move key 1 forward same direction
            case 4 :
                this.playGround[yPos][xPos] = 2;
                int xKey = getNewXPos(direction);
                int yKey = getNewYPos(direction);
                // if new key field is wall -> break
                if(this.playGround[yKey][xKey] == 1) return false;
                // if field is blank go to it;
                else if(this.playGround[yKey][xKey] == 0) {
                    this.playGround[yKey][xKey] = 4;
                    break;
                }
                // if target
                else if(this.playGround[yKey][xKey] == 3)
                {
                    this.solved = true;
                    this.playGround[yKey][xKey] = 6;
                }
                break;
            default: return false;
        }
        return true;
    }

}
