package gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import business.AutoModell;
import dao.AutoDaoMyBatis;
import exceptions.DAOException;

public class SearchPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 4824126920030177646L;

	List<AutoModell> am;
	
	AutoDaoMyBatis ad = new AutoDaoMyBatis();
	
	JComboBox comboBezeichnung;
	JComboBox comboHersteller;
	JComboBox comboAutoart;
	JComboBox comboSitzplaetze;
	JComboBox comboTreibstoff;
	Integer[] autoartenIDs;
	TableGui tableGui;
	
	Vector vecBezeichnung = new Vector();
	Vector vecHersteller = new Vector();
	Vector vecAutoart = new Vector();
	Vector vecSitzplaetze = new Vector();
	Vector vecTreibstoff = new Vector();

	@SuppressWarnings("unchecked")
	public SearchPanel(Gui gui,List<AutoModell> am) {
		this.am = am;
		this.tableGui = gui.getTableGui();
		Container c = this;
		c.setLayout(new GridLayout(6, 2));
		
		// Suche nach: Bezeichnung, Hersteller, Autoart, Sitzplaetze, Treibstoff
		vecBezeichnung.add("");
	 	vecHersteller.add("");
	 	vecAutoart.add("");
	 	vecSitzplaetze.add("");
	    vecTreibstoff.add("");

		for (int i = 0; i < am.size(); i++) {
			if(vecBezeichnung.contains(am.get(i).getBezeichnung()) == false) {
				vecBezeichnung.add(am.get(i).getBezeichnung());
			}
			if(vecHersteller.contains(am.get(i).getHersteller()) == false) {
				vecHersteller.add(am.get(i).getHersteller());
			}
			if(vecAutoart.contains(am.get(i).getArt()) == false) {
				vecAutoart.add(am.get(i).getArt());
			}
			if(vecSitzplaetze.contains(am.get(i).getSitzplaetze()) == false) {
				vecSitzplaetze.add(am.get(i).getSitzplaetze());
			}
			if(vecTreibstoff.contains(am.get(i).getTreibstoff()) == false) {
				vecTreibstoff.add(am.get(i).getTreibstoff());
			}
		}
		
		// Bezeichnung 1
		c.add(new Label("Bezeichnung"));
		this.comboBezeichnung = new JComboBox(vecBezeichnung);
		this.comboBezeichnung.setSelectedItem("");
		c.add(comboBezeichnung);

		// Hersteller 2
		c.add(new Label("Hersteller"));
		this.comboHersteller = new JComboBox(vecHersteller);
		c.add(comboHersteller);		
	
		// Autoart 3
		c.add(new Label("Autoart"));
		this.comboAutoart = new JComboBox(vecAutoart);
		c.add(comboAutoart);

		// Sitzplaetze 4
		c.add(new Label("Sitzplaetze"));
		this.comboSitzplaetze = new JComboBox(vecSitzplaetze);
		c.add(comboSitzplaetze);

		// Treibstoff 6
		c.add(new Label("Treibstoff"));
		this.comboTreibstoff = new JComboBox(vecTreibstoff);
		c.add(comboTreibstoff);

		// Suchbutton
		JButton jb = new JButton("Suche ausführen");
		jb.addActionListener(this);
		c.add(jb);

		// RESERVIEREN
		JButton reserveButton = new JButton("Reservieren");
		reserveButton.addActionListener(new Reservator(gui));
		c.add(reserveButton);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		Map<String, String> map = new HashMap<String, String>();
		if (!comboBezeichnung.getSelectedItem().equals("")) {
			map.put("Bezeichnung", (String) comboBezeichnung.getSelectedItem());
		}
		if (!comboHersteller.getSelectedItem().equals("")) {
			map.put("Hersteller", (String) comboHersteller.getSelectedItem());
		}
		if (!comboAutoart.getSelectedItem().equals("")) {
			map.put("Autoart", (String) comboAutoart.getSelectedItem());
		}
		if (!comboSitzplaetze.getSelectedItem().equals("")) {
			map.put("Sitzplaetze", comboSitzplaetze.getSelectedItem().toString());
		}
		if (!comboTreibstoff.getSelectedItem().equals("")) {
			map.put("Treibstoff", (String) comboTreibstoff.getSelectedItem());
		}
		
		if(map.size() > 0) {
			try {
				tableGui.setData(ad.getFilteredModell(map));
			} catch (DAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		} else {
			try {
				tableGui.setData(ad.getAllAutoModelle());
			} catch (DAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
