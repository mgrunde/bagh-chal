
import javax.swing.*;
import java.awt.BorderLayout;

public class Game implements Runnable {
	
	/**
	 * The run function 
	 * @param none
	 * @return none
	 */
	@Override
	public void run() {
		final JFrame frame = new JFrame("Bagh Chaal");
		
		frame.setLocation(500, 500);
		
		String instructions = "GAME OVERVIEW:\n" + 
				"Bagh Chal is a classic Nepali game. \n" + 
				"Bagh means tiger and Chal means goat, so the game involves tigers (orange) "
				+ "and goats (brown). \n" + 
				"You will play on a 5x5 board in moving along white lines. \n" + 
				"In this game, you will play as the goats.\n" + 
				"\n" + 
				"TIGERS WIN IF: they eat 5 goats (by jumping over them)\n" + 
				"GOATS WIN IF: they trap the tigers such that tigers can no longer move\n" + 
				"\n" + 
				"GAMEPLAY: \n" + 
				"Begin game with 4 tigers in the corners and all goats in the goat player's hand."
				+ " \n" + 
				"Players take turns by placing one goat, then moving one tiger.\n" + 
				"After all of the goats have been placed, the players take turns moving one "
				+ "existing goat then moving one tiger until one player achieves his or her goal."
				+ " \n" + 
				"\n" + 
				"Have fun, and try not to get eaten!\n" + 
				"";
		
		JOptionPane.showMessageDialog(frame, instructions, "Instructions", 
				JOptionPane.INFORMATION_MESSAGE);
		
		final JLabel goatsLeftLabel = new JLabel("Number of Goats Left = 20");
		final JLabel goatsEatenLabel = new JLabel("Goats Eaten = 0");
		final JLabel emptyLabel = new JLabel("                "); //TODO this seems like cheating
		final JLabel gameStatusLabel = new JLabel("Game in Progress...");
		final JButton undoButton = new JButton("Undo");

        final GameTable board = new GameTable(goatsLeftLabel,goatsEatenLabel,
        		gameStatusLabel, undoButton);

        frame.add(board, BorderLayout.CENTER);
        frame.add(gameStatusLabel, BorderLayout.NORTH);
        
        JPanel panel = new JPanel();
        frame.add(panel, BorderLayout.SOUTH);
        panel.add(goatsLeftLabel);
        panel.add(emptyLabel);
        panel.add(goatsEatenLabel);
        panel.add(emptyLabel);
        panel.add(undoButton);
		
		// Put the frame on the screen
        frame.setLocation(1000, 1000); // put the frame at (1000,1000) from the top left
        frame.pack(); // resize frame to fit exactly around its contents
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // gracefully exit when window closed
        frame.setVisible(true); // actually show the frame
		
	}
	
	
	/**
	 * The main function
	 * @param none
	 * @return none
	 */
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }

}
