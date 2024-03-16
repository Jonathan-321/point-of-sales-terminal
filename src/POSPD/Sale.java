package POSPD;

import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

/**
 * Representation of a Sale
 */
public class Sale 
{

	/**
	 * Collection of Payments known by the Sale
	 */
	private ArrayList<Payment> payments;
	
	/**
	 * Collection of SaleLineItems known by the Sale, used to generate receipts
	 */
	private ArrayList<SaleLineItem> saleLineItems;
	
	/**
	 * Date the Sale happened
	 */
	private LocalDateTime date;
	
	/**
	 * Whether or not a Sale was TaxFree
	 */
	private Boolean taxFree;

	public LocalDateTime getDate() 
	{
		return this.date;
	}

	public Boolean getTaxFree() 
	{
		return this.taxFree;
	}

	public void setTaxFree(Boolean taxFree) 
	{
		this.taxFree = taxFree;
	}

	/**
	 * Default Constructor for a Sale
	 */
	public Sale() 
	{
		date = LocalDateTime.now();
		payments = new ArrayList<Payment>();
		saleLineItems = new ArrayList<SaleLineItem>();
	}

	/**
	 * Constructor for a Sale that sets taxFree to taxFree
	 * @param taxFree Whether or not a Sale is TaxFree
	 */
	public Sale(String taxFree) 
	{
		this();
		this.taxFree = new Boolean(taxFree);
	}

	/**
	 * Adds a Payment to the collection of Payments
	 * @param payment Payment to add to the collection of Payments
	 */
	public void addPayment(Payment payment) 
	{
		payments.add(payment);
	}

	/**
	 * Removes a specified Payment from the collection of Payments
	 * @param payment Payment to be removed from the collection of Payments
	 */
	public void removePayment(Payment payment) 
	{
		payments.remove(payment);
	}

	/**
	 * Adds a SaleLineItem to the collection of SaleLineItems
	 * @param sli SaleLineItem to be added to the collection of SaleLineItems
	 */
	public void addSaleLineItem(SaleLineItem sli) 
	{
		sli.setSale(this);
		saleLineItems.add(sli);
	}

	/**
	 * Removes a specified SaleLineItem from the collection of SaleLineItems
	 * @param sli
	 */
	public void removeSaleLineItem(SaleLineItem sli) 
	{
		saleLineItems.remove(sli);
	}

	public ArrayList<SaleLineItem> getSaleLineItems()
	{
		return saleLineItems;
	}
	
	/**
	 * Calculates to Total of a Sale, SubTotal plus Tax
	 * @return Total owed to the Store by a Customer
	 */
	public BigDecimal calcTotal() 
	{
		return calcSubTotal().add(calcTax().setScale(2, RoundingMode.HALF_UP));
	}

	/**
	 * Calculates the SubTotal for all Items in a Sale
	 * @return How much each Item costs before Tax
	 */
	public BigDecimal calcSubTotal() 
	{
		BigDecimal result = new BigDecimal(0);
		for(SaleLineItem s : saleLineItems)
		{
			result = result.add(s.calcSubTotal());
		}
		
		return result;
	}

	/**
	 * Calculates the Tax for a Sale
	 * @return Tax for all items in a Sale
	 */
	public BigDecimal calcTax() 
	{
		BigDecimal result = new BigDecimal(0);
		if(!taxFree)
		{
			for(SaleLineItem s : saleLineItems)
			{
				result = result.add(s.calcTax().setScale(2, RoundingMode.HALF_UP));
			}
		}
		
		return result;
	}

	/**
	 * Gets the total Payments made by a Customer in a Sale
	 * @return Total payments made by a Customer in a Sale
	 */
	public BigDecimal getTotalPayments() 
	{
		BigDecimal result = new BigDecimal(0);
		
		for(Payment p : payments)
		{
			result = result.add(p.getAmtTendered());
		}
		
		return result.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	public ArrayList<Payment> getPayments()
	{
		return payments;
	}
	
	/**
	 * Determines whether there was enough payment for the whole sale
	 * @return True, payment is enough. False, Payment is NOT enough
	 */
	public Boolean isPaymentEnough() 
	{
		Boolean result = false;
		
		if(this.getTotalPayments().compareTo(this.calcTotal()) >= 0)
		{
			result = true;
		}
		
		return result;
	}

	/**
	 * Calculates the amount to charge the customer.
	 * @param amtTendered Money to subtract from the total.
	 */
	public BigDecimal calcAmount(BigDecimal amtTendered) 
	{
		BigDecimal result = calcTotal().subtract(getTotalPayments());
		if(result.compareTo(amtTendered) > 0)
		{
			result = amtTendered;
		}
		
		return result.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * Calculates the amount of money owed to the Customer, if any
	 * @return Amount of money to give back to the Customer
	 */
	public BigDecimal calcChange() 
	{
		BigDecimal result = new BigDecimal(0);
			result = getTotalPayments().subtract(calcTotal());
		
		return result;
	}

	/**
	 * Calculates the amount of money given by a Customer in a Sale
	 * @return the amount tendered during the sale
	 */
	public BigDecimal calcAmtTendered() 
	{
		BigDecimal result = new BigDecimal(0);
		
		for(Payment p : payments)
		{
			result = result.add(p.getAmtTendered());
		}
		
		return result;
	}
	
	public BigDecimal calcAmount()
	{
		BigDecimal result = new BigDecimal(0);
		
		for(Payment p : payments)
		{
			result = result.add(p.getAmount());
		}
		
		return result;
	}
	
	/**
	 * Makes a string representation of a Sale
	 * @return String representation of a Sale
	 */
	public String toString() 
	{
		String result = new String("\nSale: ");
		
		result += "\n  SubTotal: " + this.calcSubTotal().toString() + " Tax: " + this.calcTax().toString()
				+ " Total: " + this.calcTotal().toString() + "\n  Payment: " + this.getTotalPayments().toString() 
				+ " Change: " + this.calcChange().toString();
		
		for(SaleLineItem s : saleLineItems)
		{
			result += s.toString();
		}
		
		return result;
	}

}