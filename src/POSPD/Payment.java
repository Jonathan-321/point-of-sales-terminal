package POSPD;

import java.math.BigDecimal;

/**
 * Representation of a customer's payment to the Store
 */
public abstract class Payment 
{

	/**
	 * Amount of money to be expected from a Sale
	 */
	private BigDecimal amount;
	/**
	 * Amount of money paid during a Sale
	 */
	private BigDecimal amtTendered;

	public BigDecimal getAmount() 
	{
		return this.amount;
	}

	public void setAmount(BigDecimal amount) 
	{
		this.amount = amount;
	}

	public void setAmount(String amount)
	{
		this.amount = new BigDecimal(amount);
	}
	
	public BigDecimal getAmtTendered() 
	{
		return this.amtTendered;
	}

	public void setAmtTendered(String amtTendered)
	{
		this.amtTendered = new BigDecimal(amtTendered);
	}
	
	public void setAmtTendered(BigDecimal amtTendered) 
	{
		this.amtTendered = amtTendered;
	}

	/**
	 * Determines whether a payment counts as cash for the Sale
	 * @return True, a payment counts as cash. False, a payment does NOT count as cash
	 */
	public Boolean countsAsCash() 
	{
		return true;
	}

}