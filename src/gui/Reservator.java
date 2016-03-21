package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import business.Auto;
import business.AutoModell;
import business.Kunde;
import business.Reservierung;
import dao.AutoDaoMyBatis;

public class Reservator extends JFrame implements ActionListener{	

	private static final long serialVersionUID = -2231010003441747758L;
	Gui gui;
	
	AutoDaoMyBatis ad = new AutoDaoMyBatis();
	
	List<AutoModell> am;
	List<Kunde> kList;
	
	JTable table;
	ListSelectionModel selectionModel;
	
	JComboBox comboKunde = new JComboBox();
	JTextField vorname = new JTextField();
	JTextField nachname = new JTextField();

	JTextField car;
	JTextField carName;
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	Date dateBeginn = new Date();
	JTextField datumBeginn = new JTextField(dateFormat.format(dateBeginn));
	Date dateEnd = new Date();
	JTextField datumEnde = new JTextField(dateFormat.format(dateEnd));
	
	JButton chooseCar;
	JButton checkBtn;
	
	public Reservator(Gui gui) {
		super();
		this.gui = gui;
		table = gui.getTableGui().getTable();
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Reservierung");
		this.setLayout(new GridLayout(12, 2));
				
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		selectionModel = table.getSelectionModel();
		
		am = ad.getAllAutoModelle();
		kList = ad.getAllKunde();
		Vector vecKunde = new Vector();
	 	vecKunde.add("");
	 	for(Kunde tmp : kList) {
	 		vecKunde.add(tmp.getId());
	 	}
		comboKunde = new JComboBox(vecKunde);
		
		this.add(new JLabel("Kunden ID: "));
		this.add(comboKunde);
		this.add(new JLabel("Vorname"));
		this.add(vorname);
		this.add(new JLabel("Nachname"));
		this.add(nachname);
		
		this.add(new JSeparator(SwingConstants.HORIZONTAL));
		this.add(new JSeparator(SwingConstants.HORIZONTAL));
		
		chooseCar = new JButton("Auto");
	
		this.add(new JLabel("Auto auswählen"));
		this.add(chooseCar);
		this.add(new JLabel("Auto-ID"));
		car = new JTextField();
		this.add(car);	
		this.add(new JLabel("Auto-Name"));
		carName = new JTextField();
		this.add(carName);
		
		this.add(new JSeparator(SwingConstants.HORIZONTAL));
		this.add(new JSeparator(SwingConstants.HORIZONTAL));
		
		this.add(new JLabel("Beginn"));
		this.add(datumBeginn);
		this.add(new JLabel("Ende"));
		this.add(datumEnde);
		
		checkBtn = new JButton("Überprüfen");
		this.add(checkBtn);
		
		selectionModel.addListSelectionListener(new tableListener(this,table,car,carName));
		
		comboKunde.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
		    	if (!comboKunde.getSelectedItem().equals("")) {
		    		for(Kunde tmp : kList) {
		    			if(tmp.getId() == Integer.parseInt(comboKunde.getSelectedItem().toString())) {
		    				vorname.setText(tmp.getVorname());
		    				nachname.setText(tmp.getNachname());
		    			}
		    		}
				} else {
					vorname.setText("");
    				nachname.setText("");
				}
		    }
		});
		
		// AutoButton Fenster minimieren
		chooseCar.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent event) {
				JButton btn = (JButton) event.getSource();
		    	Reservator res = (Reservator) btn.getTopLevelAncestor();
				res.setVisible(false);
		    }
		});
		
		checkBtn.addActionListener (new ActionListener() {
		    @SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent event) {
		    	JButton btn = (JButton) event.getSource();
		    	Reservator reservator = (Reservator) btn.getTopLevelAncestor();
		    	if (!comboKunde.getSelectedItem().equals("") && !car.getText().equals("")) {
    				int result = checkAlleReservations();
    				// Auto verfügbar
    				if(result > 0) {
    					int n = JOptionPane.showConfirmDialog(reservator, (result) + " Auto(s) verfügbar!\n\nMöchten Sie ein Auto reservieren?","Auto reservieren",JOptionPane.YES_NO_OPTION);
    					if(n == 0) {
    						if(checkAlleReservations() > 0) {
		    					Reservierung reservierung = new Reservierung();
		    					reservierung.setKundeID(comboKunde.getSelectedIndex());
		    					reservierung.setModellID(Integer.parseInt(car.getText()));
		    					try {
									reservierung.setBeginn(dateFormat.parse(datumBeginn.getText()));
									reservierung.setEnde(dateFormat.parse(datumEnde.getText()));
								} catch (ParseException e) {
									e.printStackTrace();
								}
		    					ad.insertReservierung(reservierung);
		    					System.out.println("Datensatz schreiben" );
    						}
    					}
    				} else {
    					JOptionPane.showMessageDialog(reservator,"Leider sind alle Fahrzeuge mit diesen Daten reserviert!","Fehler",JOptionPane.ERROR_MESSAGE);
    					System.out.println("Kein Auto verfügbar");
    				}
		    
				} else {
					JOptionPane.showMessageDialog(reservator,"Bitten einen Kunden und ein Fahrzeug auswählen!","Fehler",JOptionPane.ERROR_MESSAGE);
					System.out.println("Bitte Kunde und Auto auswählen");
				}
		    }
		});
		
	}
	
	private int checkAlleReservations() {
		// verfügbare Autos nach Autoart
		List<Auto> carList = ad.getFilteredAuto(Integer.parseInt(car.getText()));
		int countRes = 0;
		Date begin = null;
		Date end = null;
		// Alle Autos iterieren übergebener ID
		for(Auto aTmp : carList) {
			List<Reservierung> res = ad.getReservierung(aTmp.getModell());
			countRes = 0;
			for(Reservierung rTmp : res) {
				try {
					begin = dateFormat.parse(datumBeginn.getText());
					end = dateFormat.parse(datumEnde.getText());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				// alle Autos bereits reserviert für diesen Zeitraum
				if((rTmp.getBeginn().compareTo(begin) <= 0 && rTmp.getEnde().compareTo(begin) >= 0) || (rTmp.getBeginn().compareTo(end) <=0 && rTmp.getEnde().compareTo(end)>=0)) {
					countRes++;
				}
			}
		}
		return carList.size()-countRes;
	}
	
	
	
	// Fenster anzeigen
	@Override
	public void actionPerformed(ActionEvent e) {			
		this.setSize(600,400);
		this.setLocationRelativeTo(gui);
		this.setVisible(true);	
	}
}

class tableListener implements ListSelectionListener {
	Reservator res;
	JTable table;
	JTextField car;
	JTextField carName;
	
	public tableListener(Reservator res, JTable table, JTextField car, JTextField carName) {
		this.res = res;
		this.table = table;
		this.car = car;
		this.carName = carName;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent event) {
		if (table.getSelectedRow() > -1) {
			car.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
			carName.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
			if(!res.isVisible()) {
				res.setVisible(true);
			}	
		}
	}	
}


