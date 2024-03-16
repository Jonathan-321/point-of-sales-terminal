package POSPD;

import java.util.*;

/**
 * This is a representation of a convenience store
 */
public class Store 
{

	/**
	 * Number that identifies the Store
	 */
	private String number;
	/**
	 * Name of the Store
	 */
	private String name;
	/**
	 * Collection of cashiers employed by the Store
	 */
	private TreeMap<String, Cashier> cashiers;
	/**
	 * Collection of Sessions known by the Store
	 */
	private ArrayList<Session> sessions;
	/**
	 * Collection of Registers owned by the Store
	 */
	private TreeMap<String, Register> registers;
	/**
	 * Collection of TaxCategories that are known by the Store
	 */
	private TreeMap<String, TaxCategory> taxCategories;
	/**
	 * Collection of UPCs known by  the Store
	 */
	private TreeMap<String, UPC> upcs;
	/**
	 * Collection of Items sold by the Store
	 */
	private TreeMap<String, Item> items;

	public String getNumber() 
	{
		return this.number;
	}

	public void setNumber(String number) 
	{
		this.number = number;
	}

	public String getName() 
	{
		return this.name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
	
	public TaxCategory getTaxCategory(String taxCategory)
	{
		return taxCategories.get(taxCategory);
	}
	
	public TreeMap<String, TaxCategory> getTaxCategories()
	{
		return taxCategories;
	}
	
	public ArrayList<Session> getSessions()
	{
		return sessions;
	}
	
	public TreeMap<String, Register> getRegisters()
	{
		return registers;
	}
	
	public TreeMap<String, Cashier> getCashiers()
	{
		return cashiers;
	}
	
	public TreeMap<String, Item> getItems()
	{
		return items;
	}
	
	/**
	 * Default constructor for a Store
	 */
	public Store() 
	{
		name = "";
		number = "";
		items = new TreeMap<String, Item>();
		upcs = new TreeMap<String, UPC>();
		taxCategories = new TreeMap<String, TaxCategory>();
		registers = new TreeMap<String, Register>();
		sessions = new ArrayList<Session>();
		cashiers = new TreeMap<String, Cashier>();
	}

	/**
	 * Constructor for the store that takes in a Number and a Name for the Store
	 * @param number
	 * @param name
	 */
	public Store(String name, String number) 
	{
		this();
		this.number = number;
		this.name = name;
	}

	/**
	 * Adds a new item to the collection of items sold by the Store
	 * @param item Item to be added to the collection of Items
	 */
	public void addItem(Item item) 
	{
		items.put(item.getNumber(), item);
	}

	/**
	 * Removes an item from the collection of Items sold by the Store
	 * @param item Item to find in the collection of Items and remove
	 */
	public void removeItem(Item item) 
	{
		items.remove(item.getNumber());
	}

	/**
	 * Add a new register to the collection of Registers owned by the Store
	 * @param register Register to be added to the collection of Registers
	 */
	public void addRegister(Register register) 
	{
		registers.put(register.getNumber(), register);
	}

	/**
	 * Removes an item from the collection of Items sold by the Store
	 * @param register Register to be removed from the collection of Registers
	 */
	public void removeRegister(Register register) 
	{
		registers.remove(register.getNumber());
	}

	/**
	 * Adds a new UPC to the collection of UPCs known by the Store
	 * @param upc UPC to be added to the collection of UPCs
	 */
	public void addUPC(UPC upc) 
	{
		upcs.put(upc.getUPC(), upc);
	}

	/**
	 * Removes a UPC from the collection of UPCs known by the Store
	 * @param upc UPC to be removed from the collection of UPCs
	 */
	public void removeUPC(UPC upc) 
	{
		upcs.remove(upc.getUPC());
	}

	/**
	 * Adds a new Cashier to the collection of Cashiers employed by the Store
	 * @param cashier Cashier to be added to the collection of Cashiers
	 */
	public void addCashier(Cashier cashier) 
	{
		cashiers.put(cashier.getNumber(), cashier);
	}

	/**
	 * Removes a Cashier from the collection of Cashiers employed by the Store
	 * @param cashier Cashier to be removed from the collection of Cashiers
	 */
	public void removeCashier(Cashier cashier) 
	{
		cashiers.remove(cashier.getNumber());
	}

	/**
	 * Adds a Session to the collection of Sessions known by the Store
	 * @param session Session to be added to the collection of Sessions
	 */
	public void addSession(Session session) 
	{
		sessions.add(session);
	}

	/**
	 * Removes a Session from the collection of Sessions known by the Store
	 * @param session Session to be removed from the collection of Sessions
	 */
	public void removeSession(Session session) 
	{
		sessions.remove(session);
	}

	/**
	 * Searches the collection of Items for an item using a given UPC
	 * @param upc Universal Price Code to find a specific item
	 * @return Item found using the given UPC
	 */
	public Item findItemForUPC(String upc) 
	{
		Item result = null;
		
		for(Item i : items.values())
		{
			if(i.hasUPC(upc))
			{
				result = i;
			}
		}
		
		return result;
	}

	/**
	 * Finds an Item in the collection of Items with a specific Item Number
	 * @param number Item Number used to search the collection of Items for an Item
	 * @return Item found using the Item Number
	 */
	public Item findItemForNumber(String number) 
	{
		return items.get(number);
	}

	/**
	 * Uses a given Cashier Number to find a Cashier
	 * @param number Cashier Number used to find a Cashier
	 * @return Cashier found using the given Cashier Number
	 */
	public Cashier findCashierForNumber(String number) 
	{
		return cashiers.get(number);
	}

	/**
	 * Adds a TaxCategory to the collection of TaxCategories known by the Store
	 * @param taxCategory TaxCategory to add to the collection of TaxCategories.
	 */
	public void addTaxCategory(TaxCategory taxCategory) 
	{
		taxCategories.put(taxCategory.getCategory(), taxCategory);
	}

	/**
	 * Removes a TaxCategory from the collection of TaxCategories known by the Store
	 * @param taxCategory TaxCategory to remove from the collection of TaxCategories
	 */
	public void removeTaxCategory(TaxCategory taxCategory) 
	{
		taxCategories.remove(taxCategory.getCategory());
	}

	/**
	 * Puts all of the data for a Store into a String
	 * @return The String representation of a Store
	 */
	public String toString() 
	{
		String result = new String("\nStore name: " + name + "\nCashiers: ");
		
		for(Cashier c : cashiers.values())
		{
			result += c.toString();
		}
		

		result += "\nRegisters: ";
		
		
		for(Register r : registers.values())
		{
			result += r.toString();
		}
		
		result += "\nItems: ";
		
		for(Item i : items.values())
		{
			result += "\n" + i.toString();
		}
		
		result += "\nSessions: ";
		
		for(Session s : sessions)
		{
			result += s.toString();
		}
		
		
		return result;
	}

}