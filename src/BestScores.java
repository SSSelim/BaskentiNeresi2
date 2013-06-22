import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;

import java.sql.*;

public class BestScores extends JPanel {

	private static final long serialVersionUID = 1L;

	public BestScores() {
		setLayout(null);
		
		JLabel lblIsim = new JLabel("Isim");
		lblIsim.setFont(new Font("Dialog", Font.BOLD, 16));
		lblIsim.setBounds(78, 36, 70, 15);
		add(lblIsim);
		
		JLabel lblPuan = new JLabel("Puan");
		lblPuan.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPuan.setBounds(223, 36, 70, 15);
		add(lblPuan);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(56, 62, 92, 2);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(201, 63, 92, 2);
		add(separator_1);
		
		try{
			DB db = new DB();
			ResultSet rs = db.getRS("select * from users order by score DESC limit 10 ");
			int loc = 75;
			int i = 0;
			while( rs.next() ){
				JLabel lblName = new JLabel();
				lblName.setFont(new Font("Dialog", Font.PLAIN, 12));
				lblName.setBounds(56, loc, 139, 15);
				lblName.setText(i + " - " + rs.getString("name"));
				add(lblName);
				
				JLabel label = new JLabel();
				label.setFont(new Font("Dialog", Font.PLAIN, 12));
				label.setBounds(237, loc, 139, 15);
				label.setText("" + rs.getInt("score"));
				add(label);
				
				loc += 20;
				i++;
			}
			
		} catch ( Exception e ) {
	  	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	  	      System.exit(0);
	  	}

	}
}
