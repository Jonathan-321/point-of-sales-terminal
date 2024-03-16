package POSUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import com.github.lgooddatepicker.components.DatePicker;

import POSPD.Sale;
import POSPD.Session;
import POSPD.Store;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class CashierReport extends JPanel
{
	BigDecimal total;
	BigDecimal amount;
	
	/**
	 * Create the panel.
	 */
	public CashierReport(JFrame currentFrame, Store store)
	{
		
		setLayout(null);
		
		JLabel lblCashierReport = new JLabel("Cashier Report");
		lblCashierReport.setHorizontalAlignment(SwingConstants.CENTER);
		lblCashierReport.setBounds(12, 31, 776, 16);
		add(lblCashierReport);
		
		DatePicker datePicker = new DatePicker();
		datePicker.setBounds(312, 126, 160, 22);
		add(datePicker);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(179, 170, 456, 276);
		add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				total = new BigDecimal(0);
				textArea.setText("Cashier report for " + datePicker.getDate().format(DateTimeFormatter.ofPattern("M/d/yyyy")) + "\n\n");
				textArea.append("Number\tName\t\tCount\tAmount\n");
				for(Session s : store.getSessions())
				{
					if(s.getEndDateTime().toLocalDate().isEqual(datePicker.getDate()))
					{
						amount = new BigDecimal(0);
						for(Sale sa : s.getSales())
						{
							amount = amount.add(sa.calcAmtTendered().subtract(sa.calcChange()));
							total = total.add(sa.getTotalPayments());
						}
						
						textArea.append(s.getRegister() + "\t" + s.getCashier().getPerson().getName() + "\t\t" + amount.toString() + "\t" + amount.toString() + "\n");
					}
				}
				textArea.append("\nTotal:\t\t\t\t" + total.toString());
			}
		});
		btnGenerate.setBounds(179, 459, 97, 25);
		add(btnGenerate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(538, 459, 97, 25);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHome(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		add(btnCancel);

	}
}
