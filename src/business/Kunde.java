package business;

public class Kunde {
	private int id;
	private String vorname;
	private String nachname;
	private String plz;
	private String ort;
	private String strasse;
	private String email;
	private String telNr;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	public String getPlz() {
		return plz;
	}
	public void setPlz(String plz) {
		this.plz = plz;
	}
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelNr() {
		return telNr;
	}
	public void setTelNr(String telNr) {
		this.telNr = telNr;
	}
	@Override
	public String toString() {
		return "Kunde [id=" + id + ", vorname=" + vorname + ", nachname=" + nachname + ", plz=" + plz + ", ort=" + ort
				+ ", strasse=" + strasse + ", email=" + email + ", telNr=" + telNr + "]";
	}
}
