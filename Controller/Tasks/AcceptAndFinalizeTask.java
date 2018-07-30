package Controller.Tasks;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;

import DAL.KaszperPage;
import DAL.Menu91.Menu91ListPage;
import DAL.Menu91.Menu91ListPage.Color;
import DAL.Menu91.Menu91ListPage.Filter;
import net.bytebuddy.asm.Advice.OnDefaultValue;

//TODO might skip rows if database changes while in operation
public class AcceptAndFinalizeTask extends Task {

	private Menu91ListPage menu91;
	private String bankPenztarNapSzuro;
	private String evSzuro;

	public AcceptAndFinalizeTask() {}

	public AcceptAndFinalizeTask(KaszperPage startingpage) {
		super(startingpage);
	}

	@Override
	public KaszperPage execute() {
		menu91 = new Menu91ListPage(startingpage);
		menu91.setFilter(bankPenztarNapSzuro, Filter.BANKPTNAP);
		menu91.setFilter(evSzuro, Filter.EV);
		menu91.applyFilters();
		
		for (int i = 0; i < 90; i++) {
			menu91.selectNextPage();
		}

		do {
			processPage();
		}while(menu91.selectNextPage());

		return menu91;
	}

	public void setBankpenztarnapszuro(String bankpenztarnapszuro) {
		this.bankPenztarNapSzuro = bankpenztarnapszuro;
	}

	public void setEvszuro(String evszuro) {
		this.evSzuro = evszuro;
	}

	private void processPage() {
		int currentrow = 0;
		String lastid = "";
		while (true) {
			try {
				if (lastid.equals(menu91.getId(currentrow))) {
					currentrow++;
					if(currentrow>=menu91.getNumberofRows())
						break;
				}
				lastid = menu91.getId(currentrow);
				processRow(currentrow);
			}catch(Exception e) {
				menu91.resolveExitError();
			}
		}
	}

	private void removePreviousPreloadedRecords() {
		try {
			while(menu91.getNumberOfElotoltottTetelek()!=0) {

				menu91.removeElotolottTetel();
			}
		}catch(Exception e) {
			System.out.println("Hiba volt előző tételek törlésénél");
		}
	}

	private void processRow(int rowindex) {
		System.out.println("processing row:" + rowindex);
		if(menu91.getColor(rowindex) != Color.GREY) {
			menu91.enterRowDetailsPage(rowindex);
			removePreviousPreloadedRecords();
			try {
				int numofkontirtetelek = menu91.getNumberofKontirTetelek();
				for (int i = 0; i < numofkontirtetelek; i++) {
					if (menu91.kontirtetelHasElotoltButton(i)) {
						menu91.elotoltRow(i);
						menu91.hozzaad();
					} else
						menu91.hozzaad();
				}
				menu91.closeDetails();
				menu91.selectRow(rowindex);
				menu91.veglegesit();
			}catch(Exception e) {
				removePreviousPreloadedRecords();
				menu91.closeDetails();
			}
		}
	}

}
