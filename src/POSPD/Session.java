package POSPD;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;

/**
 * Representation of a Session
 */
public class Session 
{

	/**
	 * Cashier of the session
	 */
	private Cashier cashier;
	/**
	 * Register for a session
	 */
	private Register register;
	/**
	 * Collection of Sales known by the Session
	 */
	private ArrayList<Sale> sales;
	/**
	 * When a session started
	 */
	private LocalDateTime startDateTime;
	/**
	 * When a session ended
	 */
	private LocalDateTime endDateTime;

	public Cashier getCashier() 
	{
		return this.cashier;
	}

	public void setCashier(Cashier cashier) 
	{
		this.cashier = cashier;
	}

	public Register getRegister() 
	{
		return this.register;
	}

	public void setRegister(Register register) 
	{
		this.register = register;
	}

	public ArrayList<Sale> getSales()
	{
		return sales;
	}
	
	public LocalDateTime getStartDateTime() 
	{
		return this.startDateTime;
	}

	public LocalDateTime getEndDateTime() 
	{
		return this.endDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime) 
	{
		this.endDateTime = endDateTime;
	}
	
	public Session()
	{
		startDateTime = LocalDateTime.now();
		sales = new ArrayList<Sale>();
	}
	
	public Session(String cashier, String register, Store store)
	{
		this();
		this.cashier = store.findCashierForNumber(cashier);
		this.register = store.getRegisters().get(register);
		this.cashier.addSession(this);
		this.register.addSession(this);
	}
	
	public Session(Cashier cashier, Register register, Store store)
	{
		this();
		this.cashier = cashier;
		this.register = register;
		this.cashier.addSession(this);
		this.register.addSession(this);
	}
	
	/**
	 * Constructor for a Session that initializes cashier to cashier, and register to register
	 * @param cashier Cashier for a Session
	 * @param register Register for a Session
	 */
	public Session(Cashier cashier, Register register) 
	{
		this();
		this.cashier = cashier;
		this.register = register;
		this.cashier.addSession(this);
		this.register.addSession(this);
	}

	/**
	 * Adds a Sale to the collection of Sales
	 * @param sale Sale to add to the collection of Sales
	 */
	public void addSale(Sale sale) 
	{
		sales.add(sale);
	}

	/**
	 * Removes a Sale from the collection of Sales
	 * @param sale Sale to remove from the collection of Sales
	 */
	public void removeSale(Sale sale) 
	{
		sales.remove(sale);
	}

	/**
	 * Calculates the difference between current cash in the drawer of the register and how much that was in the drawer in the beginning.
	 * @param cash Amount of cash in the drawer at the beginning of the Session
	 * @return Difference between current cash in the drawer of the register and how much that was in the drawer in the beginning
	 */
	public BigDecimal calcCashCountDiff(BigDecimal cash) 
	{
		return cash.subtract(register.getCashDrawer().getCash());
	}

	/**
	 * Makes a string representation of a Session
	 * @return String representation of a Session
	 */
	public String toString() 
	{
		String result = new  String("\nSession: Cashier: " + cashier.getPerson().getName() + " on register: " + register.getNumber());
		
		result += "\nSession Start: " + startDateTime.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
		
		for(Sale s : sales)
		{
			result += s.toString();
		}
		
		return result;
	}

}