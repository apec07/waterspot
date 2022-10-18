package db.table.model;

import java.util.List;

public class TableService {
	
	private TableImp dao = null;
	
	public TableService() {
		dao = new TableDAO();
	}
	
	public TableService(String dbUrl) {
		dao = new TableDAO(dbUrl);
	}
	
	public List<String> getAllTables(){
		return dao.getAllTables();
	}

}
