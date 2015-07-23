import javax.swing.*;
import java.awt.*;
public class Tile extends JPanel {
	
	JLabel number;
	int myValue;
	
	public Tile(int value) {
		myValue = value;
		if (value != 0) {
			number = new JLabel(Integer.toString(value));
		} else {
			number = new JLabel();
		}
		setLayout(new GridBagLayout());
		
		number.setFont(getFont().deriveFont(80.0f));
		add(number,new GridBagConstraints());
	}
	
	public void paintComponent(Graphics g) {
		if (myValue != 0) {
			g.drawImage(Toolkit.getDefaultToolkit().getImage("res/rect.png"), 0, 0, getWidth(), getHeight(), this);
		}
	}
	
	
}
