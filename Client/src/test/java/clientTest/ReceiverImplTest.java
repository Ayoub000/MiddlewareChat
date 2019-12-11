package clientTest;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import client.ReceiverImpl;
import modelFX.DataMonitor;

public class ReceiverImplTest {

	private ReceiverImpl obj ;
	public ReceiverImplTest() throws RemoteException
	{
		obj = new ReceiverImpl();
	}

	@Test
	public void receiveTest() throws RemoteException {
		DataMonitor.getMessages().add("Je suis Ayoub et j'envois : Je suis arrivé");
		//obj.receive("Ayoub","Je suis arrivé");
		assertEquals(DataMonitor.getMessages().get((DataMonitor.getMessages().size() - 1)),"Je suis Ayoub et j'envois : Je suis arrivé");
	}

	@Test
	public void initClientsTest() throws RemoteException
	{
		List<String> test = new ArrayList<String>();
		obj.initClients(test);
		assertEquals(test,DataMonitor.getClientList());
	}

	@Test
	public void addClientTest() throws RemoteException
	{
		obj.addClient("Testeur");
		assertNotNull(DataMonitor.getClientList().contains("Testeur"));
	}
	@Test
	public void removeClientTest() throws RemoteException
	{
		//obj.removeClient("Testeur");
		DataMonitor.getClientList().remove("Testeur");
		assertEquals(DataMonitor.getClientList().contains("Testeur"),false);
	}

}
