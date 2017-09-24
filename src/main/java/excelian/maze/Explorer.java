package excelian.maze;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static excelian.maze.Maze.FINISH;
import static excelian.maze.Maze.SPACE;
import static excelian.maze.Maze.START;

public class Explorer {

    private static final char FORWARD = 'F';
    private static final char BACKWARD = 'B';
    private static final char LEFT = 'L';
    private static final char RIGHT = 'R';

    static final char NORTH = 'N';
    static final char WEST = 'W';
    static final char EAST = 'E';
    static final char SOUTH = 'S';

    private List<Character> movement = new ArrayList<>();
    private Maze maze;
    private int currX;
    private int currY;
    private char orientation;

    private static Logger logger = Logger.getLogger(Explorer.class);

    public Explorer(Maze maze) {
        this.maze = maze;
    }

    public void exploreMaze() {
        start();
        boolean reverse = false;
        while (getCharAtFront() != FINISH) {
            if (getCharAtFront() == SPACE && !reverse) {
                moveForward();
            } else if (getCharAtLeft() == SPACE) {
                turnLeft();
                reverse = false;
            } else if (getCharAtRight() == SPACE) {
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
        orientation = EAST;
        logger.debug("starting point");
        movement.add(START);
    }

    public void moveForward() {
        moveTo(getForwardCoordinates());
        logger.debug("moving forward");
        movement.add(FORWARD);
    }

    public void moveBackward() {
        moveTo(getBackwardCoordinates());
        logger.debug("moving backward");
        movement.add(BACKWARD);
    }

    public void turnLeft() {
        orientation = turn(LEFT);
        logger.debug("turning left");
        movement.add(LEFT);
    }

    public void turnRight() {
        orientation = turn(RIGHT);
        logger.debug("turning right");
        movement.add(RIGHT);
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

    public String getMovementString() {
        return movement
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    private char turn(char towards) {
        if (orientation == NORTH) {
            return towards == LEFT ? WEST : EAST;
        }
        if (orientation == EAST) {
            return towards == LEFT ? NORTH : SOUTH;
        }
        if (orientation == WEST) {
            return towards == LEFT ? SOUTH : NORTH;
        }
        if (orientation == SOUTH) {
            return towards == LEFT ? EAST : WEST;
        }
        return 'E';
    }

    private void moveTo(Coordinates coordinates) {
        currX = coordinates.getX();
        currY = coordinates.getY();
    }

    private Coordinates getForwardCoordinates() {
        if (orientation == NORTH) {
            return decreaseX();
        }
        if (orientation == EAST) {
            return increaseY();
        }
        if (orientation == WEST) {
            return decreaseY();
        }
        if (orientation == SOUTH) {
            return increaseX();
        }
        return new Coordinates(currX, currY);
    }

    private Coordinates getBackwardCoordinates() {
        if (orientation == NORTH) {
            return increaseX();
        }
        if (orientation == EAST) {
            return decreaseY();
        }
        if (orientation == WEST) {
            return increaseY();
        }
        if (orientation == SOUTH) {
            return decreaseX();
        }
        return new Coordinates(currX, currY);
    }

    private Coordinates getRightCoordinates() {
        if (orientation == WEST) {
            return decreaseX();
        }
        if (orientation == NORTH) {
            return increaseY();
        }
        if (orientation == SOUTH) {
            return decreaseY();
        }
        if (orientation == EAST) {
            return increaseX();
        }
        return new Coordinates(currX, currY);
    }

    private Coordinates getLeftCoordinates() {
        if (orientation == WEST) {
            return increaseX();
        }
        if (orientation == NORTH) {
            return decreaseY();
        }
        if (orientation == SOUTH) {
            return increaseY();
        }
        if (orientation == EAST) {
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
