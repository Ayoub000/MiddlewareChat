package modelFX;

import java.rmi.RemoteException;

import client.Dialogue;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class DataMonitor {

	private static String pseudo;
	private final IntegerProperty new_message;
	private final IntegerProperty new_client;
	private static Dialogue dial;

	public DataMonitor()
	{
		new_message = new SimpleIntegerProperty();
		new_client = new SimpleIntegerProperty();
	}

	public static Dialogue getDial() {
		return dial;
	}

	public static void setDial(Dialogue dial) {
		DataMonitor.dial = dial;
	}

	public void setNewClient() {
		  try {
			new_client.set(dial.getNewClient());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void setNewMessage() {
		  try {
			new_message.set(dial.getNewMessage());
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
