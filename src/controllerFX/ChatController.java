package controllerFX;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import client.Client;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import modelFX.DataMonitor;


public class ChatController implements Initializable {

	@FXML
	private Button disconnectButton;
	@FXML
	private Button sendButton;
	@FXML
	private ListView<String> messageList;
	@FXML
	private ListView<String> clientList;
	@FXML
	private TextField messageInput;

	private String clientChoisi;
	private DataMonitor mapMonitor;
	private Timeline timeline;

	public ChatController()
	{
		mapMonitor = new DataMonitor();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		infiniteTimer();
		clientList.setItems(refresh_clients());
		messageList.setItems(refresh_messages());
		mapMonitor.NewMessageProperty().addListener((ObservableValue<? extends Number > observable, Number oldValue, Number newValue) -> {
			messageList.setItems(refresh_messages());
		});
		mapMonitor.NewClientProperty().addListener((ObservableValue<? extends Number > observable, Number oldValue, Number newValue) -> {
			clientList.setItems(refresh_clients());
		});
	}

	public void disconnectAction() throws Exception{
		Stage secondStage = (Stage) disconnectButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("../viewFX/Connection.fxml"));
		Scene scene = new Scene(root);
		secondStage.setScene(scene);
		secondStage.show();
		Client.getMyConnection().disconnect(DataMonitor.getPseudo());
	}

	public void clientAction()
	{
		try
		{
			clientChoisi=clientList.getSelectionModel().getSelectedItem().toString();
		}
		catch (NullPointerException e)
		{
			System.err.println("Please Select a valid client !");
		}
	}

	public void sendAction() throws RemoteException
	{
		String message = messageInput.getText();
		messageInput.clear();
		DataMonitor.getDial().sendMessage(clientChoisi, message);
		ObservableList<String> messages;
		try {
			messages = FXCollections.observableArrayList(DataMonitor.getDial().getMessages(DataMonitor.getPseudo()));
			messageList.setItems(messages);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private ObservableList<String> refresh_clients()
	{
		List<String> list = new  ArrayList<String>();
		try {
			for(Object c : DataMonitor.getDial().getClients().keySet())
			{
				String client = (String)c;
				if(!client.equals(DataMonitor.getPseudo()))
				{
					list.add(client);
				}
				else
				{
					list.add(client+" - Moi");
				}
			}
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}
		ObservableList<String> items=FXCollections.observableArrayList(list);
		return items;
	}
	private ObservableList<String> refresh_messages()
	{
		ObservableList<String> messages = null;
		try {
			messages = FXCollections.observableArrayList(DataMonitor.getDial().getMessages(DataMonitor.getPseudo()));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return messages;
	}
	private void infiniteTimer()
	{
		timeline = new Timeline(new KeyFrame(Duration.seconds(1), evt -> {
			mapMonitor.setNewMessage();mapMonitor.setNewClient();
		}));
	    timeline.setCycleCount(Animation.INDEFINITE);
	    timeline.play();
	}

}
