package Controller;

import Controller.Tasks.Task;
import DAL.InstitutionChooserMenu;
import DAL.KaszperPage;

public class SetupTask extends Task{
	String username;
	String password;

	public SetupTask(String username,String password) {
		// There is no starting page before logging in
		super(null);
		this.username = username;
		this.password = password;
	}

	@Override
	public KaszperPage execute() {
		KaszperPage startingpage = new KaszperPage(username, password);
		return startingpage;
	}

}
