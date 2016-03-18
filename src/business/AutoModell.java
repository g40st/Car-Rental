package business;

public class AutoModell {
	private int id;
	private String bezeichnung;
	private String hersteller;
	private String art;
	private String treibstoff;
	private int achsen;
	private int kw;
	private int sitzplaetze;
	private String preisTag;
	private String preisKM;
	private int ladevolumen;
	private int zuladung;
	private String fuererschein;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	public String getHersteller() {
		return hersteller;
	}
	public void setHersteller(String hersteller) {
		this.hersteller = hersteller;
	}
	public String getArt() {
		return art;
	}
	public void setArt(String art) {
		this.art = art;
	}
	public String getTreibstoff() {
		return treibstoff;
	}
	public void setTreibstoff(String treibstoff) {
		this.treibstoff = treibstoff;
	}
	public int getKw() {
		return kw;
	}
	public void setKw(int kw) {
		this.kw = kw;
	}
	public int getAchsen() {
		return achsen;
	}
	public void setAchsen(int achsen) {
		this.achsen = achsen;
	}
	public int getSitzplaetze() {
		return sitzplaetze;
	}
	public void setSitzplaetze(int sitzplaetze) {
		this.sitzplaetze = sitzplaetze;
	}
	public String getPreisTag() {
		return preisTag;
	}
	public void setPreisTag(String preisTag) {
		this.preisTag = preisTag;
	}
	public String getPreisKM() {
		return preisKM;
	}
	public void setPreisKM(String preisKM) {
		this.preisKM = preisKM;
	}
	public int getLadevolumen() {
		return ladevolumen;
	}
	public void setLadevolumen(int ladevolumen) {
		this.ladevolumen = ladevolumen;
	}
	public int getZuladung() {
		return zuladung;
	}
	public void setZuladung(int zuladung) {
		this.zuladung = zuladung;
	}
	public String getFuererschein() {
		return fuererschein;
	}
	public void setFuererschein(String fuhrerschein) {
		this.fuererschein = fuhrerschein;
	}
	@Override
	public String toString() {
		return "AutoModell [id=" + id + ", bezeichnung=" + bezeichnung + ", hersteller=" + hersteller + ", art=" + art
				+ ", treibstoff=" + treibstoff + ", achsen=" + achsen + ", kw=" + kw + ", sitzplaetze=" + sitzplaetze
				+ ", preisTag=" + preisTag + ", preisKM=" + preisKM + ", ladevolumen=" + ladevolumen + ", zuladung="
				+ zuladung + ", fuhrerschein=" + fuererschein + "]";
	}
}
