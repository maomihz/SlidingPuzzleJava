import javax.swing.*;
import java.awt.*;
public class Tile extends JPanel {
	
	JLabel number;
	
	public Tile(int value) {
		number = new JLabel(Integer.toString(value));
		setLayout(new GridBagLayout());
		
		number.setFont(getFont().deriveFont(80.0f));
		add(number,new GridBagConstraints());
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(Toolkit.getDefaultToolkit().getImage("res/rect.png"), 0, 0, getWidth(), getHeight(), this);
	}
	
	
}
