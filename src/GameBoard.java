import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import javax.swing.*;

/**
 * class that combines the board and the tiles (handles actions)
 * basically its a linked list of boards (history) that updates with each move/undo/reset
 */
public class GameBoard extends JPanel {

    // fields
    private Board board;
    private LinkedList<Tile[][]> history;
    private int numMoves;
    private JLabel moveCounter;
    
    private boolean viewInstructions;
    private boolean isRunning;

    public GameBoard(JLabel status) {
        board = new Board();
        board.createBoard();

        history = new LinkedList<Tile[][]>();
        numMoves = 0;

        setFocusable(true);
        // handles all the movements and the state of the game
        addKeyListener(new KeyAdapter() {    
            @Override
            public void keyPressed(KeyEvent e) {
                if (true) { // can only move if the game is running
                    if (e.getKeyCode() == KeyEvent.VK_LEFT && board.validMove("left")) {
                        history.addFirst(board.getBoard());
                        board.slideLeft();
                        board.randomTile();
                        numMoves++;
                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && board.validMove("right")) {
                        history.addFirst(board.getBoard());
                        board.slideRight();
                        board.randomTile();
                        numMoves++;
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN && board.validMove("down")) {
                        history.addFirst(board.getBoard());
                        board.slideDown();
                        board.randomTile();
                        numMoves++;
                    } else if (e.getKeyCode() == KeyEvent.VK_UP && board.validMove("up")) {
                        history.addFirst(board.getBoard());
                        board.slideUp();
                        board.randomTile();
                        numMoves++;
                    }
                }
                
                moveCounter.setText("Moves: " + Integer.toString(numMoves));
                
                if (endGame()) { 
                    isRunning = false;
                }
                repaint();
            }
        });
        moveCounter = status;

    };

    // undo function
    public void undo() {
        isRunning = true;
        if (history.size() != 0) {
            // remove last board from LL
            board.setBoard(history.removeFirst());
            repaint();
            // decrease moves
            if (numMoves > 0) {
                numMoves--;
            }
            moveCounter.setText("Moves: " + Integer.toString(numMoves));
        }
        requestFocusInWindow();
    }

    // reset function (aka create a new board)
    public void reset() {
        isRunning = true;
        board.createBoard();
        history.clear();
        numMoves = 0;
        moveCounter.setText("Moves: " + Integer.toString(numMoves));  

        repaint();
        requestFocusInWindow();
    }

    // view instructions
    public void instructions() {
        if (viewInstructions) {
            viewInstructions = false;
        } else {
            viewInstructions = true;
        }
        requestFocusInWindow();
    }

    // checks if the game over
    public boolean endGame() {
        return board.gameWon() || board.gameLost();        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        board.draw(g); 

        if (viewInstructions) {
            board.drawWelcomeScreen(g);
        }

        // game isn't running, draw end screen
        if (!isRunning) {       
            if (board.gameWon()) {   
                board.drawWinningScreen(g); 
            }
            if (board.gameLost()) {
                board.drawLosingScreen(g);     
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }
}
