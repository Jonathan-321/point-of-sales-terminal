package POSUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import POSPD.Item;
import POSPD.Store;
import POSPD.UPC;
import javax.swing.JTextField;

public class UpcEditPanel extends JPanel
{
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public UpcEditPanel(JFrame currentFrame, JPanel currentPanel, Store store, Item item, UPC upc, Boolean isAdd)
	{
		setLayout(null);
		JLabel lblUpcEdit = new JLabel("UPC Edit");
		lblUpcEdit.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpcEdit.setBounds(12, 57, 776, 16);
		add(lblUpcEdit);
		
		JLabel lblUpc = new JLabel("UPC:");
		lblUpc.setBounds(102, 132, 28, 16);
		add(lblUpc);
		
		textField = new JTextField(upc.getUPC());
		textField.setBounds(200, 129, 116, 22);
		add(textField);
		textField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				upc.setUPC(textField.getText());
				
				if(isAdd)
				{
					upc.setItem(item);
					item.addUPC(upc);
					store.addUPC(upc);
				}
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().repaint();
			}
		});
		btnSave.setBounds(200, 445, 97, 25);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().repaint();
			}
		});
		btnCancel.setBounds(497, 445, 97, 25);
		add(btnCancel);
		
	}

}
