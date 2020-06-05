import java.awt.Graphics;

/**
 * class that makes the 2D array for the board
 */
public class Board {

    private Tile[][] board;

    // constructor
    public Board() {
        board = new Tile[4][4]; // 4x4 tile grid
    }

    /**
     *  BASIC FUNCTIONS TO CREATE BOARD & TILES
     */

    // make the board
    public void createBoard() {
        // fill up grid with 0s and some random tiles 
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = new Tile(0);
            }
        }
        randomTile();
        randomTile();
    }

    // method to add random tiles (can be a 2 or a 4)
    public void randomTile() {
        if (!isBoardFilled()) {
            // randomize value (2 or 4)
            int val;
            if (Math.random() > 0.2) { // make 2s more common
                val = 2;
            } else {
                val = 4;
            }

            // randomize row and column (0-3)
            int row = (int) (Math.random() * 4);
            int col = (int) (Math.random() * 4);

            // check that the space is 0
            while (!board[row][col].isEmpty()) {
                row = (int) (Math.random() * 4);
                col = (int) (Math.random() * 4);
            }

            // insert random tile!
            board[row][col].setValue(val);
        }
    }

    // method to create a blank board (USE ONLY FOR TESTING)
    public void blankBoard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = new Tile(0);
            }
        }
    }

    // method to add a specific tile (USE ONLY FOR TESTING)
    public void addTile(int val, int x, int y) {
        board[x][y].setValue(val);
    }

    /**
     *  MOVE FUNCTIONS
     */

    // slide right
    public void slideRight() {
        for (int i = 0; i < 4; i++) {
            for (int j = 2; j >= 0; j--) {
                int position = j;
                // while the next tile is 0 and its not at the end
                while (position + 1 <= 3 && board[i][position + 1].isEmpty()) {
                    // shift tile one over
                    int val = board[i][position].getValue();
                    board[i][position + 1].setValue(val);
                    board[i][position].setValue(0);
                    // check next position
                    position++;
                }
                // if the next tile is the same val, handle merge
                if (position < 3 && !board[i][position + 1].isEmpty() && 
                        board[i][position].getValue() == board[i][position + 1].getValue() &&
                        !board[i][position + 1].getMerged()) {
                    int val = board[i][position].getValue();
                    board[i][position + 1].setValue(2 * val);
                    board[i][position + 1].setMerged(true); // just merged, shouldn't merge again
                    board[i][position].setValue(0);
                }
            }
        }
        resetHasMerged();
    }

    // slide left
    public void slideLeft() {
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                int position = j;
                // while the next tile is 0 and its not at the end
                while (position - 1 >= 0 && board[i][position - 1].isEmpty()) {
                    // shift tile one over
                    int val = board[i][position].getValue();
                    board[i][position - 1].setValue(val);
                    board[i][position].setValue(0);
                    // check next position
                    position--;
                }
                // if the next tile is the same val, handle merge
                if (position > 0 && !board[i][position - 1].isEmpty() && 
                        board[i][position].getValue() == board[i][position - 1].getValue() &&
                        !board[i][position - 1].getMerged()) {
                    int val = board[i][position].getValue();
                    board[i][position - 1].setValue(2 * val);
                    board[i][position - 1].setMerged(true); // just merged, shouldn't merge again
                    board[i][position].setValue(0);
                }
            }
        }
        resetHasMerged();
    }

    // slide up
    public void slideUp() {
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int position = i;
                // while the next tile is 0 and its not at the end
                while (position - 1 >= 0 && board[position - 1][j].isEmpty()) {
                    // shift tile one over
                    int val = board[position][j].getValue();
                    board[position - 1][j].setValue(val);
                    board[position][j].setValue(0);
                    // check next position
                    position--;
                }
                // if the next tile is the same val, handle merge
                if (position > 0 && !board[position - 1][j].isEmpty() && 
                        board[position][j].getValue() == board[position - 1][j].getValue() &&
                        !board[position - 1][j].getMerged()) {
                    int val = board[position][j].getValue();
                    board[position - 1][j].setValue(2 * val);
                    board[position - 1][j].setMerged(true); // just merged, shouldn't merge again
                    board[position][j].setValue(0);
                }
            }
        }
        resetHasMerged();
    }

    // slide down
    public void slideDown() {
        for (int i = 2; i >= 0; i--) {
            for (int j = 0; j < 4; j++) {
                int position = i;
                // while the next tile is 0 and its not at the end
                while (position + 1 <= 3 && board[position + 1][j].isEmpty()) {
                    // shift tile one over
                    int val = board[position][j].getValue();
                    board[position + 1][j].setValue(val);
                    board[position][j].setValue(0);
                    // check next position
                    position++;
                }
                // if the next tile is the same val, handle merge
                if (position < 3 && !board[position + 1][j].isEmpty() &&
                        board[position][j].getValue() == board[position + 1][j].getValue() &&
                        !board[position + 1][j].getMerged()) {
                    int val = board[position][j].getValue();
                    board[position + 1][j].setValue(2 * val);
                    board[position + 1][j].setMerged(true); // just merged, shouldn't merge again
                    board[position][j].setValue(0);
                }
            }
        }
        resetHasMerged();
    }
    
    // resets the hasMerged boolean for all tiles after the move is complete
    public void resetHasMerged() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j].setMerged(false);
            }
        }
    }

    // check valid move
    // (basically if everything is to the left and you press left again nothing happens)
    public boolean validMove(String direction) {
        Board copy = new Board(); // make a new board
        copy.setBoard(this.getBoard()); // copy the old board before transformation
        if (direction.equals("left")) {
            copy.slideLeft(); // perform action on copy
        }
        if (direction.equals("right")) {
            copy.slideRight(); // perform action on copy
        }
        if (direction.equals("up")) {
            copy.slideUp(); // perform action on copy
        }
        if (direction.equals("down")) {
            copy.slideDown(); // perform action on copy
        }
        // only valid if the board changes
        return !sameBoard(copy);
    }

    // checks if two boards are structurally equal (same elements)
    public boolean sameBoard(Board copy) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int copyVal = copy.getBoard()[i][j].getValue();
                int boardVal = board[i][j].getValue();
                if (copyVal != boardVal) { // check each indiv value
                    return false; // if the board changes, return false
                }
            }
        }
        return true; // if the board doesn't change, return true
    }

    /**
     *  END GAME CHECKERS
     */

    // check if board is filled
    public boolean isBoardFilled() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].getValue() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // check if there are still moves left (if you can merge tiles)
    public boolean canMerge() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].getValue() != 0) {
                    // check top
                    if (i + 1 < 4) { // make sure its not in the top row
                        if (board[i][j].getValue() == board[i + 1][j].getValue()) {
                            return true;
                        }
                    }
                    // check bottom
                    if (i - 1 > 0) { // make sure its not in the bottom row
                        if (board[i][j].getValue() == board[i - 1][j].getValue()) {
                            return true;
                        }
                    }
                    // check right
                    if (j + 1 < 4) { // make sure its not in the rightmost row
                        if (board[i][j].getValue() == board[i][j + 1].getValue()) {
                            return true;
                        }
                    }
                    // check left
                    if (j - 1 >= 0) { // make sure its not in the leftmost row
                        if (board[i][j].getValue() == board[i][j - 1].getValue()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // check if the user won the game
    public boolean gameWon() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].getValue() == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    // check if user lost the game
    public boolean gameLost() {
        return !canMerge() && isBoardFilled();
    }

    /**
     * GETTER AND SETTER FUNCTIONS (use for linked list/undo function)
     */
    public Tile[][] getBoard() { // make copies of each board to save them for undo
        Tile[][] boardCopy = new Tile[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                boardCopy[i][j] = new Tile(board[i][j].getValue());
            }
        }
        return boardCopy;
    }

    public void setBoard(Tile[][] newBoard) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = newBoard[i][j];
            }
        }
    }
    
    // get a specific tile in the board (USE ONLY FOR TESTING)
    public Tile getTileInBoard(int x, int y) {
        return board[x][y];
    }

    /**
     * ~DRAWING~
     */
    public void draw(Graphics g) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j].draw(g, 25 + 115 * j, 25 + 115 * i);
            }
        }
    }

    public void drawWelcomeScreen(Graphics g) {
        g.drawImage(TileImage.welcomeScreen, 25, 25, 445, 445, null);
    }

    public void drawWinningScreen(Graphics g) {
        g.drawImage(TileImage.winningScreen, 25, 25, 450, 450, null);
    }

    public void drawLosingScreen(Graphics g) {
        g.drawImage(TileImage.losingScreen, 25, 25, 450, 450, null);
    }
}
