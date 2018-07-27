package Controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import Controller.Tasks.SetupTask;
import Controller.Tasks.Task;
import DAL.KaszperPage;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import javafx.util.StringConverter;

public class FrameController implements Initializable{
	@FXML
	TextField usernameField;
	@FXML
	TextField passwordField;
	@FXML
	Button connectButton;
	@FXML
	Label connectionStateLabel;
	@FXML
	NewTaskController newtasktabController;
	
	KaszperPage currentpage;
	

	public FrameController() {
		
	}

	@FXML
	public void connectEventHandler(ActionEvent event) {
		// Getting the input from the UI.
		String userName = usernameField.getText();
		String password = passwordField.getText();
		SetupTask setup = new SetupTask();
		setup.setUserName(userName);
		setup.setPassword(password);
		currentpage = setup.execute();
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//TODO only for testing default user data
		usernameField.setText("szucs.judit@tiszanana");
		passwordField.setText("457JUdit");
		newtasktabController.setFramecontroller(this);
		
		//tipusComboBox.getItems().add(new ComboBoxItem<String>("Hang", "hang"));
		// egy �res �rt�ket �ll�tunk be defaultnak
		//tipusComboBox.getSelectionModel().select(new ComboBoxItem<>("","" ));

		//dateColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
		
		/*StringConverter converter = new StringConverter<LocalDate>() {
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			@Override
			public String toString(LocalDate date) {
				if (date != null) {
					return dateFormatter.format(date);
				} else {
					return "";
				}
			}

			@Override
			public LocalDate fromString(String string) {
				if (string != null && !string.isEmpty()) {
					return LocalDate.parse(string, dateFormatter);
				} else {
					return null;
				}
			}
		};
		vasarlasDatePicker.setConverter(converter);
		vasarlasDatePicker.setPromptText("eeee-hh-nn");*/

	}
	
	public void executeTask(Task task) {
		task.setStartingPage(currentpage);
		currentpage = task.execute();
	}

	public void disconnect() {
	}
}
