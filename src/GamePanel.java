import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener {
	private static final long serialVersionUID = -6998828044326737584L;
	private static int MOVE_DURATION = 50;
	private Board game;
	// 0 for nothing is moving
	private Point movingDestination;
	private long startTime;
	private Point movingDirection;

	public GamePanel(Board board) {
		game = board;
		setLayout(null);
		addKeyListener(this);
		setFocusable(true);
	}

	private void finishMoving() {
		movingDirection = null;
		startTime = 0;
		movingDestination = null;
	}

	private int getSquareSize() {
		int reference = Math.min(getWidth(), getHeight());
		return reference / 4;
	}

	private long getTime() {
		return new Date().getTime();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			move(Board.DIRECTION_UP);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			move(Board.DIRECTION_DOWN);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			move(Board.DIRECTION_LEFT);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			move(Board.DIRECTION_RIGHT);
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	private void move(Point direction) {
		if (!someIsMoving()) {
			if (game.canMove(direction)) {
				game.move(direction);
				setupMoving(direction);
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		int size = getSquareSize();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (someIsMoving() && (movingDestination.x == i && movingDestination.y == j)) {
					int timeDiff = (int) (getTime() - startTime);
					if (timeDiff > MOVE_DURATION) {
						finishMoving();
						g.drawImage(squareImageAt(i, j), i * size, j * size, size, size, this);
					} else {
						// draw the moving square
						int displacement = size - (int) (((double) timeDiff / MOVE_DURATION) * size);
						g.drawImage(squareImageAt(i, j), i * size + movingDirection.x * displacement,
								j * size + movingDirection.y * displacement, size, size, this);
					}
				} else {
					g.drawImage(squareImageAt(i, j), i * size, j * size, size, size, this);
				}
			}
		}
		repaint();
	}

	private void setupMoving(Point direction) {
		movingDestination = new Point(game.getZeroPos().x - direction.x, game.getZeroPos().y - direction.y);
		startTime = getTime();
		movingDirection = direction;
	}

	private boolean someIsMoving() {
		if (movingDestination == null || movingDirection == null || startTime == 0)
			return false;
		return true;
	}

	private Image squareImageAt(int i, int j) {
		return Toolkit.getDefaultToolkit().getImage("res/rect" + game.getBoard()[i][j] + ".png");
	}
}
