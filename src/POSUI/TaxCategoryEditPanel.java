package POSUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import POSPD.Store;
import POSPD.TaxCategory;
import POSPD.TaxRate;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.SwingConstants;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class TaxCategoryEditPanel extends JPanel
{
	JButton btnEdit;
	JButton btnDelete;
	
	JList<TaxRate> list;
	
	private JTextField textField;
	
	JPanel currentPanel = this;
	
	DefaultListModel listModel;
	
	/**
	 * Create the panel.
	 */
	public TaxCategoryEditPanel(JFrame currentFrame, Store store, TaxCategory taxCategory, Boolean isAdd)
	{
		addAncestorListener(new AncestorListener() 
		{
			public void ancestorAdded(AncestorEvent arg0) 
			{
				listModel = new DefaultListModel();
				for(TaxRate taxRate : taxCategory.getTaxRates())
					listModel.addElement(taxRate);
				list.setModel(listModel);
			}
			public void ancestorMoved(AncestorEvent arg0) {
			}
			public void ancestorRemoved(AncestorEvent arg0) {
			}
		});
		setLayout(null);
		
		JLabel lblEditTaxCategory = new JLabel("Edit Tax Category");
		lblEditTaxCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditTaxCategory.setBounds(12, 53, 776, 16);
		add(lblEditTaxCategory);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setBounds(102, 132, 56, 16);
		add(lblCategory);
		
		textField = new JTextField(taxCategory.getCategory());
		textField.setBounds(181, 129, 116, 22);
		add(textField);
		textField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				taxCategory.setCategory(textField.getText());
				if(isAdd) store.addTaxCategory(taxCategory);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryListPanel(currentFrame, store));
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
				currentFrame.getContentPane().add(new TaxCategoryListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(497, 445, 97, 25);
		add(btnCancel);
		
		listModel = new DefaultListModel();
		for(TaxRate taxRate : taxCategory.getTaxRates())
			listModel.addElement(taxRate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(497, 130, 258, 190);
		add(scrollPane);
			
		list = new JList<TaxRate>(listModel);
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
		
		JLabel lblTaxRates = new JLabel("Tax Rates:");
		lblTaxRates.setBounds(497, 97, 72, 16);
		add(lblTaxRates);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxRateEditPanel(currentFrame, currentPanel, store, taxCategory, new TaxRate(), true));
				currentFrame.getContentPane().revalidate();	
			}
		});
		btnAdd.setBounds(497, 347, 56, 25);
		add(btnAdd);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxRateEditPanel(currentFrame, currentPanel, store, taxCategory, list.getSelectedValue(), false));
				currentFrame.getContentPane().revalidate();	
			}
		});
		btnEdit.setBounds(589, 347, 61, 25);
		add(btnEdit);
		btnEdit.setEnabled(false);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				taxCategory.removeTaxRate(list.getSelectedValue());
				
				listModel.removeElement(list.getSelectedValue());
				btnDelete.setEnabled(false);
			}
		});
		btnDelete.setBounds(683, 347, 72, 25);
		add(btnDelete);
		btnDelete.setEnabled(false);
	}
}
