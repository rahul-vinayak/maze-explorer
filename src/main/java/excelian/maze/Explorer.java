package excelian.maze;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Explorer {

    private List<Character> movement = new ArrayList<>();
    private Maze maze;
    private int currX;
    private int currY;
    private char orientation;

    public static Logger logger = Logger.getLogger(Explorer.class);

    public Explorer(Maze maze) {
        this.maze = maze;
    }

    public void exploreMaze() {
        start();
        boolean reverse = false;
        while (getCharAtFront() != 'F') {
            if (getCharAtFront() == ' ' && !reverse) {
                moveForward();
            } else if (getCharAtLeft() == ' ') {
                turnLeft();
                reverse = false;
            } else if(getCharAtRight() == ' ') {
                turnRight();
                reverse = false;
            } else {
                reverse = true;
                moveBackward();
            }
        }
        logger.debug("Finished exploring");
        movement.add('E');
    }

    public void start() {
        Coordinates coordinates = maze.getStartCoordinates();
        moveTo(coordinates);
        logger.debug("starting point");
        movement.add('S');
        orientation = 'E';
    }

    public void moveForward() {
        logger.debug("moving forward");
        movement.add('F');
        moveTo(getForwardCoordinates());
    }

    public void moveBackward() {
        logger.debug("moving backward");
        movement.add('B');
        moveTo(getBackwardCoordinates());
    }

    public void turnLeft() {
        logger.debug("turning left");
        movement.add('L');
        orientation = turn('L');
    }

    public void turnRight() {
        logger.debug("turning right");
        movement.add('R');
        orientation = turn('R');
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

    public String getMovement() {
        return movement
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining());
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

    private Coordinates getBackwardCoordinates() {
        if (orientation == 'N') {
            return increaseX();
        }
        if (orientation == 'E') {
            return decreaseY();
        }
        if (orientation == 'W') {
            return increaseY();
        }
        if (orientation == 'S') {
            return decreaseX();
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
