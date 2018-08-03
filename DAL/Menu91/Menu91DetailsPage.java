package DAL.Menu91;

import DAL.BasePage;
import DAL.KaszperPage;

public class Menu91DetailsPage extends KaszperPage{

	public Menu91DetailsPage(BasePage source) {
		super(source);
	}

	private void resolveExitError() {
		String css = ".titanappleterrors > ul:nth-child(4) > li:nth-child(1) > b:nth-child(1) > span:nth-child(2) > input:nth-child(1)";
		getElementByCss(css).click();
		waitForLoading();
	}

	public int getNumberOfElotoltottTetelek() {
		String selector = "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(10) > span:nth-child(2) "
				+ "> span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(7) "
				+ "> td:nth-child(1) > span:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) "
				+ "> td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > "
				+ "table:nth-child(1) > tbody:nth-child(1) > tr";

		return getNumOfOccurances(selector)-1;

	}

	public void removeElotolottTetel() {
		String selector = "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(10) > span:nth-child(2) > "
				+ "span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(7) > "
				+ "td:nth-child(1) > span:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > "
				+ "td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) >"
				+ " table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > span:nth-child(2) > "
				+ "input:nth-child(1)";

		getElementByCss(selector).click();
		waitForLoading(5);
	}

	public boolean kontirtetelHasElotoltButton(int rowindex) {
		String elotoltcss = "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(10) > span:nth-child(2) "
				+ "> span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) "
				+ "> td:nth-child(1) > span:nth-child(1) > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(2) > "
				+ "td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > "
				+ "table:nth-child(1) > tbody:nth-child(1) > tr:nth-child("+(rowindex+2)+") > td:nth-child(1) > span:nth-child(1) > "
				+ "input:nth-child(1)";
		int numofitems = getNumOfOccurances(elotoltcss);
		if(numofitems>1) {
			throw new RuntimeException(new Exception("There cannot be more than one button, something is wrong with the selector"));
		}
		return numofitems==1;

	}

	public void elotoltRow(int rowindex) {
		// rakattint a sor elotolt gombjara
		String elotoltcss = "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(10) > span:nth-child(2) "
				+ "> span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) "
				+ "> td:nth-child(1) > span:nth-child(1) > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(2) "
				+ "> td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) "
				+ "> table:nth-child(1) > tbody:nth-child(1) > tr:nth-child("+(rowindex+2)+") > td:nth-child(1) > "
				+ "span:nth-child(1) > input:nth-child(1)";
		getElementByCss(elotoltcss).click();
		waitForLoading();
	}
	public void hozzaad() {
		// hozzaad gombra kattint
		String hozzaadcss = "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(10) > span:nth-child(2) > "
				+ "span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(5) > "
				+ "td:nth-child(1) > span:nth-child(1) > span:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > "
				+ "tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > "
				+ "td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(24) > td:nth-child(1) >"
				+ " span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > "
				+ "td:nth-child(1) > span:nth-child(1) > input:nth-child(1)";
		getElementByCss(hozzaadcss).click();
		waitForLoading();
	}
	public Menu91ListPage closeDetails() {
		// visszater a tablazathoz
		try {
			String bezarcss = "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(10) > span:nth-child(2) "
					+ "> span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(5) "
					+ "> td:nth-child(1) > span:nth-child(1) > span:nth-child(1) > table:nth-child(1) > tbody:nth-child(1)"
					+ " > tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) >"
					+ " td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(24) > td:nth-child(1) >"
					+ " span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1)"
					+ " > td:nth-child(2) > span:nth-child(1) > input:nth-child(1)";
			getElementByCss(bezarcss).click();
			waitForLoading();
		}catch(Exception e) {
			resolveExitError();
		}
		return new Menu91ListPage(this);

	}
	public int getNumberofKontirTetelek() {

		String rowselector ="html > body > main > center > form > span > span "
				+ "> div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > "
				+ "tr:nth-child(2) > td:nth-child(1) > span > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(2) > " 
				+ "td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > "
				+ "table:nth-child(1) > tbody:nth-child(1) > *";
		// -1 because the selector counts the header too
		return getNumOfOccurances(rowselector)-1;

	}

}
