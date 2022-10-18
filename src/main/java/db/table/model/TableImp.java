package db.table.model;

import java.util.List;

public interface TableImp {
	

	//SQL STMT
	final String GET_ALL_STMT="SHOW tables;";
	//CRUD
	List<String> getAllTables();
	

}
