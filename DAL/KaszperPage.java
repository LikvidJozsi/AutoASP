package DAL;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class KaszperPage {
	
	private WebDriver driver;
	
	public KaszperPage(String loginname,String password) {

		openWindow();
		// Fooldal megnyitasa
		goToWebSite("https://asp.lgov.hu");
		//belepesi adatok megadasa
		setLoginData(loginname, password);
		//belepesre kattintas
		driver.findElement(By.id("signinbutton")).click();
		waitForLoading(5);
		//eszig azonositas kivalasztasa
		driver.findElement(By.id("eSzig")).click();
		waitForLoading(5);
		// neha hatarozatlan hibat dob ha ez nincs itt
		sleep(1);
		String eszigbuttonxpath = "/html/body/div[1]/div[2]/div[2]/div/div/div/div/fieldset/form/div/div[3]/div/div[3]/button";
		//megint rakattintunk az masik azonositas gombra
		driver.findElement(By.xpath(eszigbuttonxpath)).click();
		waitForLoading(30);
		// a fontos üznet kiixelése ha van
		String xbuttoncss = "#high-prior-msg-modal > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > button:nth-child(1)";
		sleep(0.5f);
		if(this.getNumOfOccurances(xbuttoncss)!=0) {
			waitUntilElementisClickable(By.cssSelector(xbuttoncss));
			clickElement(xbuttoncss);
		}
		//gazdalkodas gombra kattintas
		String gazdalkodascss = "div.container-fluid:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)";
		waitUntilElementisClickable(By.cssSelector(gazdalkodascss));
		driver.findElement(By.cssSelector(gazdalkodascss)).click();
		
		waitForLoading(10);
		
		sleep(1);
		// switch to the window we opened close the other
		switchToWindowAndCloseOthers("gazd");
		
		//kaszper gombra kattintas
		String kaszperxpath = "/html/body/main/center/center/table/tbody/tr[2]/td[1]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[4]/td/ul/table/tbody/tr[2]/td/font/a";
		driver.findElement(By.xpath(kaszperxpath)).click();
		
		waitForLoading(10);
	}
	
	private void goToWebSite(String url) {
		driver.get(url);
	}
	
	
	private void setLoginData(String loginname,String password) {
		driver.findElement(By.id("UserName")).sendKeys(loginname);
		driver.findElement(By.id("Password")).sendKeys(password);
	}
	
	@SuppressWarnings("deprecation")
	private void openWindow() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\bduvi\\Desktop\\webscraping\\geckodriver.exe");
		File file = new File("C:\\Users\\bduvi\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\epcaih5m.Selenium");
	    DesiredCapabilities dc = DesiredCapabilities.firefox();
	    FirefoxProfile profile = new FirefoxProfile(file);
	    dc.setCapability(FirefoxDriver.PROFILE, profile);
	    driver = new FirefoxDriver(dc);
	}
	
	protected void selectMenu(String menuname) {
		//switch to the header part of the screen
		driver.switchTo().defaultContent();
		driver.switchTo().frame("navig");
		//wait for stuff to load
		waitUntilElementisVisible(By.id("1"));
		sleep(1.5f);
		driver.findElement(By.id("1")).sendKeys(menuname+"\n");
		waitForLoading(10);
		//switch back to the main frame of the screen
		driver.switchTo().defaultContent();
		driver.switchTo().frame("main");
	}
	
	
	
	
	protected KaszperPage(WebDriver driver) {
		this.driver = driver;
	}
	
	protected WebElement getElementByCss(String selector) {
		return getDriver().findElement(By.cssSelector(selector));
	}
	
	protected KaszperPage(KaszperPage source,String menuname) {
		this.driver = source.getDriver();
		source.leavingPage();
		selectMenu(menuname);
	}
	
	protected WebDriver getDriver() {
		if(driver != null)
			return driver;
		else
			throw new RuntimeException(new Exception("Already left the menu!"));
	}
	protected void leavingPage() {
		driver = null;
	}
	
	private void switchToWindowAndCloseOthers(String destinationurlpart) {
		for(String currentwindow: driver.getWindowHandles()) {
			driver.switchTo().window(currentwindow);
			if(driver.getCurrentUrl().contains(destinationurlpart)){
				break;
			}else {
				driver.close();
			}
		}
	}
	
	protected void waitForLoading(int timeoutsecs) {
		driver.manage().timeouts().implicitlyWait(timeoutsecs, TimeUnit.SECONDS);
		//debug
		sleep(1);
	}
	protected void waitForLoading() {
		waitForLoading(60);
	}
	protected void waitUntilElementisVisible(By selector) {
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(selector));
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
		 getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);  
		 int present = getDriver().findElements(By.cssSelector(cssselector)).size();
		 getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
		 return present;
	}
	protected void acceptAlert() {
		new WebDriverWait(getDriver(), 10).until(ExpectedConditions.alertIsPresent());
		getDriver().switchTo().alert().accept();
	}
	
}
