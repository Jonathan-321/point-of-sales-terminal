package POSUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import POSPD.Cashier;
import POSPD.Person;
import POSPD.Store;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class CashierEditPanel extends JPanel
{
	JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	
	/**
	 * Create the panel.
	 */
	public CashierEditPanel(JFrame currentFrame, Store store, Cashier cashier, Boolean isAdd)
	{
		setLayout(null);
		
		JLabel lblCashierEdit = new JLabel("Cashier Edit");
		lblCashierEdit.setHorizontalAlignment(SwingConstants.CENTER);
		lblCashierEdit.setBounds(12, 57, 776, 16);
		add(lblCashierEdit);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				cashier.getPerson().setName(textField.getText());
				cashier.getPerson().setSSN(textField_8.getText());
				cashier.getPerson().setAddress(textField_3.getText());
				cashier.getPerson().setCity(textField_4.getText());
				cashier.getPerson().setState(textField_5.getText());
				cashier.getPerson().setZip(textField_6.getText());
				cashier.getPerson().setPhone(textField_2.getText());
				cashier.setPassword(textField_7.getText());
				cashier.setNumber(textField_1.getText());
				
				if(isAdd)
				{
					cashier.getPerson().setCashier(cashier);
					store.addCashier(cashier);
				}
					
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new CashierListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
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
				currentFrame.getContentPane().add(new CashierListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(497, 445, 97, 25);
		add(btnCancel);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(102, 132, 56, 16);
		add(lblName);
		
		textField = new JTextField(cashier.getPerson().getName());
		textField.setBounds(181, 129, 116, 22);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblId = new JLabel("ID: ");
		lblId.setBounds(102, 164, 56, 16);
		add(lblId);
		
		textField_1 = new JTextField(cashier.getNumber());
		textField_1.setBounds(181, 164, 116, 22);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(102, 196, 56, 16);
		add(lblPhone);
		
		textField_2 = new JTextField(cashier.getPerson().getPhone());
		textField_2.setBounds(181, 196, 116, 22);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(102, 228, 56, 16);
		add(lblAddress);
		
		textField_3 = new JTextField(cashier.getPerson().getAddress());
		textField_3.setBounds(181, 228, 116, 22);
		add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(102, 260, 56, 16);
		add(lblCity);
		
		textField_4 = new JTextField(cashier.getPerson().getCity());
		textField_4.setBounds(181, 260, 116, 22);
		add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblState = new JLabel("State:");
		lblState.setBounds(102, 292, 56, 16);
		add(lblState);
		
		textField_5 = new JTextField(cashier.getPerson().getState());
		textField_5.setBounds(181, 292, 116, 22);
		add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblZip = new JLabel("ZIP:");
		lblZip.setBounds(102, 324, 56, 16);
		add(lblZip);
		
		textField_6 = new JTextField(cashier.getPerson().getZip());
		textField_6.setBounds(181, 324, 116, 22);
		add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(405, 164, 67, 16);
		add(lblPassword);
		
		textField_7 = new JTextField(cashier.getPassword());
		textField_7.setBounds(497, 161, 116, 22);
		add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblSsn = new JLabel("SSN:");
		lblSsn.setBounds(405, 132, 56, 16);
		add(lblSsn);
		
		textField_8 = new JTextField(cashier.getPerson().getSSN());
		textField_8.setBounds(497, 129, 116, 22);
		add(textField_8);
		textField_8.setColumns(10);
	}
}
