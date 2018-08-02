package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Controller.Tasks.Task;
import Model.KontirSor;
import Model.TaskComboboxItem;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class NewTaskController implements Initializable{
	
	@FXML
	ComboBox<TaskComboboxItem> taskChooser;
	
	@FXML
	Pane formPane;
	
	private TaskFormController taskFormController; 
	private FrameController framecontroller;

	public void setFramecontroller(FrameController framecontroller) {
		this.framecontroller = framecontroller;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		taskChooser.getItems().add(new TaskComboboxItem("91 - Igazolás&Véglegesítés","/View/AcceptAndFinalizeForm.fxml"));
		taskChooser.getItems().add(new TaskComboboxItem("1230 - Kontírozás","/View/Kontirozas1230TaskForm.fxml"));
		taskChooser.getItems().add(new TaskComboboxItem("Intézmény választás","/View/InstitutionChooserTaskForm.fxml"));
		//TODO empty form letrehozasa (lehet nem is kell mert lehetetlen selectelni)
		taskChooser.getSelectionModel().select(new TaskComboboxItem("",""));
	}
	
	@FXML
	public void chooseEventHandler() {
		try {
			String taskFormPath =  taskChooser.getValue().getTaskFormPath();
			final FXMLLoader loader = new FXMLLoader(getClass().getResource(taskFormPath));
			final GridPane viewRoot = (GridPane) loader.load();
			taskFormController = loader.getController();
			formPane.getChildren().clear();
			formPane.getChildren().add(viewRoot);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void startEventHandler() {
		Task taskToRun = taskFormController.getTask();
		framecontroller.executeTask(taskToRun);
	}

}
