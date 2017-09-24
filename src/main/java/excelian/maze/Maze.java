package excelian.maze;

import java.nio.CharBuffer;
import java.util.Arrays;

public class Maze {

    public static final char WALL = 'X';
    public static final char SPACE = ' ';
    public static final char START = 'S';
    public static final char FINISH = 'F';

    private final char[][] maze;

    public Maze(char[][] maze) {
        this.maze = maze;
    }

    public int getWallsCount() {
        return (int) Arrays.stream(maze)
                .map(CharBuffer::wrap)
                .flatMapToInt(CharBuffer::chars)
                .filter(i -> i == WALL)
                .count();
    }

    public int getSpacesCount() {
        return (int) Arrays.stream(maze)
                .map(CharBuffer::wrap)
                .flatMapToInt(CharBuffer::chars)
                .filter(i -> i == SPACE)
                .count();
    }

    public char getCharAtCoord(Coordinates coordinates) {
        return maze[coordinates.getX()][coordinates.getY()];
    }

    public Coordinates getStartCoordinates() {
        return getCoordinatesFor(START);
    }

    public Coordinates getFinishCoordinates() {
        return getCoordinatesFor(FINISH);
    }

    public int getMaxX() {
        return maze.length - 1;
    }

    public int getMaxY() {
        return maze[getMaxX()].length - 1;
    }

    private Coordinates getCoordinatesFor(char chr) {
        for (int x = 0; x < maze.length; x++) {
            for (int y = 0; y < maze[x].length; y++) {
                if (maze[x][y] == chr) {
                    return new Coordinates(x, y);
                }
            }
        }
        return null;
    }
}
