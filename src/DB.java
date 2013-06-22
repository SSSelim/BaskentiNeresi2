import java.sql.*;

public class DB{
    private Connection conn = null;
    private Statement stmt = null;
    
    public DB(){
	    try {
	      Class.forName("org.sqlite.JDBC");
	      conn = DriverManager.getConnection("jdbc:sqlite:test.db");
	      stmt = conn.createStatement();     
	      	      
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
    }
    
    public void execute(String sql) throws SQLException{
        stmt.executeUpdate(sql);      
    }

    public ResultSet getRS(String sql) throws SQLException{   	
        return stmt.executeQuery(sql);
    }
    
    public void closeAll(){
    	if (stmt!=null) { try { stmt.close(); } catch (Exception ignore) {} }
    	if (conn!=null) { try { conn.close(); } catch (Exception ignore) {} }   	
    }
    
    public PreparedStatement getPS(String sql) throws SQLException{
    	return conn.prepareStatement(sql);
    }
    


}