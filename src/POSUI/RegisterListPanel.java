package POSUI;

import javax.swing.JPanel;

import POSPD.Register;
import POSPD.Store;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class RegisterListPanel extends JPanel
{
	JButton btnAdd;
	JButton btnEdit;
	JButton btnDelete;
	private JScrollPane scrollPane;
	
	/**
	 * Create the panel.
	 */
	public RegisterListPanel(JFrame currentFrame, Store store)
	{
		setLayout(null);
		
		JLabel lblRegisterList = new JLabel("Register List");
		lblRegisterList.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterList.setBounds(12, 32, 776, 16);
		add(lblRegisterList);
		
		DefaultListModel listModel = new DefaultListModel();
		for(Register register : store.getRegisters().values())
		{
			listModel.addElement(register);
		}
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(200, 61, 394, 363);
		add(scrollPane);
		
		JList<Register> list = new JList<Register>(listModel);
		scrollPane.setViewportView(list);
		list.addListSelectionListener(new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent e) 
			{
				if(list.getSelectedValue() != null)
				{
					if(list.getSelectedValue() != null)
						btnEdit.setEnabled(true);
					if(list.getSelectedValue().isUsed())
						btnDelete.setEnabled(false);
					else
						btnDelete.setEnabled(true);
				}
				else
				{
					btnEdit.setEnabled(false);
					btnEdit.setEnabled(false);
				}
			}
		});
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new RegisterEditPanel(currentFrame, store, list.getSelectedValue(), false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnEdit.setBounds(358, 470, 97, 25);
		add(btnEdit);
		btnEdit.setEnabled(false);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new RegisterEditPanel(currentFrame, store, new Register(), true));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnAdd.setBounds(200, 470, 97, 25);
		add(btnAdd);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				store.removeRegister(list.getSelectedValue());
				
				listModel.removeElement(list.getSelectedValue());
				btnDelete.setEnabled(false);
			}
		});
		btnDelete.setBounds(497, 470, 97, 25);
		add(btnDelete);
		btnDelete.setEnabled(false);
		
	}
}
