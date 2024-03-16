package POSUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import POSPD.Cashier;
import POSPD.Register;
import POSPD.Sale;
import POSPD.Session;
import POSPD.Store;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class POSLogin extends JPanel
{
	private JTextField textField;
	private JPasswordField passwordField;
	JLabel lblPasswordIsInvalid;

	/**
	 * Create the panel.
	 */
	public POSLogin(JFrame currentFrame, Store store)
	{
		setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(348, 82, 56, 16);
		add(lblLogin);
		
		JLabel lblRegister = new JLabel("Register:");
		lblRegister.setBounds(246, 148, 56, 16);
		add(lblRegister);
		
		JLabel lblCashier = new JLabel("Cashier:");
		lblCashier.setBounds(246, 193, 56, 16);
		add(lblCashier);
		
		JLabel lblStartingCash = new JLabel("Starting Cash:");
		lblStartingCash.setBounds(227, 241, 128, 19);
		add(lblStartingCash);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(246, 293, 82, 16);
		add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(403, 239, 116, 22);
		add(textField);
		textField.setColumns(10);
		
		DefaultComboBoxModel registerModel = new DefaultComboBoxModel();
		for(Register register : store.getRegisters().values())
			registerModel.addElement(register);
			
		JComboBox<Register> comboBox = new JComboBox<Register>(registerModel);
		comboBox.setBounds(403, 147, 116, 19);
		add(comboBox);
		
		DefaultComboBoxModel cashierModel = new DefaultComboBoxModel();
		for(Cashier cashier : store.getCashiers().values())
			 cashierModel.addElement(cashier);
		
		JComboBox<Cashier> comboBox_1 = new JComboBox<Cashier>(cashierModel);
		comboBox_1.setBounds(403, 192, 117, 19);
		add(comboBox_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(403, 290, 116, 22);
		add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Cashier cashier = (Cashier)(comboBox_1.getSelectedItem());
				Register register = (Register)(comboBox.getSelectedItem());
				String password = new String(passwordField.getText());
				if(cashier.isAuthorized(password))
				{
					register.getCashDrawer().addCash(new BigDecimal(textField.getText()));
					Session session = new Session(cashier, register, store);
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new POSSale(currentFrame, store, session, new Sale()));
					currentFrame.getContentPane().revalidate();
				}
				else
				{
					lblPasswordIsInvalid.setVisible(true);
				}
			}
		});
		btnLogin.setBounds(200, 445, 97, 25);
		add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHome(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(497, 445, 97, 25);
		add(btnCancel);
		
		lblPasswordIsInvalid = new JLabel("Password is invalid!");
		lblPasswordIsInvalid.setBounds(402, 329, 117, 16);
		add(lblPasswordIsInvalid);
		lblPasswordIsInvalid.setVisible(false);
	}
}
