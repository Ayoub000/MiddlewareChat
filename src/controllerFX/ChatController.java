package controllerFX;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import client.Client;
import client.Informations;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class ChatController implements Initializable {

	@FXML
	private AnchorPane anchor;
	@FXML
	private Button disconnectButton;
	@FXML
	private ListView<String> messageList;
	@FXML
	private ListView<String> listClients;
	@FXML
	private TextField messageInput;
	@FXML
	private Button sendButton;

	private String clientChoisi;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		List<String> list = new  ArrayList<String>();
		try {
			for(Object c : Client.getMyComponent().getClients().keySet())
			{
				String client = (String)c;
				list.add(client);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ObservableList<String> items=FXCollections.observableArrayList(list);
		listClients.setItems(items);
		try {

			ObservableList<String> messages=FXCollections.observableArrayList(Client.getMyComponent().getMessages(Informations.getPseudo()));
			messageList.setItems(messages);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public void disconnectAction() throws Exception{
		Stage secondStage = (Stage) disconnectButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("../viewFX/Connection.fxml"));
		Scene scene = new Scene(root);
		secondStage.setScene(scene);
		secondStage.show();
		Client.getMyComponent().disconnect(Informations.getPseudo());
	}

	public void clientAction()
	{
		clientChoisi=listClients.getSelectionModel().getSelectedItem().toString();

	}

	public void sendAction() throws RemoteException
	{
		String message = messageInput.getText();
		Client.getMyComponent().sendMessage(Informations.getPseudo(), clientChoisi, message);
		ObservableList<String> messages;
		try {
			messages = FXCollections.observableArrayList(Client.getMyComponent().getMessages(Informations.getPseudo()));
			System.out.println(messages);
			messageList.setItems(messages);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void newMessageAction()
	{


	}





}
