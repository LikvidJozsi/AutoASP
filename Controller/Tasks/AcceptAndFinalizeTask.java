package Controller.Tasks;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;

import DAL.KaszperPage;
import DAL.Menu91.Menu91DetailsPage;
import DAL.Menu91.Menu91ListPage;
import DAL.Menu91.Menu91ListPage.Color;
import DAL.Menu91.Menu91ListPage.Filter;
import net.bytebuddy.asm.Advice.OnDefaultValue;

//TODO might skip rows if database changes while in operation
public class AcceptAndFinalizeTask extends Task {

	private Menu91ListPage listPage;
	private String bankPenztarNapSzuro;
	private String evSzuro;

	public AcceptAndFinalizeTask() {}

	public AcceptAndFinalizeTask(KaszperPage startingpage) {
		super(startingpage);
	}

	@Override
	public KaszperPage execute() {
		listPage = new Menu91ListPage(startingpage);
		listPage.setFilter(bankPenztarNapSzuro, Filter.BANKPTNAP);
		listPage.setFilter(evSzuro, Filter.EV);
		listPage.applyFilters();
		
		/*for (int i = 0; i < 90; i++) {
			menu91.selectNextPage();
		}*/

		do {
			processPage();
		}while(listPage.selectNextPage());

		return listPage;
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
				if (lastid.equals(listPage.getId(currentrow))) {
					currentrow++;
					if(currentrow>=listPage.getNumberofRows())
						break;
				}
				lastid = listPage.getId(currentrow);
				processRow(currentrow);
		}
	}

	private void removePreviousPreloadedRecords(Menu91DetailsPage detailsPage) {
		try {
			while(detailsPage.getNumberOfElotoltottTetelek()!=0) {

				detailsPage.removeElotolottTetel();
			}
		}catch(Exception e) {
			System.out.println("Hiba volt előző tételek törlésénél");
		}
	}

	private void processRow(int rowindex) {
		System.out.println("processing row:" + rowindex);
		if(listPage.getColor(rowindex) != Color.GREY) {
			Menu91DetailsPage detailsPage =  listPage.enterRowDetailsPage(rowindex);
			removePreviousPreloadedRecords(detailsPage);
			try {
				int numofkontirtetelek = detailsPage.getNumberofKontirTetelek();
				for (int i = 0; i < numofkontirtetelek; i++) {
					if (detailsPage.kontirtetelHasElotoltButton(i)) {
						detailsPage.elotoltRow(i);
						detailsPage.hozzaad();
					} else
						detailsPage.hozzaad();
				}
				listPage = detailsPage.closeDetails();
				listPage.selectRow(rowindex);
				listPage.veglegesit();
			}catch(Exception e) {
				removePreviousPreloadedRecords(detailsPage);
				listPage = detailsPage.closeDetails();
			}
		}
	}

}
