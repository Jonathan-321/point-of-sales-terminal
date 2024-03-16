package POSUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import POSPD.Cash;
import POSPD.Check;
import POSPD.Credit;
import POSPD.Sale;
import POSPD.Session;
import POSPD.Store;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class POSPayment extends JPanel
{
	private JTextField textField;
	private JTextField textField_1;
	
	JButton btnCompletePayment;
	
	CashPanel cashPanel;
	CheckPanel checkPanel;
	CreditPanel creditPanel;
	
	/**
	 * Create the panel.
	 */
	public POSPayment(JFrame currentFrame, JPanel salePanel, Store store, Session session, Sale sale)
	{	
		addAncestorListener(new AncestorListener() 
		{
			public void ancestorAdded(AncestorEvent event) 
			{
				textField.setText(sale.calcTotal().toString());
				textField_1.setText(sale.calcAmtTendered().toString());
				btnCompletePayment.setEnabled(sale.isPaymentEnough());
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		POSPayment thisPanel = this;
		setLayout(null);
		
		JLabel lblPayments = new JLabel("Payments");
		lblPayments.setHorizontalAlignment(SwingConstants.CENTER);
		lblPayments.setBounds(12, 59, 776, 16);
		add(lblPayments);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(132, 140, 56, 16);
		add(lblTotal);
		
		textField = new JTextField(sale.calcTotal().toString());
		textField.setBounds(213, 137, 116, 22);
		add(textField);
		textField.setColumns(10);
		textField.setEditable(false);
		
		JLabel lblTendered = new JLabel("Tendered: ");
		lblTendered.setBounds(132, 183, 64, 16);
		add(lblTendered);
		
		textField_1 = new JTextField(sale.calcAmtTendered().toString());
		textField_1.setBounds(213, 180, 116, 22);
		add(textField_1);
		textField_1.setColumns(10);
		textField_1.setEditable(false);
		
		JButton btnCash = new JButton("Cash");
		btnCash.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				cashPanel = new CashPanel(currentFrame, thisPanel, store, session, sale, new Cash());
				cashPanel.setBounds(372, 88, 400, 300);
				add(cashPanel);
				if ( checkPanel != null)
					remove(checkPanel);
				else if ( creditPanel != null)
					remove(creditPanel);
				revalidate();
				repaint();
			}
		});
		btnCash.setBounds(132, 247, 97, 25);
		add(btnCash);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				checkPanel = new CheckPanel(currentFrame, thisPanel, store, session, sale, new Check());
				checkPanel.setBounds(372, 88, 400, 300);
				add(checkPanel);
				if (cashPanel != null)
					remove(cashPanel);
				else if ( creditPanel != null)
					remove(creditPanel);
				revalidate();
				repaint();
			}
		});
		btnCheck.setBounds(132, 303, 97, 25);
		add(btnCheck);
		
		JButton btnCredit = new JButton("Credit");
		btnCredit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				creditPanel = new CreditPanel(currentFrame, thisPanel, store, session, sale, new Credit());
				creditPanel.setBounds(372, 88, 400, 300);
				add(creditPanel);
				if (cashPanel != null)
					remove(cashPanel);
				else if ( checkPanel != null) 
					remove(checkPanel);
				revalidate();
				repaint();
			}
		});
		btnCredit.setBounds(132, 363, 97, 25);
		add(btnCredit);
		
		btnCompletePayment = new JButton("Complete Payment");
		btnCompletePayment.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(salePanel);
				currentFrame.repaint();
			}
		});
		btnCompletePayment.setBounds(505, 401, 152, 25);
		add(btnCompletePayment);
		btnCompletePayment.setEnabled(false);
		
	}
}
