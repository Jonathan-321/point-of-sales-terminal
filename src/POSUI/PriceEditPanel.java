package POSUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import POSPD.Item;
import POSPD.Price;
import POSPD.PromoPrice;
import POSPD.Store;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JCheckBox;

public class PriceEditPanel extends JPanel
{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	Price price;
	JLabel lblEndDate;
	
	/**
	 * Create the panel.
	 */
	public PriceEditPanel(JFrame currentFrame, JPanel currentPanel, Store store, Item item, Price inPrice, Boolean isAdd)
	{	
		price = inPrice;
		
		setLayout(null);
		JLabel lblPriceEdit = new JLabel("Price Edit");
		lblPriceEdit.setHorizontalAlignment(SwingConstants.CENTER);
		lblPriceEdit.setBounds(12, 57, 776, 16);
		add(lblPriceEdit);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(102, 132, 38, 16);
		add(lblPrice);
		
		textField = new JTextField(price.getPrice().toString());
		textField.setBounds(195, 129, 116, 22);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblEffectiveDate = new JLabel("Effective Date:");
		lblEffectiveDate.setBounds(102, 187, 83, 16);
		add(lblEffectiveDate);
		
		textField_1 = new JTextField(price.getEffectiveDate().format(DateTimeFormatter.ofPattern("M/d/yyyy")));
		textField_1.setBounds(195, 184, 116, 22);
		add(textField_1);
		textField_1.setColumns(10);
		
		lblEndDate = new JLabel("End Date:");
		lblEndDate.setBounds(102, 252, 56, 16);
		add(lblEndDate);
		lblEndDate.setVisible(false);
		
		textField_2 = new JTextField();
		textField_2.setBounds(195, 249, 116, 22);
		add(textField_2);
		textField_2.setColumns(10);
		textField_2.setVisible(false);
		
		JCheckBox chckbxPromoPrice = new JCheckBox("Promo Price");
		if(price instanceof PromoPrice)
		{
			chckbxPromoPrice.setSelected(true);
			textField_2.setText(((PromoPrice)price).getEndDate().format(DateTimeFormatter.ofPattern("M/d/yyyy")));
			lblEndDate.setVisible(true);
			textField_2.setVisible(true);
		}
		chckbxPromoPrice.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(chckbxPromoPrice.isSelected())
				{
					price = new PromoPrice();
					lblEndDate.setVisible(true);
					textField_2.setVisible(true);
				}
				else
				{
					price = new Price();
					lblEndDate.setVisible(false);
					textField_2.setVisible(false);
				}
			}
		});
		chckbxPromoPrice.setBounds(418, 159, 113, 25);
		add(chckbxPromoPrice);
		chckbxPromoPrice.setVisible(false);
		
		if(isAdd)
		{
			chckbxPromoPrice.setVisible(true);
		}
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				price.setPrice(new BigDecimal(textField.getText()));
				price.setEffectiveDate(LocalDate.parse(textField_1.getText(), DateTimeFormatter.ofPattern("M/d/yyyy")));
				if(price instanceof PromoPrice)
					((PromoPrice) price).setEndDate(LocalDate.parse(textField_2.getText(), DateTimeFormatter.ofPattern("M/d/yyyy")));
				if(isAdd)
					item.addPrice(price);
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
