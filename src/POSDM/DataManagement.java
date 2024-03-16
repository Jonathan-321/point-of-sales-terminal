package POSDM;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import POSPD.Cash;
import POSPD.Cashier;
import POSPD.Check;
import POSPD.Credit;
import POSPD.Item;
import POSPD.Price;
import POSPD.PromoPrice;
import POSPD.Register;
import POSPD.Sale;
import POSPD.SaleLineItem;
import POSPD.Session;
import POSPD.Store;
import POSPD.TaxCategory;
import POSPD.TaxRate;
import POSPD.UPC;

public class DataManagement
{
	public static Store loadStore(Store store)
	{
		Sale saAdd = null;

		String fileName = "StoreData_v2022.csv";
		String line = null;
		String dataType;
		String[] splitter;
		FileReader fileReader;
		BufferedReader bufferedReader;
		try
		{
			fileReader = new FileReader(fileName);

			bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null)
			{
				
				splitter = line.split(",");

				dataType = splitter[0];

				if (dataType.equals("Store"))
				{
					store.setName(splitter[1]);
				}
				else if (dataType.equals("TaxCategory"))
				{
					// tax category to add to the store
					TaxCategory tCAdd = new TaxCategory(splitter[1]);
					// tax rate to add to the above tax category
					TaxRate tRAdd = new TaxRate(splitter[3], splitter[2]);
					tCAdd.addTaxRate(tRAdd);
					store.addTaxCategory(tCAdd);
				}
				else if (dataType.equals("Cashier"))
				{
					store.addCashier(new Cashier(splitter[1], splitter[2], splitter[3], splitter[4], splitter[5],
							splitter[6], splitter[7], splitter[8], splitter[9]));
				} 
				else if (dataType.equals("Item"))
				{
					// new item to add to the store
					Item iAdd = new Item(splitter[1], splitter[3]);
					// price to add to item
					Price pAdd = new Price(splitter[5], splitter[6]);
					//upc to add to item
					UPC uAdd = new UPC(splitter[2], iAdd);
					// set item tax category
					iAdd.setTaxCategory(splitter[4], store);
					iAdd.addPrice(pAdd);
					// if the splitter array is greater than 7 then we have a promoprice to add
					if (splitter.length > 7)
					{
						PromoPrice pPAdd = new PromoPrice(splitter[7], splitter[8], splitter[9]);
						iAdd.addPrice(pPAdd);
					}
					store.addItem(iAdd);
				} 
				else if (dataType.equals("Register"))
				{
					store.addRegister(new Register(splitter[1]));
				}

				else if (dataType.equals("Session"))
				{
					Session session = new Session(splitter[1], splitter[2], store);
					session.setEndDateTime(LocalDateTime.now());
					store.addSession(session);
				} 
				else if (dataType.equals("Sale"))
				{
					saAdd = new Sale(splitter[1]);
					store.getSessions().get(store.getSessions().size() - 1).addSale(saAdd);
				} 
				else if (dataType.equals("SaleLineItem"))
				{
					Session session = store.getSessions().get(store.getSessions().size() - 1);
					session.getSales().get(session.getSales().size() - 1).addSaleLineItem(new SaleLineItem(splitter[1], splitter[2], store));
				} 
				else if (dataType.equals("Payment"))
				{
					dataType = splitter[1];

					if (dataType.equals("Cash"))
					{
						Session session = store.getSessions().get(store.getSessions().size() - 1);
						session.getSales().get(session.getSales().size() - 1).addPayment(new Cash(splitter[2], splitter[3]));
					} 
					else if (dataType.equals("Credit"))
					{
						Session session = store.getSessions().get(store.getSessions().size() - 1);
						Credit cAdd = new Credit(splitter[4], splitter[5], splitter[6]);
						cAdd.setAmount(splitter[2]);
						cAdd.setAmtTendered(splitter[3]);
						session.getSales().get(session.getSales().size() - 1).addPayment(cAdd);
					} 
					else if (dataType.equals("Check"))
					{
						Session session = store.getSessions().get(store.getSessions().size() - 1);
						Check cAdd = new Check(splitter[2], splitter[3], splitter[5], splitter[6]);
						cAdd.setRoutingNumber(splitter[4]);
						session.getSales().get(session.getSales().size() - 1).addPayment(cAdd);
					}
				}
			}
			bufferedReader.close();
		} 
		catch (FileNotFoundException e)
		{
			System.out.println("Unable to open file '" + fileName + "'");
		} 
		catch (IOException e)
		{
			System.out.println("Error reading file '" + fileName + "'");
		}

		return store;
	}
}
