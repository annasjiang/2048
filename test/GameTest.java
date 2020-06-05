import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/** 
 *  You can use this file (and others) to test your
 *  implementation.
 */

public class GameTest {

    /**
     * TESTS FOR THE TILE CLASS
     */

    // test constructor
    @Test
    public void testTileConstructor() {
        Tile tile = new Tile(2);
        assertEquals(2, tile.getValue());
    }

    // test setValue
    @Test
    public void testTileSetter() {
        Tile tile = new Tile(2);
        tile.setValue(4);
        assertEquals(4, tile.getValue());
    }

    // test isEmpty true
    @Test
    public void testEmptyTile() {
        Tile tile = new Tile(0);
        assertTrue(tile.isEmpty());
    }

    // test isEmpty false
    @Test
    public void testNonEmptyTile() {
        Tile tile = new Tile(2);
        assertFalse(tile.isEmpty());
    }
    
    // test that a tile that hasn't merge is false
    @Test
    public void testHasMergedNoMerge() {
        Board board = new Board();
        board.blankBoard();
        board.addTile(2, 0, 0);
        Tile tile = board.getTileInBoard(0, 0);
        assertFalse(tile.getMerged()); 
    }
    
    // test that a tile after a merged is still false because of reset merge
    @Test
    public void testHasMergedAfterMerge() {
        Board board = new Board();
        board.blankBoard();
        board.addTile(2, 0, 0);
        board.addTile(2, 0, 1);
        board.slideLeft();
        Tile tile = board.getTileInBoard(0, 0);
        assertEquals(4, tile.getValue());
        assertFalse(tile.getMerged()); 
    }
    
    // test that tile starts with hasMerged false
    @Test
    public void testTileMergeOG() {
        Tile tile = new Tile(2);
        assertFalse(tile.getMerged()); 
    }
    
    // test that set merge works
    @Test
    public void testSetMerge() {
        Tile tile = new Tile(2);
        tile.setMerged(true);
        assertTrue(tile.getMerged()); 
    }

    /**
     * TESTS FOR THE BOARD CLASS
     */

    // test that constructor makes a nonempty board
    @Test
    public void testCreateBoard() {
        Board board = new Board();
        board.createBoard();
        Board emptyBoard = new Board();
        emptyBoard.blankBoard();

        assertNotEquals(board, emptyBoard); 
    }
    
    // test same board (structurally equal)
    @Test
    public void testSameBoard() {
        Board board = new Board();
        board.blankBoard();

        Board newBoard = new Board();
        newBoard.blankBoard();

        assertTrue(board.sameBoard(newBoard));
    }

    // test that random tile is added (will change the board)
    @Test
    public void testRandomTile() {
        Board board = new Board();
        board.createBoard();
        Board newBoard = new Board();
        newBoard.createBoard();
        newBoard.randomTile();

        assertFalse(board.sameBoard(newBoard)); 
    }

    // test getter/setter
    @Test
    public void testBoardGetterSetter() {
        Board board = new Board();
        board.createBoard();

        Board copy = new Board();
        copy.setBoard(board.getBoard());

        // boards are structurally equal
        assertTrue(board.sameBoard(copy));

        // makes diff copy (not ref equal)
        assertNotEquals(board, copy);
    }

    // test valid/invalid slide left (no merge)
    @Test
    public void testBoardSlideLeft() {
        Board board = new Board();
        board.blankBoard();
        board.addTile(2, 0, 3);
        board.slideLeft();

        Board newBoard = new Board();
        newBoard.blankBoard();
        newBoard.addTile(2, 0, 0);

        assertTrue(board.sameBoard(newBoard));

        // test invalid slide
        board.slideLeft();
        assertTrue(board.sameBoard(newBoard));
    }

    // test valid/invalid slide right (no merge)
    @Test
    public void testBoardSlideRight() {
        Board board = new Board();
        board.blankBoard();
        board.addTile(2, 0, 0);
        board.slideRight();

        Board newBoard = new Board();
        newBoard.blankBoard();
        newBoard.addTile(2, 0, 3);

        assertTrue(board.sameBoard(newBoard));

        // test invalid slide
        board.slideRight();
        assertTrue(board.sameBoard(newBoard));
    }

