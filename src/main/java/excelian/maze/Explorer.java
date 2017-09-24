package excelian.maze;

public class Explorer {

    private char[] movement;
    private Maze maze;
    private int currX;
    private int currY;
    private char orientation;

    public void exploreMaze(Maze maze) {
        this.maze = maze;
//        start();
//        while (getCharInFront() != 'F') {
//            if (getCharInFront() == ' ') {
//                moveForward();
//            }
//
//        }
    }

    public void start() {
        Coordinates coordinates = maze.getStartCoordinates();
        moveTo(coordinates);
        orientation = 'E';
    }

    public void moveForward() {
        moveTo(getForwardCoordinates());
    }

    public char getCharAtFront() {
        return maze.getCharAtCoord(getForwardCoordinates());
    }

    public char getCharAtRight() {
        return maze.getCharAtCoord(getRightCoordinates());
    }

    public char getCharAtLeft() {
        return maze.getCharAtCoord(getLeftCoordinates());
    }

    public void turnLeft() {
        orientation = turn('L');
    }

    public void turnRight() {
        orientation = turn('R');
    }

    public int getCurrX() {
        return currX;
    }

    public int getCurrY() {
        return currY;
    }

    public char getOrientation() {
        return orientation;
    }

    public void setOrientation(char orientation) {
        this.orientation = orientation;
    }

    public void setCurrY(int currY) {
        this.currY = currY;
    }

    public void setCurrX(int currX) {
        this.currX = currX;
    }

    private char turn(char towards) {
        if (orientation == 'N') {
            return towards == 'L' ? 'W' : 'E';
        }
        if (orientation == 'E') {
            return towards == 'L' ? 'N' : 'S';
        }
        if (orientation == 'W') {
            return towards == 'L' ? 'S' : 'N';
        }
        if (orientation == 'S') {
            return towards == 'L' ? 'E' : 'W';
        }
        return 'E';
    }

    private void moveTo(Coordinates coordinates) {
        currX = coordinates.getX();
        currY = coordinates.getY();
    }

    private Coordinates getForwardCoordinates() {
        if (orientation == 'N') {
            return decreaseX();
        }
        if (orientation == 'E') {
            return increaseY();
        }
        if (orientation == 'W') {
            return decreaseY();
        }
        if (orientation == 'S') {
            return increaseX();
        }
        return new Coordinates(currX, currY);
    }

    private Coordinates getRightCoordinates() {
        if (orientation == 'W') {
            return decreaseX();
        }
        if (orientation == 'N') {
            return increaseY();
        }
        if (orientation == 'S') {
            return decreaseY();
        }
        if (orientation == 'E') {
            return increaseX();
        }
        return new Coordinates(currX, currY);
    }

    private Coordinates getLeftCoordinates() {
        if (orientation == 'W') {
            return increaseX();
        }
        if (orientation == 'N') {
            return decreaseY();
        }
        if (orientation == 'S') {
            return increaseY();
        }
        if (orientation == 'E') {
            return decreaseX();
        }
        return new Coordinates(currX, currY);
    }

    private Coordinates decreaseX() {
        return new Coordinates(currX > 0 ? currX - 1 : currX, currY);
    }

    private Coordinates increaseX() {
        return new Coordinates(currX < maze.getMaxX() ? currX + 1 : currX, currY);
    }

    private Coordinates decreaseY() {
        return new Coordinates(currX, currY > 0 ? currY - 1 : currY);
    }

    private Coordinates increaseY() {
        return new Coordinates(currX, currY < maze.getMaxY() ? currY + 1 : currY);
    }
}
