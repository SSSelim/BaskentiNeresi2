import java.sql.*;
import java.util.*;


public class Soru {
	// dinamik bir dizi olmali cunku diziye eklenecek
	// eleman sayisi vt nindan gelen veriye gore belirleniyor
	ArrayList<String[]> ulkeler = new ArrayList<String[]>();
	private DB db = null;

	public void SoruSetiOlustur(int bolge) {
		if( db == null ){ db = new DB(); }
		ResultSet rs = null;						
		PreparedStatement ps = null;
		
		try {
			if( bolge == 0){
				ps = db.getPS("select * from countries");
			}else{
				ps = db.getPS("select * from countries where bolge = ?");
				ps.setInt(1, bolge); // bolge : [1,7]
			}
			
			rs = ps.executeQuery();         	
			
        	while( rs.next() ){        		
        		// bu degiskeni burda tanimlamam gerekiyor cunku
        		// ustte tanimlarsam hem ayni diziyi eklemis oluyorum.
        		String[] ulkeBilgisi = new String[2];
        		
        		ulkeBilgisi[0] = rs.getString("ulke");
        		ulkeBilgisi[1] = rs.getString("baskent");
        		ulkeler.add(ulkeBilgisi);
        	}  	      	     
  	    } catch ( Exception e ) {
  	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
  	    	System.exit(0);
  	    }		
	}
	
	public String[] soruOlustur(int bolge){
		SoruSetiOlustur(bolge);
		
		int[] choices = new int[4]; // veritabanindan secilecek 4lunun arraylistteki indisleri
		int sorulacakUlke; // sorulacak ulkenin choicesdaki indisi
		Random r = new Random();
		String[] soru = new String[6]; // {sorulacakulke, cevabi, sec1,sec2,sec3,sec4}
		
    	choices = getFourDifRanInt(ulkeler.size());
    	sorulacakUlke = choices[r.nextInt(4)];
    	
    	soru[0] = ulkeler.get(sorulacakUlke)[0];
    	soru[1] = ulkeler.get(sorulacakUlke)[1];
    	
    	// secenekleri diziye ekle
    	for( int i=0; i<4; i++ ){
    		soru[i+2] = ulkeler.get(choices[i])[1];
    	}
    	
    	return soru;
	}
	
	private int[] getFourDifRanInt(int sinir){
		// secenekleri olusturmak icin kullanacam dort
		// random indexi olustuyor
		int[] dizi = {-1,-1,-1,-1};
		Random r = new Random();
		int j, i=0;
				
		while( i != 4 ){
			j = r.nextInt(sinir);
			if( thereIs(dizi, j) ){	continue; }			
			dizi[i] = j;
			i++;
		}
		
		return dizi;
	}
	
	private boolean thereIs(int[] dizi, int num){
		// random sayi daha onceden diziye eklenmis mi?
		int k;
		
		for( k=0; k<4; k++ ){
			if( dizi[k] == num ){ return true; }
		}
		
		return false;
	}
	
	public void addScore(int skor, String name){		
		try{
			String sql = "insert into users(name,score) values('" + name + "'," + skor + ");";
			db.execute(sql);			
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
	  	}
	}
}
