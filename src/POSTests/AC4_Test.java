package POSTests;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import POSPD.*;

public class AC4_Test
{
	private Store store;
	private Session session;
	private Cashier cashier1;
	private Person person1;
	private Register reg1;
	private CashDrawer cD1 = new CashDrawer();
	private Sale sale;
	private SaleLineItem sLI1, sLI2;
	private Item item1, item2;
	private Price price1, price2, price3, price4;
	private TaxCategory tC1, tC2;
	private UPC upc, upc2;
	private TaxRate tR1, tR2, tR3, tR4;
	
	public AC4_Test()
	{
		store = new Store("Convenience Store", "0001");
		person1 = new Person("Bobby Bobson", "340 Street St.", "OKC", "OK", "234234", "555-555-5555", "444-44-4444", cashier1);
		cashier1 = new Cashier("7777", person1, "*******");
		cD1.addCash(new BigDecimal(500));
		reg1 = new Register("01", cD1);
		session = new Session(cashier1, reg1);
		sale = new Sale("false");
		item1 = new Item("Coke", "00001");
		item2 = new Item("Skittles", "00002");
		price1 = new Price("0.5", "1920/3/23");
		price2 = new Price("1.5", "1998/4/25");
		price3 = new Price("0.25", "1920/5/16");
		price4 = new Price("0.5", "2001/6/23");
		tC1 = new TaxCategory("Soda");
		tC2 = new TaxCategory("Candy");
		tR1 = new TaxRate(LocalDate.of(1920, 5, 16), BigDecimal.valueOf(0.05));
		tR2 = new TaxRate(LocalDate.of(1998, 4, 25), BigDecimal.valueOf(0.5));
		tR3 = new TaxRate(LocalDate.of(1920, 5, 16), BigDecimal.valueOf(0.02));
		tR4 = new TaxRate(LocalDate.of(2001, 6, 23), BigDecimal.valueOf(0.05));
		upc = new UPC("0000000000010110", item1);
		upc2 = new UPC("0000000000010111", item2);
		sLI1 = new SaleLineItem(item1.getNumber(), "1", store);
		sLI2 = new SaleLineItem(item2.getNumber(), "4", store);
		
		item1.addPrice(price1);
		item1.addPrice(price2);
		item1.setTaxCategory(tC1);
		item1.addUPC(upc);
		price1.setItem(item1);
		price2.setItem(item1);
		
		item2.addPrice(price3);
		item2.addPrice(price4);
		item2.setTaxCategory(tC2);
		item2.addUPC(upc2);
		price3.setItem(item2);
		price4.setItem(item2);
		
		tC1.addTaxRate(tR1);
		tC1.addTaxRate(tR2);
		tC2.addTaxRate(tR3);
		tC2.addTaxRate(tR4);
		
		sLI1.setItem(item1);
		sLI2.setItem(item2);
		
		sale.addSaleLineItem(sLI1);
		sale.addSaleLineItem(sLI2);	
		
		sLI1.setSale(sale);
		sLI2.setSale(sale);
		
		session.addSale(sale);
		session.setEndDateTime(LocalDateTime.of(2018, 10, 11, 4, 31, 21));
		
		store.addSession(session);
		store.addCashier(cashier1);
		store.addRegister(reg1);
		store.addItem(item1);
		store.addItem(item2);
		store.addTaxCategory(tC1);
		store.addTaxCategory(tC2);
		store.addUPC(upc);
		store.addUPC(upc2);
		
		
	}
	
	public void runTest()
	{
		System.out.println(store.toString());
	}
}
