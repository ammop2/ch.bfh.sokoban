package ssolver.java;

import main.java.Coordinate;

import java.io.*;
import java.nio.file.Files;
import java.util.*;


class Move{
    Coordinates position;
    String path;
    int distance;

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    Move(String path, Coordinates position){
        this.path = path;
        this.position = position;
    }

    public Coordinates getPosition() {
        return position;
    }

    public void setPosition(Coordinates position) {
        this.position = position;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}

class Coordinates{
    int x;
    int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }
    public String getKey(){
        String key = "X:"+this.x+"Y:"+this.y;
        return ""+key.hashCode();
    }
}

class KeyTarget{
    Coordinates key;
    Coordinates target;
    KeyTarget(Coordinates key, Coordinates target){
        this.key = key;
        this.target = target;
    }

    public Coordinates getKey() {
        return key;
    }

    public Coordinates getTarget() {
        return target;
    }
}
class Solution implements Comparable<Solution>{
    Coordinates key;
    Coordinates target;
    int distance;
    Move move;
    int moveLength;

    Solution(Coordinates key, Coordinates target, int distance, Move move){
        this.distance = distance;
        this.key = key;
        this.target = target;
        this.move = move;
        moveLength = this.move.getPath().length();
    }

    public int getMoveLength() {
        return moveLength;
    }

    public Coordinates getKey() {
        return key;
    }

    public Coordinates getTarget() {
        return target;
    }

    public int getDistance() {
        return distance;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public int compareTo(Solution o) {
        return Integer.compare(this.getMoveLength(), o.getMoveLength());
    }
}


/**
 * Created by Pascal on 16.01.2016.
 */
public class Solver {

    char[][] map;
    int ySize;
    int xSize;
    List<Coordinates> keys;
    List<Coordinates> targets;
    List<KeyTarget> keyTargetList;

    Hashtable<String, Move> moves = new Hashtable<String, Move>();
    List<Solution>  solutions = new ArrayList<Solution>();

