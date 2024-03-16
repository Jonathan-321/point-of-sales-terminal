package POSUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import POSPD.Cashier;
import POSPD.Store;

public class CashierListPanel extends JPanel
{
	JButton btnAdd;
	JButton btnEdit;
	JButton btnDelete;
	private JScrollPane scrollPane;
	
	/**
	 * Create the panel.
	 */
	public CashierListPanel(JFrame currentFrame, Store store)
	{
		setLayout(null);
		
		JLabel lblCashierList = new JLabel("Cashier List");
		lblCashierList.setHorizontalAlignment(SwingConstants.CENTER);
		lblCashierList.setBounds(12, 32, 776, 16);
		add(lblCashierList);
		
		DefaultListModel listModel = new DefaultListModel();
		for(Cashier cashier : store.getCashiers().values())
		{
			listModel.addElement(cashier);
		}
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(200, 61, 394, 363);
		add(scrollPane);
		
		JList<Cashier> list = new JList<Cashier>(listModel);
		scrollPane.setViewportView(list);
		list.addListSelectionListener(new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent e) 
			{
				if(list.getSelectedValue() != null)
				{
					if(list.getSelectedValue()!=null)
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
				currentFrame.getContentPane().add(new CashierEditPanel(currentFrame, store, list.getSelectedValue(), false));
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
				currentFrame.getContentPane().add(new CashierEditPanel(currentFrame, store, new Cashier(), true));
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
				store.removeCashier(list.getSelectedValue());
				
				listModel.removeElement(list.getSelectedValue());
				btnDelete.setEnabled(false);
			}
		});
		btnDelete.setBounds(497, 470, 97, 25);
		add(btnDelete);
		btnDelete.setEnabled(false);
		
	}
}
