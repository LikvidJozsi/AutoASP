package DAL;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
	private WebDriver driver;

	
	public BasePage(BasePage source) {
		this(source.getDriver());
		source.leavingPage();
	}
	public BasePage(WebDriver driver) {
		this.driver=driver;
	}

	protected void leavingPage() {
		this.driver = null;
	}

	protected WebDriver getDriver() {
		if(driver != null)
			return driver;
		else
			throw new RuntimeException(new Exception("Already left the menu!"));
	}
	
	public void close() {
		driver.close();
	}
	
	protected WebElement getElementByCss(String selector) {
		return getDriver().findElement(By.cssSelector(selector));
	}
	
	protected void waitForLoading(int timeoutsecs) {
		getDriver().manage().timeouts().implicitlyWait(timeoutsecs, TimeUnit.SECONDS);
		//debug
		sleep(1);
	}
	protected void waitForLoading() {
		waitForLoading(60);
	}
	protected void waitUntilElementisVisible(By selector) {
		new WebDriverWait(getDriver(), 60).until(ExpectedConditions.visibilityOfElementLocated(selector));
	}
	protected void waitUntilElementisClickable(By selector) {
		new WebDriverWait(getDriver(), 60).until(ExpectedConditions.elementToBeClickable(selector));
	}
	protected void clickElement(String cssselector) {
		getDriver().findElement(By.cssSelector(cssselector)).click();
	}
	
	public void sleep(float secs) {
		try {
			Thread.sleep((long)secs*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected int getNumOfOccurances(String cssselector) {
		 setTimeout(2);
		 int present = getDriver().findElements(By.cssSelector(cssselector)).size();
		 setTimeout(30);
		 return present;
	}
	protected void acceptAlert() {
		new WebDriverWait(getDriver(), 10).until(ExpectedConditions.alertIsPresent());
		getDriver().switchTo().alert().accept();
	}
	
	public void setTimeout(int secs) {
		getDriver().manage().timeouts().implicitlyWait(secs, TimeUnit.SECONDS);  
	}
}
