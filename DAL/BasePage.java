package DAL;

//The generic page the you arrive on after choosing an institution
public class BasePage extends KaszperPage{
	
	
	
	public BasePage(String loginname, String password) {
		super(loginname, password);
	}
	public BasePage(KaszperPage source) {
		// No switching, assumes you created the instance because you are unintentionally already on this page
		super(source.getDriver());
		source.leavingPage();
	}

}
