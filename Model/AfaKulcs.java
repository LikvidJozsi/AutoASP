package Model;

import net.bytebuddy.asm.Advice.This;

public enum AfaKulcs {
	KULCS_27("27%",0.27f),
	KULCS_18("18%",0.18f),
	KULCS_5("5%",0.05f),
	MENTES("adó alól mentes",0.0f),
	AHK("ÁFA hatályán kívüli",0.0f);
	
	
	private AfaKulcs(String text,float value) {
		this.text = text;
		this.value = value;
	}
	
	private String text;
	private float value;
	
	
	public static AfaKulcs of(String text) {
		for (AfaKulcs kulcs : AfaKulcs.values()) {
			if(text.equals(kulcs.text)) {
				return kulcs;
			}
		}
		throw new RuntimeException(new Exception("No afakulcs with the given text!"));
	}
	
	public String getText() {
		return text;
	}
	
	public float getValue() {
		return value;
	}
}