    public Solver(String MapPath) {
        try {
            targets = new ArrayList<>();
            keys = new ArrayList<>();
            File file = new File(MapPath);
            if (!file.exists()) return;

            String[] fElements = file.getName().split("[_.XY]");
            xSize = Integer.parseInt(fElements[2]);
            ySize = Integer.parseInt(fElements[4]);

            System.out.println(xSize);
            System.out.println(ySize);

            map = new char[ySize][xSize];

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            line = br.readLine();
            int yPtr = 0;
            while (line != null) {

                for (int x = 0; x < xSize; x++) {

                    switch (line.charAt(x)) {
                        case ' ': map[yPtr][x] = 'F';
                            break;
                        case 'X': map[yPtr][x] = 'B';
                            break;
                        case '@': map[yPtr][x] = 'F';
                            break;
                        case '.': map[yPtr][x] = 'T'; targets.add(new Coordinates(x, yPtr));
                            break;
                        case '*': map[yPtr][x] = 'K'; keys.add(new Coordinates(x, yPtr));
                            break;
                        default:
                            break;
                    }
                }
                yPtr++;
                line = br.readLine();
            }
            cleanMap();
            printMap();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mapTargetToKey(){

        keyTargetList = new ArrayList<>();

        for (Coordinates target : targets) {
            int kBestPtr = 0;
            int shortestDistance = -1;
        //find key with shortest path length
            for(int kPtr = 0; kPtr < keys.size(); kPtr++){
                Coordinates key = keys.get(kPtr);
                if(shortestDistance < 0)
                {
                    shortestDistance = calcDistance(target, key);
                    kBestPtr = kPtr;
                }
                else if(shortestDistance > calcDistance(target, key))
                {
                    shortestDistance = calcDistance(target, key);
                    kBestPtr = kPtr;
                }
            }
            keyTargetList.add(new KeyTarget(keys.get(kBestPtr), target));
            keys.remove(kBestPtr);

        }
    }

    public String solve(){
        String res = "Sokoban solver from pascal & gabriel";
        mapTargetToKey();

        for(KeyTarget keytarget : keyTargetList) {
            moves.clear();
            Move rootMove = new Move("", keytarget.key);
            makeMove(keytarget.key, rootMove, keytarget.target);
        }

        Collections.sort(solutions);

        for (Solution solution : solutions) {
            res +="\r\n" + "Found some paths for key X:" + solution.getKey().getX() + " Y:"
                    + solution.getKey().getY() + "\r\nDistance: " + solution.getDistance()
                    + "Pfad distance: "+ solution.getMoveLength() + "\r\n"
                    + "__________________________________\r\n";
            for(char c : solution.getMove().getPath().toCharArray())
            {
                switch(c){
                    case 'T': res += "Top"+"\r\n"; break;
                    case 'B': res += "Bottom"+"\r\n"; break;
                    case 'L': res += "Left"+"\r\n"; break;
                    case 'R': res += "Right"+"\r\n"; break;
                    default:break;
                }
            }

        }
        res += "__________________________________\r\n";
        return res;
    }

    private void makeMove(Coordinates key, Move parentMove, Coordinates target){

        if(topMoveAllowed(parentMove))
        {
            Move top = moveTop(parentMove);
            if(storeMove(key, top, target)){
                makeMove(key, top, target);
            }
        }
        if(bottomMoveAllowed(parentMove)) {
            Move moveBottom = moveBottom(parentMove);
            if (storeMove(key, moveBottom, target)) {
                makeMove(key, moveBottom, target);
            }
        }
        if(leftMoveAllowed(parentMove)) {
            Move moveLeft = moveLeft(parentMove);
            if (storeMove(key, moveLeft, target)) {
                makeMove(key, moveLeft, target);
            }
        }
        if(rightMoveAllowed(parentMove)) {
            Move moveRight = moveRight(parentMove);
            if (storeMove(key, moveRight, target)) {
                makeMove(key, moveRight, target);
            }
        }
    }
    private boolean topMoveAllowed(Move parentMove){
        return (map[parentMove.position.getY()+1][parentMove.position.getX()] != 'B');
    }
    private boolean bottomMoveAllowed(Move parentMove){
        return (map[parentMove.position.getY()-1][parentMove.position.getX()] != 'B');
    }
    private boolean leftMoveAllowed(Move parentMove){
        return (map[parentMove.position.getY()][parentMove.position.getX()+1] != 'B');
    }
    private boolean rightMoveAllowed(Move parentMove){
        return (map[parentMove.position.getY()][parentMove.position.getX()-1] != 'B');
    }



    private boolean storeMove(Coordinates key, Move move, Coordinates target){
        if(moveLegal(move))
        {
            if(!moveAlreadyExist(move))
            {
                move.setDistance(calcDistanceToTarget(move, target));


                moves.put((String)move.getPosition().getKey(), move);
                if(move.getDistance() == 0)
                {
                    solutions.add(new Solution(key, target, calcDistance(key, target), move));
                }
                return true;
            }
        }
        return false;
    }

    private int calcDistance(Coordinates coordinatesA,  Coordinates coordinatesB){
        int y = coordinatesB.getY() - coordinatesA.getY();
        if(y < 0) y= y*-1;

        int x = coordinatesB.getX() - coordinatesA.getX();
        if(x < 0) x = x*-1;
        return  y + x;
    }

    private int calcDistanceToTarget(Move move,  Coordinates target){
        int y = target.getY() - move.position.getY();
        if(y < 0) y= y*-1;

        int x = target.getX() - move.position.getX();
        if(x < 0) x = x*-1;
        return  y + x;
    }

    private boolean moveAlreadyExist(Move move){
        return moves.get(move.getPosition().getKey()) != null;
    }

    private boolean moveLegal(Move move) {
        return map[move.position.getY()][move.position.getX()] == 'F' ||  map[move.position.getY()][move.position.getX()] == 'T' ||  map[move.position.getY()][move.position.getX()] == 'K';
    }

    private Move moveTop(Move move){
        //1 erster Zug
        Move newMove = new Move(move.getPath()+"T", new Coordinates(
                move.getPosition().getX(),
                move.getPosition().getY() -1)
        );
        return  newMove;
    }

    private Move moveBottom(Move move){
        //1 erster Zug
        Move newMove = new Move(move.getPath()+"B", new Coordinates(
                move.getPosition().getX(),
                move.getPosition().getY() +1)
        );
        return  newMove;
    }

    private Move moveLeft(Move move){
        //1 erster Zug
        Move newMove = new Move(move.getPath()+"L", new Coordinates(
                move.getPosition().getX()-1,
                move.getPosition().getY())
        );
        return  newMove;
    }

    private Move moveRight(Move move){
        //1 erster Zug
        Move newMove = new Move(move.getPath()+"R", new Coordinates(
                move.getPosition().getX()+1,
                move.getPosition().getY())
        );
        return  newMove;
    }

    //Ensure Fields outside from Border are null
    private void cleanMap(){
        for(int y = 0; y < ySize; y++) {
            for(int x = 0; x < xSize; x++) {
                if(map[y][x] == 'F'){
                    if(!checkIfIsReallyCleanField(x, y)){
                        map[y][x] = 'B';
                    }
                    else
                    {
                        if(isFieldDeadLock(x, y)){
                            map[y][x] = 'D';
                        }
                    }
                }
            }
        }
    }
    private void printMap(){
        for(int y = 0; y < ySize; y++) {
            for(int x = 0; x < xSize; x++) {
                System.out.print(map[y][x]);
            }
            System.out.println();
        }
    }

    private boolean isFieldDeadLock(int xRef, int yRef){
        boolean leftWall = map[yRef][xRef-1] == 'B';
        boolean rightWall = map[yRef][xRef+1] == 'B';
        boolean topWall = map[yRef-1][xRef] == 'B';
        boolean bottomWall = map[yRef+1][xRef] == 'B';

        return  leftWall && bottomWall ||
                leftWall && topWall ||
                rightWall && bottomWall ||
                rightWall && topWall;
    }



    private boolean checkIfIsReallyCleanField(int xRef, int yRef){
        //searchBorder
        boolean hasTopBorder = false;
        boolean hasRightBorder = false;
        boolean hasBottomBorder = false;
        boolean hasLeftBorder = false;

        for(int y = yRef; y >= 0; y--){
            if(map[y][xRef] == 'B'){
                hasTopBorder = true;
            }
        }
        for(int y = yRef; y < ySize; y++){
            if(map[y][xRef] == 'B'){
                hasBottomBorder = true;
            }
        }
        for(int x = xRef; x >= 0; x--){
            if(map[yRef][x] == 'B'){
                hasLeftBorder = true;
            }
        }
        for(int x = xRef; x < xSize; x++){
            if(map[yRef][x] == 'B'){
                hasRightBorder = true;
            }
        }

        return hasBottomBorder && hasLeftBorder && hasRightBorder && hasTopBorder;
    }
}
