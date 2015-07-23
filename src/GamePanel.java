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
	}
	
	//Keys 
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_K) {
			move(Board.DIRECTION_UP);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_J) {
			move(Board.DIRECTION_DOWN);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_H) {
			move(Board.DIRECTION_LEFT);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_L) {
			move(Board.DIRECTION_RIGHT);
		}
		if (game.checkWin() && e.getKeyCode() == KeyEvent.VK_R) {
			game.shuffle();
		}
		repaint();
		

	}

	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	
	
	//Mouse
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
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
