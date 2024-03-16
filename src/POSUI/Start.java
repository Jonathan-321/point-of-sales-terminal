package POSUI;

import java.io.FileNotFoundException;
import java.io.IOException;

import POSDM.DataManagement;
import POSPD.Store;
import POSTests.AC5_Test;

//import POSTests.AC1_Test;
//import POSTests.AC2_Test;
//import POSTests.AC3_Test;
//import POSTests.AC4_Test;

public class Start
{	
	public static void main(String[] args)
	{	

		
		Store myStore = new Store();
		DataManagement.loadStore(myStore);
		POSFrame storeFrame = new POSFrame(myStore);
		storeFrame.run(myStore);
	}
}