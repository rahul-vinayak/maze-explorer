package excelian.maze;

import org.junit.Test;

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
        assertPosition('E', 3, 3);
    }

    @Test
    public void shouldMoveForward() {
        setExplorerPosition('N', 3, 3);
        explorer.moveForward();
        assertPosition('N', 2, 3);

        setExplorerPosition('E', 3, 3);
        explorer.moveForward();
        assertPosition('E', 3, 4);

        setExplorerPosition('W', 3, 3);
        explorer.moveForward();
        assertPosition('W', 3, 2);

        setExplorerPosition('S', 3, 3);
        explorer.moveForward();
        assertPosition('S', 4, 3);
    }

    @Test
    public void shouldMoveBackward() {
        setExplorerPosition('N', 3, 3);
        explorer.moveBackward();
        assertPosition('N', 4, 3);

        setExplorerPosition('E', 3, 3);
        explorer.moveBackward();
        assertPosition('E', 3, 2);

        setExplorerPosition('W', 3, 3);
        explorer.moveBackward();
        assertPosition('W', 3, 4);

        setExplorerPosition('S', 3, 3);
        explorer.moveBackward();
        assertPosition('S', 2, 3);
    }

    @Test
    public void shouldNotMoveBeyondMazeBoundary() {
        setExplorerPosition('E', 14, 14);
        explorer.moveForward();
        assertPosition('E', 14, 14);

        setExplorerPosition('S', 14, 14);
        explorer.moveForward();
        assertPosition('S', 14, 14);

        setExplorerPosition('N', 0, 0);
        explorer.moveForward();
        assertPosition('N', 0, 0);

        setExplorerPosition('W', 0, 0);
        explorer.moveForward();
        assertPosition('W', 0, 0);
    }

    @Test
    public void shouldTurnLeft() {
        setExplorerPosition('E', 3, 3);
        explorer.turnLeft();
        assertPosition('N', 3, 3);

        setExplorerPosition('N', 3, 3);
        explorer.turnLeft();
        assertPosition('W', 3, 3);

        setExplorerPosition('W', 3, 3);
        explorer.turnLeft();
        assertPosition('S', 3, 3);

        setExplorerPosition('S', 3, 3);
        explorer.turnLeft();
        assertPosition('E', 3, 3);
    }

    @Test
    public void shouldTurnRight() {
        setExplorerPosition('E', 3, 3);
        explorer.turnRight();
        assertPosition('S', 3, 3);

        setExplorerPosition('S', 3, 3);
        explorer.turnRight();
        assertPosition('W', 3, 3);

        setExplorerPosition('W', 3, 3);
        explorer.turnRight();
        assertPosition('N', 3, 3);

        setExplorerPosition('N', 3, 3);
        explorer.turnRight();
        assertPosition('E', 3, 3);
    }

    @Test
    public void shouldGetCharAtFront() {
        setExplorerPosition('E', 3, 4);
        assertEquals(' ', explorer.getCharAtFront());

        setExplorerPosition('S', 3, 4);
        assertEquals('X', explorer.getCharAtFront());

        setExplorerPosition('W', 3, 4);
        assertEquals('S', explorer.getCharAtFront());

        setExplorerPosition('N', 3, 4);
        assertEquals('X', explorer.getCharAtFront());
    }

    @Test
    public void shouldGetCharAtRight() {
        setExplorerPosition('W', 3, 4);
        assertEquals('X', explorer.getCharAtRight());

        setExplorerPosition('S', 3, 4);
        assertEquals('S', explorer.getCharAtRight());

        setExplorerPosition('E', 3, 4);
        assertEquals('X', explorer.getCharAtRight());

        setExplorerPosition('N', 3, 4);
        assertEquals(' ', explorer.getCharAtRight());
    }

    @Test
    public void shouldGetCharAtLeft() {
        setExplorerPosition('W', 3, 4);
        assertEquals('X', explorer.getCharAtLeft());

        setExplorerPosition('S', 3, 4);
        assertEquals(' ', explorer.getCharAtLeft());

        setExplorerPosition('E', 3, 4);
        assertEquals('X', explorer.getCharAtLeft());

        setExplorerPosition('N', 3, 4);
        assertEquals('S', explorer.getCharAtLeft());
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
