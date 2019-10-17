package model.DAO;
import java.sql.*;
import org.sqlite.*;



/**
 * Consists of the factory methods connection objects.
 */
public class ConnectionFactory {
	
	private static ConnectionFactory object=null;
	
	Connection connection=null;
	private final Driver driver= new org.sqlite.JDBC();
	SQLiteConfig config = new SQLiteConfig();
	private static String URL=null;
	
	private ConnectionFactory() {}
	
	/**
	 * Used to get the singleton instance of this class and avoid memory leaks.
	 * @return {@link ConnectionFactory}
	 */
	public static ConnectionFactory getInstance() {
		if(object==null)
			object=new ConnectionFactory();
		
		return object;
	}
	
	/**
	 * Used to fetch the single time connection instance of the database.
	 * @return {@link Connection}
	 */
	public Connection getConnection() {
		if(connection==null) {
			try {
				DriverManager.registerDriver(driver);
				
				config.enforceForeignKeys(true);
				config.setJournalMode(SQLiteConfig.JournalMode.WAL);
				config.setSynchronous(SynchronousMode.OFF);
				config.setTempStore(TempStore.MEMORY);
				config.setTransactionMode(SQLiteConfig.TransactionMode.DEFERRED);

				connection=DriverManager.getConnection("jdbc:sqlite:"+URL+"\\ttmsDS.db",
						config.toProperties());
				connection.setAutoCommit(true);
			}catch (SQLiteException e) {
				e.printStackTrace();
			}catch (SQLException s) {
				s.printStackTrace();
			}
		}
		return connection;
	}
	
	public static void setDBpath(String url) {
		if(URL==null)
			URL=url;
	}
	
}
