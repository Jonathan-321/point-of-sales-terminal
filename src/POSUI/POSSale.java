package POSUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import POSPD.Item;
import POSPD.Sale;
import POSPD.SaleLineItem;
import POSPD.Session;
import POSPD.Store;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class POSSale extends JPanel
{
	private JTextField itemField;
	private JTextField quantityField;
	private JTextField subTotField;
	private JTextField taxField;
	private JTextField totalField;
	private JTextField tenderedField;
	private JTextField changeField;
	JLabel lblItemNotFound;

	JButton btnCompleteSale;
	
	DefaultListModel liListModel;
	
	/**
	 * Create the panel.
	 */
	public POSSale(JFrame currentFrame, Store store, Session session, Sale sale)
	{
		sale.setTaxFree(false);
		
		addAncestorListener(new AncestorListener() 
		{
			public void ancestorAdded(AncestorEvent event) 
			{
				if(!liListModel.isEmpty()) 
				{
					subTotField.setText(sale.calcSubTotal().toString());
					taxField.setText(sale.calcTax().toString());
					totalField.setText(sale.calcTotal().toString());
					tenderedField.setText(sale.calcAmtTendered().toString());
					changeField.setText(sale.calcChange().toString());
					if(sale.calcAmtTendered().compareTo(sale.calcTotal()) >= 0)
						btnCompleteSale.setEnabled(true);
				}
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		JPanel currentPanel = this;
		setLayout(null);
		
		JLabel lblSale = new JLabel("Sale");
		lblSale.setHorizontalAlignment(SwingConstants.CENTER);
		lblSale.setBounds(12, 41, 776, 16);
		add(lblSale);
		
		JLabel lblRegister = new JLabel("Register: ");
		lblRegister.setBounds(134, 70, 56, 16);
		add(lblRegister);
		
		JLabel lblRegnum = new JLabel(session.getRegister().getNumber());
		lblRegnum.setBounds(202, 70, 195, 16);
		add(lblRegnum);
		
		JLabel lblCashier = new JLabel("Cashier:");
		lblCashier.setBounds(134, 100, 56, 16);
		add(lblCashier);
		
		JLabel lblCashName = new JLabel(session.getCashier().getPerson().getName());
		lblCashName.setBounds(202, 99, 195, 16);
		add(lblCashName);
		
		JCheckBox chckbxTaxable = new JCheckBox("Taxable");
		chckbxTaxable.setSelected(true);
		chckbxTaxable.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(chckbxTaxable.isSelected())
				{
					sale.setTaxFree(false);
					taxField.setText(sale.calcTax().toString());
					totalField.setText(sale.calcTotal().toString());
					if(!sale.getTotalPayments().equals(0))
						changeField.setText(sale.calcChange().toString());
				}
				else
				{
					sale.setTaxFree(true);
					taxField.setText(sale.calcTax().toString());
					totalField.setText(sale.calcTotal().toString());
					if(!sale.getTotalPayments().equals(0))
						changeField.setText(sale.calcChange().toString());
				}
			}
		});
		chckbxTaxable.setBounds(470, 96, 113, 25);
		add(chckbxTaxable);
		
		JLabel lblItem = new JLabel("Item:");
		lblItem.setBounds(90, 144, 56, 16);
		add(lblItem);
		
		itemField = new JTextField();
		itemField.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Item item = store.findItemForUPC(itemField.getText());
				if(item != null)
				{
					lblItemNotFound.setVisible(false);
					SaleLineItem saleLineItem = new SaleLineItem(sale, item, Integer.parseInt(quantityField.getText()));
					liListModel.addElement(saleLineItem);
					subTotField.setText(sale.calcSubTotal().toString());
					taxField.setText(sale.calcTax().toString());
					totalField.setText(sale.calcTotal().toString());
				}
				else
				{
					lblItemNotFound.setVisible(true);
				}
				
			}
		});
		itemField.setBounds(170, 141, 116, 22);
		add(itemField);
		itemField.setColumns(10);
		
		JLabel lblQty = new JLabel("Qty: ");
		lblQty.setBounds(410, 144, 56, 16);
		add(lblQty);
		
		quantityField = new JTextField("1");
		quantityField.setBounds(470, 141, 63, 22);
		add(quantityField);
		quantityField.setColumns(10);
		
		JLabel lblSubtotal = new JLabel("SubTotal");
		lblSubtotal.setBounds(592, 246, 56, 16);
		add(lblSubtotal);
		
		JLabel lblTax = new JLabel("Tax");
		lblTax.setBounds(592, 275, 56, 16);
		add(lblTax);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(592, 308, 56, 16);
		add(lblTotal);
		
		JLabel lblTendered = new JLabel("Tendered");
		lblTendered.setBounds(592, 336, 56, 16);
		add(lblTendered);
		
		JLabel lblChange = new JLabel("Change");
		lblChange.setBounds(592, 365, 56, 16);
		add(lblChange);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSSale(currentFrame, store, session, new Sale()));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(134, 433, 116, 25);
		add(btnCancel);
		
		JButton btnPayments = new JButton("Payments");
		btnPayments.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSPayment(currentFrame, currentPanel, store, session, sale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnPayments.setBounds(281, 433, 116, 25);
		add(btnPayments);
		
		btnCompleteSale = new JButton("Complete Sale");
		btnCompleteSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				session.getRegister().getCashDrawer().addCash(sale.getTotalPayments());
				session.getRegister().getCashDrawer().removeCash(sale.calcChange());
				session.setEndDateTime(LocalDateTime.now());
				session.addSale(sale);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSSale(currentFrame, store, session, new Sale()));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCompleteSale.setBounds(134, 471, 116, 25);
		add(btnCompleteSale);
		btnCompleteSale.setEnabled(false);
		
		subTotField = new JTextField();
		subTotField.setBounds(660, 243, 99, 22);
		add(subTotField);
		subTotField.setColumns(10);
		subTotField.setEditable(false);
		
		taxField = new JTextField();
		taxField.setBounds(660, 272, 99, 22);
		add(taxField);
		taxField.setColumns(10);
		taxField.setEditable(false);
		
		totalField = new JTextField();
		totalField.setBounds(660, 305, 99, 22);
		add(totalField);
		totalField.setColumns(10);
		totalField.setEditable(false);
		
		tenderedField = new JTextField();
		tenderedField.setBounds(660, 333, 99, 22);
		add(tenderedField);
		tenderedField.setColumns(10);
		tenderedField.setEditable(false);
		
		changeField = new JTextField();
		changeField.setBounds(660, 362, 99, 22);
		add(changeField);
		changeField.setColumns(10);
		changeField.setEditable(false);
		
		liListModel = new DefaultListModel();
		
		JList<SaleLineItem> list = new JList<SaleLineItem>(liListModel);
		list.addListSelectionListener(new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent e) 
			{
				
			}
		});
		list.setBounds(90, 176, 490, 245);
		add(list);
		
		JButton btnEndSession = new JButton("End Session");
		btnEndSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.addSession(session);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSEndSessionPanel(currentFrame, store, session));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnEndSession.setBounds(281, 471, 116, 25);
		add(btnEndSession);
		
		lblItemNotFound = new JLabel("Item not found.");
		lblItemNotFound.setBounds(298, 144, 99, 16);
		add(lblItemNotFound);
		lblItemNotFound.setVisible(false);

	}
}
