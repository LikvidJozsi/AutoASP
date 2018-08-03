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

	public String getId(int rowindex) {
		String cellselector = ".filter-table > tbody:nth-child(1) > tr:nth-child(3)"
				+ " > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) >"
				+ " tr:nth-child("+ (rowindex+4) +") > td:nth-child(2) > "
				+ "span:nth-child(1) > nobr:nth-child(1)";
		return getDriver().findElement(By.cssSelector(cellselector)).getText();
	}
	
	public Menu91DetailsPage enterRowDetailsPage(int rowindex) {
		//int columnindex = 3;
		
		String detailslinkcss = ".filter-table > tbody:nth-child(1) > "
				+ "tr:nth-child(3) > td:nth-child(1) > table:nth-child(1) "
				+ "> tbody:nth-child(1) > tr:nth-child("+(rowindex+4)+") > td:nth-child(3) > "
						+ "span > span > a > nobr";
		getDriver().findElement(By.cssSelector(detailslinkcss)).click();
		sleep(1);
		return new Menu91DetailsPage(this);
	}
}
