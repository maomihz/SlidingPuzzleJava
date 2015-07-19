import java.awt.Point;
import java.util.Arrays;

public class Board {
	public static final int DIRECTION_UP = 0;
	public static final int DIRECTION_RIGHT = 1;
	
	public static final int DIRECTION_DOWN = 2;
	public static final int DIRECTION_LEFT = 3;
	private int[][] board;
	
	
	private Point zeroPos;
	
	public Board() {
		board = new int[4][4];
		for (int i=0;i<15;i++) {
			board[i/4][i%4] = i + 1;
		}
		zeroPos = new Point(3, 3);
	}
	
	public Board(Board anoboard) {
		board = new int[4][4];
		for (int i=0;i<4;i++) {
			for (int j=0;j<4;j++) {
				board[i][j] = anoboard.board[i][j];
				if (board[i][j] == 0) {
					zeroPos = new Point(i, j);
				}
			}
		}
		
	}
	
	//Check whether can move
	public boolean canMove(int direction) {
		if(direction == DIRECTION_UP && zeroPos.x != 3) {
			return true;
		}
		if (direction == DIRECTION_DOWN && zeroPos.x != 0) {
			return true;
		}
		if(direction == DIRECTION_LEFT && zeroPos.y != 3) {
			return true;
		}
		if (direction == DIRECTION_RIGHT && zeroPos.y != 0) {
			return true;
		}
		return false;
	}
	
	
	public boolean checkWin() {
		if (board[3][3] != 0) 
			return false;
		for (int i=0;i<4;i++) {
			for (int j=0;j<4;j++) {
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
	
	//Move in a specified direction
	public void move(int direction) {
		if (canMove(direction)) {
		switch(direction) {
		case DIRECTION_UP:
			up();
			break;
		case DIRECTION_DOWN:
			down();
			break;
		case DIRECTION_LEFT:
			left();
			break;
		case DIRECTION_RIGHT:
			right();
			break;
		}
		}
	}
	
	
	public void right() {
		if (canMove(DIRECTION_RIGHT)) {
			board[zeroPos.x][zeroPos.y] = board[zeroPos.x][zeroPos.y - 1];
			board[zeroPos.x][zeroPos.y - 1] = 0;
			zeroPos.y -= 1;
		}
	}
	
	
	
	//Shuffle, default 300
	public void shuffle() {
		this.shuffle(300);
	}
	

	

	public void shuffle(int times) {
		for (int i=0;i<times;i++) {
			move((int)(Math.random() * 4));
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

	//Methods for moving
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
