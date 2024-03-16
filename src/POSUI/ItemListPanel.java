package POSUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import POSPD.Item;
import POSPD.Store;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class ItemListPanel extends JPanel
{
	
	JButton btnEdit;
	JButton btnDelete;
	
	/**
	 * Create the panel.
	 */
	public ItemListPanel(JFrame currentFrame, Store store)
	{
		setLayout(null);
		
		JLabel lblItemList = new JLabel("Item List");
		lblItemList.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemList.setBounds(12, 32, 776, 16);
		add(lblItemList);
		
		DefaultListModel listModel = new DefaultListModel();
		for(Item item : store.getItems().values())
			listModel.addElement(item);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(200, 61, 394, 363);
		add(scrollPane);
		
		JList<Item> list = new JList<Item>(listModel);
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
		scrollPane.setViewportView(list);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemEditPanel(currentFrame, store, new Item(), true));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnAdd.setBounds(200, 470, 97, 25);
		add(btnAdd);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemEditPanel(currentFrame, store, list.getSelectedValue(), false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnEdit.setBounds(358, 470, 97, 25);
		add(btnEdit);
		btnEdit.setEnabled(false);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				store.removeItem(list.getSelectedValue());
				
				listModel.removeElement(list.getSelectedValue());
				btnDelete.setEnabled(false);
				
			}
		});
		btnDelete.setBounds(497, 470, 97, 25);
		add(btnDelete);
		btnDelete.setEnabled(false);
	}
}
