package controllerFX;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
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
	private DataMonitor dataMonitor;
	private Timeline timeline;

	public ChatController()
	{
		dataMonitor = new DataMonitor();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		infiniteTimer();
		clientList.setItems(refresh_clients());
		try {
			messageList.setItems(refresh_messages());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataMonitor.NewMessageProperty().addListener((ObservableValue<? extends Number > observable, Number oldValue, Number newValue) -> {
			try {
				messageList.setItems(refresh_messages());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		dataMonitor.NewClientProperty().addListener((ObservableValue<? extends Number > observable, Number oldValue, Number newValue) -> {
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
			clientChoisi = clientChoisi.replace(" - Moi", "");
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
		DataMonitor.getEmitter().sendMessage(clientChoisi, message);
		ObservableList<String> messages;
		messages = FXCollections.observableArrayList(DataMonitor.getMessages());
		messageList.setItems(messages);
	}

	private ObservableList<String> refresh_clients()
	{
		List<String> list = new  ArrayList<String>();
		for(Object c : DataMonitor.getClientList())
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
		ObservableList<String> items=FXCollections.observableArrayList(list);
		return items;
	}
	private ObservableList<String> refresh_messages() throws RemoteException
	{
		ObservableList<String> messages = null;
		List<String> list = new  ArrayList<String>();
		Iterator<String> it = DataMonitor.getMessages().iterator();
		while(it.hasNext())
		{
			list.add(it.next());
		}
		messages = FXCollections.observableArrayList(DataMonitor.getMessages());
		return messages;
	}
	private void infiniteTimer()
	{
		timeline = new Timeline(new KeyFrame(Duration.seconds(1), evt -> {
			dataMonitor.setNewMessage();dataMonitor.setNewClient();
		}));
	    timeline.setCycleCount(Animation.INDEFINITE);
	    timeline.play();
	}

}
