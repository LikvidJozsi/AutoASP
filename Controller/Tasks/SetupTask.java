package Controller.Tasks;

import DAL.InstitutionChooserMenu;
import DAL.KaszperPage;
import DAL.LoginPage;

public class SetupTask extends Task{
	private String userName;
	private String password;

	public SetupTask() {
	}

	@Override
	public KaszperPage execute() {
		LoginPage loginPage = new LoginPage();

		loginPage.goToWebSite("https://asp.lgov.hu");
		loginPage.setLoginData(userName, password);
		loginPage.clickSignInButton();
		loginPage.selectEszigAuthentication();
		loginPage.clickAuthenticateButton();

		if(loginPage.thereIsPopup()) 
			loginPage.closeMessagePopup();

		loginPage.clickGazdalkodasButton();

		// gazdalkodas page opens in a new window, the other is closed
		loginPage.switchToWindowAndCloseOthers("gazd");

		loginPage.clickKaszperButton();

		return new KaszperPage(loginPage);
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
