package POSUI;

import javax.swing.JPanel;

import POSPD.Store;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class POSHome extends JPanel
{

	/**
	 * Create the panel.
	 */
	public POSHome(JFrame currentFrame, Store store)
	{
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel(store.getName());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 16));
		lblNewLabel.setBounds(-108, 133, 776, 22);
		add(lblNewLabel);

	}

}
