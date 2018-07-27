package Controller;

import Controller.Tasks.InstitutionChooserTask;
import Controller.Tasks.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class InstitutionChooserTaskFormController extends TaskFormController{
	
	@FXML
	TextField name;

	@Override
	public Task getTask() {
		InstitutionChooserTask task = new InstitutionChooserTask();
		task.setInstitutionToChoose(name.getText());
		return task;
	}

}
