package POSPD;

import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Representation of an Item
 */
public class Item 
{

	/**
	 * Item Number set by the store to make it easy to find
	 */
	private String number;
	
	/**
	 * Description of the Item given by the Store
	 */
	private String description;
	
	/**
	 * Collection of UPCs known by the Item
	 */
	private TreeMap<String, UPC> upcs;
	
	/**
	 * Price to charge for the Item
	 */
	private TreeSet<Price> prices;
	
	/**
	 * TaxCategory the Item is in
	 */
	private TaxCategory taxCategory;
	

	public String getNumber() 
	{
		return this.number;
	}

	public void setNumber(String number) 
	{
		this.number = number;
	}

	public String getDescription() 
	{
		return this.description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	public TreeMap<String, UPC> getUpcs()
	{
		return upcs;
	}
	
	public TreeSet<Price> getPrices()
	{
		return prices;
	}
	
	public TaxCategory getTaxCategory() 
	{
		return this.taxCategory;
	}

	public void setTaxCategory(TaxCategory taxCategory) 
	{
		this.taxCategory = taxCategory;
	}
	
	public void setTaxCategory(String taxCategory, Store store)
	{
		this.taxCategory = store.getTaxCategory(taxCategory);
	}
	
	/**
	 * Default Constructor for an Item
	 */
	public Item() 
	{
		taxCategory = new TaxCategory("");
		prices = new TreeSet<Price>();
		upcs = new TreeMap<String, UPC>();
	}

	/**
	 * Constructor for an Item that sets number to number and description to description.
	 * @param number Item Number given by the Store
	 * @param description Description of an Item given by the Store
	 */
	public Item(String number, String description) 
	{
		this();
		this.number = number;
		this.description = description;
	}

	/**
	 * Adds a Price to an item
	 * @param price Price to be added to an Item
	 */
	public void addPrice(Price price) 
	{
		price.setItem(this);
		prices.add(price);
	}

	/**
	 * Removes a Price from an Item
	 * @param price Price to remove from an Item
	 */
	public void removePrice(Price price) 
	{
		prices.remove(price);
	}

	/**
	 * Adds a UPC to the collection of UPCs known by the Item
	 * @param upc UPC to add to the collection of UPCs
	 */
	public void addUPC(UPC upc) 
	{
		upcs.put(upc.getUPC(), upc);
	}

	public Boolean hasUPC(String upc)
	{
		Boolean result = false;
		
		if(upcs.containsKey(upc))
		{
			result = true;
		}
		
		return result;
	}
	
	/**
	 * Removes a specified UPC from the collection of UPCs
	 * @param upc UPC to remove from the collection of UPCs
	 */
	public void removeUPC(UPC upc) 
	{
		upcs.remove(upc.getUPC());
	}

	/**
	 * Gets the Price of the Item for a given Date
	 * @param date Date used to find Price of the Item
	 * @return Price of the Item for the given Date
	 */
	public Price getPriceForDate(LocalDate date) 
	{
		Price result = null;
		for(Price p : prices)
		{
			if(p.isEffective(date))
				 result = p;
		}
		
		return result;
	}

	/**
	 * Gets the TaxRate for an item using the given Date
	 * @param date Date used to find the TaxRate for an Item
	 * @return TaxRate for an Item found using the given Date
	 */
	public BigDecimal getTaxRate(LocalDate date) 
	{
		return taxCategory.getTaxRateForDate(date);
	}

	/**
	 * Calculates the amount of money to charge for a quantity of Items on a given Date
	 * @param date Date used to check for the Price of an Item
	 * @param quantity Quantity of an Item
	 * @return Amount to charge for the Items
	 */
	public BigDecimal calcAmountForDateQty(LocalDate date, int quantity) 
	{
		return getPriceForDate(date).calcAmountForQty(quantity);
	}

	/**
	 * Makes a string representation of an Item
	 * @return String representation of an Item
	 */
	public String toString() 
	{	
		return new String(number + " " + description + " " + this.getPriceForDate(LocalDate.now())+" "+ taxCategory.getTaxRateForDate(LocalDate.now())   );
	}

	
	public Boolean isUsed()
	{
		return !(upcs.isEmpty() && prices.isEmpty());
	}
}