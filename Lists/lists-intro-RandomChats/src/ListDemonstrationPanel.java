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
public class ListDemonstrationPanel extends JPanel{
	
	private JButton addButton, removeButton;
	private JTextField inputBox;
	private JPanel buttonPanel, textPanel;
	private JLabel textArea;
	private LinkedList<String> textInputs;
	
	public ListDemonstrationPanel() {
		//All of the code in here sets up my JPanel
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(100, 850));
		
		addButton = new JButton("Add Text");
		addButton.addActionListener(new AddButtonListener());
		removeButton = new JButton("Remove Text");
		removeButton.addActionListener(new RemoveButtonListener());
		inputBox = new JTextField(9);
		
		buttonPanel.add(addButton);
		buttonPanel.add(inputBox);
		buttonPanel.add(removeButton);

		textPanel = new JPanel();
		textPanel.setPreferredSize(new Dimension(550, 850));
		textInputs = new LinkedList<String>();
		
		this.add(buttonPanel, 0);
		this.add(textPanel, 1);
	
	}
	//This button adds the Enqueue function to the Enqueue button
	private class AddButtonListener implements ActionListener {
		
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
	private class RemoveButtonListener implements ActionListener {
		
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
