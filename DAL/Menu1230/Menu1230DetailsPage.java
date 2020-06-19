package DAL.Menu1230;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import DAL.BasePage;
import DAL.KaszperPage;
import Model.AfaKulcs;

public class Menu1230DetailsPage extends KaszperPage{

	public Menu1230DetailsPage(BasePage source) {
		super(source);
	}
	
	private static String konyvelesiEsemenyDatumaCss= "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(13) "
			+ "> span:nth-child(2) > span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > "
			+ "tr:nth-child(5) > td:nth-child(1) > span:nth-child(1) > span:nth-child(1) > table:nth-child(1) > "
			+ "tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > "
			+ "tr:nth-child(1) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(9) > "
			+ "td:nth-child(2) > span:nth-child(1) > input:nth-child(1)";
	
	public void setKonyvelesiEsemenyDatuma(String datum) {
		getElementByCss(konyvelesiEsemenyDatumaCss).sendKeys(datum);
		sleep(0.1f);
	}
	
	public String getKonyvelesiEsemenyDatum() {
		return getElementByCss(konyvelesiEsemenyDatumaCss).getText();
	}
	
	String kontirtetelsumcss = "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(10) > span:nth-child(2) > "
			+ "span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(5) > "
			+ "td:nth-child(1) > span:nth-child(1) > span:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > "
			+ "tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > "
			+ "td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(19) > td:nth-child(2) > "
			+ "span:nth-child(1) > input:nth-child(1)";
	
	public 	void setKontirTetelSum(float sum) {
		WebElement sumfield = getElementByCss(kontirtetelsumcss);
		sumfield.clear();
		sleep(0.1f);
		sumfield.sendKeys(Float.toString(sum));
		sleep(0.1f);
	}
	
	public float getSzamlaTetelSum(int rownum) {
		String css = "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(10) > span:nth-child(2) > "
				+ "span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > "
				+ "td:nth-child(1) > span:nth-child(1) > span:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > "
				+ "tr:nth-child(3) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > "
				+ "td:nth-child(1) > span:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > "
				+ "td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(20) > td:nth-child(1) > "
				+ "table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > table:nth-child(1) > "
				+ "tbody:nth-child(1) > tr:nth-child("+(rownum+2)+") > td:nth-child(7)";
		return Float.parseFloat(getElementByCss(css).getText().replaceAll(",", ".").replaceAll(" ", ""));
		
	}
	
	public AfaKulcs getAfaKulcs(int rowindex) {
		String css = "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(10) > span:nth-child(2) > "
				+ "span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) "
				+ "> td:nth-child(1) > span:nth-child(1) > span:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) "
				+ "> tr:nth-child(3) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > "
				+ "td:nth-child(1) > span:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > "
				+ "td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(20) > td:nth-child(1) > "
				+ "table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > table:nth-child(1) > "
				+ "tbody:nth-child(1) > tr:nth-child("+(rowindex+2)+") > td:nth-child(9)";
		return AfaKulcs.of(getElementByCss(css).getText());
	}
	public int getNumberOfAltetelek() {
		String css = "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(10) > span:nth-child(2) > "
				+ "span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) "
				+ "> td:nth-child(1) > span:nth-child(1) > span:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) "
				+ "> tr:nth-child(3) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > "
				+ "td:nth-child(1) > span:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > "
				+ "td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(20) > td:nth-child(1) > "
				+ "table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > table:nth-child(1) > "
				+ "tbody:nth-child(1) > *";
		return getNumOfOccurances(css)-1;
	}
	
	public Menu1230ListPage clickBefejezesButton() {
		String css = "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(10) > span:nth-child(2) > "
				+ "span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(5) > "
				+ "td:nth-child(1) > span:nth-child(1) > span:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > "
				+ "tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > "
				+ "td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(22) > td:nth-child(1) > "
				+ "span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > "
				+ "td:nth-child(2) > span:nth-child(1) > input:nth-child(1)";
		getElementByCss(css).click();
		waitForLoading();
		return new Menu1230ListPage(this);
	}
	
