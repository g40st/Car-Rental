package gui;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import business.AutoModell;
import dao.AutoDaoMyBatis;

public class Gui extends JFrame {
	private static final long serialVersionUID = -9127982338060064352L;
	public static final String STR_AUTOVERLEIH = "Autoverleih";
	public static final String STR_AUTOMODELL = "Automodell";
	private static Gui instance = null;
	TableGui tableGui = new TableGui();
	AutoDaoMyBatis ad;

	public static Gui getInstance() {
		if (instance == null) {
			instance = new Gui();
		}
		return instance;
	}

	private Gui() throws HeadlessException {
		super();
		
		AutoDaoMyBatis ad = new AutoDaoMyBatis();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(STR_AUTOVERLEIH);
		this.setLayout(new BorderLayout());

		// Menu-Bar
		JMenuBar menuBar = new JMenuBar();
		JMenu searchMenu = new JMenu("Suche");
		JMenuItem searchAutomodell = new JMenuItem(STR_AUTOMODELL);
		searchMenu.add(searchAutomodell);
		// searchMenu.addMenuListener(this);
		menuBar.add(searchMenu);
		this.setJMenuBar(menuBar);
		
		// Die Tabellen-Daten
		this.add(tableGui, BorderLayout.NORTH);
		
		// Alle Autos abfragen
		List<AutoModell> tmp = ad.getAllAutoModelle();
		tableGui.setData(tmp);
		
		// Search Panel
		SearchPanel sPanel = new SearchPanel(this,tmp);
		this.add(sPanel, BorderLayout.SOUTH);
		
		// Alles anzeigen
		this.setLocationRelativeTo(null);
		this.setSize(1200, 650);
		this.setVisible(true);
	}
	
	/**
	 * @return table
	 */
	public TableGui getTableGui() {
		return tableGui;
	}
		
	// start der Anwendung
	public static void main(String[] args) { 
		Gui.getInstance();
	}
}

class TableGui extends JPanel {
	private static final long serialVersionUID = 1L;
    String[] columnNames = {"ID","Bezeichung","Hersteller","Autoart","Sitzplätze","kw","Treibstoff","PreisTag","PreisKM","Achsen","Ladevolumen","Zuladung","Führerschein"};
    Object[][] dataTable = new Object[0][14];
    DefaultTableModel dtm = new DefaultTableModel(dataTable,columnNames);
    JTable table = new JTable(dtm);
    JScrollPane scrollPane = new JScrollPane(table);

    public TableGui() {
    	this.setLayout(new BorderLayout());
        this.add(scrollPane);
    }
    public void setData(List<AutoModell> list) {
        Object[][] dataTable = new Object[list.size()][14];
        for (int i = 0; i < list.size(); i++) {
            dataTable[i][0] = list.get(i).getId();
            dataTable[i][1] = list.get(i).getBezeichnung();
            dataTable[i][2] = list.get(i).getHersteller();
            dataTable[i][3] = list.get(i).getArt();
            dataTable[i][4] = list.get(i).getSitzplaetze();
            dataTable[i][5] = list.get(i).getKw();
            dataTable[i][6] = list.get(i).getTreibstoff();
            dataTable[i][7] = list.get(i).getPreisTag();
            dataTable[i][8] = list.get(i).getPreisKM();
            dataTable[i][9] = list.get(i).getAchsen();
            dataTable[i][10] = list.get(i).getLadevolumen();
            dataTable[i][11] = list.get(i).getZuladung();
            dataTable[i][12] = list.get(i).getFuererschein();
        }
        dtm.setDataVector(dataTable,columnNames);
        dtm.fireTableDataChanged(); 
    }
    
    public JTable getTable() {	
    	return table;
    }
}
