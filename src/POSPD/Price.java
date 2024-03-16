package POSPD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Representation of a Price
 */
public class Price implements Comparable<Price>
{
	
	/**
	 * How much an item costs
	 */
	private BigDecimal price;
	/**
	 * When the Price will be used in Sales
	 */
	private LocalDate effectiveDate;
	/**
	 * Item that price refers to
	 */
	private Item item;

	public BigDecimal getPrice() 
	{
		return this.price;
	}

	public void setPrice(BigDecimal price) 
	{
		this.price = price;
	}

	public LocalDate getEffectiveDate() 
	{
		return this.effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate) 
	{
		this.effectiveDate = effectiveDate;
	}

	public Item getItem() 
	{
		return this.item;
	}

	public void setItem(Item item) 
	{
		this.item = item;
	}

	/**
	 * Default Constructor for a Price
	 */
	public Price() 
	{
		price = new BigDecimal(0);
		effectiveDate = LocalDate.parse("1/1/1111", DateTimeFormatter.ofPattern("M/d/yyyy"));
	}

	/**
	 * Constructor for a Price that sets price to price, and effectiveDate to effectiveDate
	 * @param price How much an item costs
	 * @param effectiveDate When the cost of an item will take effect
	 */
	public Price(String price, String effectiveDate) 
	{
		this();
		this.price = new BigDecimal(price);
		this.effectiveDate = LocalDate.parse(effectiveDate, DateTimeFormatter.ofPattern("M/d/yy"));
	}

	/**
	 * Determines whether a price if effective or not
	 * @param date Date to check against to determine whether a price is effective
	 * @return True, price is effective. False, price is NOT effective
	 */
	public Boolean isEffective(LocalDate date) 
	{
		Boolean result = false;
		if(this.effectiveDate.isBefore(date) || this.effectiveDate.equals(date))
			result = true;
			
		return result;
	}

	/**
	 * Calculates how much money to charge a Customer for a quantity of Items
	 * @param quantity Number of Items to calculate Prices for
	 * @return Amount of money to charge for the quantity given
	 */
	public BigDecimal calcAmountForQty(int quantity) 
	{
		BigDecimal result;
		
		result = price.multiply(new BigDecimal(quantity));
		
		return result;
	}

	/**
	 * Compares one Price to another Price
	 * @param price Price to compare to this.Price
	 * @return 0 means numbers are the same, negative means parameter is bigger, positive means parameter is smaller
	 */
	public int compareTo(Price price) 
	{
		return this.effectiveDate.compareTo(price.effectiveDate);
	}

	/**
	 * Makes a string representation of a Price
	 * @return String representation of a Price
	 */
	public String toString() 
	{
		return new String(price.toString() + " " + effectiveDate.format(DateTimeFormatter.ofPattern("M/d/yyyy")));
	}

	public Boolean isUsed()
	{
		return false;
	}
	
}