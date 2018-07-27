package Model;

import Controller.TaskFormController;
import Controller.Tasks.Task;

public class TaskComboboxItem {
	private String label;
	private String taskformpath;
	
	public TaskComboboxItem(String label, String taskformpath) {
		super();
		this.label = label;
		this.taskformpath = taskformpath;
	}

	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}

	public String getTaskFormPath() {
		return taskformpath;
	}

	@Override
	public String toString() {
		return label;
	}
}
