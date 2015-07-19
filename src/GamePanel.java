import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener, MouseListener {
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
		addMouseListener(this);
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
		if (game.checkWin() && e.getKeyCode() == KeyEvent.VK_R) {
			game.shuffle();
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
				if (game.checkWin()) {
					repaint();
					JOptionPane.showMessageDialog(null, "You Win!! Press R to restart!");
				}
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		int size = getSquareSize();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (someIsMoving() && (movingDestination.x == j && movingDestination.y == i)) {
					int timeDiff = (int) (getTime() - startTime);
					if (timeDiff > MOVE_DURATION) {
						finishMoving();
						g.drawImage(squareImageAt(j, i), j * size, i * size, size, size, this);
					} else {
						// draw the moving square
						int displacement = size-(int) (((double) timeDiff / MOVE_DURATION) * size);
						g.drawImage(squareImageAt(j, i), j * size - movingDirection.x * displacement,
								i * size - movingDirection.y * displacement, size, size, this);
					}
				} else {
					g.drawImage(squareImageAt(j, i), j * size, i * size, size, size, this);
				}
			}
		}
		repaint();
	}

	//use after move is finished
	private void setupMoving(Point direction) {
		movingDestination = new Point(game.getZeroPos().x + direction.x, game.getZeroPos().y + direction.y);
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

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point p = new Point(e.getX() / getSquareSize(), e.getY() / getSquareSize());
		Point zero = game.getZeroPos();
		
		//Not valid position
		if (p.x < 0 || p.x > 3 || p.y < 0 || p.y > 3) {
			return;
		}
		
		if (p.x - 1 == zero.x && p.y == zero.y) {
			move(Board.DIRECTION_LEFT);
		}
		if (p.x + 1 == zero.x && p.y == zero.y) {
			move(Board.DIRECTION_RIGHT);
		}
		if (p.x == zero.x && p.y - 1 == zero.y) {
			move(Board.DIRECTION_UP);
		}
		if (p.x == zero.x && p.y + 1 == zero.y) {
			move(Board.DIRECTION_DOWN);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
