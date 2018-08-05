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
		try {
			menu.enterDetailsPage(rowindex);
			removePreviousKontirTetelek();
			menu.setKonyvelesiEsemenyDatuma(menu.getTeljesitesDatuma());
			List<SzamlaTetel> tetelek = getSzamlaTetelek();
			while(!tetelek.isEmpty()) {
				SzamlaTetel baseValueTetel = findBaseValueKontirTetel(tetelek);
				createKontirTetel(baseValueTetel, kontirsor.getKontirAzonosito());
			}
			tetelek = getSzamlaTetelek();
			fixRoundingError(tetelek);
			menu.clickBefejezesButton();
			menu.tickRecord(rowindex);
			menu.IgazolEsVeglegesit();
		}catch(Exception e) {
			System.out.println("Processrow hiba volt");
			e.printStackTrace();
			removePreviousKontirTetelek();
			menu.clickBefejezesButton();
		}
	}
	
	private void createKontirTetel(SzamlaTetel tetel,String kontirSorId) {
		System.out.println("Kontirtetel hozzaadasa, afa: " + tetel.afakulcs.getText() + ", Osszeg:" + tetel.osszeg);
		menu.setKontirTetelSum(tetel.osszeg);
		menu.enterKontirChooserMenu();
		menu.setKontirIdFilter(kontirSorId);
		menu.filterKontirList();
		if(!menu.getFirstKontirTetelAzon().equals(kontirSorId)) {
			System.out.println("Nem mukodott a kontirtetel szures! " + menu.getFirstKontirTetelAzon() + " != " + kontirSorId);
			menu.filterKontirList();
		}
		menu.chooseFirstKontirTetel();
		List<SzamlaTetel> kontirTetelek = getKontirTetelek();
		for (int i = 0; i < kontirTetelek.size(); i++) {
			if(Util.equals(kontirTetelek.get(i).osszeg,tetel.osszeg)) {
				menu.modositKontirTetel(i);
			}
		}
		menu.selectAfaKulcs(tetel.afakulcs.getText());
		menu.hozzaadKontirTetel();
		if(!Util.equals(tetel.afakulcs.getValue(),0.0f)) {
			menu.hozzaadKontirTetel();
		}
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

		//System.out.println("Tetel befejezve");
	}

	private void removePreviousKontirTetelek() {
		while(menu.getNumOfKontirTetelek()!=0) {
			//System.out.println(menu.getNumOfKontirTetelek());
			try {
				menu.deleteKontirTetel(0);
			}catch(Exception e) {
				System.out.println("Hiba volt törlésnél!");
				break;
			}
		}
	}

	private void fixRoundingError(List<SzamlaTetel> szamlatetelek) {
		List<SzamlaTetel> kontirtetelek = getKontirTetelek();
		kontirtetelek.forEach(tetel -> System.out.println("Kontirtetel: " + tetel.osszeg));
		for (int i = 0; i < kontirtetelek.size(); i++) {
			if(!contains(szamlatetelek, kontirtetelek.get(i).osszeg)) {
				System.out.println("Kerekitesi hibat tanaltam: osszeg: " + kontirtetelek.get(i).osszeg);
				//Tricky
				menu.modositKontirTetel(i-1);
				menu.hozzaadKontirTetel();
				menu.hozzaadKontirTetel();
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

	private List<SzamlaTetel> getKontirTetelek(){
		int numofrows = menu.getNumOfKontirTetelek();
		List<SzamlaTetel> kontirtetelek = new ArrayList<>(numofrows);
		for (int i = 0; i < numofrows; i++) {
			SzamlaTetel ujTetel = new SzamlaTetel();
			ujTetel.afakulcs = menu.getKontirTetelKulcs(i);
			ujTetel.osszeg = menu.getKontirTetelOsszeg(i);
			//System.out.println("Kontirtetel osszege:" + ujTetel.osszeg);
			kontirtetelek.add(ujTetel);
		}
		return kontirtetelek;
	}

	private void createKontirTetel(String kontirsorName, String kulcs,List<SzamlaTetel> tetelek) {
		int rownum = getIndexOfBiggestTetel(tetelek, kulcs);
		if(rownum!= -1) {
			System.out.println("Kontirtetel hozzaadasa, afa: " + kulcs + ", Osszeg:" + tetelek.get(rownum).osszeg);
			menu.setKontirTetelSum(tetelek.get(rownum).osszeg);
			menu.enterKontirChooserMenu();
			menu.setKontirIdFilter(kontirsorName);
			menu.filterKontirList();
			if(!menu.getFirstKontirTetelAzon().equals(kontirsorName)) {
				System.out.println("Nem mukodott a kontirtetel szures! " + menu.getFirstKontirTetelAzon() + " != " + kontirsorName);
				menu.filterKontirList();
			}
			menu.chooseFirstKontirTetel();
		}
	}

	private List<SzamlaTetel> getSzamlaTetelek(){
		int tetelnum = menu.getNumberOfAltetelek();
		List<SzamlaTetel> tetelek = new ArrayList<>(tetelnum);
		for (int i = 0; i < tetelnum; i++) {
			SzamlaTetel newtetel = new SzamlaTetel();
			newtetel.afakulcs = menu.getAfaKulcs(i);
			newtetel.osszeg = menu.getSzamlaTetelSum(i);
			tetelek.add(newtetel);
		}
		return tetelek;
	}
	private int getIndexOfBiggestTetel(List<SzamlaTetel> tetelek,String kulcs) {
		int biggestindex = -1;
		float biggestvalue = Float.MIN_VALUE;
		for (int i = 0; i < tetelek.size(); i++) {
			if(tetelek.get(i).osszeg > biggestvalue && tetelek.get(i).afakulcs.getText().equals(kulcs)) {
				biggestvalue = tetelek.get(i).osszeg;
				biggestindex = i;
			}
		}
		if(biggestindex == -1) {
			throw new RuntimeException(new Exception("No kontirtetel with the given afakulcs found"));
		}
		return biggestindex;
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
