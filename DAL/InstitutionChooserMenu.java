package DAL;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

public class InstitutionChooserMenu extends KaszperPage{
	// after choosing an institution the page is automatically left, 
	//so if we want to choose another, we have to enter again

	
	public InstitutionChooserMenu(KaszperPage source) {
		super(source, "i");
	}
	
	private int getNumberOfInstitutions() {
		String tableselector = ".filter-table > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > *";
		return getNumOfOccurances(tableselector)-4; // 4 is the number of header/padding rows
	}
	
	public List<String> getInstitutions(){
		//a valaszthato intezmenyek neveinek lekerese TODO mivan ha tobb oldalnyi intezmeny van
		int numberofinstiutions = getNumberOfInstitutions();
		List<String> names = new ArrayList<>(numberofinstiutions);
		for (int i = 0; i < numberofinstiutions; i++) {
			names.add(getCellContent(i,2));
		}
		return names;
	}
	private String getCellContent(int rowindex,int cellindex) {
		String cellselector = ".filter-table > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > " + 
				"table:nth-child(1) > tbody:nth-child(1) > tr:nth-child("+ (rowindex+4) +") > td:nth-child("+ (cellindex+1) +")";
		return getElementByCss(cellselector).getText();
	}
	
	public void selectInstituiton(int rowindex) {
		String selectbuttoncss =".filter-table > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) "
				+ "> table:nth-child(1) > tbody:nth-child(1) > tr:nth-child("+ (rowindex+4) +") > "
						+ "td:nth-child(1) > span:nth-child(1) > input:nth-child(1)";
		getDriver().findElement(By.cssSelector(selectbuttoncss)).click();
		waitForLoading();
	}

}
