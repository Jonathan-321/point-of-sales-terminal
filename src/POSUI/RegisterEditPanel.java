package POSUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import POSPD.Register;
import POSPD.Store;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class RegisterEditPanel extends JPanel
{
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public RegisterEditPanel(JFrame currentFrame, Store store, Register register, Boolean isAdd)
	{
		setLayout(null);
		
		JLabel lblRegisterEdit = new JLabel("Register Edit");
		lblRegisterEdit.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterEdit.setBounds(12, 53, 776, 16);
		add(lblRegisterEdit);
		
		JLabel lblNumber = new JLabel("Number:");
		lblNumber.setBounds(102, 132, 56, 16);
		add(lblNumber);
		
		textField = new JTextField(register.getNumber());
		textField.setBounds(181, 129, 116, 22);
		add(textField);
		textField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				register.setNumber(textField.getText());
				
				if(isAdd)
					store.addRegister(register);
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new RegisterListPanel(currentFrame, store));
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
				currentFrame.getContentPane().add(new RegisterListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(497, 445, 97, 25);
		add(btnCancel);
		
	}
}
