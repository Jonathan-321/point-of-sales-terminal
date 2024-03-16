package POSPD;

/**
 * Representation of a Universal Price Code of an item
 */
public class UPC implements Comparable<UPC>
{

	/**
	 * Universal Price Code
	 */
	private String uPC;
	
	/**
	 * Item that "belongs" to the Universal Price Code
	 */
	private Item item;
	
	public String getUPC()
	{
		return uPC;
	}
	
	public void setUPC(String upc)
	{
		uPC = upc;
	}
	
	public void setItem(Item item)
	{
		this.item = item;
	}
	
	/**
	 * Default constructor for a UPC
	 */
	public UPC() 
	{
		uPC = "";
	}

	/**
	 * Constructor for a UPC that takes a UPC
	 * @param upc UPC to initialize the UPC's UPC attribute
	 */
	public UPC(String upc, Item item) 
	{
		uPC = upc;
		this.item = item;
		this.item.addUPC(this);
	}

	/**
	 * Makes a representation of a UPC as a String
	 * @return String form of a UPC
	 */
	public String toString() 
	{
		return new String(uPC);
	}
	
	public Boolean isUsed()
	{
		return false;
	}

	@Override
	public int compareTo(UPC upc)
	{
		return this.getUPC().compareTo(upc.getUPC());
	}
}