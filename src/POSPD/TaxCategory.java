package POSPD;

import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Representation of a TaxCategory
 */
public class TaxCategory 
{

	/**
	 * Name/Category for taxes
	 */
	private String category;
	/**
	 * Collection of TaxRates for Categories
	 */
	private TreeSet<TaxRate> taxRates;

	public String getCategory() 
	{
		return category;
	}
	
	public void setCategory(String category)
	{
		this.category = category;
	}
	
	public TreeSet<TaxRate> getTaxRates()
	{
		return taxRates;
	}
	
	/**
	 * Default constructor for a TaxCategory
	 */
	public TaxCategory() 
	{
		category = "";
		taxRates = new TreeSet<TaxRate>();
	}

	/**
	 * Constructor for a TaxCategory that sets category to category
	 * @param category Name/Category for taxes
	 */
	public TaxCategory(String category) 
	{
		this();
		this.category = category;
	}

	/**
	 * Gets the TaxRate for a specified date
	 * @param date Date to determine a TaxRate
	 * @return TaxRate for given date
	 */
	public BigDecimal getTaxRateForDate(LocalDate date) 
	{
		BigDecimal result = new BigDecimal(0);
		
		for(TaxRate t : taxRates)
		{
			if(t.isEffective(date))
				result = t.getTaxRate();
		}
		
		return result;
	}

	/**
	 * Adds a Tax Rate to the collection of Tax Rates
	 * @param taxRate Tax Rate to be added to the collection of Tax Rates
	 */
	public void addTaxRate(TaxRate taxRate) 
	{
		taxRates.add(taxRate);
	}

	/**
	 * Removes a Tax Rate from the collection of TaxRates
	 * @param taxRate Tax Rate to remove from the collection of TaxRates
	 */
	public void removeTaxRate(TaxRate taxRate) 
	{
		taxRates.remove(taxRate);
	}

	/**
	 * Makes a string representation of a Tax Category
	 * @return String representation of a Tax Category
	 */
	public String toString() 
	{
		return new String(category);
	}
	
	public Boolean isUsed()
	{
		return false;
	}
}