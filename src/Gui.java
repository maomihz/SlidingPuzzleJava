import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
