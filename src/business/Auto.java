package business;

import java.util.Date;

public class Auto {
	private String kennzeichen;
	private int kmStand;
	private Date tuvTermin;
	private Date kaufdatum;
	private int modell;
	
	public String getKennzeichen() {
		return kennzeichen;
	}
	public void setKennzeichen(String kennzeichen) {
		this.kennzeichen = kennzeichen;
	}
	public int getKmStand() {
		return kmStand;
	}
	public void setKmStand(int kmStand) {
		this.kmStand = kmStand;
	}
	public Date getTuvTermin() {
		return tuvTermin;
	}
	public void setTuvTermin(Date tuvTermin) {
		this.tuvTermin = tuvTermin;
	}
	public Date getKaufdatum() {
		return kaufdatum;
	}
	public void setKaufdatum(Date kaufdatum) {
		this.kaufdatum = kaufdatum;
	}
	public int getModell() {
		return modell;
	}
	public void setDate(int modell) {
		this.modell = modell;
	}
	@Override
	public String toString() {
		return "Auto [kennzeichen=" + kennzeichen + ", kmStand=" + kmStand + ", tuvTermin=" + tuvTermin + ", kaufdatum="
				+ kaufdatum + ", modell=" + modell + "]";
	}
	
	
}
