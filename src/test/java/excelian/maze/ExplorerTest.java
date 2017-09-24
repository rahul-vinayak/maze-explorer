package excelian.maze;

import org.junit.Test;

import static excelian.maze.Explorer.*;
import static excelian.maze.Maze.SPACE;
import static excelian.maze.Maze.WALL;
import static org.junit.Assert.*;

public class ExplorerTest {

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

    private Maze maze = new Maze(mazeArr);
    private Explorer explorer = new Explorer(maze);

    @Test
    public void explorerShouldBeAtStartPosition() {
        explorer.start();
        assertPosition(EAST, 3, 3);
    }

    @Test
    public void shouldMoveForward() {
        setExplorerPosition(NORTH, 3, 3);
        explorer.moveForward();
        assertPosition(NORTH, 2, 3);

        setExplorerPosition(EAST, 3, 3);
        explorer.moveForward();
        assertPosition(EAST, 3, 4);

        setExplorerPosition(WEST, 3, 3);
        explorer.moveForward();
        assertPosition(WEST, 3, 2);

        setExplorerPosition(SOUTH, 3, 3);
        explorer.moveForward();
        assertPosition(SOUTH, 4, 3);
    }

    @Test
    public void shouldMoveBackward() {
        setExplorerPosition(NORTH, 3, 3);
        explorer.moveBackward();
        assertPosition(NORTH, 4, 3);

        setExplorerPosition(EAST, 3, 3);
        explorer.moveBackward();
        assertPosition(EAST, 3, 2);

        setExplorerPosition(WEST, 3, 3);
        explorer.moveBackward();
        assertPosition(WEST, 3, 4);

        setExplorerPosition(SOUTH, 3, 3);
        explorer.moveBackward();
        assertPosition(SOUTH, 2, 3);
    }

    @Test
    public void shouldNotMoveBeyondMazeBoundary() {
        setExplorerPosition(EAST, 14, 14);
        explorer.moveForward();
        assertPosition(EAST, 14, 14);

        setExplorerPosition(SOUTH, 14, 14);
        explorer.moveForward();
        assertPosition(SOUTH, 14, 14);

        setExplorerPosition(NORTH, 0, 0);
        explorer.moveForward();
        assertPosition(NORTH, 0, 0);

        setExplorerPosition(WEST, 0, 0);
        explorer.moveForward();
        assertPosition(WEST, 0, 0);
    }

    @Test
    public void shouldTurnLeft() {
        setExplorerPosition(EAST, 3, 3);
        explorer.turnLeft();
        assertPosition(NORTH, 3, 3);

        setExplorerPosition(NORTH, 3, 3);
        explorer.turnLeft();
        assertPosition(WEST, 3, 3);

        setExplorerPosition(WEST, 3, 3);
        explorer.turnLeft();
        assertPosition(SOUTH, 3, 3);

        setExplorerPosition(SOUTH, 3, 3);
        explorer.turnLeft();
        assertPosition(EAST, 3, 3);
    }

    @Test
    public void shouldTurnRight() {
        setExplorerPosition(EAST, 3, 3);
        explorer.turnRight();
        assertPosition(SOUTH, 3, 3);

        setExplorerPosition(SOUTH, 3, 3);
        explorer.turnRight();
        assertPosition(WEST, 3, 3);

        setExplorerPosition(WEST, 3, 3);
        explorer.turnRight();
        assertPosition(NORTH, 3, 3);

        setExplorerPosition(NORTH, 3, 3);
        explorer.turnRight();
        assertPosition(EAST, 3, 3);
    }

    @Test
    public void shouldGetCharAtFront() {
        setExplorerPosition(EAST, 3, 4);
        assertEquals(SPACE, explorer.getCharAtFront());

        setExplorerPosition(SOUTH, 3, 4);
        assertEquals(WALL, explorer.getCharAtFront());

        setExplorerPosition(WEST, 3, 4);
        assertEquals(SOUTH, explorer.getCharAtFront());

        setExplorerPosition(NORTH, 3, 4);
        assertEquals(WALL, explorer.getCharAtFront());
    }

    @Test
    public void shouldGetCharAtRight() {
        setExplorerPosition(WEST, 3, 4);
        assertEquals(WALL, explorer.getCharAtRight());

        setExplorerPosition(SOUTH, 3, 4);
        assertEquals(SOUTH, explorer.getCharAtRight());

        setExplorerPosition(EAST, 3, 4);
        assertEquals(WALL, explorer.getCharAtRight());

        setExplorerPosition(NORTH, 3, 4);
        assertEquals(SPACE, explorer.getCharAtRight());
    }

    @Test
    public void shouldGetCharAtLeft() {
        setExplorerPosition(WEST, 3, 4);
        assertEquals(WALL, explorer.getCharAtLeft());

        setExplorerPosition(SOUTH, 3, 4);
        assertEquals(SPACE, explorer.getCharAtLeft());

        setExplorerPosition(EAST, 3, 4);
        assertEquals(WALL, explorer.getCharAtLeft());

        setExplorerPosition(NORTH, 3, 4);
        assertEquals(SOUTH, explorer.getCharAtLeft());
    }

    @Test
    public void shouldExploreMaze() {
        explorer.exploreMaze();
        assertEquals("SFFFFFFFFRFFFFFBBRFFFFFLFFFRFFFLFFFLFFFFFFFFRFLFFLFFFFFFFFFFFFLFFFFFFFFFFFFLFFFFFFFFFFFFE", explorer.getMovementString());
    }

    private void setExplorerPosition(char orient, int x, int y) {
        explorer.setOrientation(orient);
        explorer.setCurrX(x);
        explorer.setCurrY(y);
    }

    private void assertPosition(char orient, int x, int y) {
        assertEquals(x, explorer.getCurrX());
        assertEquals(y, explorer.getCurrY());
        assertEquals(orient, explorer.getOrientation());
    }
}
