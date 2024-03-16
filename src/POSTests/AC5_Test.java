package POSTests;

import POSDM.DataManagement;
import POSPD.Store;

public class AC5_Test
{
	Store store = new Store();
	DataManagement dataManager = new DataManagement();
	
	public AC5_Test()
	{
		store = dataManager.loadStore(store);
	}
	
	public void runTest()
	{
		System.out.println(store.toString());
	}
}
