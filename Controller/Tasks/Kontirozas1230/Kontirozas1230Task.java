package Controller.Tasks.Kontirozas1230;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.ToDoubleBiFunction;

import org.openqa.selenium.support.ui.Sleeper;

import com.sun.prism.shader.Texture_Color_AlphaTest_Loader;

import Controller.Alternatives;
import Controller.ExpressionComparator;
import Controller.Util;
import Controller.Tasks.Task;
import DAL.KaszperPage;
import DAL.Menu1230.KontirChooserPage;
import DAL.Menu1230.Menu1230DetailsPage;
import DAL.Menu1230.Menu1230ListPage;
import DAL.Menu1230.Menu1230ListPage.Filter;
import Model.AfaKulcs;
import Model.KontirSor;

public class Kontirozas1230Task extends Task{

	private String bankPenztarNapFilter;
	private String partnerNevFilter;

	private ArrayList<Pair> kontirSorok;


	Menu1230ListPage menu;
	
	
	public void setBankPenztarNapFilter(String bankPentrarNapFilter) {
		this.bankPenztarNapFilter = bankPentrarNapFilter;
	}

	public void setPartnerNevFilter(String partnerNevFilter ) {
		this.partnerNevFilter = partnerNevFilter;
	}

	public void setKontirSorok(List<KontirSor> kontirSorok) {
		this.kontirSorok = new ArrayList<>(kontirSorok.size());
		for (KontirSor kontirSor : kontirSorok) {
			Pair toadd = new Pair();
			toadd.comparator = new ExpressionComparator(kontirSor.getMegjegyzes());
			// deep copy so changes to a kontirSor wouldn't affect a running task
			toadd.kontirSor = new KontirSor(kontirSor);
			this.kontirSorok.add(toadd);
		}
	}

	@Override
	public KaszperPage execute() {
		menu = new Menu1230ListPage(startingpage);
		menu.clearAllFilters();
		menu.setFilter(bankPenztarNapFilter, Filter.BANKPTNAP);
		menu.setFilter(partnerNevFilter, Filter.PARTNERNEV);
		menu.applyFilters();

		do {
			ProcessPage();
		}while(menu.selectNextPage());
		return menu;
	}

	private void ProcessPage() {
		int currentrow = 0;
		String lastid = "";
		System.out.println("Sorok szama: " + menu.getNumberofRows() );
		while (true) {
			if (lastid.equals(menu.getId(currentrow))) {
				currentrow++;
				if(currentrow>=menu.getNumberofRows())
					break;
			}
			lastid = menu.getId(currentrow);
			System.out.println("Sor feldolgozása: " + currentrow);
			evaluateRow(currentrow);
		}
	}


	private void processRow(KontirSor kontirsor, int rowindex) {
		Kontirozas1230TetelTask tetelTask = new Kontirozas1230TetelTask();
		tetelTask.setKontirSor(kontirsor);
		tetelTask.setStartingPage(menu.enterDetailsPage(rowindex));
		menu = (Menu1230ListPage) tetelTask.execute();
		menu.tickRecord(rowindex);
		menu.IgazolEsVeglegesit();
	}

	private void evaluateRow(int rowindex) {
		System.out.println(menu.getMegjegyzes(rowindex));

		String megjegyzes = menu.getMegjegyzes(rowindex);
		boolean matchFound = false;
		for (Pair pair : kontirSorok) {
			if(pair.comparator.compare(megjegyzes)) {
				processRow(pair.kontirSor, rowindex);
				matchFound = true;
				break;
			}
		}
		if(!matchFound) {
			System.out.println("A sor egy mintára sem illeszkedik");
		}
	}

	private class Pair{
		public ExpressionComparator comparator;
		public KontirSor kontirSor;
	}
	

}
