package POSTests;

import POSPD.Store;
import POSPD.Cashier;
import POSPD.Person;

public class AC2_Test
{
	private Store store;
	private Cashier worker1, worker2, worker3;
	private Person person1, person2, person3;
	
	public AC2_Test()
	{
		store = new Store("Store Store", "0001");
		person1 = new Person("Bobby Bobson", "340 Street St.", "OKC", "OK", "234234", "555-555-5555", "444-44-4444", worker1);
		worker1 = new Cashier("7777", person1, "*******");
		person2 = new Person("Poppy Popson", "340 Road Rd.", "OKC", "OK", "234234", "555-555-5556", "444-44-4445", worker2);
		worker2 = new Cashier("7778", person2, "*******");
		person3 = new Person("Daddy Dad-san", "340 Boulevard Bd.", "OKC", "OK", "234234", "555-555-5557", "444-44-4446", worker3);
		worker3 = new Cashier("7779", person3, "*******");
		
		store.addCashier(worker1);
		store.addCashier(worker2);
		store.addCashier(worker3);
	}
	
	public void runTest()
	{		
		System.out.println(store.toString());
	}

}
