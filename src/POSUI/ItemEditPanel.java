package POSUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import POSPD.Item;
import POSPD.Price;
import POSPD.Store;
import POSPD.TaxCategory;
import POSPD.TaxRate;
import POSPD.UPC;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class ItemEditPanel extends JPanel
{
	private JTextField textField;
	private JTextField textField_1;
	
	JButton btnEdit;
	JButton btnDelete;
	
	JButton btnEdit_1;
	JButton btnDelete_1;
	
	JPanel currentPanel = this;
	
	DefaultListModel listModel;
	DefaultListModel listModel_1;
	
	/**
	 * Create the panel.
	 */
	public ItemEditPanel(JFrame currentFrame, Store store, Item item, Boolean isAdd)
	{
		setLayout(null);
		
		JLabel lblItemEdit = new JLabel("Item Edit");
		lblItemEdit.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemEdit.setBounds(12, 57, 776, 16);
		add(lblItemEdit);
		
		JLabel lblNumber = new JLabel("Number: ");
		lblNumber.setBounds(102, 132, 56, 16);
		add(lblNumber);
		
		JLabel lblDescription = new JLabel("Description: ");
		lblDescription.setBounds(102, 164, 72, 16);
		add(lblDescription);
		
		textField = new JTextField(item.getNumber());
		textField.setBounds(278, 132, 116, 22);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(item.getDescription());
		textField_1.setBounds(278, 164, 154, 22);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblUpc = new JLabel("UPC:");
		lblUpc.setBounds(102, 228, 56, 16);
		add(lblUpc);
		
		JLabel lblTaxCategory = new JLabel("Tax Category:");
		lblTaxCategory.setBounds(102, 196, 81, 16);
		add(lblTaxCategory);
		
		DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
		for(TaxCategory taxCategory : store.getTaxCategories().values())
			comboBoxModel.addElement(taxCategory);
		
		JComboBox<TaxCategory> comboBox = new JComboBox<TaxCategory>(comboBoxModel);
		comboBox.setBounds(278, 196, 154, 22);
		comboBox.setSelectedItem(item.getTaxCategory());
		add(comboBox);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				item.setNumber(textField.getText());
				item.setDescription(textField_1.getText());
				item.setTaxCategory((TaxCategory)comboBox.getSelectedItem());
				if(isAdd) store.addItem(item);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemListPanel(currentFrame,store));
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
				currentFrame.getContentPane().add(new ItemListPanel(currentFrame,store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(497, 445, 97, 25);
		add(btnCancel);
		
		listModel = new DefaultListModel();
		for(UPC upc : item.getUpcs().values())
		{
			listModel.addElement(upc);
		}
			
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(133, 247, 164, 118);
		add(scrollPane);
		
		JList<UPC> list = new JList<UPC>(listModel);
		list.addAncestorListener(new AncestorListener() 
		{
			public void ancestorAdded(AncestorEvent e) 
			{
				listModel = new DefaultListModel();
				for(UPC upc : item.getUpcs().values())
				{
					listModel.addElement(upc);
				}
				list.setModel(listModel);
			}
			public void ancestorMoved(AncestorEvent e) 
			{
				
			}
			public void ancestorRemoved(AncestorEvent e) 
			{
				
			}
		});
		list.addListSelectionListener(new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent e) 
			{
				if(list.getSelectedValue() != null)
				{
					if(list.getSelectedValue() != null)
						btnEdit.setEnabled(true);
					if(!list.getSelectedValue().isUsed())
						btnDelete.setEnabled(true);
					else
						btnDelete.setEnabled(false);
				}
				else
				{
					btnEdit.setEnabled(false);
					btnEdit.setEnabled(false);
				}
			}
		});
		scrollPane.setViewportView(list);
		
		JLabel lblPrices = new JLabel("Price(s): ");
		lblPrices.setBounds(480, 228, 56, 16);
		add(lblPrices);
			
		listModel_1 = new DefaultListModel();
		for(Price price : item.getPrices())
		{
			listModel_1.addElement(price);
		}
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(508, 247, 164, 118);
		add(scrollPane_1);
		
		JList<Price> list_1 = new JList<Price>(listModel_1);
		list_1.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent e) 
			{
				listModel_1 = new DefaultListModel();
				for(Price price : item.getPrices())
				{
					listModel_1.addElement(price);
				}
				list_1.setModel(listModel_1);
			}
			public void ancestorMoved(AncestorEvent e) 
			{
				
			}
			public void ancestorRemoved(AncestorEvent e)
			{
				
			}
		});
		list_1.addListSelectionListener(new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent e) 
			{
				if(list_1.getSelectedValue() != null)
				{
					if(list_1.getSelectedValue() != null)
						btnEdit_1.setEnabled(true);
					if(!list_1.getSelectedValue().isUsed())
						btnDelete_1.setEnabled(true);
				}
				else
				{
					btnEdit_1.setEnabled(false);
					btnEdit_1.setEnabled(false);
				}
			}
		});
		scrollPane_1.setViewportView(list_1);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new UpcEditPanel(currentFrame, currentPanel, store, item, new UPC(), true));
				currentFrame.getContentPane().revalidate();	
			}
		});
		btnAdd.setBounds(102, 378, 61, 25);
		add(btnAdd);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new UpcEditPanel(currentFrame, currentPanel, store, item, list.getSelectedValue(), false));
				currentFrame.getContentPane().revalidate();	
			}
		});
		btnEdit.setBounds(175, 378, 61, 25);
		add(btnEdit);
		btnEdit.setEnabled(false);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				item.removeUPC(list.getSelectedValue());
				
				listModel.removeElement(list.getSelectedValue());
				btnDelete.setEnabled(false);
			}
		});
		btnDelete.setBounds(248, 378, 72, 25);
		add(btnDelete);
		btnDelete.setEnabled(false);
		
		// buttons below are for prices
		JButton btnAdd_1 = new JButton("Add");
		btnAdd_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new PriceEditPanel(currentFrame, currentPanel, store, item, new Price(), true));
				currentFrame.getContentPane().revalidate();	
			}
		});
		btnAdd_1.setBounds(449, 378, 61, 25);
		add(btnAdd_1);
		
		btnEdit_1 = new JButton("Edit");
		btnEdit_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new PriceEditPanel(currentFrame, currentPanel, store, item, list_1.getSelectedValue(), false));
				currentFrame.getContentPane().revalidate();	
			}
		});
		btnEdit_1.setBounds(553, 378, 61, 25);
		add(btnEdit_1);
		btnEdit_1.setEnabled(false);
		
		btnDelete_1 = new JButton("Delete");
		btnDelete_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				item.removePrice(list_1.getSelectedValue());
				
				listModel_1.removeElement(list_1.getSelectedValue());
				btnDelete_1.setEnabled(false);
			}
		});
		btnDelete_1.setBounds(626, 378, 72, 25);
		add(btnDelete_1);
		btnDelete_1.setEnabled(false);
	}
}
