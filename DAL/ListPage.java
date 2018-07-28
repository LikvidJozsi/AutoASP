package DAL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import DAL.Menu91.Menu91ListPage.Filter;

/**
 * Base class for pages that can be characterized by having one big paginated table. These usually have the
 * table and generic controls like the next page button at the same place in the html hierarchy. Make sure
 * to override the functions if it is not so in a use case.
 * @author Bánóczi Dávid
 *
 */
public abstract class ListPage extends KaszperPage{

	protected ListPage(KaszperPage source, String menuname) {
		super(source, menuname);
	}
	
	protected WebElement getCell(int rowindex,int columnindex) {
		String cellcss = ".filter-table > tbody:nth-child(1) > "
				+ "tr:nth-child(3) > td:nth-child(1) > table:nth-child(1) "
				+ "> tbody:nth-child(1) > tr:nth-child("+(rowindex+4)+") > td:nth-child("+columnindex+")";
		return getElementByCss(cellcss);
	}
	
	
	public void setFilter(String value,IColumn filter){
		String filtercss = "td.filter-table-control:nth-child(" + filter.getColumn() + ") > font:nth-child(1) > input:nth-child(1)";
		getElementByCss(filtercss).sendKeys(value);
	}
	
	public void applyFilters() {
		String css = "font.default:nth-child(3) > input:nth-child(1)";
		getElementByCss(css).click();
		waitForLoading();
	}
	
	public void clearAllFilters() {
		String css = ".filter-table-pager-top > div:nth-child(1) > div:nth-child(1) > table:nth-child(1) > "
				+ "tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > font:nth-child(6) > "
				+ "input:nth-child(1)";
		getElementByCss(css).click();
		waitForLoading();
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
	
	public int getNumberofRows() {
		String rowcss = ".filter-table > tbody:nth-child(1) > tr:nth-child(3) > "
				+ "td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > * ";
		return getNumOfOccurances(rowcss)-4;
	}
	

}
