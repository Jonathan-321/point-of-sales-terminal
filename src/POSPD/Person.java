package POSPD;

/**
 * Representation of a person
 */
public class Person 
{

	/**
	 * The name of the person
	 */
	private String name;
	/**
	 * The address of the person's abode
	 */
	private String address;
	/**
	 * The home city of the person
	 */
	private String city;
	/**
	 * The home state of the person
	 */
	private String state;
	/**
	 * The zip number of the person
	 */
	private String zip;
	/**
	 * The phone number of the person
	 */
	private String phone;
	/**
	 * The Social Security Number
	 */
	private String sSN;
	/**
	 * Cashier a Person knows (could be themselves)
	 */
	private Cashier cashier;

	public String getName() 
	{
		return this.name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getAddress() 
	{
		return this.address;
	}

	public void setAddress(String address) 
	{
		this.address = address;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public String getState()
	{
		return state;
	}
	
	public void setState(String state)
	{
		this.state = state;
	}
	
	public String getZip()
	{
		return zip;
	}
	
	public void setZip(String zip)
	{
		this.zip = zip;
	}
	
	public String getPhone() 
	{
		return this.phone;
	}

	public void setPhone(String phone) 
	{
		this.phone = phone;
	}

	public String getSSN() 
	{
		return this.sSN;
	}

	public void setSSN(String sSN) 
	{
		this.sSN = sSN;
	}

	public Cashier getCashier() 
	{
		if(cashier != null)
			return this.cashier;
		else
			return null;
	}

	public void setCashier(Cashier cashier) 
	{
		this.cashier = cashier;
	}

	/**
	 * Default constructor of a Person
	 */
	public Person() 
	{
		name = "";
		address = "";
		phone = "";
		sSN = "";
		cashier = null;
	}

	/**
	 * Constructor of a Person that initializes name to name, address to address, phone to phone, sSn to sSN, and cashier to cashier
	 * @param name Name of the person
	 * @param address Address of the person
	 * @param phone Phone number of the person
	 * @param sSN Social Security Number of the person
	 * @param cashier Cashier the Person may know (this could mean themselves)
	 */
	public Person(String name, String sSN,String address, String city, String state, String zip,String phone, Cashier cashier) 
	{
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
		this.sSN = sSN;
		this.cashier = cashier;
	}

	public String toString() 
	{
		return new String("\nName: " + this.name + "\nAddress: " + this.address + "\nPhone: " + this.phone + "\nSSN: " + sSN + "\nCashier: " + cashier.getNumber());
	}

}