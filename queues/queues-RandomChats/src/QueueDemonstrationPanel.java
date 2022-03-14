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
public class QueueDemonstrationPanel extends JPanel{
	
	private JButton enqueueButton, dequeueButton;
	private JTextField inputBox;
	private JPanel buttonPanel, textPanel;
	private JLabel textArea;
	private Queue<String> textInputs;
	
	public QueueDemonstrationPanel() {
		//All of the code in here sets up my JPanel
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(100, 850));
		
		enqueueButton = new JButton("Enqueue Text");
		enqueueButton.addActionListener(new EnqueueButtonListener());
		dequeueButton = new JButton("Dequeue Text");
		dequeueButton.addActionListener(new DequeueButtonListener());
		inputBox = new JTextField(9);
		
		buttonPanel.add(enqueueButton);
		buttonPanel.add(inputBox);
		buttonPanel.add(dequeueButton);

		textPanel = new JPanel();
		textPanel.setPreferredSize(new Dimension(550, 850));
		textInputs = new LinkedList<String>();
		
		this.add(buttonPanel, 0);
		this.add(textPanel, 1);
	
	}
	//This button adds the Enqueue function to the Enqueue button
	private class EnqueueButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			//This code grabs the user input and then prints it
			//on the right hand panel, making sure to clear it after
			//every time something is added to make it clean
			String userInput = inputBox.getText();
			textInputs.add(userInput);
			String Queue = textInputs.toString();
			textArea = new JLabel(Queue);
			textPanel.removeAll();
			textPanel.add(textArea);
			textPanel.revalidate();
			textPanel.repaint();
		}
		
		
	}
	

	//This button adds the Dequeue function to the Dequeue button
	private class DequeueButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			//This code just uses the built in dequeue function and
			//repaints the entire area so show the information was removed
			textInputs.remove();
			String Queue = textInputs.toString();
			textArea = new JLabel(Queue);
			textPanel.removeAll();
			textPanel.add(textArea);
			textPanel.revalidate();
			textPanel.repaint();
		}
		
	}
	
}
