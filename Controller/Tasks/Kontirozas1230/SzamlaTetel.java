package Controller.Tasks.Kontirozas1230;

import Model.AfaKulcs;

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
