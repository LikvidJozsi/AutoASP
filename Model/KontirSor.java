package Model;

import java.io.Serializable;

import Controller.KontirSorManager;

public class KontirSor implements Serializable{
	private static final long serialVersionUID = 8264047119749867619L;
	
	
	private String nev;
	private String megjegyzes;
	private String kontirAzonosito;
	
	public KontirSor() {	}
	
	public KontirSor(KontirSor kontirSor) {
		this.nev = kontirSor.nev;
		this.megjegyzes = kontirSor.megjegyzes;
		this.kontirAzonosito = kontirSor.kontirAzonosito;
	}
	
	private void notifyChange() {
		KontirSorManager.getInstance().notifyContentChanged();
	}
	
	public String getNev() {
		return nev;
	}
	public String getMegjegyzes() {
		return megjegyzes;
	}
	public String getKontirAzonosito() {
		return kontirAzonosito;
	}
	public void setNev(String nev) {
		this.nev = nev;
		notifyChange();
		
	}
	public void setMegjegyzes(String megjegyzes) {
		this.megjegyzes = megjegyzes;
		notifyChange();
	}
	public void setKontirAzonosito(String kontirAzonosito) {
		this.kontirAzonosito = kontirAzonosito;
		notifyChange();
	}
}
