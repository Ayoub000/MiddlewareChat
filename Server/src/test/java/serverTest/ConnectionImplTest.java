package serverTest;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

import server.ConnectionImpl;
import server.DataMap;

public class ConnectionImplTest {

	ConnectionImpl connection;
	public ConnectionImplTest() throws RemoteException
	{
		connection = new ConnectionImpl();
	}
	@Test
	public void connectTest() {
		DataMap.getClients().add("Ayoub");
		assertEquals(DataMap.getClients().contains("Ayoub"),true);
	}
	@Test
	public void disconnectTest() {
		DataMap.getClients().remove("Ayoub");
		assertEquals(DataMap.getClients().contains("Ayoub"),false);
	}

}
