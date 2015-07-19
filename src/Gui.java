import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Gui extends JFrame {
	
	private Board gameBoard;
	private JPanel panel;
	
	private JButton[][] buttons;
	
	public Gui(Board board) {
		super("Sliding Puzzle");
		gameBoard = board;
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(612,612);
		setLayout(null);
		setLocationRelativeTo(null);
		paintBoard();
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					gameBoard.up();
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					gameBoard.down();
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					gameBoard.left();
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					gameBoard.right();
				}
				paintBoard();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
				
			}
			
		});
		
		this.setFocusable(true);
		
	}
	
	
	public void paintBoard() {
		getContentPane().removeAll();
		if (gameBoard != null) {
			buttons = new JButton[4][4];
			for (int i=0;i<buttons.length;i++) {
				for (int j=0;j<buttons.length;j++) {	
					buttons[i][j] = new JButton();
					buttons[i][j].setBorderPainted(false);
					buttons[i][j].setSize(128, 128);
					buttons[i][j].setLocation(j * 128, i * 128);
					buttons[i][j].setFocusable(false);
					
					if (gameBoard.getBoard()[i][j] != 0) {
						buttons[i][j].setIcon(new ImageIcon("res/rect" + Integer.toString(gameBoard.getBoard()[i][j]) + ".png"));
						this.getContentPane().add(buttons[i][j]);
					}
				}
			}
		}
		repaint();
	}
	

	public void setBoard(Board gameBoard) {
		this.gameBoard = gameBoard;
	}
}
