package DAL;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Special page that does not conform to the behaviour of other pages, since it is a combination of 
 * pages. This is so because login is done through many pages with usually one-two interactions on each, 
 * and all the small classes would only clutter the project otherwise. Accordingly not all functions may be called
 * at any time.
 * @author Bánóczi Dávid
 *
 */

public class LoginPage extends BasePage{

	public LoginPage() {
		super(openWindow());
	}
	
	
	/**
	 * It is static so it may be called before the parent constructor was called.
	 * @return A firefox window with the homepage open
	 */
	@SuppressWarnings("deprecation")
	private static WebDriver openWindow() {
		System.setProperty("webgetDriver().gecko.getDriver()", "C:\\Users\\bduvi\\Desktop\\webscraping\\geckogetDriver().exe");
		File file = new File("C:\\Users\\bduvi\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\epcaih5m.Selenium");
		DesiredCapabilities dc = DesiredCapabilities.firefox();
		FirefoxProfile profile = new FirefoxProfile(file);
		dc.setCapability(FirefoxDriver.PROFILE, profile);
		return new FirefoxDriver(dc);
	}


	private String xButtonCss = "#high-prior-msg-modal > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > button:nth-child(1)";

	public void clickKaszperButton() {
		String kaszperxpath = "/html/body/main/center/center/table/tbody/tr[2]/td[1]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[4]/td/ul/table/tbody/tr[2]/td/font/a";
		getDriver().findElement(By.xpath(kaszperxpath)).click();
		waitForLoading();
	}
	
	public boolean thereIsPopup() {
		return getNumOfOccurances(xButtonCss)!=0;
	}

	public void closeMessagePopup() {
		waitUntilElementisClickable(By.cssSelector(xButtonCss));
		clickElement(xButtonCss);
	}

	public void clickAuthenticateButton() {
		String eszigbuttonxpath = "/html/body/div[1]/div[2]/div[2]/div/div/div/div/fieldset/form/div/"
				+ "div[3]/div/div[3]/button";
		getDriver().findElement(By.xpath(eszigbuttonxpath)).click();
		waitForLoading();
		// it sometimes dies with unexpected error if this is not here
		sleep(1);
	}

	public void selectEszigAuthentication() {
		getDriver().findElement(By.id("eSzig")).click();
		waitForLoading(5);
	}


	public void clickSignInButton() {
		getDriver().findElement(By.id("signinbutton")).click();
		waitForLoading();
	}

	public void goToWebSite(String url) {
		getDriver().get(url);
	}

	public void setLoginData(String loginname,String password) {
		getDriver().findElement(By.id("UserName")).sendKeys(loginname);
		getDriver().findElement(By.id("Password")).sendKeys(password);
	}

	public void switchToWindowAndCloseOthers(String destinationurlpart) {
		for(String currentwindow: getDriver().getWindowHandles()) {
			getDriver().switchTo().window(currentwindow);
			if(getDriver().getCurrentUrl().contains(destinationurlpart)){
				break;
			}else {
				getDriver().close();
			}
		}
	}
	
	public void clickGazdalkodasButton() {
		String gazdalkodascss = "div.container-fluid:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)";
		waitUntilElementisClickable(By.cssSelector(gazdalkodascss));
		getElementByCss(gazdalkodascss).click();
		waitForLoading();
		sleep(1);
	}
}
