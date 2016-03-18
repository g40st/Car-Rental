package business;

import java.util.Date;

public class Reservierung {
	private int id;
	private int kundeID;
	private int modellID;
	private Date beginn;
	private Date ende;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getKundeID() {
		return kundeID;
	}
	public void setKundeID(int kundeID) {
		this.kundeID = kundeID;
	}
	public int getModellID() {
		return modellID;
	}
	public void setModellID(int modellID) {
		this.modellID = modellID;
	}
	public Date getBeginn() {
		return beginn;
	}
	public void setBeginn(Date beginn) {
		this.beginn = beginn;
	}
	public Date getEnde() {
		return ende;
	}
	public void setEnde(Date ende) {
		this.ende = ende;
	}
	@Override
	public String toString() {
		return "Reservierung [id=" + id + ", kundeID=" + kundeID + ", modellID=" + modellID + ", beginn=" + beginn
				+ ", ende=" + ende + "]";
	}
	
}
