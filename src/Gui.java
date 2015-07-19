import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Gui extends JFrame {
	private static final long serialVersionUID = 1L;
	private GamePanel panel;
	
	public Gui(Board board) {
		super("Sliding Puzzle");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(512,512);
		setLocationRelativeTo(null);
		setFocusable(false);
		
		panel = new GamePanel(board);
		getContentPane().add(panel);
	}
	

}
