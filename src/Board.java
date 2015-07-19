import java.awt.Point;
import java.util.Arrays;

public class Board {
	public static final Point DIRECTION_DOWN = new Point(0, -1);
	public static final Point DIRECTION_UP = new Point(0, 1);
	public static final Point DIRECTION_LEFT = new Point(1, 0);
	public static final Point DIRECTION_RIGHT = new Point(-1, 0);
	private int[][] board;

	private Point zeroPos;

	public Board() {
		board = new int[4][4];
		for (int i = 0; i < 15; i++) {
			board[i / 4][i % 4] = i + 1;
		}
		zeroPos = new Point(3, 3);
	}

	public Board(Board anoboard) {
		board = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				board[i][j] = anoboard.board[i][j];
				if (board[i][j] == 0) {
					zeroPos = new Point(i, j);
				}
			}
		}
	}

	// Check whether can move
	public boolean canMove(Point direction) {
		Point p = new Point(direction.x + zeroPos.x, direction.y + zeroPos.y);
		if (p.x != -1 && p.x != 4 && p.y != -1 && p.y != 4)
			return true;
		return false;
	}

	public boolean checkWin() {
		if (board[3][3] != 0)
			return false;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] != i * 4 + j + 1) {
					if (i != 3 || j != 3)
						return false;
					else {
						return board[i][j] == 0;
					}
				}
			}
		}
		return true;
	}

	public void down() {
		if (canMove(DIRECTION_DOWN)) {
			board[zeroPos.x][zeroPos.y] = board[zeroPos.x - 1][zeroPos.y];
			board[zeroPos.x - 1][zeroPos.y] = 0;
			zeroPos.x -= 1;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (!Arrays.deepEquals(board, other.board))
			return false;
		return true;
	}

	public int[][] getBoard() {
		return board;
	}

	public Point getZero() {
		return zeroPos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(board);
		return result;
	}

	public void left() {
		if (canMove(DIRECTION_LEFT)) {
			board[zeroPos.x][zeroPos.y] = board[zeroPos.x][zeroPos.y + 1];
			board[zeroPos.x][zeroPos.y + 1] = 0;
			zeroPos.y += 1;
		}
	}

	public void move(Point direction) {
		if (canMove(direction)) {
			board[zeroPos.x][zeroPos.y] = board[zeroPos.x + direction.x][zeroPos.y + direction.y];
			board[zeroPos.x + direction.x][zeroPos.y + direction.y] = 0;
			zeroPos.x += direction.x;
			zeroPos.y += direction.y;
		}
	}

	public void right() {
		if (canMove(DIRECTION_RIGHT)) {
			board[zeroPos.x][zeroPos.y] = board[zeroPos.x][zeroPos.y - 1];
			board[zeroPos.x][zeroPos.y - 1] = 0;
			zeroPos.y -= 1;
		}
	}

	// Shuffle, default 300
	public void shuffle() {
		this.shuffle(300);
	}

	public void shuffle(int times) {
		for (int i = 0; i < times; i++) {
			int seed = (int) (Math.random() * 4);
			switch (seed) {
			case 0:
				move(DIRECTION_DOWN);
				break;
			case 1:
				move(DIRECTION_LEFT);
				break;
			case 2:
				move(DIRECTION_RIGHT);
				break;
			case 3:
				move(DIRECTION_UP);
			}
		}
	}

	public String toString() {
		String result = "";
		for (int[] i : board) {
			for (int j : i) {
				result += j + " ";
			}
		}
		return result;
	}

	// Methods for moving
	public void up() {
		if (canMove(DIRECTION_UP)) {
			board[zeroPos.x][zeroPos.y] = board[zeroPos.x + 1][zeroPos.y];
			board[zeroPos.x + 1][zeroPos.y] = 0;
			zeroPos.x += 1;
		}
	}

	public static void main(String[] args) {
		Board board = new Board();
		board.shuffle();
		Gui window = new Gui(board);
		window.setVisible(true);

	}

}
