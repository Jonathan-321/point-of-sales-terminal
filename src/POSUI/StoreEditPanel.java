package POSUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import POSPD.Store;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class StoreEditPanel extends JPanel
{
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public StoreEditPanel(JFrame currentFrame, Store store)
	{
		setLayout(null);
		
		JLabel lblStoreEdit = new JLabel("Store Edit");
		lblStoreEdit.setHorizontalAlignment(SwingConstants.CENTER);
		lblStoreEdit.setBounds(12, 53, 776, 16);
		add(lblStoreEdit);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(102, 132, 56, 16);
		add(lblName);
		
		textField = new JTextField(store.getName());
		textField.setBounds(144, 129, 196, 25);
		add(textField);
		textField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				store.setName(textField.getText());
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHome(currentFrame, store));
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
				currentFrame.getContentPane().add(new POSHome(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(497, 445, 97, 25);
		add(btnCancel);

	}
}
