package db.table.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TableDAO implements TableImp {

	private static DataSource ds = null;
	private String dbUrl;
	public Logger LOGGER = LogManager.getLogger(this.getClass());
	
	public TableDAO() {
		this.dbUrl = "default URl"; 
		try {
			javax.naming.Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestMYSQL_LOCAL");
			LOGGER.info("default DB "+dbUrl);
		} catch (NamingException e) {
			LOGGER.error("no DataBase defined!\n"+e.getStackTrace());
		}
	}
	public TableDAO(String dbUrl) {
		this.dbUrl = dbUrl;
		try {
			javax.naming.Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/"+dbUrl);
			LOGGER.info("new DB "+dbUrl);
		} catch (NamingException e) {
			LOGGER.warn("no DataBase defined!\n"+e.getStackTrace()); // handle on it
			new TableDAO();
		}
	}
	
	
	@Override
	public List<String> getAllTables() {
		Connection con = null;
		PreparedStatement psmt;
		ResultSet rs = null;
		List<String> tables = new LinkedList<>();
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GET_ALL_STMT);
			rs = psmt.executeQuery();
			while(rs.next()) {
				String tableName = rs.getString(1);
				//LOGGER.info(tableName);
				tables.add(tableName);
			}
		} catch (SQLException e) {
			LOGGER.error("No Connection"); // no action, front-end handle
		}
		LOGGER.info("Lable size - "+ tables.size());
		return tables;
	}
	
}
