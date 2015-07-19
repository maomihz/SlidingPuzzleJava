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
		try {
			int[][] gameContent = game.getBoard();
			
			for (int i=0;i<gameContent.length;i++) {
				for (int j=0;j<gameContent[i].length;j++) {
					if (gameContent[i][j] != 0) {
						int width = getWidth() / 4;
						int height = getHeight() / 4;
						
						int x = i * width;
						int y = j * height;
						
						g.drawImage(ImageIO.read(new File("res/rect" + gameContent[i][j] + ".png")), x, y, width, height, null);
					}
				}
			}
		} catch (IOException e) {}
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
