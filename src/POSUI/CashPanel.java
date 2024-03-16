package POSUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.AncestorEvent;

import POSPD.Cash;
import POSPD.Sale;
import POSPD.Session;
import POSPD.Store;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.AWTEvent;
import java.awt.event.ActionEvent;

public class CashPanel extends JPanel
{
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public CashPanel(JFrame currentFrame, JPanel paymentPanel, Store store, Session session, Sale sale, Cash cash)
	{
		CashPanel thisPanel = this;
		setLayout(null);
		
		JLabel lblCash = new JLabel("Cash");
		lblCash.setHorizontalAlignment(SwingConstants.CENTER);
		lblCash.setBounds(12, 32, 376, 16);
		add(lblCash);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(51, 69, 56, 16);
		add(lblAmount);
		
		textField = new JTextField();
		textField.setBounds(140, 66, 116, 22);
		add(textField);
		textField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				cash.setAmtTendered(new BigDecimal(textField.getText()));
				cash.setAmount(sale.calcAmount(cash.getAmtTendered()));
				sale.addPayment(cash);
				paymentPanel.remove(thisPanel);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(paymentPanel);
				currentFrame.getContentPane().repaint();
			}
		});
		btnSave.setBounds(72, 241, 97, 25);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				paymentPanel.remove(thisPanel);
				paymentPanel.repaint();
			}
		});
		btnCancel.setBounds(224, 241, 97, 25);
		add(btnCancel);

	}

}
