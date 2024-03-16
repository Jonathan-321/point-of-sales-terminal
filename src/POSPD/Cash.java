package POSPD;

import java.math.BigDecimal;

/**
 * Representation of Cash thrown about during transactions
 */
public class Cash extends Payment 
{

	/**
	 * Default constructor for Cash
	 */
	public Cash() 
	{
		this.setAmount(new BigDecimal(0));
		this.setAmtTendered(new BigDecimal(0));
	}

	/**
	 * Constructor that takes its Amount of cash owed and AmountTendered to initialize
	 * @param amount Amount of cash owed in a Sale
	 * @param amtTendered Amount paid during a Sale
	 */
	public Cash(String amount, String amtTendered) 
	{
		this.setAmount(new BigDecimal(amount));
		this.setAmtTendered(new BigDecimal(amtTendered));
	}

	/**
	 * Determines whether a Cash payment counts as Cash
	 * @return True, counts as cash. False, does not count as cash
	 */
	public Boolean countsAsCash() 
	{
		return true;
	}

	/**
	 * Creates a string representation of Cash
	 * @return The String representation of Cash
	 */
	public String toString() 
	{
		return new String(getAmtTendered().toString() + " paid out of " + getAmount().toString());
	}

}