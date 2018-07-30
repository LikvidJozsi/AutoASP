package Model;

import java.io.Serializable;

public class KontirSor implements Serializable{
	private static final long serialVersionUID = 8264047119749867619L;
	
	
	private String nev;
	private String megjegyzes;
	private String kontirAzonosito;
	
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
	}
	public void setMegjegyzes(String megjegyzes) {
		this.megjegyzes = megjegyzes;
	}
	public void setKontirAzonosito(String kontirAzonosito) {
		this.kontirAzonosito = kontirAzonosito;
	}
}
