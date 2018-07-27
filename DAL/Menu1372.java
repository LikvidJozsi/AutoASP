package DAL;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Menu1372 extends KaszperPage {

	public Menu1372(KaszperPage source) {
		super(source, "1372");
	}

	public void setDateFilter(String filter) {
		String filterxpath = "/html/body/main/center/form/span[1]/span/div/table/tbody/tr/td/table/tbody/tr[2]/td/span/table/tbody/tr[3]/td/table/tbody/tr[1]/td[7]/font/input";
		WebElement filterfield = getDriver().findElement(By.xpath(filterxpath));
		filterfield.clear();
		filterfield.sendKeys(filter);
	}
	
	public void refreshTable() {
		String refreshbuttonxpath = "/html/body/main/center/form/span[1]/span/div/table/tbody/tr/td/table/tbody/tr[2]/td/span/table/tbody/tr[3]/td/table/tbody/tr[2]/td/div/div/table/tbody/tr/td/font[2]/input";
		getDriver().findElement(By.xpath(refreshbuttonxpath)).click();	
		waitForLoading();
	}
	public void sortByBizonylatDateAscending() {
		String sortbuttonxpath="/html/body/main/center/form/span[1]/span/div/table/tbody/tr/td/table/tbody/tr[2]/td/span/table/tbody/tr[3]/td/table/tbody/tr[3]/th[8]/a[2]";
		waitUntilElementisVisible(By.xpath(sortbuttonxpath));
		getDriver().findElement(By.xpath(sortbuttonxpath)).click();
		waitForLoading();
	}
	
	
	public void removeEmptyDateRows() {
		//TODO refactor
		boolean vanbor = true;
		while(vanbor) {
			String datumfieldxpath = "/html/body/main/center/form/span[1]/span/div/table/tbody/tr/td/table/tbody/tr[2]/td/span/table/tbody/tr[3]/td/table/tbody/tr[4]/td[8]/span/nobr";
			String datum = getDriver().findElement(By.xpath(datumfieldxpath)).getText();
			System.out.println("A datum: " +datum);
			//TODO ez nem mukszik
			if(Pattern.matches("\\S", datum)) {
				break;
			}
			String checkboxcss = ".filter-table > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(4)>td>span>nobr>span>input";
			waitUntilElementisClickable(By.cssSelector(checkboxcss));
			sleep(1);
			getDriver().findElement(By.cssSelector(checkboxcss)).click();
			//TODO lehet nem kell
			waitForLoading();
			String movebuttonxpath = "/html/body/main/center/form/span[1]/span/div/table/tbody/tr/td/table/tbody/tr[3]/td/div/div/table/tbody/tr/td/span/div/table/tbody/tr/td[5]/span/input";
			getDriver().findElement(By.xpath(movebuttonxpath)).click();
			acceptAlert();
			getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
	}

}
