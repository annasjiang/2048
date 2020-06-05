// imports necessary libraries for Java swing
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
    public void run() {
        // top-level frame in which game components live
        final JFrame frame = new JFrame("2048");
        frame.setLocation(300, 300);
        frame.setLayout(new BorderLayout());
        
        // status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.CENTER);
        final JLabel status = new JLabel("Moves: 0");
        status_panel.add(status);

        // main playing area
        final GameBoard gameBoard = new GameBoard(status);
        frame.add(gameBoard, BorderLayout.SOUTH);

        // control panel
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);
        
        
        // reset button
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameBoard.reset();
                gameBoard.repaint();
                gameBoard.requestFocusInWindow();
            }
        });
        control_panel.add(reset);
        
        // instructions button
        final JButton instructions = new JButton("Instructions");
        instructions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameBoard.instructions();
                gameBoard.repaint();
                gameBoard.requestFocusInWindow();
            }
        });
        control_panel.add(instructions);
        
        // undo button
        final JButton undo = new JButton("Undo");
        undo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameBoard.undo();
                gameBoard.repaint();
                gameBoard.requestFocusInWindow();
            }
        });
        control_panel.add(undo);
        
        // put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        // start the game
        gameBoard.reset();
    }

    /**
     * Main method run to start and run the game. Initializes the GUI elements specified in Game and
     * runs it. IMPORTANT: Do NOT delete! You MUST include this in your final submission.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}