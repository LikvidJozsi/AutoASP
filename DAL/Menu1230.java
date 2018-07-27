package DAL;

import org.openqa.selenium.WebElement;

import DAL.Menu91.Filter;

public class Menu1230 extends KaszperPage{
	
	public Menu1230(KaszperPage source) {super(source,"1230");}
	
	
	public void setFilter(String value,Filter filter){
		String filtercss = "td.filter-table-control:nth-child(" + filter.getValue() + ") > font:nth-child(1) > input:nth-child(1)";
		getElementByCss(filtercss).sendKeys(value);
		sleep(0.1f);
	}
	
	public void clearAllFilters() {
		String css = ".filter-table-pager-top > div:nth-child(1) > div:nth-child(1) > table:nth-child(1) > "
				+ "tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > font:nth-child(6) > "
				+ "input:nth-child(1)";
		getElementByCss(css).click();
		waitForLoading();
	}
	
	public enum Filter{
		BANKPTNAP(10),
		PARTNERNEV(4);
		private int value;
		private Filter(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}
	
	public void applyFilters() {
		String css = "font.default:nth-child(3) > input:nth-child(1)";
		getElementByCss(css).click();
		waitForLoading();
	}
	
	public int getNumberofRowsOnListPage() {
		String rowcss = ".filter-table > tbody:nth-child(1) > tr:nth-child(3) > "
				+ "td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > * ";
		return getNumOfOccurances(rowcss)-4;
	}
	
	
	public boolean selectNextPage() {
		String nextpagecss = ".filter-table-pager-bottom > div:nth-child(1) > div:nth-child(1) > table:nth-child(1) "
				+ "> tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > font:nth-child(6) > input:nth-child(1)";
		
		WebElement nextpagebutton = getElementByCss(nextpagecss);
		if(nextpagebutton.isEnabled()) {
			nextpagebutton.click();
			waitForLoading();
			return true;
		}
		return false;
	}
	
	public void tickRecord(int rowindex) {
		String css = ".filter-table > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > table:nth-child(1) "
				+ "> tbody:nth-child(1) > tr:nth-child("+(rowindex+4)+") > td:nth-child(1) > span:nth-child(1) > nobr:nth-child(1) "
				+ "> span:nth-child(1) > input:nth-child(1)";
		getElementByCss(css).click();
		sleep(0.1f);
	}
	
	public String getBankPenztarDatum(int rowindex) {
		String css = ".filter-table > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > table:nth-child(1) > "
				+ "tbody:nth-child(1) > tr:nth-child("+(rowindex+4)+") > td:nth-child(10) > span:nth-child(1) > nobr:nth-child(1)";
		return getElementByCss(css).getText();
	}
	
	public void enterDetailsPage(int rowindex) {
		String css = ".filter-table > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > table:nth-child(1) > "
				+ "tbody:nth-child(1) > tr:nth-child("+(rowindex+4)+") > td:nth-child(2) > span:nth-child(1) > span:nth-child(1) > "
				+ "a:nth-child(2)";
		getElementByCss(css).click();
		waitForLoading();
	}
	
	public String getId(int rowindex) {
		String css = ".filter-table > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > table:nth-child(1) > "
				+ "tbody:nth-child(1) > tr:nth-child("+(rowindex+4)+") > td:nth-child(2) > span:nth-child(1) > span:nth-child(1) > "
				+ "a:nth-child(2) > nobr:nth-child(1)";
		return getElementByCss(css).getText();
	}
	
	public void IgazolEsVeglegesit() {
		String css = "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(10) > span:nth-child(2) "
				+ "> span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) "
				+ "> td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > "
				+ "div:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) >"
				+ " td:nth-child(1) > span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > "
				+ "tr:nth-child(1) > td:nth-child(4) > span:nth-child(1) > input:nth-child(1)";
		getElementByCss(css).click();
		waitForLoading();
	}
	
	public String getMegjegyzes(int rownum) {
		String css = ".filter-table > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > "
				+ "table:nth-child(1) > tbody:nth-child(1) > tr:nth-child("+(rownum+4)+") > "
						+ "td:nth-child(18) > span:nth-child(1) > nobr:nth-child(1)";
		return getElementByCss(css).getText();
	}
	
	
	//Details menu
	
