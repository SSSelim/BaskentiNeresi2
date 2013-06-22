import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.util.*;

public class Ana extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel helpPanel = new Help();
	private JPanel about = new About();
	private HashMap<String, String> bolgeler = new HashMap<String, String>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ana frame = new Ana();
					frame.setVisible(true);
					frame.setPreferredSize(new Dimension(600,300));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Ana() {
		setTitle("Baskenti Neresi?");
		bolgeleriDuzenle();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 340);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSecenekler = new JMenu("Yeni Oyun");
		menuBar.add(mnSecenekler);
		
		for( String bolgeAdi : bolgeler.keySet() ){
			JMenuItem mntmYeniOyun = new JMenuItem(bolgeAdi);
			mnSecenekler.add(mntmYeniOyun);
			mntmYeniOyun.addActionListener(new MenuAction2(bolgeAdi));
		}
		
		JMenu mnSkorTabelasi = new JMenu("Puan Tablosu");
		menuBar.add(mnSkorTabelasi);
		
		JMenuItem mntmEnIyiSkorlar = new JMenuItem("En iyi Skorlar");
		mnSkorTabelasi.add(mntmEnIyiSkorlar);
		mntmEnIyiSkorlar.addActionListener(new MenuAction3());
		
		JMenu mnYardim = new JMenu("Yardim");
		menuBar.add(mnYardim);
		
		JMenuItem mntmNasilCalisir = new JMenuItem("Nasil Calisir?");
		mnYardim.add(mntmNasilCalisir);
		mntmNasilCalisir.addActionListener(new MenuAction(helpPanel));
		
		JMenuItem mntmHakkinda = new JMenuItem("Hakkinda");
		mnYardim.add(mntmHakkinda);
		mntmHakkinda.addActionListener(new MenuAction(about));
		
		mntmNasilCalisir.doClick();
	}
	
	private class MenuAction3 implements ActionListener {
	    // her menu itemina bastiginda yenisi(yeni degerler) olusturulacak
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        changePanel(new BestScores());
	    }
	}
	
	private class MenuAction2 implements ActionListener {
		// ayni sekilde altaki metot gibi olursa hep ayni panele gider
		// yeni oyun istedi geldiginde panel sifirlanmali
	    private String bolgeAdi;
	    private int bolge;
	    
	    private MenuAction2(String bolgeAdi) {
	    	this.bolgeAdi = bolgeAdi;
	    	this.bolge = Integer.parseInt(bolgeler.get(bolgeAdi));
	    }
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        changePanel(new NewGame(this.bolge, this.bolgeAdi));
	    }
	}
	
	private class MenuAction implements ActionListener {
	    private JPanel panel;
	    
	    private MenuAction(JPanel pnl) {
	        this.panel = pnl;
	    }
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        changePanel(panel);
	    }
	}
	
	private void changePanel(JPanel panel) {
	    getContentPane().removeAll();
	    getContentPane().add(panel, BorderLayout.CENTER);
	    getContentPane().doLayout();
	    update(getGraphics());
	}
	
	private void bolgeleriDuzenle(){
		bolgeler.put("Avrupa", "1");
		bolgeler.put("Asya", "2");
		bolgeler.put("Uzak Dogu", "3");
		bolgeler.put("Okyanusya", "4");
		bolgeler.put("Afrika", "5");
		bolgeler.put("Kuzey Amerika", "6");
		bolgeler.put("Guney Amerika", "7");
		bolgeler.put("Karisik", "0");
	}

}
