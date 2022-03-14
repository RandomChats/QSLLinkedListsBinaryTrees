import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

@SuppressWarnings("serial")
/**
 * 
 * @author corbin.mclaughlin
 *
 */
public class PezDispenserPanel extends JPanel{
	
	private JButton pushButton, popButton;
	private JPanel buttonPanel, pezPanel;
	private Color randomColor, backgroundColor;
	private Stack<JPanel> pezContainer;
	private Random rand;
	private int amount = 0;
	
	
	public PezDispenserPanel() {
		backgroundColor = new Color(143,143,143);
		
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(100, 850));
		
		pushButton = new JButton("Push Pez");
		pushButton.addActionListener(new PushButtonListener());
		popButton = new JButton("Pop Pez");
		popButton.addActionListener(new PopButtonListener());
		
		buttonPanel.add(pushButton);
		buttonPanel.add(popButton);
		
		pezPanel = new JPanel();
		pezPanel.setPreferredSize(new Dimension(550, 850));
		pezPanel.setBackground(backgroundColor);
		pezContainer = new Stack<JPanel>();
		
		
		this.add(buttonPanel, 0);
		this.add(pezPanel, 1);
		
		
	}
	

	private class PushButtonListener implements ActionListener {

		@Override

		public void actionPerformed(ActionEvent e) {
			int max = 8;
			
			if(max > amount) {
				rand = new Random();
				randomColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
				JPanel generatedPezPiece = new JPanel();
				generatedPezPiece.setPreferredSize(new Dimension(540, 100));
				generatedPezPiece.setBackground(randomColor);
				pezContainer.push(generatedPezPiece);
				amount++;
				pezPanel.add(pezContainer.peek());
				pezPanel.revalidate();
				pezPanel.repaint();
			} else {
				
			}

		}
		
		
	}
	

	private class PopButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if(amount > 0) {
				pezPanel.remove(pezContainer.pop());
				pezPanel.revalidate();
				pezPanel.repaint();
				amount--;
			}
		}
		
	}
	
}
