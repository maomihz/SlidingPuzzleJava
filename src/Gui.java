import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Gui extends JFrame implements KeyListener {
	private static final long serialVersionUID = 1L;
	private Board game;
	private JPanel panel;
	private JButton[][] buttons;
	
	public Gui(Board board) {
		super("Sliding Puzzle");
		game = board;
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(612,612);
		setLayout(null);
		setLocationRelativeTo(null);
		paintBoard();
		addKeyListener(this);
	}
	
	
	public void paintBoard() {
		getContentPane().removeAll();
		if (game != null) {
			buttons = new JButton[4][4];
			for (int i=0;i<buttons.length;i++) {
				for (int j=0;j<buttons.length;j++) {	
					buttons[i][j] = new JButton();
					buttons[i][j].setBorderPainted(false);
					buttons[i][j].setSize(128, 128);
					buttons[i][j].setLocation(j * 128, i * 128);
					buttons[i][j].setFocusable(false);
					
					if (game.getBoard()[i][j] != 0) {
						buttons[i][j].setIcon(new ImageIcon("res/rect" + Integer.toString(game.getBoard()[i][j]) + ".png"));
						this.getContentPane().add(buttons[i][j]);
					}
				}
			}
		}
		repaint();
	}
	

	public void setBoard(Board gameBoard) {
		this.game = gameBoard;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			game.move(Board.DIRECTION_UP);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			game.move(Board.DIRECTION_DOWN);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			game.move(Board.DIRECTION_LEFT);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			game.move(Board.DIRECTION_RIGHT);
		}
		paintBoard();
	}

	@Override
	public void keyReleased(KeyEvent e) {}
}
