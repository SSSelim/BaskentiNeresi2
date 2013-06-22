import java.io.*;

public class Ayristir {

	public static void main(String[] args) throws IOException{
		BufferedReader inputStream = null;
		DB db = new DB();
		
		
	    String sql = "CREATE TABLE countries " +
                  "(ID 			INTEGER PRIMARY KEY  autoincrement   NOT NULL," +
                  " ulke		TEXT NOT NULL, " + 
                  " baskent		Text  NOT NULL, " + 
                  " bolge		INT )";
	    
	    String sql2 = "create table users" +
	    		"(id integer primary key auto increment not nul, " +
	    		"name text," +
	    		"score int)";
	    
	    try{
	    	db.execute(sql);
	    	db.execute(sql2);
	    	
	    	inputStream = new BufferedReader(new FileReader("info.txt"));
	    	String line;
	    	String[] val; // ulke ve baskenti  tutacak
	    	int bolge = 0;
	    	
	    	while( (line = inputStream.readLine()) != null ){
	    		if( line.contains(":") == false ){ // bolge adi satiri	    			
	    			bolge++;
	    			System.out.println(bolge);
	    		}else{
	    			//System.out.println(line);
	    			val = line.split(" : ");
	    			// textin icinde ' olunca hata veriyor.
	    			sql = "insert into countries(ulke,baskent,bolge) values('"+ val[0] + "','" + val[1] + "'," + bolge + ");";
	    			db.execute(sql);
	    		}
	    	}
	    } catch ( Exception e ) {
	  	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	  	      System.exit(0);
	  	}finally {
	    	if( inputStream != null ){
	    		inputStream.close();
	    	}
	    }
	}
}