	public float getKontirTetelOsszeg(int rownum) {
		String css = "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(10) > span:nth-child(2) > "
				+ "span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(6) > "
				+ "td:nth-child(1) > span:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > "
				+ "td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > "
				+ "table:nth-child(1) > tbody:nth-child(1) > tr:nth-child("+(rownum+2)+") > td:nth-child(15)";
		return Float.parseFloat(getElementByCss(css).getText().replaceAll(",", ".").replaceAll(" ", ""));
		
	}
	public AfaKulcs getKontirTetelKulcs(int rownum) {
		String css = "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(10) > span:nth-child(2) > "
				+ "span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(6) > "
				+ "td:nth-child(1) > span:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > "
				+ "td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > "
				+ "table:nth-child(1) > tbody:nth-child(1) > tr:nth-child("+(rownum+2)+") > td:nth-child(7)";
		return AfaKulcs.of(getElementByCss(css).getText());
	}
	public int getNumOfKontirTetelek() {
		String css = "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(10) > span:nth-child(2) > "
				+ "span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(6) > "
				+ "td:nth-child(1) > span:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > "
				+ "td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > "
				+ "table:nth-child(1) > tbody:nth-child(1) > *";
		return getNumOfOccurances(css)-1;
	}
	
	public void modositKontirTetel(int rownum) {
		String css = "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(10) > span:nth-child(2) > "
				+ "span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(6) > "
				+ "td:nth-child(1) > span:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > "
				+ "td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > "
				+ "table:nth-child(1) > tbody:nth-child(1) > tr:nth-child("+(rownum+2)+") > td:nth-child(1) > span:nth-child(1) > "
				+ "input:nth-child(1)";
		getElementByCss(css).click();
		waitForLoading();
	}
	
	public void hozzaadKontirTetel() {
		String css = "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(10) > span:nth-child(2) > "
				+ "span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(5) > "
				+ "td:nth-child(1) > span:nth-child(1) > span:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > "
				+ "tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > "
				+ "td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(22) > td:nth-child(1) > "
				+ "span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > "
				+ "td:nth-child(1) > span:nth-child(1) > input:nth-child(1)";
		getElementByCss(css).click();
		waitForLoading();
		
	}
	public void deleteKontirTetel(int rownum) {
		String css ="body > main:nth-child(4) > center:nth-child(1) > form:nth-child(10) > span:nth-child(2) > "
				+ "span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(6) > "
				+ "td:nth-child(1) > span:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > "
				+ "td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > "
				+ "table:nth-child(1) > tbody:nth-child(1) > tr:nth-child("+(2+rownum)+") > td:nth-child(1) > span:nth-child(2) >"
				+ " input:nth-child(1)";
		getElementByCss(css).click();
		waitForLoading(5);
	}
	
	public String getTeljesitesDatuma() {
		String css = "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(13) > span:nth-child(2) "
				+ "> span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > "
				+ "tr:nth-child(2) > td:nth-child(1) > span:nth-child(1) > span:nth-child(1) > "
				+ "table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > "
				+ "table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > "
				+ "span:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > "
				+ "td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(4) > "
				+ "td:nth-child(2)";
		return getElementByCss(css).getText().replaceAll("-", "");
	}
	
	public KontirChooserPage enterKontirChooserMenu() {
		String css = "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(10) > span:nth-child(2) > "
				+ "span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(5) > "
				+ "td:nth-child(1) > span:nth-child(1) > span:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) >"
				+ " tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > "
				+ "td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(2) > "
				+ "span:nth-child(1) > span:nth-child(1) > input:nth-child(1)";
		getElementByCss(css).click();
		waitForLoading();
		sleep(0.2f);
		return new KontirChooserPage(this);
	}
	
	public void selectAfaKulcs(String kulcs) {
		String css = "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(10) > span:nth-child(2) "
				+ "> span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > "
				+ "tr:nth-child(5) > td:nth-child(1) > span:nth-child(1) > span:nth-child(1) > "
				+ "table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > "
				+ "table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > "
				+ "table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(11) > td:nth-child(2) > "
				+ "span:nth-child(1) > select:nth-child(1)";
		
		Select selectbox = new Select(getElementByCss(css));
		selectbox.selectByVisibleText(kulcs);
	}
}