    // test valid/invalid slide up (no merge)
    @Test
    public void testBoardSlideUp() {
        Board board = new Board();
        board.blankBoard();
        board.addTile(2, 0, 0);
        board.slideUp();

        Board newBoard = new Board();
        newBoard.blankBoard();
        newBoard.addTile(2, 0, 0);

        assertTrue(board.sameBoard(newBoard));

        // test invalid slide
        board.slideUp();
        assertTrue(board.sameBoard(newBoard));
    }

    // test valid/invalid slide down (no merge)
    @Test
    public void testBoardSlideDown() {
        Board board = new Board();
        board.blankBoard();
        board.addTile(2, 0, 0);
        board.slideDown();

        Board newBoard = new Board();
        newBoard.blankBoard();
        newBoard.addTile(2, 3, 0);

        assertTrue(board.sameBoard(newBoard));

        // test invalid slide
        board.slideDown();
        assertTrue(board.sameBoard(newBoard));
    }

    // test slide with a merge
    @Test
    public void testBoardSlideOneMerge() {
        Board board = new Board();
        board.blankBoard();
        board.addTile(2, 0, 0);
        board.addTile(2, 0, 1);
        board.slideLeft();

        Board newBoard = new Board();
        newBoard.blankBoard();
        newBoard.addTile(4, 0, 0);

        assertTrue(board.sameBoard(newBoard));
    }

    // test slide multiple merges with same numbers
    @Test
    public void testBoardSlideMultMergesSameNum() {
        Board board = new Board();
        board.blankBoard();
        board.addTile(4, 0, 0);
        board.addTile(4, 0, 1);
        board.addTile(4, 0, 2);
        board.addTile(4, 0, 3);
        board.slideLeft();

        Board newBoard = new Board();
        newBoard.blankBoard();
        newBoard.addTile(8, 0, 0);
        newBoard.addTile(8, 0, 1);

        assertTrue(board.sameBoard(newBoard));
        
        // test that it can merge again afterwards
        board.slideLeft();
        
        Board newBoard1 = new Board();
        newBoard1.blankBoard();
        newBoard1.addTile(16, 0, 0);
        
        assertTrue(board.sameBoard(newBoard1));
    }
    
    // test slide multiple merges with diff numbers in increasing order
    @Test
    public void testBoardSlideMultMergesDiffNumInc() {
        Board board = new Board();
        board.blankBoard();
        board.addTile(4, 0, 0);
        board.addTile(4, 0, 1);
        board.addTile(2, 0, 2);
        board.addTile(2, 0, 3);
        board.slideLeft();

        Board newBoard = new Board();
        newBoard.blankBoard();
        newBoard.addTile(8, 0, 0);
        newBoard.addTile(4, 0, 1);

        assertTrue(board.sameBoard(newBoard));
        
        // test that it can merge again afterwards
        board.addTile(8, 1, 0);
        board.slideUp();
        
        Board newBoard1 = new Board();
        newBoard1.blankBoard();
        newBoard1.addTile(16, 0, 0);
        newBoard1.addTile(4, 0, 1);
        
        assertTrue(board.sameBoard(newBoard1));   
    }
    
    // test slide multiple merges with diff numbers in decreasing order
    @Test
    public void testBoardSlideMultMergesDiffNumDec() {
        Board board = new Board();
        board.blankBoard();
        board.addTile(2, 0, 0);
        board.addTile(2, 0, 1);
        board.addTile(4, 0, 2);
        board.addTile(4, 0, 3);
        board.slideLeft();

        Board newBoard = new Board();
        newBoard.blankBoard();
        newBoard.addTile(4, 0, 0);
        newBoard.addTile(8, 0, 1);

        assertTrue(board.sameBoard(newBoard));
    }

    // test canMerge left/right
    @Test
    public void testCanMergeLeftRight() {
        Board board = new Board();
        board.blankBoard();
        board.addTile(2, 0, 0);
        board.addTile(2, 0, 1);
        
        assertTrue(board.canMerge());
    }

    // test canMerge up/down
    @Test
    public void testCanMergeUpDown() {
        Board board = new Board();
        board.blankBoard();
        board.addTile(2, 0, 0);
        board.addTile(2, 1, 0);
        
        assertTrue(board.canMerge());
    }

    // test canMerge false
    @Test
    public void testCanMergeFalse() {
        Board board = new Board();
        board.blankBoard();
        board.addTile(2, 0, 0);
        board.addTile(2, 3, 3);
        
        assertFalse(board.canMerge());
    }

