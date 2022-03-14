import java.awt.*;
import javax.swing.JFrame;

public class QueueDemonstration {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Pez Dispenser");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new QueueDemonstrationPanel());
		frame.setPreferredSize(new Dimension(700, 900));
		frame.pack();
		frame.setVisible(true);
	}

}
