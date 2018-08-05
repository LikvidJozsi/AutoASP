package DAL.Menu1230;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import DAL.IColumn;
import DAL.KaszperPage;
import DAL.ListPage;
import Model.AfaKulcs;

public class Menu1230ListPage extends ListPage{
	
	public Menu1230ListPage(KaszperPage source) {super(source,"1230");}
	
	public enum Filter implements IColumn{
		BANKPTNAP(10),
		PARTNERNEV(4);
		private int value;
		private Filter(int value) {
			this.value = value;
		}
		
		@Override
		public int getColumn() {
			return value;
		}
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
	
	public Menu1230DetailsPage enterDetailsPage(int rowindex) {
		String css = ".filter-table > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > table:nth-child(1) > "
				+ "tbody:nth-child(1) > tr:nth-child("+(rowindex+4)+") > td:nth-child(2) > span:nth-child(1) > span:nth-child(1) > "
				+ "a:nth-child(2)";
		getElementByCss(css).click();
		waitForLoading();
		return new Menu1230DetailsPage(this);
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
}
