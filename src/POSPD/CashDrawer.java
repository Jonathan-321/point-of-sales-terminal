package POSPD;

import java.math.BigDecimal;

/**
 * Representation of a CashDrawer held by a Register
 */
public class CashDrawer 
{

	/**
	 * Cash amount held by the drawer
	 */
	private BigDecimal cashAmount;
	
	public BigDecimal getCash()
	{
		return cashAmount;
	}
	
	/**
	 * Position of the drawer, Open, Closed, or Other.
	 */
	private int position;

	public int getPosition() 
	{
		return this.position;
	}

	public void setPosition(int position) 
	{
		this.position = position;
	}

	public CashDrawer()
	{
		cashAmount = new BigDecimal(0);
		position = 0;
	}
	
	/**
	 * Add cash to the drawer
	 * @param cash Amount of cash to add to a drawer
	 */
	public void addCash(BigDecimal cash) 
	{
		cashAmount = cashAmount.add(cash);
	}
	
	/**
	 * Remove cash from the cash drawer
	 * @param cash Amount of cash to remove from the drawer
	 */
	public void removeCash(BigDecimal cash) 
	{
		cashAmount.subtract(cash);
	}

	/**
	 * Creates a String representation of a CashDrawer
	 * @return String representation of a drawer
	 */
	public String toString() 
	{
		return new String("\nCash in drawer: " + cashAmount.toString());
	}

}