    // test isFilled true
    @Test
    public void testIsBoardFilledTrue() {
        Board board = new Board();
        board.blankBoard();
        
        // row one
        board.addTile(2, 0, 0);
        board.addTile(4, 0, 1);
        board.addTile(2, 0, 2);
        board.addTile(4, 0, 3);

        // row two 
        board.addTile(4, 1, 0);
        board.addTile(2, 1, 1);
        board.addTile(4, 1, 2);
        board.addTile(2, 1, 3);

        // row three
        board.addTile(2, 2, 0);
        board.addTile(4, 2, 1);
        board.addTile(2, 2, 2);
        board.addTile(4, 2, 3);

        // row four
        board.addTile(4, 3, 0);
        board.addTile(2, 3, 1);
        board.addTile(4, 3, 2);
        board.addTile(2, 3, 3);
        
        assertTrue(board.isBoardFilled());
    }

    // test isFilled false
    @Test
    public void testIsBoardFilledFalse() {
        Board board = new Board();
        board.createBoard();
        
        assertFalse(board.isBoardFilled());
    }

    // test gameWon winning board
    @Test
    public void testGameWonWinningBoard() {
        Board board = new Board();
        board.blankBoard();
        board.addTile(2048, 0, 0);
        assertTrue(board.gameWon());
    }

    // test gameWon losing board 
    @Test
    public void testGameWonLosingBoard() {
        Board board = new Board();
        board.blankBoard();
        assertFalse(board.gameWon());
    }

    // test gameWon filled board (with 2048)
    @Test
    public void testGameWonFilledBoard() {
        Board board = new Board();
        board.blankBoard();

        // row one
        board.addTile(2048, 0, 0);
        board.addTile(4, 0, 1);
        board.addTile(2, 0, 2);
        board.addTile(4, 0, 3);

        // row two 
        board.addTile(4, 1, 0);
        board.addTile(2, 1, 1);
        board.addTile(4, 1, 2);
        board.addTile(2, 1, 3);

        // row three
        board.addTile(2, 2, 0);
        board.addTile(4, 2, 1);
        board.addTile(2, 2, 2);
        board.addTile(4, 2, 3);

        // row four
        board.addTile(4, 3, 0);
        board.addTile(2, 3, 1);
        board.addTile(4, 3, 2);
        board.addTile(2, 3, 3);

        assertTrue(board.gameWon());
    }

    // test gameLost regular board
    @Test
    public void testGameLostRegularBoard() {
        Board board = new Board();
        board.createBoard();
        assertFalse(board.gameLost());
    }

    // test gameLost filled board (but can still merge)
    @Test
    public void testGameLostFilledBoard() {
        Board board = new Board();
        board.blankBoard();
        // row one
        board.addTile(2, 0, 0);
        board.addTile(4, 0, 1);
        board.addTile(2, 0, 2);
        board.addTile(4, 0, 3);

        // row two 
        board.addTile(2, 1, 0);
        board.addTile(4, 1, 1);
        board.addTile(2, 1, 2);
        board.addTile(4, 1, 3);

        // row three
        board.addTile(4, 2, 0);
        board.addTile(2, 2, 1);
        board.addTile(4, 2, 2);
        board.addTile(2, 2, 3);

        // row four
        board.addTile(4, 3, 0);
        board.addTile(2, 3, 1);
        board.addTile(4, 3, 2);
        board.addTile(2, 3, 3);

        assertFalse(board.gameLost());
    }

    // test gameWon losing board 
    @Test
    public void testGameLostLosingBoard() {
        Board board = new Board();
        board.blankBoard();

        // row one
        board.addTile(2, 0, 0);
        board.addTile(4, 0, 1);
        board.addTile(2, 0, 2);
        board.addTile(4, 0, 3);

        // row two 
        board.addTile(4, 1, 0);
        board.addTile(2, 1, 1);
        board.addTile(4, 1, 2);
        board.addTile(2, 1, 3);

        // row three
        board.addTile(2, 2, 0);
        board.addTile(4, 2, 1);
        board.addTile(2, 2, 2);
        board.addTile(4, 2, 3);

        // row four
        board.addTile(4, 3, 0);
        board.addTile(2, 3, 1);
        board.addTile(4, 3, 2);
        board.addTile(2, 3, 3);

        assertTrue(board.gameLost());
    }

}
