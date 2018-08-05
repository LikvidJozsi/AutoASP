package Controller.Tasks;

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
		Menu1230DetailsPage detailsPage = menu.enterDetailsPage(rowindex);
		try {
			removePreviousKontirTetelek(detailsPage);
			detailsPage.setKonyvelesiEsemenyDatuma(detailsPage.getTeljesitesDatuma());
			List<SzamlaTetel> tetelek = getSzamlaTetelek(detailsPage);
			while(!tetelek.isEmpty()) {
				SzamlaTetel baseValueTetel = findBaseValueKontirTetel(tetelek);
				detailsPage = createKontirTetel(detailsPage,baseValueTetel, kontirsor.getKontirAzonosito());
			}
			tetelek = getSzamlaTetelek(detailsPage);
			fixRoundingError(detailsPage,tetelek);
			menu =  detailsPage.clickBefejezesButton();
		}catch(Exception e) {
			System.out.println("Processrow hiba volt");
			e.printStackTrace();
			removePreviousKontirTetelek(detailsPage);
			menu = detailsPage.clickBefejezesButton();
		}
		menu.tickRecord(rowindex);
		menu.IgazolEsVeglegesit();
	}
	
	private Menu1230DetailsPage selectKontirSor(Menu1230DetailsPage detailsPage,String kontirSorId) {
		KontirChooserPage kontirPage = detailsPage.enterKontirChooserMenu();
		if(kontirPage.getFirstKontirTetelAzon().equals(kontirSorId)) {
			// if the correct kontirsor is already filtered
			return kontirPage.chooseFirstKontirTetel();
		}
		kontirPage.setKontirIdFilter(kontirSorId);
		kontirPage.filterKontirList();
		while(!kontirPage.getFirstKontirTetelAzon().equals(kontirSorId)) {
			System.out.println("Nem mukodott a kontirtetel szures! " + kontirPage.getFirstKontirTetelAzon() + " != " + kontirSorId);
			kontirPage.filterKontirList();
		}
		return kontirPage.chooseFirstKontirTetel();
	}
	
	private Menu1230DetailsPage createKontirTetel(Menu1230DetailsPage detailsPage,SzamlaTetel tetel,String kontirSorId) {
		System.out.println("Kontirtetel hozzaadasa, afa: " + tetel.afakulcs.getText() + ", Osszeg:" + tetel.osszeg);
		detailsPage.setKontirTetelSum(tetel.osszeg);
		detailsPage = selectKontirSor(detailsPage,kontirSorId);
		List<SzamlaTetel> kontirTetelek = getKontirTetelek(detailsPage);
		for (int i = 0; i < kontirTetelek.size(); i++) {
			if(Util.equals(kontirTetelek.get(i).osszeg,tetel.osszeg)) {
				detailsPage.modositKontirTetel(i);
			}
		}
		detailsPage.selectAfaKulcs(tetel.afakulcs.getText());
		detailsPage.hozzaadKontirTetel();
		if(!Util.equals(tetel.afakulcs.getValue(),0.0f)) {
			detailsPage.hozzaadKontirTetel();
		}
		return detailsPage;
	}
	
	private SzamlaTetel findBaseValueKontirTetel(List<SzamlaTetel> tetelek) {
		for (SzamlaTetel szamlaTetel : tetelek) {
			if(szamlaTetel.afakulcs.getValue() == 0.0f) {
				return szamlaTetel;
			}
			float afa = (float) Math.ceil(szamlaTetel.osszeg*szamlaTetel.afakulcs.getValue());
			SzamlaTetel afaTetel = findTetel(tetelek,afa);
			if(afaTetel != null) {
				tetelek.remove(afaTetel);
				tetelek.remove(szamlaTetel);
				return szamlaTetel;
			}
		}
		throw new RuntimeException(new Exception("Anomalyous SzamlaTetel is present!"));
	}
	
	private SzamlaTetel findTetel(List<SzamlaTetel> tetelek,float sum) {
		for (SzamlaTetel szamlaTetel : tetelek) {
			if(Util.equals(szamlaTetel.osszeg,sum,1.5f))
				return szamlaTetel;
		}
		return null;
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

	private void removePreviousKontirTetelek(Menu1230DetailsPage detailsPage) {
		while(detailsPage.getNumOfKontirTetelek()!=0) {
			//System.out.println(menu.getNumOfKontirTetelek());
			try {
				detailsPage.deleteKontirTetel(0);
			}catch(Exception e) {
				System.out.println("Hiba volt törlésnél!");
				break;
			}
		}
	}

	private void fixRoundingError(Menu1230DetailsPage detailsPage,List<SzamlaTetel> szamlatetelek) {
		List<SzamlaTetel> kontirtetelek = getKontirTetelek(detailsPage);
		kontirtetelek.forEach(tetel -> System.out.println("Kontirtetel: " + tetel.osszeg));
		for (int i = 0; i < kontirtetelek.size(); i++) {
			if(!contains(szamlatetelek, kontirtetelek.get(i).osszeg)) {
				System.out.println("Kerekitesi hibat tanaltam: osszeg: " + kontirtetelek.get(i).osszeg);
				//Tricky
				detailsPage.modositKontirTetel(i-1);
				detailsPage.hozzaadKontirTetel();
				detailsPage.hozzaadKontirTetel();
			}

		}
	}

	private boolean contains(List<SzamlaTetel> tetelek, float osszeg) {
		for (int i = 0; i < tetelek.size(); i++) {
			if(Util.equals(tetelek.get(i).osszeg,osszeg)) {
				return true;
			}
		}
		return false;
	}

	private List<SzamlaTetel> getKontirTetelek(Menu1230DetailsPage detailsPage){
		int numofrows = detailsPage.getNumOfKontirTetelek();
		List<SzamlaTetel> kontirtetelek = new ArrayList<>(numofrows);
		for (int i = 0; i < numofrows; i++) {
			SzamlaTetel ujTetel = new SzamlaTetel();
			ujTetel.afakulcs = detailsPage.getKontirTetelKulcs(i);
			ujTetel.osszeg = detailsPage.getKontirTetelOsszeg(i);
			//System.out.println("Kontirtetel osszege:" + ujTetel.osszeg);
			kontirtetelek.add(ujTetel);
		}
		return kontirtetelek;
	}


	private List<SzamlaTetel> getSzamlaTetelek(Menu1230DetailsPage detailsPage){
		int tetelnum = detailsPage.getNumberOfAltetelek();
		List<SzamlaTetel> tetelek = new ArrayList<>(tetelnum);
		for (int i = 0; i < tetelnum; i++) {
			SzamlaTetel newtetel = new SzamlaTetel();
			newtetel.afakulcs = detailsPage.getAfaKulcs(i);
			newtetel.osszeg = detailsPage.getSzamlaTetelSum(i);
			tetelek.add(newtetel);
		}
		return tetelek;
	}

	class SzamlaTetel{
		public float osszeg;
		public AfaKulcs afakulcs;
		
		public SzamlaTetel() {}
		
		public SzamlaTetel(float osszeg,String afakulcs) {
			this.osszeg = osszeg;
			this.afakulcs = AfaKulcs.of(afakulcs);
		}
		
		@Override
		public boolean equals(Object otherAsObj) {
			SzamlaTetel other = (SzamlaTetel) otherAsObj;
			return (Math.abs(this.osszeg-other.osszeg)<=1) && (this.afakulcs == other.afakulcs);
				
		}
	}


	private class Pair{
		public ExpressionComparator comparator;
		public KontirSor kontirSor;
	}
	

}
