package Controller.Tasks.Kontirozas1230;

import java.util.ArrayList;
import java.util.List;

import Controller.Util;
import Controller.Tasks.Task;
import DAL.KaszperPage;
import DAL.Menu1230.KontirChooserPage;
import DAL.Menu1230.Menu1230DetailsPage;
import Model.KontirSor;

public class Kontirozas1230TetelTask extends Task{
	private Menu1230DetailsPage page;
	private KontirSor kontirSor;
	
	
	public void setKontirSor(KontirSor kontirSor) {
		this.kontirSor = kontirSor;
	}
	
	@Override
	public KaszperPage execute() {
		page = new Menu1230DetailsPage(startingpage);
		
		try {
			removePreviousKontirTetelek();
			page.setKonyvelesiEsemenyDatuma(page.getTeljesitesDatuma());
			List<SzamlaTetel> tetelek = getSzamlaTetelek();
			while(!tetelek.isEmpty()) {
				SzamlaTetel baseValueTetel = findBaseValueKontirTetel(tetelek);
				page = createKontirTetel(baseValueTetel);
			}
			tetelek = getSzamlaTetelek();
			fixRoundingError(tetelek);
			return page.clickBefejezesButton();
		}catch(Exception e) {
			System.out.println("Processrow hiba volt");
			e.printStackTrace();
			removePreviousKontirTetelek();
			return page.clickBefejezesButton();
		}
	}
	
	private void removePreviousKontirTetelek() {
		while(page.getNumOfKontirTetelek()!=0) {
			//System.out.println(menu.getNumOfKontirTetelek());
			try {
				page.deleteKontirTetel(0);
			}catch(Exception e) {
				System.out.println("Hiba volt törlésnél!");
				break;
			}
		}
	}
	
	private List<SzamlaTetel> getSzamlaTetelek(){
		int tetelnum = page.getNumberOfAltetelek();
		List<SzamlaTetel> tetelek = new ArrayList<>(tetelnum);
		for (int i = 0; i < tetelnum; i++) {
			SzamlaTetel newtetel = new SzamlaTetel();
			newtetel.afakulcs = page.getAfaKulcs(i);
			newtetel.osszeg = page.getSzamlaTetelSum(i);
			tetelek.add(newtetel);
		}
		return tetelek;
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
	
	private Menu1230DetailsPage createKontirTetel(SzamlaTetel tetel) {
		System.out.println("Kontirtetel hozzaadasa, afa: " + tetel.afakulcs.getText() + ", Osszeg:" + tetel.osszeg);
		String kontirSorId = kontirSor.getKontirAzonosito();
		page.setKontirTetelSum(tetel.osszeg);
		page = selectKontirSor(kontirSorId);
		List<SzamlaTetel> kontirTetelek = getKontirTetelek();
		for (int i = 0; i < kontirTetelek.size(); i++) {
			if(Util.equals(kontirTetelek.get(i).osszeg,tetel.osszeg)) {
				page.modositKontirTetel(i);
			}
		}
		page.selectAfaKulcs(tetel.afakulcs.getText());
		page.hozzaadKontirTetel();
		if(!Util.equals(tetel.afakulcs.getValue(),0.0f)) {
			page.hozzaadKontirTetel();
		}
		return page;
	}
	
	private Menu1230DetailsPage selectKontirSor(String kontirSorId) {
		KontirChooserPage kontirPage = page.enterKontirChooserMenu();
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
	
	private List<SzamlaTetel> getKontirTetelek(){
		int numofrows = page.getNumOfKontirTetelek();
		List<SzamlaTetel> kontirtetelek = new ArrayList<>(numofrows);
		for (int i = 0; i < numofrows; i++) {
			SzamlaTetel ujTetel = new SzamlaTetel();
			ujTetel.afakulcs = page.getKontirTetelKulcs(i);
			ujTetel.osszeg = page.getKontirTetelOsszeg(i);
			//System.out.println("Kontirtetel osszege:" + ujTetel.osszeg);
			kontirtetelek.add(ujTetel);
		}
		return kontirtetelek;
	}
	
	private void fixRoundingError(List<SzamlaTetel> szamlatetelek) {
		List<SzamlaTetel> kontirtetelek = getKontirTetelek();
		kontirtetelek.forEach(tetel -> System.out.println("Kontirtetel: " + tetel.osszeg));
		for (int i = 0; i < kontirtetelek.size(); i++) {
			if(!contains(szamlatetelek, kontirtetelek.get(i).osszeg)) {
				System.out.println("Kerekitesi hibat tanaltam: osszeg: " + kontirtetelek.get(i).osszeg);
				//Tricky
				page.modositKontirTetel(i-1);
				page.hozzaadKontirTetel();
				page.hozzaadKontirTetel();
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
}
