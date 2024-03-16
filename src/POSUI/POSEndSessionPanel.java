package POSUI;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import POSPD.Sale;
import POSPD.Session;
import POSPD.Store;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class POSEndSessionPanel extends JPanel
{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the panel.
	 */
	public POSEndSessionPanel(JFrame currentFrame, Store store, Session session)
	{
		setLayout(null);
		
		JLabel lblEndOfSession = new JLabel("End of Session");
		lblEndOfSession.setHorizontalAlignment(SwingConstants.CENTER);
		lblEndOfSession.setBounds(12, 45, 776, 16);
		add(lblEndOfSession);
		
		JLabel lblTotalSaleMade = new JLabel("Total Sales Made:");
		lblTotalSaleMade.setBounds(193, 234, 107, 16);
		add(lblTotalSaleMade);
		
		textField = new JTextField(String.valueOf(session.getSales().size()));
		System.out.println(session.getSales().size());
		textField.setBounds(342, 231, 116, 22);
		add(textField);
		textField.setColumns(10);
		textField.setEnabled(false);
		
		
		JLabel lblTotalCashMade = new JLabel("Total Cash Made:");
		lblTotalCashMade.setBounds(193, 271, 107, 16);
		add(lblTotalCashMade);
		
		BigDecimal totalCash = new BigDecimal(0);
		for(Sale s : session.getSales())
		{
			totalCash = totalCash.add(s.getTotalPayments());		
		}
			
		textField_1 = new JTextField(totalCash.toString());
		textField_1.setBounds(342, 268, 116, 22);
		add(textField_1);
		textField_1.setColumns(10);
		textField_1.setEnabled(false);
		
		JLabel lblEnterCash = new JLabel("Enter Cash:");
		lblEnterCash.setBounds(193, 310, 67, 16);
		add(lblEnterCash);
		
		JLabel lblCashDifference = new JLabel("Cash Difference:");
		lblCashDifference.setBounds(193, 351, 98, 16);
		add(lblCashDifference);
		
		textField_2 = new JTextField();
		textField_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_3.setText(session.calcCashCountDiff(new BigDecimal(textField_2.getText())).toString());
			}
		});
		textField_2.setBounds(342, 307, 116, 22);
		add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(342, 348, 116, 22);
		add(textField_3);
		textField_3.setColumns(10);
		textField_3.setEnabled(false);
		
		JLabel lblCashier = new JLabel("Cashier:");
		lblCashier.setBounds(193, 142, 56, 16);
		add(lblCashier);
		
		JLabel lblRegister = new JLabel("Register:");
		lblRegister.setBounds(193, 171, 56, 16);
		add(lblRegister);
		
		JLabel lblName = new JLabel(session.getCashier().toString());
		lblName.setBounds(342, 142, 116, 16);
		add(lblName);
		
		JLabel lblNumber = new JLabel(session.getRegister().toString());
		lblNumber.setBounds(342, 171, 116, 16);
		add(lblNumber);
		
		JButton btnEndSession = new JButton("End Session");
		btnEndSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHome(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnEndSession.setBounds(342, 441, 116, 25);
		add(btnEndSession);

	}

}