	public String getTeljesitesDatuma() {
		String css = "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(10) > span:nth-child(2) "
				+ "> span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > "
				+ "tr:nth-child(2) > td:nth-child(1) > span:nth-child(1) > span:nth-child(1) > "
				+ "table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > "
				+ "table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > "
				+ "span:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > "
				+ "td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(4) > "
				+ "td:nth-child(2)";
		return getElementByCss(css).getText().replaceAll("-", "");
	}
	
	public void enterKontirChooserMenu() {
		String css = "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(10) > span:nth-child(2) > "
				+ "span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(5) > "
				+ "td:nth-child(1) > span:nth-child(1) > span:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) >"
				+ " tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > "
				+ "td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(2) > "
				+ "span:nth-child(1) > span:nth-child(1) > input:nth-child(1)";
		getElementByCss(css).click();
		waitForLoading();
		sleep(0.2f);
		
	}
	
	private static String konyvelesiEsemenyDatumaCss= "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(10) "
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
	
	public String getAfaKulcs(int rowindex) {
		String css = "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(10) > span:nth-child(2) > "
				+ "span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) "
				+ "> td:nth-child(1) > span:nth-child(1) > span:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) "
				+ "> tr:nth-child(3) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > "
				+ "td:nth-child(1) > span:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > "
				+ "td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(20) > td:nth-child(1) > "
				+ "table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > table:nth-child(1) > "
				+ "tbody:nth-child(1) > tr:nth-child("+(rowindex+2)+") > td:nth-child(9)";
		return getElementByCss(css).getText();
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
	
	public void clickBefejezesButton() {
		String css = "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(10) > span:nth-child(2) > "
				+ "span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(5) > "
				+ "td:nth-child(1) > span:nth-child(1) > span:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > "
				+ "tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > "
				+ "td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(22) > td:nth-child(1) > "
				+ "span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > "
				+ "td:nth-child(2) > span:nth-child(1) > input:nth-child(1)";
		getElementByCss(css).click();
		waitForLoading();	
	}
	
	public float getKontirTetelOsszeg(int rownum) {
		String css = "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(10) > span:nth-child(2) > "
				+ "span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(6) > "
				+ "td:nth-child(1) > span:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > "
				+ "td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > "
				+ "table:nth-child(1) > tbody:nth-child(1) > tr:nth-child("+(rownum+2)+") > td:nth-child(15)";
		return Float.parseFloat(getElementByCss(css).getText().replaceAll(",", ".").replaceAll(" ", ""));
		
	}
	public String getKontirTetelKulcs(int rownum) {
		String css = "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(10) > span:nth-child(2) > "
				+ "span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(6) > "
				+ "td:nth-child(1) > span:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > "
				+ "td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > "
				+ "table:nth-child(1) > tbody:nth-child(1) > tr:nth-child("+(rownum+2)+") > td:nth-child(7)";
		return getElementByCss(css).getText();
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
	
	//Kontirsor valaszto menu
	
	
	public void setKontirIdFilter(String filter) {
		String css = "td.filter-table-control:nth-child(1) > font:nth-child(1) > input:nth-child(1)";
		getElementByCss(css).clear();
		getElementByCss(css).sendKeys(filter);
		sleep(1.0f);
	}
	
	public void filterKontirList() {
		String css = ".filter-table-pager-top > div:nth-child(1) > div:nth-child(1) > "
				+ "table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > "
				+ "font:nth-child(3) > input:nth-child(1)";
		
		getElementByCss(css).click();
		waitForLoading();
		sleep(1.0f);
	}
	
	public String getFirstKontirTetelAzon() {
		String css = ".filter-table > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > "
				+ "table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(1) > "
				+ "span:nth-child(1) > span:nth-child(1) > a:nth-child(2) > nobr:nth-child(1)";
		return getElementByCss(css).getText();
		
	}
	public void chooseFirstKontirTetel() {
		String css = ".filter-table > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > table:nth-child(1) > "
				+ "tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(1) > span:nth-child(1) > span:nth-child(1) > "
				+ "a:nth-child(2)";
		getElementByCss(css).click();
		waitForLoading();
	}
	
}
