import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {
    private Game game;

    @Before
    public void setUp() {
        game = new Game(8);
    }

    @Test
    public void testChooseBoardSize() {
        game.setBoardSize(10);
        assertEquals(10, game.getBoardSize());
    }

    @Test
    public void testBoardSizeEnteredIsNumeric() {
        assertEquals(false, game.isBoardSizeTextNumeric("a"));
        assertEquals(true, game.isBoardSizeTextNumeric("5"));
    }

    @Test
    public void testBoardSizeEnteredIsGreaterThanTwo() {
        assertEquals(false, game.isBoardSizeGreaterThanTwo(2));
        assertEquals(true, game.isBoardSizeGreaterThanTwo(5));
    }

    @Test
    public void testSimpleGameMode() {
        game.setSimpleGame(true);
        assertTrue(game.isSimpleGame());
    }

    @Test
    public void testGeneralGameMode() {
        game.setSimpleGame(false);
        assertFalse(game.isSimpleGame());
    }

    @Test
    public void testStartGameWithChosenSizeAndMode() {
        int boardSize = 9;
        boolean isSimpleGame = false;
        game.reset(boardSize, isSimpleGame);
        assertEquals(boardSize, game.getBoardSize());
        assertEquals(false, game.isSimpleGame());
        assertEquals("", game.getGameBoard()[0][0]);
    }

    @Test
    public void testMakeSMoveInSimpleGame() {
        game.reset(8, true);
        game.makeMove(5, 0, "S");
        assertEquals("S", game.getGameBoard()[5][0]);
    }

    @Test
    public void testMakeOMoveInSimpleGame() {
        game.reset(8, true);
        game.makeMove(0, 2, "O");
        assertEquals("O", game.getGameBoard()[0][2]);
    }

    @Test
    public void testMakeSMoveInGeneralGame() {
        game.reset(8, false);
        game.makeMove(5, 0, "S");
        assertEquals("S", game.getGameBoard()[5][0]);
    }

    @Test
    public void testMakeOMoveInGeneralGame() {
        game.reset(8, false);
        game.makeMove(0, 2, "O");
        assertEquals("O", game.getGameBoard()[0][2]);
    }

    @Test
    public void testInitialBoardSize() {
        assertEquals(8, game.getBoardSize());
    }

    @Test
    public void testInitializeBoard() {
        game.initializeBoard();
        assertEquals("", game.getGameBoard()[0][0]);
    }

    @Test
    public void testInitialPlayerTurn() {
        assertTrue(game.isBluePlayersTurn());
    }

    @Test
    public void testInitialGameMode() {
        assertTrue(game.isSimpleGame());
    }

    @Test
    public void testResetGame() {
        game.makeMove(0, 0, "S");
        game.reset(10, false);
        assertEquals(10, game.getBoardSize());
        assertEquals(false, game.isSimpleGame());
        assertEquals("", game.getGameBoard()[0][0]);
    }
}
