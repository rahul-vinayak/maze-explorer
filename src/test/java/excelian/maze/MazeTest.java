package excelian.maze;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MazeTest {

    private char[][] mazeArr = {
            "XXXXXXXXXXXXXXX".toCharArray(),
            "X             X".toCharArray(),
            "X XXXXXXXXXXX X".toCharArray(),
            "X XS        X X".toCharArray(),
            "X XXXXXXXXX X X".toCharArray(),
            "X XXXXXXXXX X X".toCharArray(),
            "X XXXX      X X".toCharArray(),
            "X XXXX XXXX X X".toCharArray(),
            "X XXXX XXXX X X".toCharArray(),
            "X X    XXXXXX X".toCharArray(),
            "X X XXXXXXXXX X".toCharArray(),
            "X X XXXXXXXXX X".toCharArray(),
            "X X         X X".toCharArray(),
            "X XXXXXXXXX   X".toCharArray(),
            "XFXXXXXXXXXXXXX".toCharArray()
    };

    private Maze maze;

    @Before
    public void before(){
        maze = new Maze(mazeArr);
    }

    @Test
    public void shouldGetNumberOfWalls() {
        assertEquals(149, maze.getWallsCount());
    }

    @Test
    public void shouldGetNumberOfSpaces() {
        assertEquals(74, maze.getSpacesCount());
    }

    @Test
    public void shouldGetCharAtCoord() {
        assertEquals('F', maze.getCharAtCoord(new Coordinates(14,1)));
    }

    @Test
    public void shouldGetStartCoordinates() {
        assertEquals(3, maze.getStartCoordinates().getX());
        assertEquals(3, maze.getStartCoordinates().getY());
    }

    @Test
    public void shouldGetFinishCoordinates() {
        assertEquals(14, maze.getFinishCoordinates().getX());
        assertEquals(1, maze.getFinishCoordinates().getY());
    }

    @Test
    public void assertMaxX() {
        assertEquals(14, maze.getMaxX());
    }

    @Test
    public void assertMaxY() {
        assertEquals(14, maze.getMaxY());
    }
}
