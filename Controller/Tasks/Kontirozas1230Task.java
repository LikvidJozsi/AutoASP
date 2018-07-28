package Controller.Tasks;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.ui.Sleeper;

import Controller.Alternatives;
import Controller.ExpressionComparator;
import DAL.KaszperPage;
import DAL.Menu1230.Menu1230ListPage;
import DAL.Menu1230.Menu1230ListPage.Filter;

public class Kontirozas1230Task extends Task{

	private String bankPenztarNapFilter;
	private String partnerNevFilter;

	private String szamlaTipus;
	private String afa27Kontir;
	private String afa18Kontir;
	private String afahatalyankivuliKontir;
	private String afaMentesKontir;
	private String afa5Kontir;

	Menu1230ListPage menu;
	private ExpressionComparator megjegyzesComparator;

	public void setAfaMentesKontir(String afaMentesKontir) {
		this.afaMentesKontir = afaMentesKontir;
	}

	public void setPartnerNevFilter(String partnerNevFilter) {
		this.partnerNevFilter = partnerNevFilter;
	}

	public void setAfa18Kontir(String afa18Kontir) {
		this.afa18Kontir = afa18Kontir;
	}

	public void setAfa5Kontir(String afa5Kontir) {
		this.afa5Kontir = afa5Kontir;
	}

	public void setAfahatalyankivuliKontir(String afahatalyankivuliKontir) {
		this.afahatalyankivuliKontir = afahatalyankivuliKontir;
	}


	public void setBankPenztarNapFilter(String bankPenztarNapFilter) {
		this.bankPenztarNapFilter = bankPenztarNapFilter;
	}

	public void setSzamlaTipus(String szamlaTipus) {
		this.szamlaTipus = szamlaTipus;
	}

	public void setAfa27Kontir(String afa27Kontir) {
		this.afa27Kontir = afa27Kontir;
	}


	@Override
	public KaszperPage execute() {
		initComparator();
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
	
	private void initComparator() {
		megjegyzesComparator = new ExpressionComparator();
		String [] subParts = szamlaTipus.split(" ");
		for (String part : subParts) {
			Alternatives alternatives = new Alternatives();
			alternatives.addAll(part.split("VAGY"));
			megjegyzesComparator.add(alternatives);
		}
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
			processRow(currentrow);
		}
	}

	private void processRow(int rowindex) {
		System.out.println(menu.getMegjegyzes(rowindex));
		try {
			if(megjegyzesComparator.compare(menu.getMegjegyzes(rowindex))) {
				menu.enterDetailsPage(rowindex);
				removePreviousKontirTetelek();
				menu.setKonyvelesiEsemenyDatuma(menu.getTeljesitesDatuma());
				List<SzamlaTetel> tetelek = getSzamlaTetelek();
				createKontirTetel(afa27Kontir, "27%", tetelek);
				createKontirTetel(afa18Kontir, "18%", tetelek);
				createKontirTetel(afa5Kontir, "5%", tetelek);
				createKontirTetel(afahatalyankivuliKontir, "ÁFA hatályán kívüli", tetelek);
				createKontirTetel(afaMentesKontir,"adó alól mentes", tetelek);
				fixRoundingError(tetelek);
				menu.clickBefejezesButton();
				menu.tickRecord(rowindex);
				menu.IgazolEsVeglegesit();
			}else {
				System.out.println("Nem megfelelő közlemény!");
			}
		}catch(Exception e) {
			removePreviousKontirTetelek();
			menu.clickBefejezesButton();
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
		for (int i = 0; i < kontirtetelek.size(); i++) {
			if(!contains(szamlatetelek, kontirtetelek.get(i).osszeg)) {
				System.out.println("Kerekitesi hibat tanaltam");
				int rowindex = getIndexOfBiggestTetel(kontirtetelek, kontirtetelek.get(i).afakulcs);
				menu.modositKontirTetel(rowindex);
				menu.hozzaadKontirTetel();
				menu.hozzaadKontirTetel();
			}

		}
	}

	private boolean contains(List<SzamlaTetel> tetelek, float osszeg) {
		for (int i = 0; i < tetelek.size(); i++) {
			if(tetelek.get(i).osszeg == osszeg) {
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
			if(tetelek.get(i).osszeg > biggestvalue && tetelek.get(i).afakulcs.equals(kulcs)) {
				biggestvalue = tetelek.get(i).osszeg;
				biggestindex = i;
			}
		}
		return biggestindex;
	}

	class SzamlaTetel{
		public float osszeg;
		public String afakulcs;
	}
}
