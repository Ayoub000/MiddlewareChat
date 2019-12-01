package modelFX;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import client.Emitter;
import client.ReceiverImpl;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import server.Receiver;

public class DataMonitor {

	private static List<String> clientList = new ArrayList<String>();
	private static List<String> messages = new ArrayList<String>();

	private static Emitter emitter;
	private static Receiver receiver;
	private static String pseudo;

	private final IntegerProperty new_message = new SimpleIntegerProperty();
	private final IntegerProperty new_client = new SimpleIntegerProperty();

	public static List<String> getMessages() {
		return messages;
	}
	public static List<String> getClientList() {
		return clientList;
	}

	public static void setClientList(List<String> clientList) {
		DataMonitor.clientList = clientList;
	}

	public static void setReceiver(Receiver receiver) {
		DataMonitor.receiver = receiver;
	}

	public static Receiver getReceiver() throws RemoteException {
		DataMonitor.receiver = new ReceiverImpl();
		return receiver;
	}
	public static Emitter getEmitter() {
		return emitter;
	}

	public static void setEmitter(Emitter emiter) {
		emitter = emiter;
	}

	public void setNewClient() {
		  try {
			int temp = emitter.getNew_client();
			new_client.set(temp);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void setNewMessage() {
		  try {
			int temp = emitter.getNew_message();
			new_message.set(temp);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public final IntegerProperty NewMessageProperty() {
		return new_message;
	}

	public IntegerProperty NewClientProperty() {
		return new_client;
	}

	public static String getPseudo() {
		return pseudo;
	}

	public static void setPseudo(String pseudo) {
		DataMonitor.pseudo = pseudo;
	}

}
