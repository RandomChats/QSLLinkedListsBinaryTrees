import java.awt.*;
import javax.swing.JFrame;

public class PezDispenser {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Pez Dispenser");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new PezDispenserPanel());
		frame.setPreferredSize(new Dimension(700, 900));
		frame.pack();
		frame.setVisible(true);
	}
	
}
