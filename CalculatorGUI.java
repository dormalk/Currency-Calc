package ac.shenkar.Calc;

import java.awt.Font;

import javax.swing.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;

public class CalculatorGUI {

	private JFrame frame;
	private JTextField txtAmount;
	private CurrencyTable ct = null;
	private TableGUI tb=null;
	private XmlParse xmlparse=null;
	/**
	 * Create the application.
	 * @throws TransformerException 
	 */

	public CalculatorGUI() throws TransformerException{
		try {
			xmlparse=new XmlParse();
			Thread t1=new Thread(xmlparse);
			t1.start();
		} catch (IOException | ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
		this.tb=new TableGUI(xmlparse.getCurrencyTable());
		this.ct=xmlparse.getCurrencyTable();
		initialize();	
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setForeground(Color.GRAY);
		getFrame().getContentPane().setForeground(new Color(128, 128, 128));
		getFrame().setBounds(100, 100, 613, 489);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		getFrame().getContentPane().setLayout(springLayout);
		
		txtAmount = new JTextField();
		txtAmount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtAmount.setText("");
			}
		});

		springLayout.putConstraint(SpringLayout.WEST, txtAmount, 82, SpringLayout.WEST, getFrame().getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtAmount, -73, SpringLayout.EAST, getFrame().getContentPane());
		txtAmount.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtAmount.setHorizontalAlignment(SwingConstants.LEFT);
		txtAmount.setFont(new Font("Arial", Font.PLAIN, 30));
		txtAmount.setText("Amount");
		springLayout.putConstraint(SpringLayout.NORTH, txtAmount, 51, SpringLayout.NORTH, getFrame().getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, txtAmount, 99, SpringLayout.NORTH, getFrame().getContentPane());
		getFrame().getContentPane().add(txtAmount);
		txtAmount.setColumns(10);
		
		JComboBox<Object> comboBox = new JComboBox<Object>();
		springLayout.putConstraint(SpringLayout.EAST, comboBox, 279, SpringLayout.WEST, frame.getContentPane());
		comboBox.setFont(new Font("Arial", Font.PLAIN, 30));
		springLayout.putConstraint(SpringLayout.NORTH, comboBox, 42, SpringLayout.SOUTH, txtAmount);
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 82, SpringLayout.WEST, getFrame().getContentPane());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"From", "USD", "ILS", "EUR", "GBP", "JPY", "AUD", "CAD", "DKK", "NOK", "CAR", "SEK", "CHF", "JOD", "EGP"}));
		comboBox.setToolTipText("USD ILS");
		getFrame().getContentPane().add(comboBox);
		
		
		
		JTextPane txtpnSum = new JTextPane();
		txtpnSum.setFont(new Font("Arial", Font.PLAIN, 30));
		springLayout.putConstraint(SpringLayout.NORTH, txtpnSum, 312, SpringLayout.NORTH, getFrame().getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, txtpnSum, -58, SpringLayout.SOUTH, getFrame().getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, comboBox, -123, SpringLayout.NORTH, txtpnSum);
		springLayout.putConstraint(SpringLayout.WEST, txtpnSum, 0, SpringLayout.WEST, txtAmount);
		springLayout.putConstraint(SpringLayout.EAST, txtpnSum, 0, SpringLayout.EAST, txtAmount);
		txtpnSum.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getFrame().getContentPane().add(txtpnSum);
		
		JButton submitButton = new JButton("Convert");
		springLayout.putConstraint(SpringLayout.NORTH, submitButton, 43, SpringLayout.SOUTH, comboBox);
		springLayout.putConstraint(SpringLayout.SOUTH, submitButton, -32, SpringLayout.NORTH, txtpnSum);
		submitButton.setBackground(SystemColor.scrollbar);
		submitButton.setFont(new Font("Arial", Font.PLAIN, 30));
		springLayout.putConstraint(SpringLayout.WEST, submitButton, 0, SpringLayout.WEST, txtAmount);
		springLayout.putConstraint(SpringLayout.EAST, submitButton, -73, SpringLayout.EAST, getFrame().getContentPane());
		getFrame().getContentPane().add(submitButton);
		
		JComboBox<Object> toBox = new JComboBox<Object>();
		springLayout.putConstraint(SpringLayout.NORTH, toBox, 0, SpringLayout.NORTH, comboBox);
		springLayout.putConstraint(SpringLayout.WEST, toBox, -197, SpringLayout.EAST, txtAmount);
		springLayout.putConstraint(SpringLayout.SOUTH, toBox, 0, SpringLayout.SOUTH, comboBox);
		toBox.setModel(new DefaultComboBoxModel(new String[] {"To", "USD", "ILS", "EUR", "GBP", "JPY", "AUD", "CAD", "DKK", "NOK", "CAR", "SEK", "CHF", "JOD", "EGP"}));
		springLayout.putConstraint(SpringLayout.EAST, toBox, 0, SpringLayout.EAST, txtAmount);
		toBox.setToolTipText("USD ILS");
		toBox.setFont(new Font("Arial", Font.PLAIN, 30));
		frame.getContentPane().add(toBox);
		
		JButton refreshBtn = new JButton("Refresh");
		refreshBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				xmlparse.run();
				tb=new TableGUI(xmlparse.getCurrencyTable());
				ct=xmlparse.getCurrencyTable();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, refreshBtn, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, refreshBtn, 0, SpringLayout.WEST, txtAmount);
		springLayout.putConstraint(SpringLayout.SOUTH, refreshBtn, -16, SpringLayout.NORTH, txtAmount);
		springLayout.putConstraint(SpringLayout.EAST, refreshBtn, -413, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(refreshBtn);
		
		JButton btnShowTable = new JButton("Show table");
		springLayout.putConstraint(SpringLayout.NORTH, btnShowTable, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnShowTable, 26, SpringLayout.EAST, refreshBtn);
		springLayout.putConstraint(SpringLayout.SOUTH, btnShowTable, 35, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnShowTable, 143, SpringLayout.EAST, refreshBtn);
		frame.getContentPane().add(btnShowTable);
		
		
		btnShowTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					tb.getFrame().setVisible(true);				
			}
		});
		
		
		submitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String from = new String((String)comboBox.getItemAt(comboBox.getSelectedIndex()));
				String to = new String((String)toBox.getItemAt(toBox.getSelectedIndex()));
			//	System.out.println(from);
			//	System.out.println(to);
				double sum = Double.parseDouble(txtAmount.getText());
			//	System.out.println(sum);
				txtpnSum.setText(Double.toString(ct.calc(sum, to,from)));
				
				
				
			}
		});
		
		txtAmount.addKeyListener(new KeyListener() {
			public boolean flag = true;
			@Override
			public void keyTyped(KeyEvent e) {
				
				
			
				
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(flag)
				{
					txtAmount.setText("");
					flag=false;
				}
				
				
			}
		});
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
		frame.setBackground(new Color(25, 25, 112));
	}
}
