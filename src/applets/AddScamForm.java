package applets;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class AddScamForm extends Applet{

	private static final long serialVersionUID = 1L;
	
	private static final String[] CATEGORIES = { "Tourist attraction", "?" };
	private static final String[] CHECKBOXES = { "Tourist attraction", "?" };

	private JPanel mainPanel;
	private JTextField[] fieldArray;
	private int n;
	
	public void init(){
		fieldArray = new JTextField[4];
		n = 0;
		
		setLayout(new BorderLayout());
		
		mainPanel = createMainPanel();
		
		mainPanel.add(createLabel("Did something happend to you or", Font.BOLD, 15));
		mainPanel.add(createLabel("your friends? Report it here.", Font.BOLD, 15));
		mainPanel.add(Box.createRigidArea(new Dimension(10,10)));
		mainPanel.add(createLabel("Title", Font.BOLD, 12));
		mainPanel.add(Box.createRigidArea(new Dimension(5,5)));
		mainPanel.add(createTextField("Title"));
		mainPanel.add(Box.createRigidArea(new Dimension(10,10)));
		mainPanel.add(createLabel("Description", Font.BOLD, 12));
		mainPanel.add(Box.createRigidArea(new Dimension(5,5)));
		mainPanel.add(createTextField("Description"));
		mainPanel.add(Box.createRigidArea(new Dimension(10,10)));
		mainPanel.add(createLabel("Category", Font.BOLD, 12));
		mainPanel.add(Box.createRigidArea(new Dimension(5,5)));
		mainPanel.add(createComboBox(CATEGORIES));
		mainPanel.add(Box.createRigidArea(new Dimension(10,10)));
		mainPanel.add(createLabel("Location", Font.BOLD, 12));
		mainPanel.add(Box.createRigidArea(new Dimension(5,5)));
		mainPanel.add(createTextField("Country"));
		mainPanel.add(createTextField("City"));
		mainPanel.add(Box.createRigidArea(new Dimension(10,10)));
		mainPanel.add(createLabel("Impact", Font.BOLD, 12));
		mainPanel.add(Box.createRigidArea(new Dimension(5,5)));
		mainPanel.add(createCheckBoxes(CHECKBOXES));
		
		add(mainPanel, BorderLayout.CENTER);
		resize(mainPanel.getPreferredSize());
	}
	
	private JPanel createMainPanel(){
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setAlignmentX(LEFT_ALIGNMENT);
		return mainPanel;
	}
	
	private JLabel createLabel(String text, int textStyle, int textSize){
		JLabel label = new JLabel(text);
		Font currentFont = label.getFont();
		label.setFont(new Font(currentFont.getName(), textStyle, textSize));
		return label;
	}
	
	private JTextField createTextField(String text){
		JTextField textField = new JTextField(text);
	//	textField.setPreferredSize(new Dimension((int)width, (int)textField.getPreferredSize().getHeight()));
		textField.setAlignmentX(LEFT_ALIGNMENT);
		fieldArray[n] = textField;
		n++;
		return textField;
	}
	
	private JComboBox<String> createComboBox(String[] options){
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<>(options));
	//	comboBox.setPreferredSize(new Dimension((int)width, (int)comboBox.getPreferredSize().getHeight()));
		comboBox.setAlignmentX(LEFT_ALIGNMENT);
		return comboBox;
	}
	
	private JPanel createCheckBoxes(String[] texts){
		JPanel panel = new JPanel();
		
		
		return panel;
	}
}
