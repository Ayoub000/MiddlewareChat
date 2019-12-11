package serverTest;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import org.junit.Test;
import server.DataMap;

public class DataMapTest {

	@Test
	public void instanciationTest() throws RemoteException {
		assertNotNull(DataMap.getClients());
		assertNotNull(DataMap.getReceivers());
	}

}
