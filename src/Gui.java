import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Gui extends JFrame {
	private static final long serialVersionUID = 1L;
	private GamePanel panel;

	public Gui(Board board) {
		super("Sliding Puzzle");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(500, 522);
		setLocationRelativeTo(null);
		setFocusable(false);
		setMinimumSize(new Dimension(500, 522));

		panel = new GamePanel(board);
		getContentPane().add(panel);
	}

}
