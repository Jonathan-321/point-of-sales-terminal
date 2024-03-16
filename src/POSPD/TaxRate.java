package POSPD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representation of a TaxRate
 */
public class TaxRate implements Comparable<TaxRate>
{

	/**
	 * TaxRate of the TaxRate, percentile of what a customer should pay a governing body for an item.
	 */
	private BigDecimal taxRate;
	/**
	 * Effective date, the date the tax rate will be effective
	 */
	private LocalDate effectiveDate;

	public BigDecimal getTaxRate() 
	{
		return this.taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) 
	{
		this.taxRate = taxRate;
	}

	public LocalDate getEffectiveDate() 
	{
		return this.effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate) 
	{
		this.effectiveDate = effectiveDate;
	}
	
	public TaxRate()
	{
		taxRate = new BigDecimal(0);
		effectiveDate = LocalDate.parse("1/1/11", DateTimeFormatter.ofPattern("M/d/yy"));
	}
	
	public TaxRate(String effectiveDate, String rate)
	{
		taxRate = new BigDecimal(rate);
		this.effectiveDate = LocalDate.parse(effectiveDate, DateTimeFormatter.ofPattern("M/d/yy"));
	}
	
	/**
	 * Constructor that initialized effectiveDate to effectiveDate, and taxRate to rate
	 * @param effectiveDate EffectiveDate for a taxRate, when a tax rate will take effect
	 * @param rate
	 */
	public TaxRate(LocalDate effectiveDate, BigDecimal rate) 
	{
		taxRate = rate;
		this.effectiveDate = effectiveDate;
	}

	/**
	 * Determines whether or not a taxRate is effective.
	 * @param date Date to determine whether or not a tax rate is effective
	 * @return True, a tax rate is effective. False, a tax rate is NOT effective
	 */
	public Boolean isEffective(LocalDate date) 
	{
		Boolean result = false;
		
		if(date.isAfter(this.effectiveDate))
			result = true;
		
		return result;
	}

	/**
	 * Compares one taxRate to another tax rate
	 * @param taxRate taxRate to compare to this.taxRate
	 * @return If 0 then equals, if above 0 the parameter is less than this.taxRate, if below 0 the parameter is greater than this.taxRate
	 */
	public int compareTo(TaxRate taxRate) 
	{
		return taxRate.getTaxRate().compareTo(this.getTaxRate());
	}

	/**
	 * Makes a string representation of a Tax Rate
	 * @return String representation of a Tax Rate
	 */
	public String toString() 
	{
		return new String(taxRate.toString() + " " + effectiveDate.format(DateTimeFormatter.ofPattern("M/d/yyyy")));
	}

	public Boolean isUsed()
	{
		Boolean result = false;
		
		if(effectiveDate.isAfter(LocalDate.now()))
			result = true;
		
		return result;
	}
	
}