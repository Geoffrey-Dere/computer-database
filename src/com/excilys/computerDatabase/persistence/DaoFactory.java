package com.excilys.computerDatabase.persistence;

import com.excilys.computerDatabase.model.Company;
import com.excilys.computerDatabase.persistence.mysql.MysqlDAOFactory;

public abstract class DaoFactory {

	public static final int MYSQL_FACTORY = 0 ;
	
	private static DaoFactory currentDAOFactory = null;
	
	public abstract DAO<Company> getCompanyDAO();

	public static DaoFactory getDaoFactory(){
		
		if(currentDAOFactory == null)
			getFactory(MYSQL_FACTORY);
		
		return currentDAOFactory;
	}

	public static DaoFactory getFactory(int choice){
		switch(choice){
		case MYSQL_FACTORY :
			currentDAOFactory = MysqlDAOFactory.getInstance();
			return currentDAOFactory ;
		default:
			return null ;
		}
	}
}
