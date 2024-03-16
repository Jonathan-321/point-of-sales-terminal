package POSUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import POSPD.Check;
import POSPD.Sale;
import POSPD.Session;
import POSPD.Store;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CheckPanel extends JPanel
{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the panel.
	 */
	public CheckPanel(JFrame currentFrame, JPanel paymentPanel, Store store, Session session, Sale sale, Check check)
	{
		CheckPanel thisPanel = this;
		setLayout(null);
		
		JLabel lblCheck = new JLabel("Check");
		lblCheck.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheck.setBounds(12, 32, 376, 16);
		add(lblCheck);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(51, 69, 56, 16);
		add(lblAmount);
		
		JLabel lblRouting = new JLabel("Routing #:");
		lblRouting.setBounds(51, 107, 63, 16);
		add(lblRouting);
		
		JLabel lblAccount = new JLabel("Account #:");
		lblAccount.setBounds(51, 149, 63, 16);
		add(lblAccount);
		
		JLabel lblCheck_1 = new JLabel("Check #:");
		lblCheck_1.setBounds(51, 192, 56, 16);
		add(lblCheck_1);
		
		textField = new JTextField();
		textField.setBounds(140, 66, 116, 22);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(140, 104, 116, 22);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(140, 146, 116, 22);
		add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(140, 189, 116, 22);
		add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				check.setAmtTendered(textField.getText());
				check.setRoutingNumber(textField_1.getText());
				check.setAccountNumber(textField_2.getText());
				check.setCheckNumber(textField_3.getText());
				sale.addPayment(check);
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
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(paymentPanel);
				currentFrame.getContentPane().repaint();
			}
		});
		btnCancel.setBounds(224, 241, 97, 25);
		add(btnCancel);

	}
}
