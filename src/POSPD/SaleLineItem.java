package POSPD;

import java.math.BigDecimal;import java.time.format.DateTimeFormatter;

/**
 * Representation of a SaleLineItem
 */
public class SaleLineItem 
{

	/**
	 * Item that the SaleLineItem knows
	 */
	private Item item;
	
	/**
	 * Sale that the SaleLineItem
	 */
	private Sale sale;
	
	/**
	 * Quantity of the Item that the SaleLineItem knows
	 */
	private int quantity;

	public Item getItem() 
	{
		return this.item;
	}

	public void setItem(Item item) 
	{
		this.item = item;
	}
	
	public Sale getSale()
	{
		return this.sale;
	}
	
	public void setSale(Sale sale)
	{
		this.sale = sale;
	}
	
	public int getQuantity() 
	{
		return this.quantity;
	}

	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}

	/**
	 * Default Constructor for a SaleLineItem
	 */
	public SaleLineItem() 
	{
		item = new Item();
		sale = new Sale();
	}

	/**
	 * Constructor for a SaleLineItem that sets this.item.itemNumber to itemNumber, and quantity to quantity
	 * @param itemNumber ItemNumber of an Item to set an Item to a specific Item
	 * @param quantity Quantity of Items for the SaleLineItem
	 */
	public SaleLineItem(Sale sale, Item item, int quantity)
	{
		setQuantity(quantity);
		setItem(item);
		sale.addSaleLineItem(this);
	}
	
	public SaleLineItem(String itemNumber, String quantity, Store store) 
	{
		this();
		this.item = store.findItemForNumber(itemNumber);
		this.quantity = new Integer(quantity);
	}

	/**
	 * Calculates the SubTotal for all items in the receipt
	 * @return Total Price of all Items in the SaleLineItem
	 */
	public BigDecimal calcSubTotal() 
	{
		return item.calcAmountForDateQty(sale.getDate().toLocalDate(), quantity);
	}

	/**
	 * Calculates the tax for all Items in the receipt
	 * @return Tax total to add to the SubTotal
	 */
	public BigDecimal calcTax() 
	{
		return this.calcSubTotal().multiply(item.getTaxRate(sale.getDate().toLocalDate()));
	}

	/**
	 * Makes a string representation of a SaleLineItem
	 * @return String representation of a SaleLineItem
	 */
	public String toString() 
	{
		return new String("\n  " + item.getDescription() + " " + item.getNumber() + " " + quantity + " " + item.getPriceForDate(sale.getDate().toLocalDate()) + " " + item.getTaxRate(sale.getDate().toLocalDate()).toString());
	}

}