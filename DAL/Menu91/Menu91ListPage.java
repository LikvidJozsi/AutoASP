package DAL.Menu91;


import org.openqa.selenium.By;

import DAL.IColumn;
import DAL.KaszperPage;
import DAL.ListPage;

public class Menu91ListPage extends ListPage{
	
	
	public Menu91ListPage(KaszperPage source) {
		super(source, "91");
	}
	
	public enum Filter implements IColumn{
		BANKPTNAP(6),EV(4);
		
		private int value;
		private Filter(int value) {
			this.value = value;
		}
		
		@Override
		public int getColumn() {
			return value;
		}
	}
	
	public enum Color{
		RED,GREEN,GREY;
	}
	
	public Color getColor(int rowindex) {
		String css = ".filter-table > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > "
				+ "tr:nth-child("+(4+rowindex)+") > td:nth-child(3) > span:nth-child(1)";
		String style = getElementByCss(css).getAttribute("style");
		String backgroundattribute = "background-color:";
		System.out.println(style);
		int index = style.indexOf(backgroundattribute);
		if(index == -1) {
			System.out.println("No background color!");
			return Color.RED;
		}else {
			String color = style.substring(index+backgroundattribute.length()+5,style.length()-2);
			switch (color) {
			case "255, 0, 0":
				return Color.RED;
			case "128, 128, 128":
				return Color.GREY;
			case "76, 174, 66":
				return Color.GREEN;
			default:
				System.out.println("Color not defined: " + color);
				return Color.RED;
			}
		}
	}
	
	public void resolveExitError() {
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
	public void closeDetails() {
		// visszater a tablazathoz
		String bezarcss = "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(10) > span:nth-child(2) "
				+ "> span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(5) "
				+ "> td:nth-child(1) > span:nth-child(1) > span:nth-child(1) > table:nth-child(1) > tbody:nth-child(1)"
				+ " > tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) >"
				+ " td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(24) > td:nth-child(1) >"
				+ " span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1)"
				+ " > td:nth-child(2) > span:nth-child(1) > input:nth-child(1)";
		getElementByCss(bezarcss).click();
		waitForLoading();
		
	}
	public void selectRow(int rowindex) {
		// bepipalja a sort
		String checkboxcss = ".filter-table > tbody:nth-child(1) > tr:nth-child(3) > "
				+ "td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child("+(rowindex+4)+") > "
				+ "td:nth-child(1) > span:nth-child(1) > nobr:nth-child(1) > span:nth-child(1) > input:nth-child(1)";
		getElementByCss(checkboxcss).click();
	}
	public void veglegesit() {
		// veglegesit gombra kattint
		String veglegesitcss = "body > main:nth-child(4) > center:nth-child(1) > form:nth-child(10) > span:nth-child(2)"
				+ " > span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) "
				+ "> td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) >"
				+ " span:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > "
				+ "td:nth-child(4) > span:nth-child(1) > input:nth-child(1)";
		waitUntilElementisClickable(By.cssSelector(veglegesitcss));
		getElementByCss(veglegesitcss).click();
		acceptAlert();
		waitForLoading();
		waitUntilElementisClickable(By.cssSelector(veglegesitcss));
		
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
	

	public String getId(int rowindex) {
		String cellselector = ".filter-table > tbody:nth-child(1) > tr:nth-child(3)"
				+ " > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) >"
				+ " tr:nth-child("+ (rowindex+4) +") > td:nth-child(2) > "
				+ "span:nth-child(1) > nobr:nth-child(1)";
		return getDriver().findElement(By.cssSelector(cellselector)).getText();
	}
	
	public void enterRowDetailsPage(int rowindex) {
		//int columnindex = 3;
		
		String detailslinkcss = ".filter-table > tbody:nth-child(1) > "
				+ "tr:nth-child(3) > td:nth-child(1) > table:nth-child(1) "
				+ "> tbody:nth-child(1) > tr:nth-child("+(rowindex+4)+") > td:nth-child(3) > "
						+ "span > span > a > nobr";
		getDriver().findElement(By.cssSelector(detailslinkcss)).click();
	}
}
