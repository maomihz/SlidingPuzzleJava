import java.awt.Point;
import java.util.Arrays;

public class Board {
    public static final Point DIRECTION_DOWN = new Point(0, 1);
    public static final Point DIRECTION_UP = new Point(0, -1);
    public static final Point DIRECTION_LEFT = new Point(-1, 0);
    public static final Point DIRECTION_RIGHT = new Point(1, 0);

    public static void main(String[] args) {
        Board board = new Board();
        board.shuffle();
        Gui window = new Gui(board);
        window.setVisible(true);
    }

    private int[][] board;

    private Point zeroPos;

    public Board() {
        board = new int[4][4];
        int num = 1;
        for (int i=0;i<4;i++) {
            for (int j=0;j<4;j++) {
                board[j][i] = num;
                num++;
            }
        }
        board[3][3] = 0;
        zeroPos = new Point(3, 3);
    }

    // Check whether can move
    public boolean canMove(Point direction) {
        Point p = new Point(zeroPos.x-direction.x,zeroPos.y-direction.y);
        if (p.x != -1 && p.x != 4 && p.y != -1 && p.y != 4)
            return true;
        return false;
    }

    public boolean checkWin() {
        return new Board().equals(this);
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

    public Point getZeroPos() {
        return zeroPos;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(board);
        return result;
    }

    public void move(Point direction) {
        if (canMove(direction)) {
            board[zeroPos.x][zeroPos.y] = board[zeroPos.x - direction.x][zeroPos.y - direction.y];
            board[zeroPos.x - direction.x][zeroPos.y - direction.y] = 0;
            zeroPos.x -= direction.x;
            zeroPos.y -= direction.y;
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
            case 3
                move(DIRECTION_UP);
            }
        }
    }



    public String toString() {
        String result = "";
        for (int i=0;i<4;i++) {
            for (int j=0;j<4;j++) {
                result += board[j][i] + " ";
            }
            result+="\n";
        }
        return result;
    }

}
