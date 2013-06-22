import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.util.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class NewGame extends JPanel {
	private static final long serialVersionUID = 1L;
	private ArrayList<JRadioButton> SecListesi = new ArrayList<JRadioButton>();
	private String[] sorulacakSoru; // ulke, baskent, sec1, sec2, sec3, sec4
	private Soru s;
	private int skor = 0;
	private JTextField textField;
	private JButton btnSonKarar;
	private JButton btnSonrakiSoru;
	private int bolgeKodu;
	private JLabel soruBasligi;
	
	public NewGame(int bolge, String bolgeAdi) {
		setLayout(null);
		
		bolgeKodu = bolge;		
		s = new Soru();		
		
		soruBasligi = new JLabel();
		soruBasligi.setBounds(39, 37, 420, 30);
		add(soruBasligi);
		
		// 4 secenek olustur
		for( int i=0; i<4; i++ ){ SecListesi.add(new JRadioButton()); }
		
		// 4 secenegi arayüze ekle
		int loc1 = 75;
		for( JRadioButton radioBtn : SecListesi ){
			radioBtn.setBounds(90, loc1, 149, 23);
			add(radioBtn);
			loc1 += 24;
		}

		// 4 secegi grup yap, sadece bir tanesi secilebilsin
		final ButtonGroup bg = new ButtonGroup();
		for( JRadioButton radioBtn : SecListesi ){ bg.add(radioBtn); }
		
		// ilk soru, sonrasi butonlar arasinda
		yeniSoruOlustur();		
		
		btnSonrakiSoru = new JButton("Sonraki Soru");
		btnSonrakiSoru.setBounds(250, 193, 148, 25);
		add(btnSonrakiSoru);
		btnSonrakiSoru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yeniSoruOlustur();
				bg.clearSelection();
				btnSonrakiSoru.setEnabled(false);
				btnSonKarar.setEnabled(true);
			}
		});
		btnSonrakiSoru.setEnabled(false);
		
		btnSonKarar = new JButton("Son Kararim");
		btnSonKarar.setBounds(90, 193, 148, 25);
		add(btnSonKarar);
		
		btnSonKarar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// gelistirilebilir kisim
				for( JRadioButton sec : SecListesi ){
					if( sec.isSelected() ){
						if( sec.getText() == sorulacakSoru[1] ){
							sec.setForeground(Color.GREEN);
							btnSonrakiSoru.setEnabled(true);
							btnSonKarar.setEnabled(false);
							skor++;							
						}else{
							sec.setForeground(Color.RED);
							
							// Skor ekleme kismini panele ekle
							JLabel lblPuanTopladinizKaydetmek = new JLabel(skor + " puan topladiniz. Kaydetmek icin isminizi yazin.");
							lblPuanTopladinizKaydetmek.setBounds(12, 225, 476, 25);
							add(lblPuanTopladinizKaydetmek);
							
							textField = new JTextField();
							textField.setBounds(12, 254, 226, 25);
							add(textField);
							textField.setColumns(10);
							
							final JButton btnKaydet = new JButton("Kaydet");
							btnKaydet.setBounds(250, 254, 148, 25);
							add(btnKaydet);
							
							btnKaydet.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									String name = textField.getText();
									if( name.length() == 0 ){
										textField.setText("isim?");
									}else{
										s.addScore(skor, name);
										btnKaydet.setText("Kaydedildi.");
										btnKaydet.setEnabled(false);
									}								
								}
							});
							btnSonKarar.setEnabled(false);
							repaint();
						}
					}else{
						// dogru cevabi yesil yap.
						if( sec.getText() == sorulacakSoru[1] ){
							sec.setForeground(Color.GREEN);
						}
					}
				}
			}
		});
		
		JLabel lblInfo = new JLabel("[+] Sectiginiz bolge : " + bolgeAdi);
		lblInfo.setBounds(39, 10, 366, 25);
		add(lblInfo);	
	}
	
	private void secenekleriAyarla(ArrayList<JRadioButton> secListesi, String[] soru){
		int i = 2;
		for( JRadioButton sec : secListesi ){
			sec.setText(soru[i]);
			sec.setForeground(Color.black);
			i++;
		}
	}
	
	private void yeniSoruOlustur(){
		sorulacakSoru = s.soruOlustur(bolgeKodu);
		secenekleriAyarla(SecListesi, sorulacakSoru);
		soruBasligi.setText("SORU : " + sorulacakSoru[0] + " baskenti neresidir?");
	}
}
