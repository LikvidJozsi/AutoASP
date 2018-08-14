package Controller.Tasks;

import DAL.KaszperPage;

public abstract class Task {
	
	public KaszperPage startingpage;
	
	public Task() {}
	
	
	public Task(KaszperPage startingpage) {
		this.startingpage = startingpage;
	}
	
	public void setStartingPage(KaszperPage startingpage) {
		this.startingpage = startingpage;
	}

	public abstract KaszperPage execute();
}
