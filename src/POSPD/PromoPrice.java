package POSPD;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;

/**
 * Representation of a PromoPrice
 */
public class PromoPrice extends Price
{

	/**
	 * Date a PromoPrice will no longer be effective
	 */
	private LocalDate endDate;

	public LocalDate getEndDate() 
	{
		return this.endDate;
	}

	public void setEndDate(LocalDate endDate) 
	{
		this.endDate = endDate;
	}

	/**
	 * Default Constructor for a PromoPrice
	 */
	public PromoPrice() 
	{
		super();
		endDate = LocalDate.parse("1/1/1111", DateTimeFormatter.ofPattern("M/d/yyyy"));
	}

	/**
	 * Constructor for a PromoPrice that sets price to price, effectiveDate to effectiveDate, and endDate to endDate
	 * @param price Price to set this.price to
	 * @param effectiveDate Date to set this.effectiveDate to
	 * @param endDate Date to set this.endDate to
	 */
	public PromoPrice(String price, String effectiveDate, String endDate) 
	{
		this();
		this.setPrice(new BigDecimal(price));
		this.setEffectiveDate(LocalDate.parse(effectiveDate, DateTimeFormatter.ofPattern("M/d/yy"))); 
		this.endDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("M/d/yy"));               
	}

	/**
	 * Determines whether or not the Price is active
	 * @param date Date used to determine whether or not a price is effective
	 * @return True, price is effective. False, price is NOT effective
	 */
	public Boolean isEffective(LocalDate date) 
	{
		Boolean result = false;
		
		if(this.getEffectiveDate().isBefore(date) || this.getEffectiveDate().equals(date) && this.getEndDate().isAfter(date))
			result = true;
		
		return result;
	}

	/**
	 * Compares one PromoPrice to another PromoPrice
	 * @param price PromoPrice to compare this.PromoPrice to
	 * @return 0: the prices are the same, negative: the parameter is bigger, positive: the parameter is smaller
	 */
	public int compareTo(Price price) 
	{
		return super.compareTo(price);
	}

	/**
	 * Makes a string representation of a PromoPrice
	 * @return String representation of a PromoPrice
	 */
	public String toString() 
	{
		return new String(getPrice().toString() + " " + getEffectiveDate().format(DateTimeFormatter.ofPattern("M/d/yyyy")) + " " + endDate.format(DateTimeFormatter.ofPattern("M/d/yyyy")));
	}
	
	public Boolean isUsed()
	{
		return false;
	}
}