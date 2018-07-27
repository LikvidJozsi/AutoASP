package DAL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class KaszperPage extends BasePage{
	
	public KaszperPage(WebDriver driver) {
		super(driver);
	}
	public KaszperPage(BasePage source) {
		super(source);
	}
	
	protected KaszperPage(KaszperPage source,String menuname) {
		super(source);
		selectMenu(menuname);
	}
	
	protected void selectMenu(String menuname) {
		//switch to the header part of the page
		getDriver().switchTo().defaultContent();
		getDriver().switchTo().frame("navig");
		//wait until menu select field is visible
		waitUntilElementisVisible(By.id("1"));
		//for stability reasons? TODO maybe not needed
		sleep(1.5f);
		getDriver().findElement(By.id("1")).sendKeys(menuname+"\n");
		waitForLoading();
		//switch back to the main frame of the page
		getDriver().switchTo().defaultContent();
		getDriver().switchTo().frame("main");
	}
}
