package DAL.Menu1230;

import DAL.BasePage;
import DAL.KaszperPage;

public class KontirChooserPage extends KaszperPage{

	public KontirChooserPage(BasePage source) {
		super(source);
	}
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
	public Menu1230DetailsPage chooseFirstKontirTetel() {
		String css = ".filter-table > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > table:nth-child(1) > "
				+ "tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(1) > span:nth-child(1) > span:nth-child(1) > "
				+ "a:nth-child(2)";
		getElementByCss(css).click();
		waitForLoading();
		return new Menu1230DetailsPage(this);
	}
	
}
