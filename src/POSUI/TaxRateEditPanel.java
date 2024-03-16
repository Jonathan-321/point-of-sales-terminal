package POSUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import POSPD.Store;
import POSPD.TaxCategory;
import POSPD.TaxRate;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class TaxRateEditPanel extends JPanel
{
	private JTextField rateField;
	private JTextField dateField;

	/**
	 * Create the panel.
	 */
	public TaxRateEditPanel(JFrame currentFrame, JPanel currentPanel, Store store, TaxCategory taxCategory, TaxRate taxRate, Boolean isAdd)
	{
		setLayout(null);
		
		JLabel lblTaxRateEdit = new JLabel("Tax Rate Edit");
		lblTaxRateEdit.setHorizontalAlignment(SwingConstants.CENTER);
		lblTaxRateEdit.setBounds(12, 53, 776, 16);
		add(lblTaxRateEdit);
		
		JLabel lblRate = new JLabel("Rate:");
		lblRate.setBounds(102, 132, 56, 16);
		add(lblRate);
		
		JLabel lblEffectiveDate = new JLabel("Effective Date:");
		lblEffectiveDate.setBounds(102, 164, 83, 16);
		add(lblEffectiveDate);
		
		rateField = new JTextField(taxRate.getTaxRate().toString());
		rateField.setBounds(260, 129, 116, 22);
		add(rateField);
		rateField.setColumns(10);
		
		dateField = new JTextField(taxRate.getEffectiveDate().format(DateTimeFormatter.ofPattern("M/d/yyyy")));
		dateField.setBounds(260, 161, 116, 22);
		add(dateField);
		dateField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				taxRate.setTaxRate(new BigDecimal(rateField.getText()));
				taxRate.setEffectiveDate(LocalDate.parse(dateField.getText(), DateTimeFormatter.ofPattern("M/d/yyyy")));
				taxCategory.addTaxRate(taxRate);
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
