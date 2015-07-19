import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements KeyListener {
	Board game;
	
	public GamePanel(Board board) {
		game = board;
		setLayout(null);
		addKeyListener(this);
		setFocusable(true);
	}
	
	public void paintComponent(Graphics g) {
		int[][] gameContent = game.getBoard();

		for (int i = 0; i < gameContent.length; i++) {
			for (int j = 0; j < gameContent[i].length; j++) {
				if (gameContent[i][j] != 0) {

					int reference = Math.min(getWidth(), getHeight());

					int size = reference / 4;

					int x = i * (reference / 4);
					int y = j * (reference / 4);

					g.drawImage(Toolkit.getDefaultToolkit().getImage("res/rect" + gameContent[i][j] + ".png"), x, y,
							size, size, this);
				}
			}
		}
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
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {}
}
